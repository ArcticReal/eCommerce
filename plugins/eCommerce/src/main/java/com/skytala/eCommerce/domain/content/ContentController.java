package com.skytala.eCommerce.domain.content;

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
import com.skytala.eCommerce.domain.content.command.AddContent;
import com.skytala.eCommerce.domain.content.command.DeleteContent;
import com.skytala.eCommerce.domain.content.command.UpdateContent;
import com.skytala.eCommerce.domain.content.event.ContentAdded;
import com.skytala.eCommerce.domain.content.event.ContentDeleted;
import com.skytala.eCommerce.domain.content.event.ContentFound;
import com.skytala.eCommerce.domain.content.event.ContentUpdated;
import com.skytala.eCommerce.domain.content.mapper.ContentMapper;
import com.skytala.eCommerce.domain.content.model.Content;
import com.skytala.eCommerce.domain.content.query.FindContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/contents")
public class ContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Content
	 * @return a List with the Contents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentsBy query = new FindContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Content> contents =((ContentFound) Scheduler.execute(query).data()).getContents();

		if (contents.size() == 1) {
			return ResponseEntity.ok().body(contents.get(0));
		}

		return ResponseEntity.ok().body(contents);

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
	public ResponseEntity<Object> createContent(HttpServletRequest request) throws Exception {

		Content contentToBeAdded = new Content();
		try {
			contentToBeAdded = ContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContent(contentToBeAdded);

	}

	/**
	 * creates a new Content entry in the ofbiz database
	 * 
	 * @param contentToBeAdded
	 *            the Content thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContent(@RequestBody Content contentToBeAdded) throws Exception {

		AddContent command = new AddContent(contentToBeAdded);
		Content content = ((ContentAdded) Scheduler.execute(command).data()).getAddedContent();
		
		if (content != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(content);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Content could not be created.");
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
	public boolean updateContent(HttpServletRequest request) throws Exception {

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

		Content contentToBeUpdated = new Content();

		try {
			contentToBeUpdated = ContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContent(contentToBeUpdated, contentToBeUpdated.getContentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Content with the specific Id
	 * 
	 * @param contentToBeUpdated
	 *            the Content thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContent(@RequestBody Content contentToBeUpdated,
			@PathVariable String contentId) throws Exception {

		contentToBeUpdated.setContentId(contentId);

		UpdateContent command = new UpdateContent(contentToBeUpdated);

		try {
			if(((ContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentId}")
	public ResponseEntity<Object> findById(@PathVariable String contentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentId", contentId);
		try {

			Object foundContent = findContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentId}")
	public ResponseEntity<Object> deleteContentByIdUpdated(@PathVariable String contentId) throws Exception {
		DeleteContent command = new DeleteContent(contentId);

		try {
			if (((ContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Content could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/content/\" plus one of the following: "
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
