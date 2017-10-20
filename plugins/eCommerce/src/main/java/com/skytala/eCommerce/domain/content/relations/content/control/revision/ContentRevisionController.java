package com.skytala.eCommerce.domain.content.relations.content.control.revision;

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
import com.skytala.eCommerce.domain.content.relations.content.command.revision.AddContentRevision;
import com.skytala.eCommerce.domain.content.relations.content.command.revision.DeleteContentRevision;
import com.skytala.eCommerce.domain.content.relations.content.command.revision.UpdateContentRevision;
import com.skytala.eCommerce.domain.content.relations.content.event.revision.ContentRevisionAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.revision.ContentRevisionDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.revision.ContentRevisionFound;
import com.skytala.eCommerce.domain.content.relations.content.event.revision.ContentRevisionUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.revision.ContentRevisionMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;
import com.skytala.eCommerce.domain.content.relations.content.query.revision.FindContentRevisionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/contentRevisions")
public class ContentRevisionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentRevisionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentRevision
	 * @return a List with the ContentRevisions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentRevisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentRevisionsBy query = new FindContentRevisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentRevision> contentRevisions =((ContentRevisionFound) Scheduler.execute(query).data()).getContentRevisions();

		if (contentRevisions.size() == 1) {
			return ResponseEntity.ok().body(contentRevisions.get(0));
		}

		return ResponseEntity.ok().body(contentRevisions);

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
	public ResponseEntity<Object> createContentRevision(HttpServletRequest request) throws Exception {

		ContentRevision contentRevisionToBeAdded = new ContentRevision();
		try {
			contentRevisionToBeAdded = ContentRevisionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentRevision(contentRevisionToBeAdded);

	}

	/**
	 * creates a new ContentRevision entry in the ofbiz database
	 * 
	 * @param contentRevisionToBeAdded
	 *            the ContentRevision thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentRevision(@RequestBody ContentRevision contentRevisionToBeAdded) throws Exception {

		AddContentRevision command = new AddContentRevision(contentRevisionToBeAdded);
		ContentRevision contentRevision = ((ContentRevisionAdded) Scheduler.execute(command).data()).getAddedContentRevision();
		
		if (contentRevision != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentRevision);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentRevision could not be created.");
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
	public boolean updateContentRevision(HttpServletRequest request) throws Exception {

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

		ContentRevision contentRevisionToBeUpdated = new ContentRevision();

		try {
			contentRevisionToBeUpdated = ContentRevisionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentRevision(contentRevisionToBeUpdated, contentRevisionToBeUpdated.getContentRevisionSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentRevision with the specific Id
	 * 
	 * @param contentRevisionToBeUpdated
	 *            the ContentRevision thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentRevisionSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentRevision(@RequestBody ContentRevision contentRevisionToBeUpdated,
			@PathVariable String contentRevisionSeqId) throws Exception {

		contentRevisionToBeUpdated.setContentRevisionSeqId(contentRevisionSeqId);

		UpdateContentRevision command = new UpdateContentRevision(contentRevisionToBeUpdated);

		try {
			if(((ContentRevisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentRevisionId}")
	public ResponseEntity<Object> findById(@PathVariable String contentRevisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentRevisionId", contentRevisionId);
		try {

			Object foundContentRevision = findContentRevisionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentRevision);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentRevisionId}")
	public ResponseEntity<Object> deleteContentRevisionByIdUpdated(@PathVariable String contentRevisionId) throws Exception {
		DeleteContentRevision command = new DeleteContentRevision(contentRevisionId);

		try {
			if (((ContentRevisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentRevision could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contentRevision/\" plus one of the following: "
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
