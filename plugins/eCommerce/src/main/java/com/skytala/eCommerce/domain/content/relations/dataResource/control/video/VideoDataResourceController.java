package com.skytala.eCommerce.domain.content.relations.dataResource.control.video;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.video.AddVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.video.DeleteVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.video.UpdateVideoDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.video.VideoDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.video.VideoDataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.video.VideoDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.video.VideoDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.video.VideoDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.video.VideoDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.video.FindVideoDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/videoDataResources")
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
	@GetMapping("/find")
	public ResponseEntity<List<VideoDataResource>> findVideoDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVideoDataResourcesBy query = new FindVideoDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VideoDataResource> videoDataResources =((VideoDataResourceFound) Scheduler.execute(query).data()).getVideoDataResources();

		return ResponseEntity.ok().body(videoDataResources);

	}

	/**
	 * creates a new VideoDataResource entry in the ofbiz database
	 * 
	 * @param videoDataResourceToBeAdded
	 *            the VideoDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<VideoDataResource> createVideoDataResource(@RequestBody VideoDataResource videoDataResourceToBeAdded) throws Exception {

		AddVideoDataResource command = new AddVideoDataResource(videoDataResourceToBeAdded);
		VideoDataResource videoDataResource = ((VideoDataResourceAdded) Scheduler.execute(command).data()).getAddedVideoDataResource();
		
		if (videoDataResource != null) 
			return successful(videoDataResource);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateVideoDataResource(@RequestBody VideoDataResource videoDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		videoDataResourceToBeUpdated.setnull(null);

		UpdateVideoDataResource command = new UpdateVideoDataResource(videoDataResourceToBeUpdated);

		try {
			if(((VideoDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{videoDataResourceId}")
	public ResponseEntity<VideoDataResource> findById(@PathVariable String videoDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("videoDataResourceId", videoDataResourceId);
		try {

			List<VideoDataResource> foundVideoDataResource = findVideoDataResourcesBy(requestParams).getBody();
			if(foundVideoDataResource.size()==1){				return successful(foundVideoDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{videoDataResourceId}")
	public ResponseEntity<String> deleteVideoDataResourceByIdUpdated(@PathVariable String videoDataResourceId) throws Exception {
		DeleteVideoDataResource command = new DeleteVideoDataResource(videoDataResourceId);

		try {
			if (((VideoDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
