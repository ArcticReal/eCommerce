package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox;

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
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.AddApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.DeleteApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.UpdateApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxFound;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper.ApplicationSandboxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.query.FindApplicationSandboxsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/applicationSandboxs")
public class ApplicationSandboxController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ApplicationSandboxController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ApplicationSandbox
	 * @return a List with the ApplicationSandboxs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findApplicationSandboxsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindApplicationSandboxsBy query = new FindApplicationSandboxsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ApplicationSandbox> applicationSandboxs =((ApplicationSandboxFound) Scheduler.execute(query).data()).getApplicationSandboxs();

		if (applicationSandboxs.size() == 1) {
			return ResponseEntity.ok().body(applicationSandboxs.get(0));
		}

		return ResponseEntity.ok().body(applicationSandboxs);

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
	public ResponseEntity<Object> createApplicationSandbox(HttpServletRequest request) throws Exception {

		ApplicationSandbox applicationSandboxToBeAdded = new ApplicationSandbox();
		try {
			applicationSandboxToBeAdded = ApplicationSandboxMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createApplicationSandbox(applicationSandboxToBeAdded);

	}

	/**
	 * creates a new ApplicationSandbox entry in the ofbiz database
	 * 
	 * @param applicationSandboxToBeAdded
	 *            the ApplicationSandbox thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createApplicationSandbox(@RequestBody ApplicationSandbox applicationSandboxToBeAdded) throws Exception {

		AddApplicationSandbox command = new AddApplicationSandbox(applicationSandboxToBeAdded);
		ApplicationSandbox applicationSandbox = ((ApplicationSandboxAdded) Scheduler.execute(command).data()).getAddedApplicationSandbox();
		
		if (applicationSandbox != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(applicationSandbox);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ApplicationSandbox could not be created.");
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
	public boolean updateApplicationSandbox(HttpServletRequest request) throws Exception {

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

		ApplicationSandbox applicationSandboxToBeUpdated = new ApplicationSandbox();

		try {
			applicationSandboxToBeUpdated = ApplicationSandboxMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateApplicationSandbox(applicationSandboxToBeUpdated, applicationSandboxToBeUpdated.getApplicationId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ApplicationSandbox with the specific Id
	 * 
	 * @param applicationSandboxToBeUpdated
	 *            the ApplicationSandbox thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{applicationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateApplicationSandbox(@RequestBody ApplicationSandbox applicationSandboxToBeUpdated,
			@PathVariable String applicationId) throws Exception {

		applicationSandboxToBeUpdated.setApplicationId(applicationId);

		UpdateApplicationSandbox command = new UpdateApplicationSandbox(applicationSandboxToBeUpdated);

		try {
			if(((ApplicationSandboxUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{applicationSandboxId}")
	public ResponseEntity<Object> findById(@PathVariable String applicationSandboxId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("applicationSandboxId", applicationSandboxId);
		try {

			Object foundApplicationSandbox = findApplicationSandboxsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundApplicationSandbox);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{applicationSandboxId}")
	public ResponseEntity<Object> deleteApplicationSandboxByIdUpdated(@PathVariable String applicationSandboxId) throws Exception {
		DeleteApplicationSandbox command = new DeleteApplicationSandbox(applicationSandboxId);

		try {
			if (((ApplicationSandboxDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ApplicationSandbox could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/applicationSandbox/\" plus one of the following: "
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
