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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/taxAuthorityRateTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTaxAuthorityRateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityRateTypesBy query = new FindTaxAuthorityRateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityRateType> taxAuthorityRateTypes =((TaxAuthorityRateTypeFound) Scheduler.execute(query).data()).getTaxAuthorityRateTypes();

		if (taxAuthorityRateTypes.size() == 1) {
			return ResponseEntity.ok().body(taxAuthorityRateTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTaxAuthorityRateType(HttpServletRequest request) throws Exception {

		TaxAuthorityRateType taxAuthorityRateTypeToBeAdded = new TaxAuthorityRateType();
		try {
			taxAuthorityRateTypeToBeAdded = TaxAuthorityRateTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createTaxAuthorityRateType(@RequestBody TaxAuthorityRateType taxAuthorityRateTypeToBeAdded) throws Exception {

		AddTaxAuthorityRateType command = new AddTaxAuthorityRateType(taxAuthorityRateTypeToBeAdded);
		TaxAuthorityRateType taxAuthorityRateType = ((TaxAuthorityRateTypeAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityRateType();
		
		if (taxAuthorityRateType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(taxAuthorityRateType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TaxAuthorityRateType could not be created.");
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
	public boolean updateTaxAuthorityRateType(HttpServletRequest request) throws Exception {

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

		TaxAuthorityRateType taxAuthorityRateTypeToBeUpdated = new TaxAuthorityRateType();

		try {
			taxAuthorityRateTypeToBeUpdated = TaxAuthorityRateTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTaxAuthorityRateType(taxAuthorityRateTypeToBeUpdated, taxAuthorityRateTypeToBeUpdated.getTaxAuthorityRateTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTaxAuthorityRateType(@RequestBody TaxAuthorityRateType taxAuthorityRateTypeToBeUpdated,
			@PathVariable String taxAuthorityRateTypeId) throws Exception {

		taxAuthorityRateTypeToBeUpdated.setTaxAuthorityRateTypeId(taxAuthorityRateTypeId);

		UpdateTaxAuthorityRateType command = new UpdateTaxAuthorityRateType(taxAuthorityRateTypeToBeUpdated);

		try {
			if(((TaxAuthorityRateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{taxAuthorityRateTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String taxAuthorityRateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityRateTypeId", taxAuthorityRateTypeId);
		try {

			Object foundTaxAuthorityRateType = findTaxAuthorityRateTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTaxAuthorityRateType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taxAuthorityRateTypeId}")
	public ResponseEntity<Object> deleteTaxAuthorityRateTypeByIdUpdated(@PathVariable String taxAuthorityRateTypeId) throws Exception {
		DeleteTaxAuthorityRateType command = new DeleteTaxAuthorityRateType(taxAuthorityRateTypeId);

		try {
			if (((TaxAuthorityRateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TaxAuthorityRateType could not be deleted");

	}

}
