package com.skytala.eCommerce.domain.content.relations.content.control.webSite;

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
import com.skytala.eCommerce.domain.content.relations.content.command.webSite.AddWebSiteContent;
import com.skytala.eCommerce.domain.content.relations.content.command.webSite.DeleteWebSiteContent;
import com.skytala.eCommerce.domain.content.relations.content.command.webSite.UpdateWebSiteContent;
import com.skytala.eCommerce.domain.content.relations.content.event.webSite.WebSiteContentAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.webSite.WebSiteContentDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.webSite.WebSiteContentFound;
import com.skytala.eCommerce.domain.content.relations.content.event.webSite.WebSiteContentUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.webSite.WebSiteContentMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.webSite.WebSiteContent;
import com.skytala.eCommerce.domain.content.relations.content.query.webSite.FindWebSiteContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/webSiteContents")
public class WebSiteContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSiteContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSiteContent
	 * @return a List with the WebSiteContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WebSiteContent>> findWebSiteContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteContentsBy query = new FindWebSiteContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteContent> webSiteContents =((WebSiteContentFound) Scheduler.execute(query).data()).getWebSiteContents();

		return ResponseEntity.ok().body(webSiteContents);

	}

	/**
	 * creates a new WebSiteContent entry in the ofbiz database
	 * 
	 * @param webSiteContentToBeAdded
	 *            the WebSiteContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebSiteContent> createWebSiteContent(@RequestBody WebSiteContent webSiteContentToBeAdded) throws Exception {

		AddWebSiteContent command = new AddWebSiteContent(webSiteContentToBeAdded);
		WebSiteContent webSiteContent = ((WebSiteContentAdded) Scheduler.execute(command).data()).getAddedWebSiteContent();
		
		if (webSiteContent != null) 
			return successful(webSiteContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WebSiteContent with the specific Id
	 * 
	 * @param webSiteContentToBeUpdated
	 *            the WebSiteContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWebSiteContent(@RequestBody WebSiteContent webSiteContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webSiteContentToBeUpdated.setnull(null);

		UpdateWebSiteContent command = new UpdateWebSiteContent(webSiteContentToBeUpdated);

		try {
			if(((WebSiteContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSiteContentId}")
	public ResponseEntity<WebSiteContent> findById(@PathVariable String webSiteContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteContentId", webSiteContentId);
		try {

			List<WebSiteContent> foundWebSiteContent = findWebSiteContentsBy(requestParams).getBody();
			if(foundWebSiteContent.size()==1){				return successful(foundWebSiteContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSiteContentId}")
	public ResponseEntity<String> deleteWebSiteContentByIdUpdated(@PathVariable String webSiteContentId) throws Exception {
		DeleteWebSiteContent command = new DeleteWebSiteContent(webSiteContentId);

		try {
			if (((WebSiteContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
