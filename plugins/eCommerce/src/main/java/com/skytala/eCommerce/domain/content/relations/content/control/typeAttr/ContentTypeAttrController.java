package com.skytala.eCommerce.domain.content.relations.content.control.typeAttr;

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
import com.skytala.eCommerce.domain.content.relations.content.command.typeAttr.AddContentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.content.command.typeAttr.DeleteContentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.content.command.typeAttr.UpdateContentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.content.event.typeAttr.ContentTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.typeAttr.ContentTypeAttrDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.typeAttr.ContentTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.content.event.typeAttr.ContentTypeAttrUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.typeAttr.ContentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.typeAttr.ContentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.content.query.typeAttr.FindContentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/content/contentTypeAttrs")
public class ContentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentTypeAttr
	 * @return a List with the ContentTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentTypeAttrsBy query = new FindContentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentTypeAttr> contentTypeAttrs =((ContentTypeAttrFound) Scheduler.execute(query).data()).getContentTypeAttrs();

		if (contentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(contentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(contentTypeAttrs);

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
	public ResponseEntity<Object> createContentTypeAttr(HttpServletRequest request) throws Exception {

		ContentTypeAttr contentTypeAttrToBeAdded = new ContentTypeAttr();
		try {
			contentTypeAttrToBeAdded = ContentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentTypeAttr(contentTypeAttrToBeAdded);

	}

	/**
	 * creates a new ContentTypeAttr entry in the ofbiz database
	 * 
	 * @param contentTypeAttrToBeAdded
	 *            the ContentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentTypeAttr(@RequestBody ContentTypeAttr contentTypeAttrToBeAdded) throws Exception {

		AddContentTypeAttr command = new AddContentTypeAttr(contentTypeAttrToBeAdded);
		ContentTypeAttr contentTypeAttr = ((ContentTypeAttrAdded) Scheduler.execute(command).data()).getAddedContentTypeAttr();
		
		if (contentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentTypeAttr could not be created.");
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
	public boolean updateContentTypeAttr(HttpServletRequest request) throws Exception {

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

		ContentTypeAttr contentTypeAttrToBeUpdated = new ContentTypeAttr();

		try {
			contentTypeAttrToBeUpdated = ContentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentTypeAttr(contentTypeAttrToBeUpdated, contentTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentTypeAttr with the specific Id
	 * 
	 * @param contentTypeAttrToBeUpdated
	 *            the ContentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentTypeAttr(@RequestBody ContentTypeAttr contentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		contentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateContentTypeAttr command = new UpdateContentTypeAttr(contentTypeAttrToBeUpdated);

		try {
			if(((ContentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String contentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentTypeAttrId", contentTypeAttrId);
		try {

			Object foundContentTypeAttr = findContentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentTypeAttrId}")
	public ResponseEntity<Object> deleteContentTypeAttrByIdUpdated(@PathVariable String contentTypeAttrId) throws Exception {
		DeleteContentTypeAttr command = new DeleteContentTypeAttr(contentTypeAttrId);

		try {
			if (((ContentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentTypeAttr could not be deleted");

	}

}
