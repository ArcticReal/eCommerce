package com.skytala.eCommerce.domain.supplierRatingType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.supplierRatingType.command.AddSupplierRatingType;
import com.skytala.eCommerce.domain.supplierRatingType.command.DeleteSupplierRatingType;
import com.skytala.eCommerce.domain.supplierRatingType.command.UpdateSupplierRatingType;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeAdded;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeDeleted;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeFound;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeUpdated;
import com.skytala.eCommerce.domain.supplierRatingType.mapper.SupplierRatingTypeMapper;
import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
import com.skytala.eCommerce.domain.supplierRatingType.query.FindSupplierRatingTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/supplierRatingTypes")
public class SupplierRatingTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierRatingTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierRatingType
	 * @return a List with the SupplierRatingTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSupplierRatingTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierRatingTypesBy query = new FindSupplierRatingTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierRatingType> supplierRatingTypes =((SupplierRatingTypeFound) Scheduler.execute(query).data()).getSupplierRatingTypes();

		if (supplierRatingTypes.size() == 1) {
			return ResponseEntity.ok().body(supplierRatingTypes.get(0));
		}

		return ResponseEntity.ok().body(supplierRatingTypes);

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
	public ResponseEntity<Object> createSupplierRatingType(HttpServletRequest request) throws Exception {

		SupplierRatingType supplierRatingTypeToBeAdded = new SupplierRatingType();
		try {
			supplierRatingTypeToBeAdded = SupplierRatingTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSupplierRatingType(supplierRatingTypeToBeAdded);

	}

	/**
	 * creates a new SupplierRatingType entry in the ofbiz database
	 * 
	 * @param supplierRatingTypeToBeAdded
	 *            the SupplierRatingType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSupplierRatingType(@RequestBody SupplierRatingType supplierRatingTypeToBeAdded) throws Exception {

		AddSupplierRatingType command = new AddSupplierRatingType(supplierRatingTypeToBeAdded);
		SupplierRatingType supplierRatingType = ((SupplierRatingTypeAdded) Scheduler.execute(command).data()).getAddedSupplierRatingType();
		
		if (supplierRatingType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(supplierRatingType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SupplierRatingType could not be created.");
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
	public boolean updateSupplierRatingType(HttpServletRequest request) throws Exception {

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

		SupplierRatingType supplierRatingTypeToBeUpdated = new SupplierRatingType();

		try {
			supplierRatingTypeToBeUpdated = SupplierRatingTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSupplierRatingType(supplierRatingTypeToBeUpdated, supplierRatingTypeToBeUpdated.getSupplierRatingTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SupplierRatingType with the specific Id
	 * 
	 * @param supplierRatingTypeToBeUpdated
	 *            the SupplierRatingType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{supplierRatingTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSupplierRatingType(@RequestBody SupplierRatingType supplierRatingTypeToBeUpdated,
			@PathVariable String supplierRatingTypeId) throws Exception {

		supplierRatingTypeToBeUpdated.setSupplierRatingTypeId(supplierRatingTypeId);

		UpdateSupplierRatingType command = new UpdateSupplierRatingType(supplierRatingTypeToBeUpdated);

		try {
			if(((SupplierRatingTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{supplierRatingTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String supplierRatingTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierRatingTypeId", supplierRatingTypeId);
		try {

			Object foundSupplierRatingType = findSupplierRatingTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSupplierRatingType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{supplierRatingTypeId}")
	public ResponseEntity<Object> deleteSupplierRatingTypeByIdUpdated(@PathVariable String supplierRatingTypeId) throws Exception {
		DeleteSupplierRatingType command = new DeleteSupplierRatingType(supplierRatingTypeId);

		try {
			if (((SupplierRatingTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SupplierRatingType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/supplierRatingType/\" plus one of the following: "
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
