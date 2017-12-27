package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.control.rateType;

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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateType.AddTaxAuthorityRateType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateType.DeleteTaxAuthorityRateType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateType.UpdateTaxAuthorityRateType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType.TaxAuthorityRateTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType.TaxAuthorityRateTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType.TaxAuthorityRateTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType.TaxAuthorityRateTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateType.TaxAuthorityRateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateType.TaxAuthorityRateType;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.rateType.FindTaxAuthorityRateTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/taxAuthority/taxAuthorityRateTypes")
public class TaxAuthorityRateTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityRateTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthorityRateType
	 * @return a List with the TaxAuthorityRateTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TaxAuthorityRateType>> findTaxAuthorityRateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityRateTypesBy query = new FindTaxAuthorityRateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityRateType> taxAuthorityRateTypes =((TaxAuthorityRateTypeFound) Scheduler.execute(query).data()).getTaxAuthorityRateTypes();

		return ResponseEntity.ok().body(taxAuthorityRateTypes);

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
	public ResponseEntity<TaxAuthorityRateType> createTaxAuthorityRateType(HttpServletRequest request) throws Exception {

		TaxAuthorityRateType taxAuthorityRateTypeToBeAdded = new TaxAuthorityRateType();
		try {
			taxAuthorityRateTypeToBeAdded = TaxAuthorityRateTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createTaxAuthorityRateType(taxAuthorityRateTypeToBeAdded);

	}

	/**
	 * creates a new TaxAuthorityRateType entry in the ofbiz database
	 * 
	 * @param taxAuthorityRateTypeToBeAdded
	 *            the TaxAuthorityRateType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TaxAuthorityRateType> createTaxAuthorityRateType(@RequestBody TaxAuthorityRateType taxAuthorityRateTypeToBeAdded) throws Exception {

		AddTaxAuthorityRateType command = new AddTaxAuthorityRateType(taxAuthorityRateTypeToBeAdded);
		TaxAuthorityRateType taxAuthorityRateType = ((TaxAuthorityRateTypeAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityRateType();
		
		if (taxAuthorityRateType != null) 
			return successful(taxAuthorityRateType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TaxAuthorityRateType with the specific Id
	 * 
	 * @param taxAuthorityRateTypeToBeUpdated
	 *            the TaxAuthorityRateType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthorityRateTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTaxAuthorityRateType(@RequestBody TaxAuthorityRateType taxAuthorityRateTypeToBeUpdated,
			@PathVariable String taxAuthorityRateTypeId) throws Exception {

		taxAuthorityRateTypeToBeUpdated.setTaxAuthorityRateTypeId(taxAuthorityRateTypeId);

		UpdateTaxAuthorityRateType command = new UpdateTaxAuthorityRateType(taxAuthorityRateTypeToBeUpdated);

		try {
			if(((TaxAuthorityRateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{taxAuthorityRateTypeId}")
	public ResponseEntity<TaxAuthorityRateType> findById(@PathVariable String taxAuthorityRateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityRateTypeId", taxAuthorityRateTypeId);
		try {

			List<TaxAuthorityRateType> foundTaxAuthorityRateType = findTaxAuthorityRateTypesBy(requestParams).getBody();
			if(foundTaxAuthorityRateType.size()==1){				return successful(foundTaxAuthorityRateType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{taxAuthorityRateTypeId}")
	public ResponseEntity<String> deleteTaxAuthorityRateTypeByIdUpdated(@PathVariable String taxAuthorityRateTypeId) throws Exception {
		DeleteTaxAuthorityRateType command = new DeleteTaxAuthorityRateType(taxAuthorityRateTypeId);

		try {
			if (((TaxAuthorityRateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
