package com.skytala.eCommerce.domain.content;

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
import com.skytala.eCommerce.domain.content.command.AddContent;
import com.skytala.eCommerce.domain.content.command.DeleteContent;
import com.skytala.eCommerce.domain.content.command.UpdateContent;
import com.skytala.eCommerce.domain.content.event.ContentAdded;
import com.skytala.eCommerce.domain.content.event.ContentDeleted;
import com.skytala.eCommerce.domain.content.event.ContentFound;
import com.skytala.eCommerce.domain.content.event.ContentUpdated;
import com.skytala.eCommerce.domain.content.mapper.ContentMapper;
import com.skytala.eCommerce.domain.content.model.Content;
import com.skytala.eCommerce.domain.content.query.FindContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/contents")
public class ContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Content
	 * @return a List with the Contents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Content>> findContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentsBy query = new FindContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Content> contents =((ContentFound) Scheduler.execute(query).data()).getContents();

		return ResponseEntity.ok().body(contents);

	}

	/**
	 * creates a new Content entry in the ofbiz database
	 * 
	 * @param contentToBeAdded
	 *            the Content thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Content> createContent(@RequestBody Content contentToBeAdded) throws Exception {

		AddContent command = new AddContent(contentToBeAdded);
		Content content = ((ContentAdded) Scheduler.execute(command).data()).getAddedContent();
		
		if (content != null) 
			return successful(content);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Content with the specific Id
	 * 
	 * @param contentToBeUpdated
	 *            the Content thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContent(@RequestBody Content contentToBeUpdated,
			@PathVariable String contentId) throws Exception {

		contentToBeUpdated.setContentId(contentId);

		UpdateContent command = new UpdateContent(contentToBeUpdated);

		try {
			if(((ContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentId}")
	public ResponseEntity<Content> findById(@PathVariable String contentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentId", contentId);
		try {

			List<Content> foundContent = findContentsBy(requestParams).getBody();
			if(foundContent.size()==1){				return successful(foundContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentId}")
	public ResponseEntity<String> deleteContentByIdUpdated(@PathVariable String contentId) throws Exception {
		DeleteContent command = new DeleteContent(contentId);

		try {
			if (((ContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
