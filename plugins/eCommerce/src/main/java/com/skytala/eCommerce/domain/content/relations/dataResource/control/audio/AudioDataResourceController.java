package com.skytala.eCommerce.domain.content.relations.dataResource.control.audio;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.audio.AddAudioDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.audio.DeleteAudioDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.audio.UpdateAudioDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.audio.AudioDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.audio.FindAudioDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/audioDataResources")
public class AudioDataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AudioDataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AudioDataResource
	 * @return a List with the AudioDataResources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AudioDataResource>> findAudioDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAudioDataResourcesBy query = new FindAudioDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AudioDataResource> audioDataResources =((AudioDataResourceFound) Scheduler.execute(query).data()).getAudioDataResources();

		return ResponseEntity.ok().body(audioDataResources);

	}

	/**
	 * creates a new AudioDataResource entry in the ofbiz database
	 * 
	 * @param audioDataResourceToBeAdded
	 *            the AudioDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AudioDataResource> createAudioDataResource(@RequestBody AudioDataResource audioDataResourceToBeAdded) throws Exception {

		AddAudioDataResource command = new AddAudioDataResource(audioDataResourceToBeAdded);
		AudioDataResource audioDataResource = ((AudioDataResourceAdded) Scheduler.execute(command).data()).getAddedAudioDataResource();
		
		if (audioDataResource != null) 
			return successful(audioDataResource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AudioDataResource with the specific Id
	 * 
	 * @param audioDataResourceToBeUpdated
	 *            the AudioDataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAudioDataResource(@RequestBody AudioDataResource audioDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		audioDataResourceToBeUpdated.setnull(null);

		UpdateAudioDataResource command = new UpdateAudioDataResource(audioDataResourceToBeUpdated);

		try {
			if(((AudioDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{audioDataResourceId}")
	public ResponseEntity<AudioDataResource> findById(@PathVariable String audioDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("audioDataResourceId", audioDataResourceId);
		try {

			List<AudioDataResource> foundAudioDataResource = findAudioDataResourcesBy(requestParams).getBody();
			if(foundAudioDataResource.size()==1){				return successful(foundAudioDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{audioDataResourceId}")
	public ResponseEntity<String> deleteAudioDataResourceByIdUpdated(@PathVariable String audioDataResourceId) throws Exception {
		DeleteAudioDataResource command = new DeleteAudioDataResource(audioDataResourceId);

		try {
			if (((AudioDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
