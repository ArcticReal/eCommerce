package com.skytala.eCommerce.domain.content.relations.content.control.approval;

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
import com.skytala.eCommerce.domain.content.relations.content.command.approval.AddContentApproval;
import com.skytala.eCommerce.domain.content.relations.content.command.approval.DeleteContentApproval;
import com.skytala.eCommerce.domain.content.relations.content.command.approval.UpdateContentApproval;
import com.skytala.eCommerce.domain.content.relations.content.event.approval.ContentApprovalAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.approval.ContentApprovalDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.approval.ContentApprovalFound;
import com.skytala.eCommerce.domain.content.relations.content.event.approval.ContentApprovalUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.approval.ContentApprovalMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.approval.ContentApproval;
import com.skytala.eCommerce.domain.content.relations.content.query.approval.FindContentApprovalsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentApprovals")
public class ContentApprovalController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentApprovalController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentApproval
	 * @return a List with the ContentApprovals
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentApproval>> findContentApprovalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentApprovalsBy query = new FindContentApprovalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentApproval> contentApprovals =((ContentApprovalFound) Scheduler.execute(query).data()).getContentApprovals();

		return ResponseEntity.ok().body(contentApprovals);

	}

	/**
	 * creates a new ContentApproval entry in the ofbiz database
	 * 
	 * @param contentApprovalToBeAdded
	 *            the ContentApproval thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentApproval> createContentApproval(@RequestBody ContentApproval contentApprovalToBeAdded) throws Exception {

		AddContentApproval command = new AddContentApproval(contentApprovalToBeAdded);
		ContentApproval contentApproval = ((ContentApprovalAdded) Scheduler.execute(command).data()).getAddedContentApproval();
		
		if (contentApproval != null) 
			return successful(contentApproval);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentApproval with the specific Id
	 * 
	 * @param contentApprovalToBeUpdated
	 *            the ContentApproval thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentApprovalId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentApproval(@RequestBody ContentApproval contentApprovalToBeUpdated,
			@PathVariable String contentApprovalId) throws Exception {

		contentApprovalToBeUpdated.setContentApprovalId(contentApprovalId);

		UpdateContentApproval command = new UpdateContentApproval(contentApprovalToBeUpdated);

		try {
			if(((ContentApprovalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentApprovalId}")
	public ResponseEntity<ContentApproval> findById(@PathVariable String contentApprovalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentApprovalId", contentApprovalId);
		try {

			List<ContentApproval> foundContentApproval = findContentApprovalsBy(requestParams).getBody();
			if(foundContentApproval.size()==1){				return successful(foundContentApproval.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentApprovalId}")
	public ResponseEntity<String> deleteContentApprovalByIdUpdated(@PathVariable String contentApprovalId) throws Exception {
		DeleteContentApproval command = new DeleteContentApproval(contentApprovalId);

		try {
			if (((ContentApprovalDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
