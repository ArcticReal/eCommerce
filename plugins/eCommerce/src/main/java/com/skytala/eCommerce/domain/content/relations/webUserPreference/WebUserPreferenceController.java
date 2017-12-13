package com.skytala.eCommerce.domain.content.relations.webUserPreference;

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
import com.skytala.eCommerce.domain.content.relations.webUserPreference.command.AddWebUserPreference;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.command.DeleteWebUserPreference;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.command.UpdateWebUserPreference;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceAdded;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceDeleted;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceFound;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceUpdated;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.mapper.WebUserPreferenceMapper;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.query.FindWebUserPreferencesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/webUserPreferences")
public class WebUserPreferenceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebUserPreferenceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebUserPreference
	 * @return a List with the WebUserPreferences
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWebUserPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebUserPreferencesBy query = new FindWebUserPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebUserPreference> webUserPreferences =((WebUserPreferenceFound) Scheduler.execute(query).data()).getWebUserPreferences();

		if (webUserPreferences.size() == 1) {
			return ResponseEntity.ok().body(webUserPreferences.get(0));
		}

		return ResponseEntity.ok().body(webUserPreferences);

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
	public ResponseEntity<Object> createWebUserPreference(HttpServletRequest request) throws Exception {

		WebUserPreference webUserPreferenceToBeAdded = new WebUserPreference();
		try {
			webUserPreferenceToBeAdded = WebUserPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebUserPreference(webUserPreferenceToBeAdded);

	}

	/**
	 * creates a new WebUserPreference entry in the ofbiz database
	 * 
	 * @param webUserPreferenceToBeAdded
	 *            the WebUserPreference thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebUserPreference(@RequestBody WebUserPreference webUserPreferenceToBeAdded) throws Exception {

		AddWebUserPreference command = new AddWebUserPreference(webUserPreferenceToBeAdded);
		WebUserPreference webUserPreference = ((WebUserPreferenceAdded) Scheduler.execute(command).data()).getAddedWebUserPreference();
		
		if (webUserPreference != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webUserPreference);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebUserPreference could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateWebUserPreference(HttpServletRequest request) throws Exception {

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

		WebUserPreference webUserPreferenceToBeUpdated = new WebUserPreference();

		try {
			webUserPreferenceToBeUpdated = WebUserPreferenceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebUserPreference(webUserPreferenceToBeUpdated, webUserPreferenceToBeUpdated.getVisitId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebUserPreference with the specific Id
	 * 
	 * @param webUserPreferenceToBeUpdated
	 *            the WebUserPreference thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{visitId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebUserPreference(@RequestBody WebUserPreference webUserPreferenceToBeUpdated,
			@PathVariable String visitId) throws Exception {

		webUserPreferenceToBeUpdated.setVisitId(visitId);

		UpdateWebUserPreference command = new UpdateWebUserPreference(webUserPreferenceToBeUpdated);

		try {
			if(((WebUserPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{webUserPreferenceId}")
	public ResponseEntity<Object> findById(@PathVariable String webUserPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webUserPreferenceId", webUserPreferenceId);
		try {

			Object foundWebUserPreference = findWebUserPreferencesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebUserPreference);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{webUserPreferenceId}")
	public ResponseEntity<Object> deleteWebUserPreferenceByIdUpdated(@PathVariable String webUserPreferenceId) throws Exception {
		DeleteWebUserPreference command = new DeleteWebUserPreference(webUserPreferenceId);

		try {
			if (((WebUserPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebUserPreference could not be deleted");

	}

}
