package com.skytala.eCommerce.domain.content.relations.webAnalyticsType;

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
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.command.AddWebAnalyticsType;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.command.DeleteWebAnalyticsType;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.command.UpdateWebAnalyticsType;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event.WebAnalyticsTypeAdded;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event.WebAnalyticsTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event.WebAnalyticsTypeFound;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event.WebAnalyticsTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.mapper.WebAnalyticsTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.query.FindWebAnalyticsTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/webAnalyticsTypes")
public class WebAnalyticsTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebAnalyticsTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebAnalyticsType
	 * @return a List with the WebAnalyticsTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WebAnalyticsType>> findWebAnalyticsTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebAnalyticsTypesBy query = new FindWebAnalyticsTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebAnalyticsType> webAnalyticsTypes =((WebAnalyticsTypeFound) Scheduler.execute(query).data()).getWebAnalyticsTypes();

		return ResponseEntity.ok().body(webAnalyticsTypes);

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
	public ResponseEntity<WebAnalyticsType> createWebAnalyticsType(HttpServletRequest request) throws Exception {

		WebAnalyticsType webAnalyticsTypeToBeAdded = new WebAnalyticsType();
		try {
			webAnalyticsTypeToBeAdded = WebAnalyticsTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWebAnalyticsType(webAnalyticsTypeToBeAdded);

	}

	/**
	 * creates a new WebAnalyticsType entry in the ofbiz database
	 * 
	 * @param webAnalyticsTypeToBeAdded
	 *            the WebAnalyticsType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebAnalyticsType> createWebAnalyticsType(@RequestBody WebAnalyticsType webAnalyticsTypeToBeAdded) throws Exception {

		AddWebAnalyticsType command = new AddWebAnalyticsType(webAnalyticsTypeToBeAdded);
		WebAnalyticsType webAnalyticsType = ((WebAnalyticsTypeAdded) Scheduler.execute(command).data()).getAddedWebAnalyticsType();
		
		if (webAnalyticsType != null) 
			return successful(webAnalyticsType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WebAnalyticsType with the specific Id
	 * 
	 * @param webAnalyticsTypeToBeUpdated
	 *            the WebAnalyticsType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{webAnalyticsTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWebAnalyticsType(@RequestBody WebAnalyticsType webAnalyticsTypeToBeUpdated,
			@PathVariable String webAnalyticsTypeId) throws Exception {

		webAnalyticsTypeToBeUpdated.setWebAnalyticsTypeId(webAnalyticsTypeId);

		UpdateWebAnalyticsType command = new UpdateWebAnalyticsType(webAnalyticsTypeToBeUpdated);

		try {
			if(((WebAnalyticsTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webAnalyticsTypeId}")
	public ResponseEntity<WebAnalyticsType> findById(@PathVariable String webAnalyticsTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webAnalyticsTypeId", webAnalyticsTypeId);
		try {

			List<WebAnalyticsType> foundWebAnalyticsType = findWebAnalyticsTypesBy(requestParams).getBody();
			if(foundWebAnalyticsType.size()==1){				return successful(foundWebAnalyticsType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webAnalyticsTypeId}")
	public ResponseEntity<String> deleteWebAnalyticsTypeByIdUpdated(@PathVariable String webAnalyticsTypeId) throws Exception {
		DeleteWebAnalyticsType command = new DeleteWebAnalyticsType(webAnalyticsTypeId);

		try {
			if (((WebAnalyticsTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
