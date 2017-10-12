package com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType;

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
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.command.AddMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.command.DeleteMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.command.UpdateMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event.MrpEventTypeAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event.MrpEventTypeDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event.MrpEventTypeFound;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event.MrpEventTypeUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.mapper.MrpEventTypeMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.model.MrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.query.FindMrpEventTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/mrpEventTypes")
public class MrpEventTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MrpEventTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MrpEventType
	 * @return a List with the MrpEventTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMrpEventTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMrpEventTypesBy query = new FindMrpEventTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MrpEventType> mrpEventTypes =((MrpEventTypeFound) Scheduler.execute(query).data()).getMrpEventTypes();

		if (mrpEventTypes.size() == 1) {
			return ResponseEntity.ok().body(mrpEventTypes.get(0));
		}

		return ResponseEntity.ok().body(mrpEventTypes);

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
	public ResponseEntity<Object> createMrpEventType(HttpServletRequest request) throws Exception {

		MrpEventType mrpEventTypeToBeAdded = new MrpEventType();
		try {
			mrpEventTypeToBeAdded = MrpEventTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMrpEventType(mrpEventTypeToBeAdded);

	}

	/**
	 * creates a new MrpEventType entry in the ofbiz database
	 * 
	 * @param mrpEventTypeToBeAdded
	 *            the MrpEventType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMrpEventType(@RequestBody MrpEventType mrpEventTypeToBeAdded) throws Exception {

		AddMrpEventType command = new AddMrpEventType(mrpEventTypeToBeAdded);
		MrpEventType mrpEventType = ((MrpEventTypeAdded) Scheduler.execute(command).data()).getAddedMrpEventType();
		
		if (mrpEventType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(mrpEventType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MrpEventType could not be created.");
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
	public boolean updateMrpEventType(HttpServletRequest request) throws Exception {

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

		MrpEventType mrpEventTypeToBeUpdated = new MrpEventType();

		try {
			mrpEventTypeToBeUpdated = MrpEventTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMrpEventType(mrpEventTypeToBeUpdated, mrpEventTypeToBeUpdated.getMrpEventTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MrpEventType with the specific Id
	 * 
	 * @param mrpEventTypeToBeUpdated
	 *            the MrpEventType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{mrpEventTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMrpEventType(@RequestBody MrpEventType mrpEventTypeToBeUpdated,
			@PathVariable String mrpEventTypeId) throws Exception {

		mrpEventTypeToBeUpdated.setMrpEventTypeId(mrpEventTypeId);

		UpdateMrpEventType command = new UpdateMrpEventType(mrpEventTypeToBeUpdated);

		try {
			if(((MrpEventTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{mrpEventTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String mrpEventTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mrpEventTypeId", mrpEventTypeId);
		try {

			Object foundMrpEventType = findMrpEventTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMrpEventType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{mrpEventTypeId}")
	public ResponseEntity<Object> deleteMrpEventTypeByIdUpdated(@PathVariable String mrpEventTypeId) throws Exception {
		DeleteMrpEventType command = new DeleteMrpEventType(mrpEventTypeId);

		try {
			if (((MrpEventTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MrpEventType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/mrpEventType/\" plus one of the following: "
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
