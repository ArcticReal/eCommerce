package com.skytala.eCommerce.domain.content.relations.webPreferenceType;

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
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.AddWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.DeleteWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.command.UpdateWebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeAdded;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeFound;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.mapper.WebPreferenceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.query.FindWebPreferenceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/webPreferenceTypes")
public class WebPreferenceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebPreferenceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebPreferenceType
	 * @return a List with the WebPreferenceTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WebPreferenceType>> findWebPreferenceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebPreferenceTypesBy query = new FindWebPreferenceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebPreferenceType> webPreferenceTypes =((WebPreferenceTypeFound) Scheduler.execute(query).data()).getWebPreferenceTypes();

		return ResponseEntity.ok().body(webPreferenceTypes);

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
	public ResponseEntity<WebPreferenceType> createWebPreferenceType(HttpServletRequest request) throws Exception {

		WebPreferenceType webPreferenceTypeToBeAdded = new WebPreferenceType();
		try {
			webPreferenceTypeToBeAdded = WebPreferenceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWebPreferenceType(webPreferenceTypeToBeAdded);

	}

	/**
	 * creates a new WebPreferenceType entry in the ofbiz database
	 * 
	 * @param webPreferenceTypeToBeAdded
	 *            the WebPreferenceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebPreferenceType> createWebPreferenceType(@RequestBody WebPreferenceType webPreferenceTypeToBeAdded) throws Exception {

		AddWebPreferenceType command = new AddWebPreferenceType(webPreferenceTypeToBeAdded);
		WebPreferenceType webPreferenceType = ((WebPreferenceTypeAdded) Scheduler.execute(command).data()).getAddedWebPreferenceType();
		
		if (webPreferenceType != null) 
			return successful(webPreferenceType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WebPreferenceType with the specific Id
	 * 
	 * @param webPreferenceTypeToBeUpdated
	 *            the WebPreferenceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{webPreferenceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWebPreferenceType(@RequestBody WebPreferenceType webPreferenceTypeToBeUpdated,
			@PathVariable String webPreferenceTypeId) throws Exception {

		webPreferenceTypeToBeUpdated.setWebPreferenceTypeId(webPreferenceTypeId);

		UpdateWebPreferenceType command = new UpdateWebPreferenceType(webPreferenceTypeToBeUpdated);

		try {
			if(((WebPreferenceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webPreferenceTypeId}")
	public ResponseEntity<WebPreferenceType> findById(@PathVariable String webPreferenceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webPreferenceTypeId", webPreferenceTypeId);
		try {

			List<WebPreferenceType> foundWebPreferenceType = findWebPreferenceTypesBy(requestParams).getBody();
			if(foundWebPreferenceType.size()==1){				return successful(foundWebPreferenceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webPreferenceTypeId}")
	public ResponseEntity<String> deleteWebPreferenceTypeByIdUpdated(@PathVariable String webPreferenceTypeId) throws Exception {
		DeleteWebPreferenceType command = new DeleteWebPreferenceType(webPreferenceTypeId);

		try {
			if (((WebPreferenceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
