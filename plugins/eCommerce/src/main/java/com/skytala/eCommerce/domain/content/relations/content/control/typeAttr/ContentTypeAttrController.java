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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<ContentTypeAttr>> findContentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentTypeAttrsBy query = new FindContentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentTypeAttr> contentTypeAttrs =((ContentTypeAttrFound) Scheduler.execute(query).data()).getContentTypeAttrs();

		return ResponseEntity.ok().body(contentTypeAttrs);

	}

	/**
	 * creates a new ContentTypeAttr entry in the ofbiz database
	 * 
	 * @param contentTypeAttrToBeAdded
	 *            the ContentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentTypeAttr> createContentTypeAttr(@RequestBody ContentTypeAttr contentTypeAttrToBeAdded) throws Exception {

		AddContentTypeAttr command = new AddContentTypeAttr(contentTypeAttrToBeAdded);
		ContentTypeAttr contentTypeAttr = ((ContentTypeAttrAdded) Scheduler.execute(command).data()).getAddedContentTypeAttr();
		
		if (contentTypeAttr != null) 
			return successful(contentTypeAttr);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentTypeAttr(@RequestBody ContentTypeAttr contentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		contentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateContentTypeAttr command = new UpdateContentTypeAttr(contentTypeAttrToBeUpdated);

		try {
			if(((ContentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentTypeAttrId}")
	public ResponseEntity<ContentTypeAttr> findById(@PathVariable String contentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentTypeAttrId", contentTypeAttrId);
		try {

			List<ContentTypeAttr> foundContentTypeAttr = findContentTypeAttrsBy(requestParams).getBody();
			if(foundContentTypeAttr.size()==1){				return successful(foundContentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentTypeAttrId}")
	public ResponseEntity<String> deleteContentTypeAttrByIdUpdated(@PathVariable String contentTypeAttrId) throws Exception {
		DeleteContentTypeAttr command = new DeleteContentTypeAttr(contentTypeAttrId);

		try {
			if (((ContentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
