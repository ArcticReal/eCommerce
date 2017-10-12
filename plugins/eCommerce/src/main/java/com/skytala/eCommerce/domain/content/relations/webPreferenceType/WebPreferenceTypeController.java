package com.skytala.eCommerce.domain.content.relations.webPreferenceType;

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
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.AddWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.DeleteWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.UpdateWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeAdded;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeFound;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.mapper.WebPreferenceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.query.FindWebPreferenceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/webPreferenceTypes")
public class WebPreferenceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebPreferenceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebPreferenceType
	 * @return a List with the WebPreferenceTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWebPreferenceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebPreferenceTypesBy query = new FindWebPreferenceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebPreferenceType> webPreferenceTypes =((WebPreferenceTypeFound) Scheduler.execute(query).data()).getWebPreferenceTypes();

		if (webPreferenceTypes.size() == 1) {
			return ResponseEntity.ok().body(webPreferenceTypes.get(0));
		}

		return ResponseEntity.ok().body(webPreferenceTypes);

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
	public ResponseEntity<Object> createWebPreferenceType(HttpServletRequest request) throws Exception {

		WebPreferenceType webPreferenceTypeToBeAdded = new WebPreferenceType();
		try {
			webPreferenceTypeToBeAdded = WebPreferenceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebPreferenceType(webPreferenceTypeToBeAdded);

	}

	/**
	 * creates a new WebPreferenceType entry in the ofbiz database
	 * 
	 * @param webPreferenceTypeToBeAdded
	 *            the WebPreferenceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebPreferenceType(@RequestBody WebPreferenceType webPreferenceTypeToBeAdded) throws Exception {

		AddWebPreferenceType command = new AddWebPreferenceType(webPreferenceTypeToBeAdded);
		WebPreferenceType webPreferenceType = ((WebPreferenceTypeAdded) Scheduler.execute(command).data()).getAddedWebPreferenceType();
		
		if (webPreferenceType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webPreferenceType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebPreferenceType could not be created.");
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
	public boolean updateWebPreferenceType(HttpServletRequest request) throws Exception {

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

		WebPreferenceType webPreferenceTypeToBeUpdated = new WebPreferenceType();

		try {
			webPreferenceTypeToBeUpdated = WebPreferenceTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebPreferenceType(webPreferenceTypeToBeUpdated, webPreferenceTypeToBeUpdated.getWebPreferenceTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebPreferenceType with the specific Id
	 * 
	 * @param webPreferenceTypeToBeUpdated
	 *            the WebPreferenceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{webPreferenceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebPreferenceType(@RequestBody WebPreferenceType webPreferenceTypeToBeUpdated,
			@PathVariable String webPreferenceTypeId) throws Exception {

		webPreferenceTypeToBeUpdated.setWebPreferenceTypeId(webPreferenceTypeId);

		UpdateWebPreferenceType command = new UpdateWebPreferenceType(webPreferenceTypeToBeUpdated);

		try {
			if(((WebPreferenceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{webPreferenceTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String webPreferenceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webPreferenceTypeId", webPreferenceTypeId);
		try {

			Object foundWebPreferenceType = findWebPreferenceTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebPreferenceType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{webPreferenceTypeId}")
	public ResponseEntity<Object> deleteWebPreferenceTypeByIdUpdated(@PathVariable String webPreferenceTypeId) throws Exception {
		DeleteWebPreferenceType command = new DeleteWebPreferenceType(webPreferenceTypeId);

		try {
			if (((WebPreferenceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebPreferenceType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/webPreferenceType/\" plus one of the following: "
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
