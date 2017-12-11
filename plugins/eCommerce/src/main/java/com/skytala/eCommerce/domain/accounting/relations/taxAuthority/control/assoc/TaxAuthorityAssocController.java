package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.control.assoc;

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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assoc.AddTaxAuthorityAssoc;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assoc.DeleteTaxAuthorityAssoc;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assoc.UpdateTaxAuthorityAssoc;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc.TaxAuthorityAssocAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc.TaxAuthorityAssocDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc.TaxAuthorityAssocFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc.TaxAuthorityAssocUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assoc.TaxAuthorityAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assoc.TaxAuthorityAssoc;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.assoc.FindTaxAuthorityAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/taxAuthority/taxAuthorityAssocs")
public class TaxAuthorityAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthorityAssoc
	 * @return a List with the TaxAuthorityAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTaxAuthorityAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityAssocsBy query = new FindTaxAuthorityAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityAssoc> taxAuthorityAssocs =((TaxAuthorityAssocFound) Scheduler.execute(query).data()).getTaxAuthorityAssocs();

		if (taxAuthorityAssocs.size() == 1) {
			return ResponseEntity.ok().body(taxAuthorityAssocs.get(0));
		}

		return ResponseEntity.ok().body(taxAuthorityAssocs);

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
	public ResponseEntity<Object> createTaxAuthorityAssoc(HttpServletRequest request) throws Exception {

		TaxAuthorityAssoc taxAuthorityAssocToBeAdded = new TaxAuthorityAssoc();
		try {
			taxAuthorityAssocToBeAdded = TaxAuthorityAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTaxAuthorityAssoc(taxAuthorityAssocToBeAdded);

	}

	/**
	 * creates a new TaxAuthorityAssoc entry in the ofbiz database
	 * 
	 * @param taxAuthorityAssocToBeAdded
	 *            the TaxAuthorityAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTaxAuthorityAssoc(@RequestBody TaxAuthorityAssoc taxAuthorityAssocToBeAdded) throws Exception {

		AddTaxAuthorityAssoc command = new AddTaxAuthorityAssoc(taxAuthorityAssocToBeAdded);
		TaxAuthorityAssoc taxAuthorityAssoc = ((TaxAuthorityAssocAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityAssoc();
		
		if (taxAuthorityAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(taxAuthorityAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TaxAuthorityAssoc could not be created.");
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
	public boolean updateTaxAuthorityAssoc(HttpServletRequest request) throws Exception {

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

		TaxAuthorityAssoc taxAuthorityAssocToBeUpdated = new TaxAuthorityAssoc();

		try {
			taxAuthorityAssocToBeUpdated = TaxAuthorityAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTaxAuthorityAssoc(taxAuthorityAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TaxAuthorityAssoc with the specific Id
	 * 
	 * @param taxAuthorityAssocToBeUpdated
	 *            the TaxAuthorityAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTaxAuthorityAssoc(@RequestBody TaxAuthorityAssoc taxAuthorityAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		taxAuthorityAssocToBeUpdated.setnull(null);

		UpdateTaxAuthorityAssoc command = new UpdateTaxAuthorityAssoc(taxAuthorityAssocToBeUpdated);

		try {
			if(((TaxAuthorityAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{taxAuthorityAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String taxAuthorityAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityAssocId", taxAuthorityAssocId);
		try {

			Object foundTaxAuthorityAssoc = findTaxAuthorityAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTaxAuthorityAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{taxAuthorityAssocId}")
	public ResponseEntity<Object> deleteTaxAuthorityAssocByIdUpdated(@PathVariable String taxAuthorityAssocId) throws Exception {
		DeleteTaxAuthorityAssoc command = new DeleteTaxAuthorityAssoc(taxAuthorityAssocId);

		try {
			if (((TaxAuthorityAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TaxAuthorityAssoc could not be deleted");

	}

}
