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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/taxAuthorityAssocTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTaxAuthorityAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityAssocTypesBy query = new FindTaxAuthorityAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityAssocType> taxAuthorityAssocTypes =((TaxAuthorityAssocTypeFound) Scheduler.execute(query).data()).getTaxAuthorityAssocTypes();

		if (taxAuthorityAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(taxAuthorityAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(taxAuthorityAssocTypes);

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
	public ResponseEntity<Object> createTaxAuthorityAssocType(HttpServletRequest request) throws Exception {

		TaxAuthorityAssocType taxAuthorityAssocTypeToBeAdded = new TaxAuthorityAssocType();
		try {
			taxAuthorityAssocTypeToBeAdded = TaxAuthorityAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTaxAuthorityAssocType(taxAuthorityAssocTypeToBeAdded);

	}

	/**
	 * creates a new TaxAuthorityAssocType entry in the ofbiz database
	 * 
	 * @param taxAuthorityAssocTypeToBeAdded
	 *            the TaxAuthorityAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTaxAuthorityAssocType(@RequestBody TaxAuthorityAssocType taxAuthorityAssocTypeToBeAdded) throws Exception {

		AddTaxAuthorityAssocType command = new AddTaxAuthorityAssocType(taxAuthorityAssocTypeToBeAdded);
		TaxAuthorityAssocType taxAuthorityAssocType = ((TaxAuthorityAssocTypeAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityAssocType();
		
		if (taxAuthorityAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(taxAuthorityAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TaxAuthorityAssocType could not be created.");
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
	public boolean updateTaxAuthorityAssocType(HttpServletRequest request) throws Exception {

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

		TaxAuthorityAssocType taxAuthorityAssocTypeToBeUpdated = new TaxAuthorityAssocType();

		try {
			taxAuthorityAssocTypeToBeUpdated = TaxAuthorityAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTaxAuthorityAssocType(taxAuthorityAssocTypeToBeUpdated, taxAuthorityAssocTypeToBeUpdated.getTaxAuthorityAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTaxAuthorityAssocType(@RequestBody TaxAuthorityAssocType taxAuthorityAssocTypeToBeUpdated,
			@PathVariable String taxAuthorityAssocTypeId) throws Exception {

		taxAuthorityAssocTypeToBeUpdated.setTaxAuthorityAssocTypeId(taxAuthorityAssocTypeId);

		UpdateTaxAuthorityAssocType command = new UpdateTaxAuthorityAssocType(taxAuthorityAssocTypeToBeUpdated);

		try {
			if(((TaxAuthorityAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{taxAuthorityAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String taxAuthorityAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityAssocTypeId", taxAuthorityAssocTypeId);
		try {

			Object foundTaxAuthorityAssocType = findTaxAuthorityAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTaxAuthorityAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taxAuthorityAssocTypeId}")
	public ResponseEntity<Object> deleteTaxAuthorityAssocTypeByIdUpdated(@PathVariable String taxAuthorityAssocTypeId) throws Exception {
		DeleteTaxAuthorityAssocType command = new DeleteTaxAuthorityAssocType(taxAuthorityAssocTypeId);

		try {
			if (((TaxAuthorityAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TaxAuthorityAssocType could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/taxAuthorityAssocType/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
