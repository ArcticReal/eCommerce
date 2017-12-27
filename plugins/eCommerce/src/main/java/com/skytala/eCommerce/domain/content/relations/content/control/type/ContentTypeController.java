package com.skytala.eCommerce.domain.content.relations.content.control.type;

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
import com.skytala.eCommerce.domain.content.relations.content.command.type.AddContentType;
import com.skytala.eCommerce.domain.content.relations.content.command.type.DeleteContentType;
import com.skytala.eCommerce.domain.content.relations.content.command.type.UpdateContentType;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.type.ContentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
import com.skytala.eCommerce.domain.content.relations.content.query.type.FindContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentTypes")
public class ContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentType
	 * @return a List with the ContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentType>> findContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentTypesBy query = new FindContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentType> contentTypes =((ContentTypeFound) Scheduler.execute(query).data()).getContentTypes();

		return ResponseEntity.ok().body(contentTypes);

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
	public ResponseEntity<ContentType> createContentType(HttpServletRequest request) throws Exception {

		ContentType contentTypeToBeAdded = new ContentType();
		try {
			contentTypeToBeAdded = ContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentType(contentTypeToBeAdded);

	}

	/**
	 * creates a new ContentType entry in the ofbiz database
	 * 
	 * @param contentTypeToBeAdded
	 *            the ContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentType> createContentType(@RequestBody ContentType contentTypeToBeAdded) throws Exception {

		AddContentType command = new AddContentType(contentTypeToBeAdded);
		ContentType contentType = ((ContentTypeAdded) Scheduler.execute(command).data()).getAddedContentType();
		
		if (contentType != null) 
			return successful(contentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentType with the specific Id
	 * 
	 * @param contentTypeToBeUpdated
	 *            the ContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentType(@RequestBody ContentType contentTypeToBeUpdated,
			@PathVariable String contentTypeId) throws Exception {

		contentTypeToBeUpdated.setContentTypeId(contentTypeId);

		UpdateContentType command = new UpdateContentType(contentTypeToBeUpdated);

		try {
			if(((ContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentTypeId}")
	public ResponseEntity<ContentType> findById(@PathVariable String contentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentTypeId", contentTypeId);
		try {

			List<ContentType> foundContentType = findContentTypesBy(requestParams).getBody();
			if(foundContentType.size()==1){				return successful(foundContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentTypeId}")
	public ResponseEntity<String> deleteContentTypeByIdUpdated(@PathVariable String contentTypeId) throws Exception {
		DeleteContentType command = new DeleteContentType(contentTypeId);

		try {
			if (((ContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
