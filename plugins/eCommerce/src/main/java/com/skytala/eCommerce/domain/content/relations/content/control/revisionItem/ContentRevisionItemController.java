package com.skytala.eCommerce.domain.content.relations.content.control.revisionItem;

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
import com.skytala.eCommerce.domain.content.relations.content.command.revisionItem.AddContentRevisionItem;
import com.skytala.eCommerce.domain.content.relations.content.command.revisionItem.DeleteContentRevisionItem;
import com.skytala.eCommerce.domain.content.relations.content.command.revisionItem.UpdateContentRevisionItem;
import com.skytala.eCommerce.domain.content.relations.content.event.revisionItem.ContentRevisionItemAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.revisionItem.ContentRevisionItemDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.revisionItem.ContentRevisionItemFound;
import com.skytala.eCommerce.domain.content.relations.content.event.revisionItem.ContentRevisionItemUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.revisionItem.ContentRevisionItemMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.revisionItem.ContentRevisionItem;
import com.skytala.eCommerce.domain.content.relations.content.query.revisionItem.FindContentRevisionItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentRevisionItems")
public class ContentRevisionItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentRevisionItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentRevisionItem
	 * @return a List with the ContentRevisionItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentRevisionItem>> findContentRevisionItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentRevisionItemsBy query = new FindContentRevisionItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentRevisionItem> contentRevisionItems =((ContentRevisionItemFound) Scheduler.execute(query).data()).getContentRevisionItems();

		return ResponseEntity.ok().body(contentRevisionItems);

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
	public ResponseEntity<ContentRevisionItem> createContentRevisionItem(HttpServletRequest request) throws Exception {

		ContentRevisionItem contentRevisionItemToBeAdded = new ContentRevisionItem();
		try {
			contentRevisionItemToBeAdded = ContentRevisionItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentRevisionItem(contentRevisionItemToBeAdded);

	}

	/**
	 * creates a new ContentRevisionItem entry in the ofbiz database
	 * 
	 * @param contentRevisionItemToBeAdded
	 *            the ContentRevisionItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentRevisionItem> createContentRevisionItem(@RequestBody ContentRevisionItem contentRevisionItemToBeAdded) throws Exception {

		AddContentRevisionItem command = new AddContentRevisionItem(contentRevisionItemToBeAdded);
		ContentRevisionItem contentRevisionItem = ((ContentRevisionItemAdded) Scheduler.execute(command).data()).getAddedContentRevisionItem();
		
		if (contentRevisionItem != null) 
			return successful(contentRevisionItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentRevisionItem with the specific Id
	 * 
	 * @param contentRevisionItemToBeUpdated
	 *            the ContentRevisionItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentRevisionItem(@RequestBody ContentRevisionItem contentRevisionItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentRevisionItemToBeUpdated.setnull(null);

		UpdateContentRevisionItem command = new UpdateContentRevisionItem(contentRevisionItemToBeUpdated);

		try {
			if(((ContentRevisionItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentRevisionItemId}")
	public ResponseEntity<ContentRevisionItem> findById(@PathVariable String contentRevisionItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentRevisionItemId", contentRevisionItemId);
		try {

			List<ContentRevisionItem> foundContentRevisionItem = findContentRevisionItemsBy(requestParams).getBody();
			if(foundContentRevisionItem.size()==1){				return successful(foundContentRevisionItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentRevisionItemId}")
	public ResponseEntity<String> deleteContentRevisionItemByIdUpdated(@PathVariable String contentRevisionItemId) throws Exception {
		DeleteContentRevisionItem command = new DeleteContentRevisionItem(contentRevisionItemId);

		try {
			if (((ContentRevisionItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
