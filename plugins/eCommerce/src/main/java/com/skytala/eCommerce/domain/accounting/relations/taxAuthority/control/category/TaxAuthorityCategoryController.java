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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/taxAuthority/taxAuthorityCategorys")
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
	@GetMapping("/find")
	public ResponseEntity<List<TaxAuthorityCategory>> findTaxAuthorityCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityCategorysBy query = new FindTaxAuthorityCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityCategory> taxAuthorityCategorys =((TaxAuthorityCategoryFound) Scheduler.execute(query).data()).getTaxAuthorityCategorys();

		return ResponseEntity.ok().body(taxAuthorityCategorys);

	}

	/**
	 * creates a new TaxAuthorityCategory entry in the ofbiz database
	 * 
	 * @param taxAuthorityCategoryToBeAdded
	 *            the TaxAuthorityCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TaxAuthorityCategory> createTaxAuthorityCategory(@RequestBody TaxAuthorityCategory taxAuthorityCategoryToBeAdded) throws Exception {

		AddTaxAuthorityCategory command = new AddTaxAuthorityCategory(taxAuthorityCategoryToBeAdded);
		TaxAuthorityCategory taxAuthorityCategory = ((TaxAuthorityCategoryAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityCategory();
		
		if (taxAuthorityCategory != null) 
			return successful(taxAuthorityCategory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTaxAuthorityCategory(@RequestBody TaxAuthorityCategory taxAuthorityCategoryToBeUpdated,
			@PathVariable String taxAuthPartyId) throws Exception {

		taxAuthorityCategoryToBeUpdated.setTaxAuthPartyId(taxAuthPartyId);

		UpdateTaxAuthorityCategory command = new UpdateTaxAuthorityCategory(taxAuthorityCategoryToBeUpdated);

		try {
			if(((TaxAuthorityCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{taxAuthorityCategoryId}")
	public ResponseEntity<TaxAuthorityCategory> findById(@PathVariable String taxAuthorityCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityCategoryId", taxAuthorityCategoryId);
		try {

			List<TaxAuthorityCategory> foundTaxAuthorityCategory = findTaxAuthorityCategorysBy(requestParams).getBody();
			if(foundTaxAuthorityCategory.size()==1){				return successful(foundTaxAuthorityCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{taxAuthorityCategoryId}")
	public ResponseEntity<String> deleteTaxAuthorityCategoryByIdUpdated(@PathVariable String taxAuthorityCategoryId) throws Exception {
		DeleteTaxAuthorityCategory command = new DeleteTaxAuthorityCategory(taxAuthorityCategoryId);

		try {
			if (((TaxAuthorityCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
