package com.skytala.eCommerce.domain.content.relations.webSiteContentType;

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
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.command.AddWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.command.DeleteWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.command.UpdateWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.event.WebSiteContentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.event.WebSiteContentTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.event.WebSiteContentTypeFound;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.event.WebSiteContentTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.mapper.WebSiteContentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.model.WebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.query.FindWebSiteContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/webSiteContentTypes")
public class WebSiteContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSiteContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSiteContentType
	 * @return a List with the WebSiteContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWebSiteContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteContentTypesBy query = new FindWebSiteContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteContentType> webSiteContentTypes =((WebSiteContentTypeFound) Scheduler.execute(query).data()).getWebSiteContentTypes();

		if (webSiteContentTypes.size() == 1) {
			return ResponseEntity.ok().body(webSiteContentTypes.get(0));
		}

		return ResponseEntity.ok().body(webSiteContentTypes);

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
	public ResponseEntity<Object> createWebSiteContentType(HttpServletRequest request) throws Exception {

		WebSiteContentType webSiteContentTypeToBeAdded = new WebSiteContentType();
		try {
			webSiteContentTypeToBeAdded = WebSiteContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebSiteContentType(webSiteContentTypeToBeAdded);

	}

	/**
	 * creates a new WebSiteContentType entry in the ofbiz database
	 * 
	 * @param webSiteContentTypeToBeAdded
	 *            the WebSiteContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebSiteContentType(@RequestBody WebSiteContentType webSiteContentTypeToBeAdded) throws Exception {

		AddWebSiteContentType command = new AddWebSiteContentType(webSiteContentTypeToBeAdded);
		WebSiteContentType webSiteContentType = ((WebSiteContentTypeAdded) Scheduler.execute(command).data()).getAddedWebSiteContentType();
		
		if (webSiteContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webSiteContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebSiteContentType could not be created.");
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
	public boolean updateWebSiteContentType(HttpServletRequest request) throws Exception {

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

		WebSiteContentType webSiteContentTypeToBeUpdated = new WebSiteContentType();

		try {
			webSiteContentTypeToBeUpdated = WebSiteContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebSiteContentType(webSiteContentTypeToBeUpdated, webSiteContentTypeToBeUpdated.getWebSiteContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebSiteContentType with the specific Id
	 * 
	 * @param webSiteContentTypeToBeUpdated
	 *            the WebSiteContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{webSiteContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebSiteContentType(@RequestBody WebSiteContentType webSiteContentTypeToBeUpdated,
			@PathVariable String webSiteContentTypeId) throws Exception {

		webSiteContentTypeToBeUpdated.setWebSiteContentTypeId(webSiteContentTypeId);

		UpdateWebSiteContentType command = new UpdateWebSiteContentType(webSiteContentTypeToBeUpdated);

		try {
			if(((WebSiteContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{webSiteContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String webSiteContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteContentTypeId", webSiteContentTypeId);
		try {

			Object foundWebSiteContentType = findWebSiteContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebSiteContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{webSiteContentTypeId}")
	public ResponseEntity<Object> deleteWebSiteContentTypeByIdUpdated(@PathVariable String webSiteContentTypeId) throws Exception {
		DeleteWebSiteContentType command = new DeleteWebSiteContentType(webSiteContentTypeId);

		try {
			if (((WebSiteContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebSiteContentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/webSiteContentType/\" plus one of the following: "
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
