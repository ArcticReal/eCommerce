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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WebUserPreference>> findWebUserPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebUserPreferencesBy query = new FindWebUserPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebUserPreference> webUserPreferences =((WebUserPreferenceFound) Scheduler.execute(query).data()).getWebUserPreferences();

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
	public ResponseEntity<WebUserPreference> createWebUserPreference(HttpServletRequest request) throws Exception {

		WebUserPreference webUserPreferenceToBeAdded = new WebUserPreference();
		try {
			webUserPreferenceToBeAdded = WebUserPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WebUserPreference> createWebUserPreference(@RequestBody WebUserPreference webUserPreferenceToBeAdded) throws Exception {

		AddWebUserPreference command = new AddWebUserPreference(webUserPreferenceToBeAdded);
		WebUserPreference webUserPreference = ((WebUserPreferenceAdded) Scheduler.execute(command).data()).getAddedWebUserPreference();
		
		if (webUserPreference != null) 
			return successful(webUserPreference);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWebUserPreference(@RequestBody WebUserPreference webUserPreferenceToBeUpdated,
			@PathVariable String visitId) throws Exception {

		webUserPreferenceToBeUpdated.setVisitId(visitId);

		UpdateWebUserPreference command = new UpdateWebUserPreference(webUserPreferenceToBeUpdated);

		try {
			if(((WebUserPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webUserPreferenceId}")
	public ResponseEntity<WebUserPreference> findById(@PathVariable String webUserPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webUserPreferenceId", webUserPreferenceId);
		try {

			List<WebUserPreference> foundWebUserPreference = findWebUserPreferencesBy(requestParams).getBody();
			if(foundWebUserPreference.size()==1){				return successful(foundWebUserPreference.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webUserPreferenceId}")
	public ResponseEntity<String> deleteWebUserPreferenceByIdUpdated(@PathVariable String webUserPreferenceId) throws Exception {
		DeleteWebUserPreference command = new DeleteWebUserPreference(webUserPreferenceId);

		try {
			if (((WebUserPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
