package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig;

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
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.command.AddWebAnalyticsConfig;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.command.DeleteWebAnalyticsConfig;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.command.UpdateWebAnalyticsConfig;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigAdded;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigDeleted;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigFound;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigUpdated;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.mapper.WebAnalyticsConfigMapper;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.query.FindWebAnalyticsConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/webAnalyticsConfigs")
public class WebAnalyticsConfigController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebAnalyticsConfigController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebAnalyticsConfig
	 * @return a List with the WebAnalyticsConfigs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WebAnalyticsConfig>> findWebAnalyticsConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebAnalyticsConfigsBy query = new FindWebAnalyticsConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebAnalyticsConfig> webAnalyticsConfigs =((WebAnalyticsConfigFound) Scheduler.execute(query).data()).getWebAnalyticsConfigs();

		return ResponseEntity.ok().body(webAnalyticsConfigs);

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
	public ResponseEntity<WebAnalyticsConfig> createWebAnalyticsConfig(HttpServletRequest request) throws Exception {

		WebAnalyticsConfig webAnalyticsConfigToBeAdded = new WebAnalyticsConfig();
		try {
			webAnalyticsConfigToBeAdded = WebAnalyticsConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWebAnalyticsConfig(webAnalyticsConfigToBeAdded);

	}

	/**
	 * creates a new WebAnalyticsConfig entry in the ofbiz database
	 * 
	 * @param webAnalyticsConfigToBeAdded
	 *            the WebAnalyticsConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebAnalyticsConfig> createWebAnalyticsConfig(@RequestBody WebAnalyticsConfig webAnalyticsConfigToBeAdded) throws Exception {

		AddWebAnalyticsConfig command = new AddWebAnalyticsConfig(webAnalyticsConfigToBeAdded);
		WebAnalyticsConfig webAnalyticsConfig = ((WebAnalyticsConfigAdded) Scheduler.execute(command).data()).getAddedWebAnalyticsConfig();
		
		if (webAnalyticsConfig != null) 
			return successful(webAnalyticsConfig);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WebAnalyticsConfig with the specific Id
	 * 
	 * @param webAnalyticsConfigToBeUpdated
	 *            the WebAnalyticsConfig thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWebAnalyticsConfig(@RequestBody WebAnalyticsConfig webAnalyticsConfigToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webAnalyticsConfigToBeUpdated.setnull(null);

		UpdateWebAnalyticsConfig command = new UpdateWebAnalyticsConfig(webAnalyticsConfigToBeUpdated);

		try {
			if(((WebAnalyticsConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webAnalyticsConfigId}")
	public ResponseEntity<WebAnalyticsConfig> findById(@PathVariable String webAnalyticsConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webAnalyticsConfigId", webAnalyticsConfigId);
		try {

			List<WebAnalyticsConfig> foundWebAnalyticsConfig = findWebAnalyticsConfigsBy(requestParams).getBody();
			if(foundWebAnalyticsConfig.size()==1){				return successful(foundWebAnalyticsConfig.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webAnalyticsConfigId}")
	public ResponseEntity<String> deleteWebAnalyticsConfigByIdUpdated(@PathVariable String webAnalyticsConfigId) throws Exception {
		DeleteWebAnalyticsConfig command = new DeleteWebAnalyticsConfig(webAnalyticsConfigId);

		try {
			if (((WebAnalyticsConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
