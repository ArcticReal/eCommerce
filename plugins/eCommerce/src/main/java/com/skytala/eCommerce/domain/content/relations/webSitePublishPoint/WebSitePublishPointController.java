package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.command.AddWebSitePublishPoint;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.command.DeleteWebSitePublishPoint;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.command.UpdateWebSitePublishPoint;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointAdded;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointDeleted;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointFound;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointUpdated;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.mapper.WebSitePublishPointMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.query.FindWebSitePublishPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/webSitePublishPoints")
public class WebSitePublishPointController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSitePublishPointController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSitePublishPoint
	 * @return a List with the WebSitePublishPoints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWebSitePublishPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSitePublishPointsBy query = new FindWebSitePublishPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSitePublishPoint> webSitePublishPoints =((WebSitePublishPointFound) Scheduler.execute(query).data()).getWebSitePublishPoints();

		if (webSitePublishPoints.size() == 1) {
			return ResponseEntity.ok().body(webSitePublishPoints.get(0));
		}

		return ResponseEntity.ok().body(webSitePublishPoints);

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
	public ResponseEntity<Object> createWebSitePublishPoint(HttpServletRequest request) throws Exception {

		WebSitePublishPoint webSitePublishPointToBeAdded = new WebSitePublishPoint();
		try {
			webSitePublishPointToBeAdded = WebSitePublishPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebSitePublishPoint(webSitePublishPointToBeAdded);

	}

	/**
	 * creates a new WebSitePublishPoint entry in the ofbiz database
	 * 
	 * @param webSitePublishPointToBeAdded
	 *            the WebSitePublishPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebSitePublishPoint(@RequestBody WebSitePublishPoint webSitePublishPointToBeAdded) throws Exception {

		AddWebSitePublishPoint command = new AddWebSitePublishPoint(webSitePublishPointToBeAdded);
		WebSitePublishPoint webSitePublishPoint = ((WebSitePublishPointAdded) Scheduler.execute(command).data()).getAddedWebSitePublishPoint();
		
		if (webSitePublishPoint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webSitePublishPoint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebSitePublishPoint could not be created.");
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
	public boolean updateWebSitePublishPoint(HttpServletRequest request) throws Exception {

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

		WebSitePublishPoint webSitePublishPointToBeUpdated = new WebSitePublishPoint();

		try {
			webSitePublishPointToBeUpdated = WebSitePublishPointMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebSitePublishPoint(webSitePublishPointToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebSitePublishPoint with the specific Id
	 * 
	 * @param webSitePublishPointToBeUpdated
	 *            the WebSitePublishPoint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebSitePublishPoint(@RequestBody WebSitePublishPoint webSitePublishPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webSitePublishPointToBeUpdated.setnull(null);

		UpdateWebSitePublishPoint command = new UpdateWebSitePublishPoint(webSitePublishPointToBeUpdated);

		try {
			if(((WebSitePublishPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{webSitePublishPointId}")
	public ResponseEntity<Object> findById(@PathVariable String webSitePublishPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSitePublishPointId", webSitePublishPointId);
		try {

			Object foundWebSitePublishPoint = findWebSitePublishPointsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebSitePublishPoint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{webSitePublishPointId}")
	public ResponseEntity<Object> deleteWebSitePublishPointByIdUpdated(@PathVariable String webSitePublishPointId) throws Exception {
		DeleteWebSitePublishPoint command = new DeleteWebSitePublishPoint(webSitePublishPointId);

		try {
			if (((WebSitePublishPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebSitePublishPoint could not be deleted");

	}

}
