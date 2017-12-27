package com.skytala.eCommerce.domain.content.relations.content.control.operation;

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
import com.skytala.eCommerce.domain.content.relations.content.command.operation.AddContentOperation;
import com.skytala.eCommerce.domain.content.relations.content.command.operation.DeleteContentOperation;
import com.skytala.eCommerce.domain.content.relations.content.command.operation.UpdateContentOperation;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationFound;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.operation.ContentOperationMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
import com.skytala.eCommerce.domain.content.relations.content.query.operation.FindContentOperationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentOperations")
public class ContentOperationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentOperationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentOperation
	 * @return a List with the ContentOperations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentOperation>> findContentOperationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentOperationsBy query = new FindContentOperationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentOperation> contentOperations =((ContentOperationFound) Scheduler.execute(query).data()).getContentOperations();

		return ResponseEntity.ok().body(contentOperations);

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
	public ResponseEntity<ContentOperation> createContentOperation(HttpServletRequest request) throws Exception {

		ContentOperation contentOperationToBeAdded = new ContentOperation();
		try {
			contentOperationToBeAdded = ContentOperationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentOperation(contentOperationToBeAdded);

	}

	/**
	 * creates a new ContentOperation entry in the ofbiz database
	 * 
	 * @param contentOperationToBeAdded
	 *            the ContentOperation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentOperation> createContentOperation(@RequestBody ContentOperation contentOperationToBeAdded) throws Exception {

		AddContentOperation command = new AddContentOperation(contentOperationToBeAdded);
		ContentOperation contentOperation = ((ContentOperationAdded) Scheduler.execute(command).data()).getAddedContentOperation();
		
		if (contentOperation != null) 
			return successful(contentOperation);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentOperation with the specific Id
	 * 
	 * @param contentOperationToBeUpdated
	 *            the ContentOperation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentOperationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentOperation(@RequestBody ContentOperation contentOperationToBeUpdated,
			@PathVariable String contentOperationId) throws Exception {

		contentOperationToBeUpdated.setContentOperationId(contentOperationId);

		UpdateContentOperation command = new UpdateContentOperation(contentOperationToBeUpdated);

		try {
			if(((ContentOperationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentOperationId}")
	public ResponseEntity<ContentOperation> findById(@PathVariable String contentOperationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentOperationId", contentOperationId);
		try {

			List<ContentOperation> foundContentOperation = findContentOperationsBy(requestParams).getBody();
			if(foundContentOperation.size()==1){				return successful(foundContentOperation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentOperationId}")
	public ResponseEntity<String> deleteContentOperationByIdUpdated(@PathVariable String contentOperationId) throws Exception {
		DeleteContentOperation command = new DeleteContentOperation(contentOperationId);

		try {
			if (((ContentOperationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
