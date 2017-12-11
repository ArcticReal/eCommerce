package com.skytala.eCommerce.domain.accounting.relations.taxAuthority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.AddTaxAuthority;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.DeleteTaxAuthority;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.UpdateTaxAuthority;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.TaxAuthorityMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.FindTaxAuthoritysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/taxAuthoritys")
public class TaxAuthorityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthority
	 * @return a List with the TaxAuthoritys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTaxAuthoritysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthoritysBy query = new FindTaxAuthoritysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthority> taxAuthoritys =((TaxAuthorityFound) Scheduler.execute(query).data()).getTaxAuthoritys();

		if (taxAuthoritys.size() == 1) {
			return ResponseEntity.ok().body(taxAuthoritys.get(0));
		}

		return ResponseEntity.ok().body(taxAuthoritys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTaxAuthority(HttpServletRequest request) throws Exception {

		TaxAuthority taxAuthorityToBeAdded = new TaxAuthority();
		try {
			taxAuthorityToBeAdded = TaxAuthorityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTaxAuthority(taxAuthorityToBeAdded);

	}

	/**
	 * creates a new TaxAuthority entry in the ofbiz database
	 * 
	 * @param taxAuthorityToBeAdded
	 *            the TaxAuthority thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTaxAuthority(@RequestBody TaxAuthority taxAuthorityToBeAdded) throws Exception {

		AddTaxAuthority command = new AddTaxAuthority(taxAuthorityToBeAdded);
		TaxAuthority taxAuthority = ((TaxAuthorityAdded) Scheduler.execute(command).data()).getAddedTaxAuthority();
		
		if (taxAuthority != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(taxAuthority);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TaxAuthority could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateTaxAuthority(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		TaxAuthority taxAuthorityToBeUpdated = new TaxAuthority();

		try {
			taxAuthorityToBeUpdated = TaxAuthorityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTaxAuthority(taxAuthorityToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TaxAuthority with the specific Id
	 * 
	 * @param taxAuthorityToBeUpdated
	 *            the TaxAuthority thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTaxAuthority(@RequestBody TaxAuthority taxAuthorityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		taxAuthorityToBeUpdated.setnull(null);

		UpdateTaxAuthority command = new UpdateTaxAuthority(taxAuthorityToBeUpdated);

		try {
			if(((TaxAuthorityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{taxAuthorityId}")
	public ResponseEntity<Object> findById(@PathVariable String taxAuthorityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityId", taxAuthorityId);
		try {

			Object foundTaxAuthority = findTaxAuthoritysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTaxAuthority);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{taxAuthorityId}")
	public ResponseEntity<Object> deleteTaxAuthorityByIdUpdated(@PathVariable String taxAuthorityId) throws Exception {
		DeleteTaxAuthority command = new DeleteTaxAuthority(taxAuthorityId);

		try {
			if (((TaxAuthorityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TaxAuthority could not be deleted");

	}

}
