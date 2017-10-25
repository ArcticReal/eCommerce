package com.skytala.eCommerce.domain.accounting.relations.rateType;

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
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.AddRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.DeleteRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.UpdateRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.rateType.mapper.RateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.query.FindRateTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/rateTypes")
public class RateTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RateTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RateType
	 * @return a List with the RateTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRateTypesBy query = new FindRateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RateType> rateTypes =((RateTypeFound) Scheduler.execute(query).data()).getRateTypes();

		if (rateTypes.size() == 1) {
			return ResponseEntity.ok().body(rateTypes.get(0));
		}

		return ResponseEntity.ok().body(rateTypes);

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
	public ResponseEntity<Object> createRateType(HttpServletRequest request) throws Exception {

		RateType rateTypeToBeAdded = new RateType();
		try {
			rateTypeToBeAdded = RateTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRateType(rateTypeToBeAdded);

	}

	/**
	 * creates a new RateType entry in the ofbiz database
	 * 
	 * @param rateTypeToBeAdded
	 *            the RateType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRateType(@RequestBody RateType rateTypeToBeAdded) throws Exception {

		AddRateType command = new AddRateType(rateTypeToBeAdded);
		RateType rateType = ((RateTypeAdded) Scheduler.execute(command).data()).getAddedRateType();
		
		if (rateType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(rateType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RateType could not be created.");
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
	public boolean updateRateType(HttpServletRequest request) throws Exception {

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

		RateType rateTypeToBeUpdated = new RateType();

		try {
			rateTypeToBeUpdated = RateTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRateType(rateTypeToBeUpdated, rateTypeToBeUpdated.getRateTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RateType with the specific Id
	 * 
	 * @param rateTypeToBeUpdated
	 *            the RateType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{rateTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRateType(@RequestBody RateType rateTypeToBeUpdated,
			@PathVariable String rateTypeId) throws Exception {

		rateTypeToBeUpdated.setRateTypeId(rateTypeId);

		UpdateRateType command = new UpdateRateType(rateTypeToBeUpdated);

		try {
			if(((RateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{rateTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String rateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rateTypeId", rateTypeId);
		try {

			Object foundRateType = findRateTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRateType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{rateTypeId}")
	public ResponseEntity<Object> deleteRateTypeByIdUpdated(@PathVariable String rateTypeId) throws Exception {
		DeleteRateType command = new DeleteRateType(rateTypeId);

		try {
			if (((RateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RateType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/rateType/\" plus one of the following: "
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
