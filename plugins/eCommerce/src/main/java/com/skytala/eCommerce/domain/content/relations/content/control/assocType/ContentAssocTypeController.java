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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/contentAssocTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocTypesBy query = new FindContentAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssocType> contentAssocTypes =((ContentAssocTypeFound) Scheduler.execute(query).data()).getContentAssocTypes();

		if (contentAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(contentAssocTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContentAssocType(HttpServletRequest request) throws Exception {

		ContentAssocType contentAssocTypeToBeAdded = new ContentAssocType();
		try {
			contentAssocTypeToBeAdded = ContentAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContentAssocType(@RequestBody ContentAssocType contentAssocTypeToBeAdded) throws Exception {

		AddContentAssocType command = new AddContentAssocType(contentAssocTypeToBeAdded);
		ContentAssocType contentAssocType = ((ContentAssocTypeAdded) Scheduler.execute(command).data()).getAddedContentAssocType();
		
		if (contentAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentAssocType could not be created.");
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
	public boolean updateContentAssocType(HttpServletRequest request) throws Exception {

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

		ContentAssocType contentAssocTypeToBeUpdated = new ContentAssocType();

		try {
			contentAssocTypeToBeUpdated = ContentAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentAssocType(contentAssocTypeToBeUpdated, contentAssocTypeToBeUpdated.getContentAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContentAssocType(@RequestBody ContentAssocType contentAssocTypeToBeUpdated,
			@PathVariable String contentAssocTypeId) throws Exception {

		contentAssocTypeToBeUpdated.setContentAssocTypeId(contentAssocTypeId);

		UpdateContentAssocType command = new UpdateContentAssocType(contentAssocTypeToBeUpdated);

		try {
			if(((ContentAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String contentAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocTypeId", contentAssocTypeId);
		try {

			Object foundContentAssocType = findContentAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentAssocTypeId}")
	public ResponseEntity<Object> deleteContentAssocTypeByIdUpdated(@PathVariable String contentAssocTypeId) throws Exception {
		DeleteContentAssocType command = new DeleteContentAssocType(contentAssocTypeId);

		try {
			if (((ContentAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentAssocType could not be deleted");

	}

}
