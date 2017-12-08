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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/contentAssocPredicates")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentAssocPredicatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocPredicatesBy query = new FindContentAssocPredicatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssocPredicate> contentAssocPredicates =((ContentAssocPredicateFound) Scheduler.execute(query).data()).getContentAssocPredicates();

		if (contentAssocPredicates.size() == 1) {
			return ResponseEntity.ok().body(contentAssocPredicates.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContentAssocPredicate(HttpServletRequest request) throws Exception {

		ContentAssocPredicate contentAssocPredicateToBeAdded = new ContentAssocPredicate();
		try {
			contentAssocPredicateToBeAdded = ContentAssocPredicateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContentAssocPredicate(@RequestBody ContentAssocPredicate contentAssocPredicateToBeAdded) throws Exception {

		AddContentAssocPredicate command = new AddContentAssocPredicate(contentAssocPredicateToBeAdded);
		ContentAssocPredicate contentAssocPredicate = ((ContentAssocPredicateAdded) Scheduler.execute(command).data()).getAddedContentAssocPredicate();
		
		if (contentAssocPredicate != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentAssocPredicate);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentAssocPredicate could not be created.");
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
	public boolean updateContentAssocPredicate(HttpServletRequest request) throws Exception {

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

		ContentAssocPredicate contentAssocPredicateToBeUpdated = new ContentAssocPredicate();

		try {
			contentAssocPredicateToBeUpdated = ContentAssocPredicateMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentAssocPredicate(contentAssocPredicateToBeUpdated, contentAssocPredicateToBeUpdated.getContentAssocPredicateId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContentAssocPredicate(@RequestBody ContentAssocPredicate contentAssocPredicateToBeUpdated,
			@PathVariable String contentAssocPredicateId) throws Exception {

		contentAssocPredicateToBeUpdated.setContentAssocPredicateId(contentAssocPredicateId);

		UpdateContentAssocPredicate command = new UpdateContentAssocPredicate(contentAssocPredicateToBeUpdated);

		try {
			if(((ContentAssocPredicateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentAssocPredicateId}")
	public ResponseEntity<Object> findById(@PathVariable String contentAssocPredicateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocPredicateId", contentAssocPredicateId);
		try {

			Object foundContentAssocPredicate = findContentAssocPredicatesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentAssocPredicate);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentAssocPredicateId}")
	public ResponseEntity<Object> deleteContentAssocPredicateByIdUpdated(@PathVariable String contentAssocPredicateId) throws Exception {
		DeleteContentAssocPredicate command = new DeleteContentAssocPredicate(contentAssocPredicateId);

		try {
			if (((ContentAssocPredicateDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentAssocPredicate could not be deleted");

	}

}
