package com.skytala.eCommerce.domain.content.relations.content.control.purpose;

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
import com.skytala.eCommerce.domain.content.relations.content.command.purpose.AddContentPurpose;
import com.skytala.eCommerce.domain.content.relations.content.command.purpose.DeleteContentPurpose;
import com.skytala.eCommerce.domain.content.relations.content.command.purpose.UpdateContentPurpose;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purpose.ContentPurposeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
import com.skytala.eCommerce.domain.content.relations.content.query.purpose.FindContentPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentPurposes")
public class ContentPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentPurpose
	 * @return a List with the ContentPurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentPurpose>> findContentPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentPurposesBy query = new FindContentPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentPurpose> contentPurposes =((ContentPurposeFound) Scheduler.execute(query).data()).getContentPurposes();

		return ResponseEntity.ok().body(contentPurposes);

	}

	/**
	 * creates a new ContentPurpose entry in the ofbiz database
	 * 
	 * @param contentPurposeToBeAdded
	 *            the ContentPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentPurpose> createContentPurpose(@RequestBody ContentPurpose contentPurposeToBeAdded) throws Exception {

		AddContentPurpose command = new AddContentPurpose(contentPurposeToBeAdded);
		ContentPurpose contentPurpose = ((ContentPurposeAdded) Scheduler.execute(command).data()).getAddedContentPurpose();
		
		if (contentPurpose != null) 
			return successful(contentPurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentPurpose with the specific Id
	 * 
	 * @param contentPurposeToBeUpdated
	 *            the ContentPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentPurpose(@RequestBody ContentPurpose contentPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentPurposeToBeUpdated.setnull(null);

		UpdateContentPurpose command = new UpdateContentPurpose(contentPurposeToBeUpdated);

		try {
			if(((ContentPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentPurposeId}")
	public ResponseEntity<ContentPurpose> findById(@PathVariable String contentPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentPurposeId", contentPurposeId);
		try {

			List<ContentPurpose> foundContentPurpose = findContentPurposesBy(requestParams).getBody();
			if(foundContentPurpose.size()==1){				return successful(foundContentPurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentPurposeId}")
	public ResponseEntity<String> deleteContentPurposeByIdUpdated(@PathVariable String contentPurposeId) throws Exception {
		DeleteContentPurpose command = new DeleteContentPurpose(contentPurposeId);

		try {
			if (((ContentPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
