package com.skytala.eCommerce.domain.content.relations.videoDataResource;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.command.AddVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.command.DeleteVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.command.UpdateVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.mapper.VideoDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.query.FindVideoDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/videoDataResources")
public class VideoDataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VideoDataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VideoDataResource
	 * @return a List with the VideoDataResources
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findVideoDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVideoDataResourcesBy query = new FindVideoDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VideoDataResource> videoDataResources =((VideoDataResourceFound) Scheduler.execute(query).data()).getVideoDataResources();

		if (videoDataResources.size() == 1) {
			return ResponseEntity.ok().body(videoDataResources.get(0));
		}

		return ResponseEntity.ok().body(videoDataResources);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createVideoDataResource(HttpServletRequest request) throws Exception {

		VideoDataResource videoDataResourceToBeAdded = new VideoDataResource();
		try {
			videoDataResourceToBeAdded = VideoDataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createVideoDataResource(videoDataResourceToBeAdded);

	}

	/**
	 * creates a new VideoDataResource entry in the ofbiz database
	 * 
	 * @param videoDataResourceToBeAdded
	 *            the VideoDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createVideoDataResource(@RequestBody VideoDataResource videoDataResourceToBeAdded) throws Exception {

		AddVideoDataResource command = new AddVideoDataResource(videoDataResourceToBeAdded);
		VideoDataResource videoDataResource = ((VideoDataResourceAdded) Scheduler.execute(command).data()).getAddedVideoDataResource();
		
		if (videoDataResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(videoDataResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("VideoDataResource could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateVideoDataResource(HttpServletRequest request) throws Exception {

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

		VideoDataResource videoDataResourceToBeUpdated = new VideoDataResource();

		try {
			videoDataResourceToBeUpdated = VideoDataResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateVideoDataResource(videoDataResourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the VideoDataResource with the specific Id
	 * 
	 * @param videoDataResourceToBeUpdated
	 *            the VideoDataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateVideoDataResource(@RequestBody VideoDataResource videoDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		videoDataResourceToBeUpdated.setnull(null);

		UpdateVideoDataResource command = new UpdateVideoDataResource(videoDataResourceToBeUpdated);

		try {
			if(((VideoDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{videoDataResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String videoDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("videoDataResourceId", videoDataResourceId);
		try {

			Object foundVideoDataResource = findVideoDataResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundVideoDataResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{videoDataResourceId}")
	public ResponseEntity<Object> deleteVideoDataResourceByIdUpdated(@PathVariable String videoDataResourceId) throws Exception {
		DeleteVideoDataResource command = new DeleteVideoDataResource(videoDataResourceId);

		try {
			if (((VideoDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("VideoDataResource could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/videoDataResource/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}