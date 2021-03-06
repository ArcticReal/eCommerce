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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<TaxAuthority>> findTaxAuthoritysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthoritysBy query = new FindTaxAuthoritysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthority> taxAuthoritys =((TaxAuthorityFound) Scheduler.execute(query).data()).getTaxAuthoritys();

		return ResponseEntity.ok().body(taxAuthoritys);

	}

	/**
	 * creates a new TaxAuthority entry in the ofbiz database
	 * 
	 * @param taxAuthorityToBeAdded
	 *            the TaxAuthority thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TaxAuthority> createTaxAuthority(@RequestBody TaxAuthority taxAuthorityToBeAdded) throws Exception {

		AddTaxAuthority command = new AddTaxAuthority(taxAuthorityToBeAdded);
		TaxAuthority taxAuthority = ((TaxAuthorityAdded) Scheduler.execute(command).data()).getAddedTaxAuthority();
		
		if (taxAuthority != null) 
			return successful(taxAuthority);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTaxAuthority(@RequestBody TaxAuthority taxAuthorityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		taxAuthorityToBeUpdated.setnull(null);

		UpdateTaxAuthority command = new UpdateTaxAuthority(taxAuthorityToBeUpdated);

		try {
			if(((TaxAuthorityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{taxAuthorityId}")
	public ResponseEntity<TaxAuthority> findById(@PathVariable String taxAuthorityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityId", taxAuthorityId);
		try {

			List<TaxAuthority> foundTaxAuthority = findTaxAuthoritysBy(requestParams).getBody();
			if(foundTaxAuthority.size()==1){				return successful(foundTaxAuthority.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{taxAuthorityId}")
	public ResponseEntity<String> deleteTaxAuthorityByIdUpdated(@PathVariable String taxAuthorityId) throws Exception {
		DeleteTaxAuthority command = new DeleteTaxAuthority(taxAuthorityId);

		try {
			if (((TaxAuthorityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
