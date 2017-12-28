package com.skytala.eCommerce.domain.content.relations.content.control.attribute;

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
import com.skytala.eCommerce.domain.content.relations.content.command.attribute.AddContentAttribute;
import com.skytala.eCommerce.domain.content.relations.content.command.attribute.DeleteContentAttribute;
import com.skytala.eCommerce.domain.content.relations.content.command.attribute.UpdateContentAttribute;
import com.skytala.eCommerce.domain.content.relations.content.event.attribute.ContentAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.attribute.ContentAttributeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.attribute.ContentAttributeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.attribute.ContentAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.attribute.ContentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.attribute.ContentAttribute;
import com.skytala.eCommerce.domain.content.relations.content.query.attribute.FindContentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentAttributes")
public class ContentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentAttribute
	 * @return a List with the ContentAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentAttribute>> findContentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAttributesBy query = new FindContentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAttribute> contentAttributes =((ContentAttributeFound) Scheduler.execute(query).data()).getContentAttributes();

		return ResponseEntity.ok().body(contentAttributes);

	}

	/**
	 * creates a new ContentAttribute entry in the ofbiz database
	 * 
	 * @param contentAttributeToBeAdded
	 *            the ContentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentAttribute> createContentAttribute(@RequestBody ContentAttribute contentAttributeToBeAdded) throws Exception {

		AddContentAttribute command = new AddContentAttribute(contentAttributeToBeAdded);
		ContentAttribute contentAttribute = ((ContentAttributeAdded) Scheduler.execute(command).data()).getAddedContentAttribute();
		
		if (contentAttribute != null) 
			return successful(contentAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentAttribute with the specific Id
	 * 
	 * @param contentAttributeToBeUpdated
	 *            the ContentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentAttribute(@RequestBody ContentAttribute contentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		contentAttributeToBeUpdated.setAttrName(attrName);

		UpdateContentAttribute command = new UpdateContentAttribute(contentAttributeToBeUpdated);

		try {
			if(((ContentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentAttributeId}")
	public ResponseEntity<ContentAttribute> findById(@PathVariable String contentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAttributeId", contentAttributeId);
		try {

			List<ContentAttribute> foundContentAttribute = findContentAttributesBy(requestParams).getBody();
			if(foundContentAttribute.size()==1){				return successful(foundContentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentAttributeId}")
	public ResponseEntity<String> deleteContentAttributeByIdUpdated(@PathVariable String contentAttributeId) throws Exception {
		DeleteContentAttribute command = new DeleteContentAttribute(contentAttributeId);

		try {
			if (((ContentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
