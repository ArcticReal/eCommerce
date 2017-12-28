package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.control.assocType;

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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assocType.AddTaxAuthorityAssocType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assocType.DeleteTaxAuthorityAssocType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.assocType.UpdateTaxAuthorityAssocType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType.TaxAuthorityAssocTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType.TaxAuthorityAssocTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType.TaxAuthorityAssocTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType.TaxAuthorityAssocTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assocType.TaxAuthorityAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType.TaxAuthorityAssocType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.assocType.FindTaxAuthorityAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/taxAuthority/taxAuthorityAssocTypes")
public class TaxAuthorityAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthorityAssocType
	 * @return a List with the TaxAuthorityAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TaxAuthorityAssocType>> findTaxAuthorityAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityAssocTypesBy query = new FindTaxAuthorityAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityAssocType> taxAuthorityAssocTypes =((TaxAuthorityAssocTypeFound) Scheduler.execute(query).data()).getTaxAuthorityAssocTypes();

		return ResponseEntity.ok().body(taxAuthorityAssocTypes);

	}

	/**
	 * creates a new TaxAuthorityAssocType entry in the ofbiz database
	 * 
	 * @param taxAuthorityAssocTypeToBeAdded
	 *            the TaxAuthorityAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TaxAuthorityAssocType> createTaxAuthorityAssocType(@RequestBody TaxAuthorityAssocType taxAuthorityAssocTypeToBeAdded) throws Exception {

		AddTaxAuthorityAssocType command = new AddTaxAuthorityAssocType(taxAuthorityAssocTypeToBeAdded);
		TaxAuthorityAssocType taxAuthorityAssocType = ((TaxAuthorityAssocTypeAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityAssocType();
		
		if (taxAuthorityAssocType != null) 
			return successful(taxAuthorityAssocType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TaxAuthorityAssocType with the specific Id
	 * 
	 * @param taxAuthorityAssocTypeToBeUpdated
	 *            the TaxAuthorityAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthorityAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTaxAuthorityAssocType(@RequestBody TaxAuthorityAssocType taxAuthorityAssocTypeToBeUpdated,
			@PathVariable String taxAuthorityAssocTypeId) throws Exception {

		taxAuthorityAssocTypeToBeUpdated.setTaxAuthorityAssocTypeId(taxAuthorityAssocTypeId);

		UpdateTaxAuthorityAssocType command = new UpdateTaxAuthorityAssocType(taxAuthorityAssocTypeToBeUpdated);

		try {
			if(((TaxAuthorityAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{taxAuthorityAssocTypeId}")
	public ResponseEntity<TaxAuthorityAssocType> findById(@PathVariable String taxAuthorityAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityAssocTypeId", taxAuthorityAssocTypeId);
		try {

			List<TaxAuthorityAssocType> foundTaxAuthorityAssocType = findTaxAuthorityAssocTypesBy(requestParams).getBody();
			if(foundTaxAuthorityAssocType.size()==1){				return successful(foundTaxAuthorityAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{taxAuthorityAssocTypeId}")
	public ResponseEntity<String> deleteTaxAuthorityAssocTypeByIdUpdated(@PathVariable String taxAuthorityAssocTypeId) throws Exception {
		DeleteTaxAuthorityAssocType command = new DeleteTaxAuthorityAssocType(taxAuthorityAssocTypeId);

		try {
			if (((TaxAuthorityAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
