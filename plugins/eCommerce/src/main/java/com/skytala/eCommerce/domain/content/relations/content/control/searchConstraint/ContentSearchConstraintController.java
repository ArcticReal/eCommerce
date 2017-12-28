package com.skytala.eCommerce.domain.content.relations.content.control.searchConstraint;

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
import com.skytala.eCommerce.domain.content.relations.content.command.searchConstraint.AddContentSearchConstraint;
import com.skytala.eCommerce.domain.content.relations.content.command.searchConstraint.DeleteContentSearchConstraint;
import com.skytala.eCommerce.domain.content.relations.content.command.searchConstraint.UpdateContentSearchConstraint;
import com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint.ContentSearchConstraintAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint.ContentSearchConstraintDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint.ContentSearchConstraintFound;
import com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint.ContentSearchConstraintUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.searchConstraint.ContentSearchConstraintMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.searchConstraint.ContentSearchConstraint;
import com.skytala.eCommerce.domain.content.relations.content.query.searchConstraint.FindContentSearchConstraintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentSearchConstraints")
public class ContentSearchConstraintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentSearchConstraintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentSearchConstraint
	 * @return a List with the ContentSearchConstraints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentSearchConstraint>> findContentSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentSearchConstraintsBy query = new FindContentSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentSearchConstraint> contentSearchConstraints =((ContentSearchConstraintFound) Scheduler.execute(query).data()).getContentSearchConstraints();

		return ResponseEntity.ok().body(contentSearchConstraints);

	}

	/**
	 * creates a new ContentSearchConstraint entry in the ofbiz database
	 * 
	 * @param contentSearchConstraintToBeAdded
	 *            the ContentSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentSearchConstraint> createContentSearchConstraint(@RequestBody ContentSearchConstraint contentSearchConstraintToBeAdded) throws Exception {

		AddContentSearchConstraint command = new AddContentSearchConstraint(contentSearchConstraintToBeAdded);
		ContentSearchConstraint contentSearchConstraint = ((ContentSearchConstraintAdded) Scheduler.execute(command).data()).getAddedContentSearchConstraint();
		
		if (contentSearchConstraint != null) 
			return successful(contentSearchConstraint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentSearchConstraint with the specific Id
	 * 
	 * @param contentSearchConstraintToBeUpdated
	 *            the ContentSearchConstraint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{constraintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentSearchConstraint(@RequestBody ContentSearchConstraint contentSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		contentSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateContentSearchConstraint command = new UpdateContentSearchConstraint(contentSearchConstraintToBeUpdated);

		try {
			if(((ContentSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentSearchConstraintId}")
	public ResponseEntity<ContentSearchConstraint> findById(@PathVariable String contentSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentSearchConstraintId", contentSearchConstraintId);
		try {

			List<ContentSearchConstraint> foundContentSearchConstraint = findContentSearchConstraintsBy(requestParams).getBody();
			if(foundContentSearchConstraint.size()==1){				return successful(foundContentSearchConstraint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentSearchConstraintId}")
	public ResponseEntity<String> deleteContentSearchConstraintByIdUpdated(@PathVariable String contentSearchConstraintId) throws Exception {
		DeleteContentSearchConstraint command = new DeleteContentSearchConstraint(contentSearchConstraintId);

		try {
			if (((ContentSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
