package com.skytala.eCommerce.domain.content.relations.content.control.purposeType;

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
import com.skytala.eCommerce.domain.content.relations.content.command.purposeType.AddContentPurposeType;
import com.skytala.eCommerce.domain.content.relations.content.command.purposeType.DeleteContentPurposeType;
import com.skytala.eCommerce.domain.content.relations.content.command.purposeType.UpdateContentPurposeType;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purposeType.ContentPurposeTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purposeType.ContentPurposeType;
import com.skytala.eCommerce.domain.content.relations.content.query.purposeType.FindContentPurposeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentPurposeTypes")
public class ContentPurposeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentPurposeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentPurposeType
	 * @return a List with the ContentPurposeTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentPurposeType>> findContentPurposeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentPurposeTypesBy query = new FindContentPurposeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentPurposeType> contentPurposeTypes =((ContentPurposeTypeFound) Scheduler.execute(query).data()).getContentPurposeTypes();

		return ResponseEntity.ok().body(contentPurposeTypes);

	}

	/**
	 * creates a new ContentPurposeType entry in the ofbiz database
	 * 
	 * @param contentPurposeTypeToBeAdded
	 *            the ContentPurposeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentPurposeType> createContentPurposeType(@RequestBody ContentPurposeType contentPurposeTypeToBeAdded) throws Exception {

		AddContentPurposeType command = new AddContentPurposeType(contentPurposeTypeToBeAdded);
		ContentPurposeType contentPurposeType = ((ContentPurposeTypeAdded) Scheduler.execute(command).data()).getAddedContentPurposeType();
		
		if (contentPurposeType != null) 
			return successful(contentPurposeType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentPurposeType with the specific Id
	 * 
	 * @param contentPurposeTypeToBeUpdated
	 *            the ContentPurposeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentPurposeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentPurposeType(@RequestBody ContentPurposeType contentPurposeTypeToBeUpdated,
			@PathVariable String contentPurposeTypeId) throws Exception {

		contentPurposeTypeToBeUpdated.setContentPurposeTypeId(contentPurposeTypeId);

		UpdateContentPurposeType command = new UpdateContentPurposeType(contentPurposeTypeToBeUpdated);

		try {
			if(((ContentPurposeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentPurposeTypeId}")
	public ResponseEntity<ContentPurposeType> findById(@PathVariable String contentPurposeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentPurposeTypeId", contentPurposeTypeId);
		try {

			List<ContentPurposeType> foundContentPurposeType = findContentPurposeTypesBy(requestParams).getBody();
			if(foundContentPurposeType.size()==1){				return successful(foundContentPurposeType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentPurposeTypeId}")
	public ResponseEntity<String> deleteContentPurposeTypeByIdUpdated(@PathVariable String contentPurposeTypeId) throws Exception {
		DeleteContentPurposeType command = new DeleteContentPurposeType(contentPurposeTypeId);

		try {
			if (((ContentPurposeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
