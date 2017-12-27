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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentRevisions")
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
	@GetMapping("/find")
	public ResponseEntity<List<ContentRevision>> findContentRevisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentRevisionsBy query = new FindContentRevisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentRevision> contentRevisions =((ContentRevisionFound) Scheduler.execute(query).data()).getContentRevisions();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ContentRevision> createContentRevision(HttpServletRequest request) throws Exception {

		ContentRevision contentRevisionToBeAdded = new ContentRevision();
		try {
			contentRevisionToBeAdded = ContentRevisionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ContentRevision> createContentRevision(@RequestBody ContentRevision contentRevisionToBeAdded) throws Exception {

		AddContentRevision command = new AddContentRevision(contentRevisionToBeAdded);
		ContentRevision contentRevision = ((ContentRevisionAdded) Scheduler.execute(command).data()).getAddedContentRevision();
		
		if (contentRevision != null) 
			return successful(contentRevision);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentRevision(@RequestBody ContentRevision contentRevisionToBeUpdated,
			@PathVariable String contentRevisionSeqId) throws Exception {

		contentRevisionToBeUpdated.setContentRevisionSeqId(contentRevisionSeqId);

		UpdateContentRevision command = new UpdateContentRevision(contentRevisionToBeUpdated);

		try {
			if(((ContentRevisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentRevisionId}")
	public ResponseEntity<ContentRevision> findById(@PathVariable String contentRevisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentRevisionId", contentRevisionId);
		try {

			List<ContentRevision> foundContentRevision = findContentRevisionsBy(requestParams).getBody();
			if(foundContentRevision.size()==1){				return successful(foundContentRevision.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentRevisionId}")
	public ResponseEntity<String> deleteContentRevisionByIdUpdated(@PathVariable String contentRevisionId) throws Exception {
		DeleteContentRevision command = new DeleteContentRevision(contentRevisionId);

		try {
			if (((ContentRevisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
