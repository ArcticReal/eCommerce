package com.skytala.eCommerce.domain.content.relations.content.control.assocPredicate;

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
import com.skytala.eCommerce.domain.content.relations.content.command.assocPredicate.AddContentAssocPredicate;
import com.skytala.eCommerce.domain.content.relations.content.command.assocPredicate.DeleteContentAssocPredicate;
import com.skytala.eCommerce.domain.content.relations.content.command.assocPredicate.UpdateContentAssocPredicate;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateFound;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assocPredicate.ContentAssocPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;
import com.skytala.eCommerce.domain.content.relations.content.query.assocPredicate.FindContentAssocPredicatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentAssocPredicates")
public class ContentAssocPredicateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentAssocPredicateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentAssocPredicate
	 * @return a List with the ContentAssocPredicates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentAssocPredicate>> findContentAssocPredicatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocPredicatesBy query = new FindContentAssocPredicatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssocPredicate> contentAssocPredicates =((ContentAssocPredicateFound) Scheduler.execute(query).data()).getContentAssocPredicates();

		return ResponseEntity.ok().body(contentAssocPredicates);

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
	public ResponseEntity<ContentAssocPredicate> createContentAssocPredicate(HttpServletRequest request) throws Exception {

		ContentAssocPredicate contentAssocPredicateToBeAdded = new ContentAssocPredicate();
		try {
			contentAssocPredicateToBeAdded = ContentAssocPredicateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentAssocPredicate(contentAssocPredicateToBeAdded);

	}

	/**
	 * creates a new ContentAssocPredicate entry in the ofbiz database
	 * 
	 * @param contentAssocPredicateToBeAdded
	 *            the ContentAssocPredicate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentAssocPredicate> createContentAssocPredicate(@RequestBody ContentAssocPredicate contentAssocPredicateToBeAdded) throws Exception {

		AddContentAssocPredicate command = new AddContentAssocPredicate(contentAssocPredicateToBeAdded);
		ContentAssocPredicate contentAssocPredicate = ((ContentAssocPredicateAdded) Scheduler.execute(command).data()).getAddedContentAssocPredicate();
		
		if (contentAssocPredicate != null) 
			return successful(contentAssocPredicate);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentAssocPredicate with the specific Id
	 * 
	 * @param contentAssocPredicateToBeUpdated
	 *            the ContentAssocPredicate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentAssocPredicateId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentAssocPredicate(@RequestBody ContentAssocPredicate contentAssocPredicateToBeUpdated,
			@PathVariable String contentAssocPredicateId) throws Exception {

		contentAssocPredicateToBeUpdated.setContentAssocPredicateId(contentAssocPredicateId);

		UpdateContentAssocPredicate command = new UpdateContentAssocPredicate(contentAssocPredicateToBeUpdated);

		try {
			if(((ContentAssocPredicateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentAssocPredicateId}")
	public ResponseEntity<ContentAssocPredicate> findById(@PathVariable String contentAssocPredicateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocPredicateId", contentAssocPredicateId);
		try {

			List<ContentAssocPredicate> foundContentAssocPredicate = findContentAssocPredicatesBy(requestParams).getBody();
			if(foundContentAssocPredicate.size()==1){				return successful(foundContentAssocPredicate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentAssocPredicateId}")
	public ResponseEntity<String> deleteContentAssocPredicateByIdUpdated(@PathVariable String contentAssocPredicateId) throws Exception {
		DeleteContentAssocPredicate command = new DeleteContentAssocPredicate(contentAssocPredicateId);

		try {
			if (((ContentAssocPredicateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
