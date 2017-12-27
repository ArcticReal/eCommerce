package com.skytala.eCommerce.domain.content.relations.content.control.assocType;

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
import com.skytala.eCommerce.domain.content.relations.content.command.assocType.AddContentAssocType;
import com.skytala.eCommerce.domain.content.relations.content.command.assocType.DeleteContentAssocType;
import com.skytala.eCommerce.domain.content.relations.content.command.assocType.UpdateContentAssocType;
import com.skytala.eCommerce.domain.content.relations.content.event.assocType.ContentAssocTypeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.assocType.ContentAssocTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.assocType.ContentAssocTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.assocType.ContentAssocTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assocType.ContentAssocTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assocType.ContentAssocType;
import com.skytala.eCommerce.domain.content.relations.content.query.assocType.FindContentAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentAssocTypes")
public class ContentAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentAssocType
	 * @return a List with the ContentAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentAssocType>> findContentAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocTypesBy query = new FindContentAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssocType> contentAssocTypes =((ContentAssocTypeFound) Scheduler.execute(query).data()).getContentAssocTypes();

		return ResponseEntity.ok().body(contentAssocTypes);

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
	public ResponseEntity<ContentAssocType> createContentAssocType(HttpServletRequest request) throws Exception {

		ContentAssocType contentAssocTypeToBeAdded = new ContentAssocType();
		try {
			contentAssocTypeToBeAdded = ContentAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentAssocType(contentAssocTypeToBeAdded);

	}

	/**
	 * creates a new ContentAssocType entry in the ofbiz database
	 * 
	 * @param contentAssocTypeToBeAdded
	 *            the ContentAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentAssocType> createContentAssocType(@RequestBody ContentAssocType contentAssocTypeToBeAdded) throws Exception {

		AddContentAssocType command = new AddContentAssocType(contentAssocTypeToBeAdded);
		ContentAssocType contentAssocType = ((ContentAssocTypeAdded) Scheduler.execute(command).data()).getAddedContentAssocType();
		
		if (contentAssocType != null) 
			return successful(contentAssocType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentAssocType with the specific Id
	 * 
	 * @param contentAssocTypeToBeUpdated
	 *            the ContentAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentAssocType(@RequestBody ContentAssocType contentAssocTypeToBeUpdated,
			@PathVariable String contentAssocTypeId) throws Exception {

		contentAssocTypeToBeUpdated.setContentAssocTypeId(contentAssocTypeId);

		UpdateContentAssocType command = new UpdateContentAssocType(contentAssocTypeToBeUpdated);

		try {
			if(((ContentAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentAssocTypeId}")
	public ResponseEntity<ContentAssocType> findById(@PathVariable String contentAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocTypeId", contentAssocTypeId);
		try {

			List<ContentAssocType> foundContentAssocType = findContentAssocTypesBy(requestParams).getBody();
			if(foundContentAssocType.size()==1){				return successful(foundContentAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentAssocTypeId}")
	public ResponseEntity<String> deleteContentAssocTypeByIdUpdated(@PathVariable String contentAssocTypeId) throws Exception {
		DeleteContentAssocType command = new DeleteContentAssocType(contentAssocTypeId);

		try {
			if (((ContentAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
