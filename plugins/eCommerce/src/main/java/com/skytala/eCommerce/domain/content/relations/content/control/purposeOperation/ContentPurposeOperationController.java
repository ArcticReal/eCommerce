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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ContentPurposeOperation>> findContentPurposeOperationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentPurposeOperationsBy query = new FindContentPurposeOperationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentPurposeOperation> contentPurposeOperations =((ContentPurposeOperationFound) Scheduler.execute(query).data()).getContentPurposeOperations();

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
	public ResponseEntity<ContentPurposeOperation> createContentPurposeOperation(HttpServletRequest request) throws Exception {

		ContentPurposeOperation contentPurposeOperationToBeAdded = new ContentPurposeOperation();
		try {
			contentPurposeOperationToBeAdded = ContentPurposeOperationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ContentPurposeOperation> createContentPurposeOperation(@RequestBody ContentPurposeOperation contentPurposeOperationToBeAdded) throws Exception {

		AddContentPurposeOperation command = new AddContentPurposeOperation(contentPurposeOperationToBeAdded);
		ContentPurposeOperation contentPurposeOperation = ((ContentPurposeOperationAdded) Scheduler.execute(command).data()).getAddedContentPurposeOperation();
		
		if (contentPurposeOperation != null) 
			return successful(contentPurposeOperation);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentPurposeOperation(@RequestBody ContentPurposeOperation contentPurposeOperationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentPurposeOperationToBeUpdated.setnull(null);

		UpdateContentPurposeOperation command = new UpdateContentPurposeOperation(contentPurposeOperationToBeUpdated);

		try {
			if(((ContentPurposeOperationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentPurposeOperationId}")
	public ResponseEntity<ContentPurposeOperation> findById(@PathVariable String contentPurposeOperationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentPurposeOperationId", contentPurposeOperationId);
		try {

			List<ContentPurposeOperation> foundContentPurposeOperation = findContentPurposeOperationsBy(requestParams).getBody();
			if(foundContentPurposeOperation.size()==1){				return successful(foundContentPurposeOperation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentPurposeOperationId}")
	public ResponseEntity<String> deleteContentPurposeOperationByIdUpdated(@PathVariable String contentPurposeOperationId) throws Exception {
		DeleteContentPurposeOperation command = new DeleteContentPurposeOperation(contentPurposeOperationId);

		try {
			if (((ContentPurposeOperationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
