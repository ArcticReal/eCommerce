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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findContentSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentSearchConstraintsBy query = new FindContentSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentSearchConstraint> contentSearchConstraints =((ContentSearchConstraintFound) Scheduler.execute(query).data()).getContentSearchConstraints();

		if (contentSearchConstraints.size() == 1) {
			return ResponseEntity.ok().body(contentSearchConstraints.get(0));
		}

		return ResponseEntity.ok().body(contentSearchConstraints);

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
	public ResponseEntity<Object> createContentSearchConstraint(HttpServletRequest request) throws Exception {

		ContentSearchConstraint contentSearchConstraintToBeAdded = new ContentSearchConstraint();
		try {
			contentSearchConstraintToBeAdded = ContentSearchConstraintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentSearchConstraint(contentSearchConstraintToBeAdded);

	}

	/**
	 * creates a new ContentSearchConstraint entry in the ofbiz database
	 * 
	 * @param contentSearchConstraintToBeAdded
	 *            the ContentSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentSearchConstraint(@RequestBody ContentSearchConstraint contentSearchConstraintToBeAdded) throws Exception {

		AddContentSearchConstraint command = new AddContentSearchConstraint(contentSearchConstraintToBeAdded);
		ContentSearchConstraint contentSearchConstraint = ((ContentSearchConstraintAdded) Scheduler.execute(command).data()).getAddedContentSearchConstraint();
		
		if (contentSearchConstraint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentSearchConstraint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentSearchConstraint could not be created.");
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
	public boolean updateContentSearchConstraint(HttpServletRequest request) throws Exception {

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

		ContentSearchConstraint contentSearchConstraintToBeUpdated = new ContentSearchConstraint();

		try {
			contentSearchConstraintToBeUpdated = ContentSearchConstraintMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentSearchConstraint(contentSearchConstraintToBeUpdated, contentSearchConstraintToBeUpdated.getConstraintSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContentSearchConstraint(@RequestBody ContentSearchConstraint contentSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		contentSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateContentSearchConstraint command = new UpdateContentSearchConstraint(contentSearchConstraintToBeUpdated);

		try {
			if(((ContentSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentSearchConstraintId}")
	public ResponseEntity<Object> findById(@PathVariable String contentSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentSearchConstraintId", contentSearchConstraintId);
		try {

			Object foundContentSearchConstraint = findContentSearchConstraintsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentSearchConstraint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentSearchConstraintId}")
	public ResponseEntity<Object> deleteContentSearchConstraintByIdUpdated(@PathVariable String contentSearchConstraintId) throws Exception {
		DeleteContentSearchConstraint command = new DeleteContentSearchConstraint(contentSearchConstraintId);

		try {
			if (((ContentSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentSearchConstraint could not be deleted");

	}

}
