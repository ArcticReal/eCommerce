package com.skytala.eCommerce.domain.content.relations.content.control.webSiteType;

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
import com.skytala.eCommerce.domain.content.relations.content.command.webSiteType.AddWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.content.command.webSiteType.DeleteWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.content.command.webSiteType.UpdateWebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.content.event.webSiteType.WebSiteContentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.webSiteType.WebSiteContentTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.webSiteType.WebSiteContentTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.event.webSiteType.WebSiteContentTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.webSiteType.WebSiteContentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.webSiteType.WebSiteContentType;
import com.skytala.eCommerce.domain.content.relations.content.query.webSiteType.FindWebSiteContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/webSiteContentTypes")
public class WebSiteContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSiteContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSiteContentType
	 * @return a List with the WebSiteContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WebSiteContentType>> findWebSiteContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteContentTypesBy query = new FindWebSiteContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteContentType> webSiteContentTypes =((WebSiteContentTypeFound) Scheduler.execute(query).data()).getWebSiteContentTypes();

		return ResponseEntity.ok().body(webSiteContentTypes);

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
	public ResponseEntity<WebSiteContentType> createWebSiteContentType(HttpServletRequest request) throws Exception {

		WebSiteContentType webSiteContentTypeToBeAdded = new WebSiteContentType();
		try {
			webSiteContentTypeToBeAdded = WebSiteContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWebSiteContentType(webSiteContentTypeToBeAdded);

	}

	/**
	 * creates a new WebSiteContentType entry in the ofbiz database
	 * 
	 * @param webSiteContentTypeToBeAdded
	 *            the WebSiteContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebSiteContentType> createWebSiteContentType(@RequestBody WebSiteContentType webSiteContentTypeToBeAdded) throws Exception {

		AddWebSiteContentType command = new AddWebSiteContentType(webSiteContentTypeToBeAdded);
		WebSiteContentType webSiteContentType = ((WebSiteContentTypeAdded) Scheduler.execute(command).data()).getAddedWebSiteContentType();
		
		if (webSiteContentType != null) 
			return successful(webSiteContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WebSiteContentType with the specific Id
	 * 
	 * @param webSiteContentTypeToBeUpdated
	 *            the WebSiteContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{webSiteContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWebSiteContentType(@RequestBody WebSiteContentType webSiteContentTypeToBeUpdated,
			@PathVariable String webSiteContentTypeId) throws Exception {

		webSiteContentTypeToBeUpdated.setWebSiteContentTypeId(webSiteContentTypeId);

		UpdateWebSiteContentType command = new UpdateWebSiteContentType(webSiteContentTypeToBeUpdated);

		try {
			if(((WebSiteContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSiteContentTypeId}")
	public ResponseEntity<WebSiteContentType> findById(@PathVariable String webSiteContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteContentTypeId", webSiteContentTypeId);
		try {

			List<WebSiteContentType> foundWebSiteContentType = findWebSiteContentTypesBy(requestParams).getBody();
			if(foundWebSiteContentType.size()==1){				return successful(foundWebSiteContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSiteContentTypeId}")
	public ResponseEntity<String> deleteWebSiteContentTypeByIdUpdated(@PathVariable String webSiteContentTypeId) throws Exception {
		DeleteWebSiteContentType command = new DeleteWebSiteContentType(webSiteContentTypeId);

		try {
			if (((WebSiteContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
