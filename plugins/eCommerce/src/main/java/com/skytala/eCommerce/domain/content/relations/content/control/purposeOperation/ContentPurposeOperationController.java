package com.skytala.eCommerce.domain.content.relations.content.control.purposeOperation;

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
import com.skytala.eCommerce.domain.content.relations.content.command.purposeOperation.AddContentPurposeOperation;
import com.skytala.eCommerce.domain.content.relations.content.command.purposeOperation.DeleteContentPurposeOperation;
import com.skytala.eCommerce.domain.content.relations.content.command.purposeOperation.UpdateContentPurposeOperation;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation.ContentPurposeOperationAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation.ContentPurposeOperationDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation.ContentPurposeOperationFound;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation.ContentPurposeOperationUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purposeOperation.ContentPurposeOperationMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purposeOperation.ContentPurposeOperation;
import com.skytala.eCommerce.domain.content.relations.content.query.purposeOperation.FindContentPurposeOperationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/content/contentPurposeOperations")
public class ContentPurposeOperationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentPurposeOperationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentPurposeOperation
	 * @return a List with the ContentPurposeOperations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findContentPurposeOperationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentPurposeOperationsBy query = new FindContentPurposeOperationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentPurposeOperation> contentPurposeOperations =((ContentPurposeOperationFound) Scheduler.execute(query).data()).getContentPurposeOperations();

		if (contentPurposeOperations.size() == 1) {
			return ResponseEntity.ok().body(contentPurposeOperations.get(0));
		}

		return ResponseEntity.ok().body(contentPurposeOperations);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContentPurposeOperation(HttpServletRequest request) throws Exception {

		ContentPurposeOperation contentPurposeOperationToBeAdded = new ContentPurposeOperation();
		try {
			contentPurposeOperationToBeAdded = ContentPurposeOperationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentPurposeOperation(contentPurposeOperationToBeAdded);

	}

	/**
	 * creates a new ContentPurposeOperation entry in the ofbiz database
	 * 
	 * @param contentPurposeOperationToBeAdded
	 *            the ContentPurposeOperation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentPurposeOperation(@RequestBody ContentPurposeOperation contentPurposeOperationToBeAdded) throws Exception {

		AddContentPurposeOperation command = new AddContentPurposeOperation(contentPurposeOperationToBeAdded);
		ContentPurposeOperation contentPurposeOperation = ((ContentPurposeOperationAdded) Scheduler.execute(command).data()).getAddedContentPurposeOperation();
		
		if (contentPurposeOperation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentPurposeOperation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentPurposeOperation could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateContentPurposeOperation(HttpServletRequest request) throws Exception {

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

		ContentPurposeOperation contentPurposeOperationToBeUpdated = new ContentPurposeOperation();

		try {
			contentPurposeOperationToBeUpdated = ContentPurposeOperationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentPurposeOperation(contentPurposeOperationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentPurposeOperation with the specific Id
	 * 
	 * @param contentPurposeOperationToBeUpdated
	 *            the ContentPurposeOperation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentPurposeOperation(@RequestBody ContentPurposeOperation contentPurposeOperationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentPurposeOperationToBeUpdated.setnull(null);

		UpdateContentPurposeOperation command = new UpdateContentPurposeOperation(contentPurposeOperationToBeUpdated);

		try {
			if(((ContentPurposeOperationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentPurposeOperationId}")
	public ResponseEntity<Object> findById(@PathVariable String contentPurposeOperationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentPurposeOperationId", contentPurposeOperationId);
		try {

			Object foundContentPurposeOperation = findContentPurposeOperationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentPurposeOperation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentPurposeOperationId}")
	public ResponseEntity<Object> deleteContentPurposeOperationByIdUpdated(@PathVariable String contentPurposeOperationId) throws Exception {
		DeleteContentPurposeOperation command = new DeleteContentPurposeOperation(contentPurposeOperationId);

		try {
			if (((ContentPurposeOperationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentPurposeOperation could not be deleted");

	}

}
