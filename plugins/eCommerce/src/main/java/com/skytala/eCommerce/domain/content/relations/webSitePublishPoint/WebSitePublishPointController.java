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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WebSitePublishPoint>> findWebSitePublishPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSitePublishPointsBy query = new FindWebSitePublishPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSitePublishPoint> webSitePublishPoints =((WebSitePublishPointFound) Scheduler.execute(query).data()).getWebSitePublishPoints();

		return ResponseEntity.ok().body(webSitePublishPoints);

	}

	/**
	 * creates a new WebSitePublishPoint entry in the ofbiz database
	 * 
	 * @param webSitePublishPointToBeAdded
	 *            the WebSitePublishPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WebSitePublishPoint> createWebSitePublishPoint(@RequestBody WebSitePublishPoint webSitePublishPointToBeAdded) throws Exception {

		AddWebSitePublishPoint command = new AddWebSitePublishPoint(webSitePublishPointToBeAdded);
		WebSitePublishPoint webSitePublishPoint = ((WebSitePublishPointAdded) Scheduler.execute(command).data()).getAddedWebSitePublishPoint();
		
		if (webSitePublishPoint != null) 
			return successful(webSitePublishPoint);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWebSitePublishPoint(@RequestBody WebSitePublishPoint webSitePublishPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webSitePublishPointToBeUpdated.setnull(null);

		UpdateWebSitePublishPoint command = new UpdateWebSitePublishPoint(webSitePublishPointToBeUpdated);

		try {
			if(((WebSitePublishPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSitePublishPointId}")
	public ResponseEntity<WebSitePublishPoint> findById(@PathVariable String webSitePublishPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSitePublishPointId", webSitePublishPointId);
		try {

			List<WebSitePublishPoint> foundWebSitePublishPoint = findWebSitePublishPointsBy(requestParams).getBody();
			if(foundWebSitePublishPoint.size()==1){				return successful(foundWebSitePublishPoint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSitePublishPointId}")
	public ResponseEntity<String> deleteWebSitePublishPointByIdUpdated(@PathVariable String webSitePublishPointId) throws Exception {
		DeleteWebSitePublishPoint command = new DeleteWebSitePublishPoint(webSitePublishPointId);

		try {
			if (((WebSitePublishPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
