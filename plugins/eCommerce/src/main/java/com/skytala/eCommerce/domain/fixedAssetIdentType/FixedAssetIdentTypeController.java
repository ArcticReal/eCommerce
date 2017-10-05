package com.skytala.eCommerce.domain.fixedAssetIdentType;

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
import com.skytala.eCommerce.domain.fixedAssetIdentType.command.AddFixedAssetIdentType;
import com.skytala.eCommerce.domain.fixedAssetIdentType.command.DeleteFixedAssetIdentType;
import com.skytala.eCommerce.domain.fixedAssetIdentType.command.UpdateFixedAssetIdentType;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeAdded;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeDeleted;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeFound;
import com.skytala.eCommerce.domain.fixedAssetIdentType.event.FixedAssetIdentTypeUpdated;
import com.skytala.eCommerce.domain.fixedAssetIdentType.mapper.FixedAssetIdentTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;
import com.skytala.eCommerce.domain.fixedAssetIdentType.query.FindFixedAssetIdentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetIdentTypes")
public class FixedAssetIdentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetIdentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetIdentType
	 * @return a List with the FixedAssetIdentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetIdentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetIdentTypesBy query = new FindFixedAssetIdentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetIdentType> fixedAssetIdentTypes =((FixedAssetIdentTypeFound) Scheduler.execute(query).data()).getFixedAssetIdentTypes();

		if (fixedAssetIdentTypes.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetIdentTypes.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetIdentTypes);

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
	public ResponseEntity<Object> createFixedAssetIdentType(HttpServletRequest request) throws Exception {

		FixedAssetIdentType fixedAssetIdentTypeToBeAdded = new FixedAssetIdentType();
		try {
			fixedAssetIdentTypeToBeAdded = FixedAssetIdentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetIdentType(fixedAssetIdentTypeToBeAdded);

	}

	/**
	 * creates a new FixedAssetIdentType entry in the ofbiz database
	 * 
	 * @param fixedAssetIdentTypeToBeAdded
	 *            the FixedAssetIdentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetIdentType(@RequestBody FixedAssetIdentType fixedAssetIdentTypeToBeAdded) throws Exception {

		AddFixedAssetIdentType command = new AddFixedAssetIdentType(fixedAssetIdentTypeToBeAdded);
		FixedAssetIdentType fixedAssetIdentType = ((FixedAssetIdentTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetIdentType();
		
		if (fixedAssetIdentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetIdentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetIdentType could not be created.");
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
	public boolean updateFixedAssetIdentType(HttpServletRequest request) throws Exception {

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

		FixedAssetIdentType fixedAssetIdentTypeToBeUpdated = new FixedAssetIdentType();

		try {
			fixedAssetIdentTypeToBeUpdated = FixedAssetIdentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetIdentType(fixedAssetIdentTypeToBeUpdated, fixedAssetIdentTypeToBeUpdated.getFixedAssetIdentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetIdentType with the specific Id
	 * 
	 * @param fixedAssetIdentTypeToBeUpdated
	 *            the FixedAssetIdentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetIdentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetIdentType(@RequestBody FixedAssetIdentType fixedAssetIdentTypeToBeUpdated,
			@PathVariable String fixedAssetIdentTypeId) throws Exception {

		fixedAssetIdentTypeToBeUpdated.setFixedAssetIdentTypeId(fixedAssetIdentTypeId);

		UpdateFixedAssetIdentType command = new UpdateFixedAssetIdentType(fixedAssetIdentTypeToBeUpdated);

		try {
			if(((FixedAssetIdentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetIdentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetIdentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetIdentTypeId", fixedAssetIdentTypeId);
		try {

			Object foundFixedAssetIdentType = findFixedAssetIdentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetIdentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetIdentTypeId}")
	public ResponseEntity<Object> deleteFixedAssetIdentTypeByIdUpdated(@PathVariable String fixedAssetIdentTypeId) throws Exception {
		DeleteFixedAssetIdentType command = new DeleteFixedAssetIdentType(fixedAssetIdentTypeId);

		try {
			if (((FixedAssetIdentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetIdentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetIdentType/\" plus one of the following: "
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
