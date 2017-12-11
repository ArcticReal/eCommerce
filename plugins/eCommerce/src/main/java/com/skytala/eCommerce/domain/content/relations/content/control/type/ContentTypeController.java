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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentTypesBy query = new FindContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentType> contentTypes =((ContentTypeFound) Scheduler.execute(query).data()).getContentTypes();

		if (contentTypes.size() == 1) {
			return ResponseEntity.ok().body(contentTypes.get(0));
		}

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
	public ResponseEntity<Object> createContentType(HttpServletRequest request) throws Exception {

		ContentType contentTypeToBeAdded = new ContentType();
		try {
			contentTypeToBeAdded = ContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContentType(@RequestBody ContentType contentTypeToBeAdded) throws Exception {

		AddContentType command = new AddContentType(contentTypeToBeAdded);
		ContentType contentType = ((ContentTypeAdded) Scheduler.execute(command).data()).getAddedContentType();
		
		if (contentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentType could not be created.");
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
	public boolean updateContentType(HttpServletRequest request) throws Exception {

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

		ContentType contentTypeToBeUpdated = new ContentType();

		try {
			contentTypeToBeUpdated = ContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentType(contentTypeToBeUpdated, contentTypeToBeUpdated.getContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContentType(@RequestBody ContentType contentTypeToBeUpdated,
			@PathVariable String contentTypeId) throws Exception {

		contentTypeToBeUpdated.setContentTypeId(contentTypeId);

		UpdateContentType command = new UpdateContentType(contentTypeToBeUpdated);

		try {
			if(((ContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String contentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentTypeId", contentTypeId);
		try {

			Object foundContentType = findContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentTypeId}")
	public ResponseEntity<Object> deleteContentTypeByIdUpdated(@PathVariable String contentTypeId) throws Exception {
		DeleteContentType command = new DeleteContentType(contentTypeId);

		try {
			if (((ContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentType could not be deleted");

	}

}
