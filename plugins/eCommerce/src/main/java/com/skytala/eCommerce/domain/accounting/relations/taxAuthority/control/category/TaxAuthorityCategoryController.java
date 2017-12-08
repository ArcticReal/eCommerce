package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.control.category;

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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.category.AddTaxAuthorityCategory;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.category.DeleteTaxAuthorityCategory;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.category.UpdateTaxAuthorityCategory;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category.TaxAuthorityCategoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category.TaxAuthorityCategoryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category.TaxAuthorityCategoryFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category.TaxAuthorityCategoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.category.TaxAuthorityCategoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.category.FindTaxAuthorityCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/taxAuthorityCategorys")
public class TaxAuthorityCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthorityCategory
	 * @return a List with the TaxAuthorityCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTaxAuthorityCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityCategorysBy query = new FindTaxAuthorityCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityCategory> taxAuthorityCategorys =((TaxAuthorityCategoryFound) Scheduler.execute(query).data()).getTaxAuthorityCategorys();

		if (taxAuthorityCategorys.size() == 1) {
			return ResponseEntity.ok().body(taxAuthorityCategorys.get(0));
		}

		return ResponseEntity.ok().body(taxAuthorityCategorys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTaxAuthorityCategory(HttpServletRequest request) throws Exception {

		TaxAuthorityCategory taxAuthorityCategoryToBeAdded = new TaxAuthorityCategory();
		try {
			taxAuthorityCategoryToBeAdded = TaxAuthorityCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTaxAuthorityCategory(taxAuthorityCategoryToBeAdded);

	}

	/**
	 * creates a new TaxAuthorityCategory entry in the ofbiz database
	 * 
	 * @param taxAuthorityCategoryToBeAdded
	 *            the TaxAuthorityCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTaxAuthorityCategory(@RequestBody TaxAuthorityCategory taxAuthorityCategoryToBeAdded) throws Exception {

		AddTaxAuthorityCategory command = new AddTaxAuthorityCategory(taxAuthorityCategoryToBeAdded);
		TaxAuthorityCategory taxAuthorityCategory = ((TaxAuthorityCategoryAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityCategory();
		
		if (taxAuthorityCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(taxAuthorityCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TaxAuthorityCategory could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateTaxAuthorityCategory(HttpServletRequest request) throws Exception {

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

		TaxAuthorityCategory taxAuthorityCategoryToBeUpdated = new TaxAuthorityCategory();

		try {
			taxAuthorityCategoryToBeUpdated = TaxAuthorityCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTaxAuthorityCategory(taxAuthorityCategoryToBeUpdated, taxAuthorityCategoryToBeUpdated.getTaxAuthPartyId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TaxAuthorityCategory with the specific Id
	 * 
	 * @param taxAuthorityCategoryToBeUpdated
	 *            the TaxAuthorityCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthPartyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTaxAuthorityCategory(@RequestBody TaxAuthorityCategory taxAuthorityCategoryToBeUpdated,
			@PathVariable String taxAuthPartyId) throws Exception {

		taxAuthorityCategoryToBeUpdated.setTaxAuthPartyId(taxAuthPartyId);

		UpdateTaxAuthorityCategory command = new UpdateTaxAuthorityCategory(taxAuthorityCategoryToBeUpdated);

		try {
			if(((TaxAuthorityCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{taxAuthorityCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String taxAuthorityCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityCategoryId", taxAuthorityCategoryId);
		try {

			Object foundTaxAuthorityCategory = findTaxAuthorityCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTaxAuthorityCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taxAuthorityCategoryId}")
	public ResponseEntity<Object> deleteTaxAuthorityCategoryByIdUpdated(@PathVariable String taxAuthorityCategoryId) throws Exception {
		DeleteTaxAuthorityCategory command = new DeleteTaxAuthorityCategory(taxAuthorityCategoryId);

		try {
			if (((TaxAuthorityCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TaxAuthorityCategory could not be deleted");

	}

}
