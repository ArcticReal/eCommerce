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
	public ResponseEntity<Object> findAudioDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAudioDataResourcesBy query = new FindAudioDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AudioDataResource> audioDataResources =((AudioDataResourceFound) Scheduler.execute(query).data()).getAudioDataResources();

		if (audioDataResources.size() == 1) {
			return ResponseEntity.ok().body(audioDataResources.get(0));
		}

		return ResponseEntity.ok().body(audioDataResources);

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
	public ResponseEntity<Object> createAudioDataResource(HttpServletRequest request) throws Exception {

		AudioDataResource audioDataResourceToBeAdded = new AudioDataResource();
		try {
			audioDataResourceToBeAdded = AudioDataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAudioDataResource(audioDataResourceToBeAdded);

	}

	/**
	 * creates a new AudioDataResource entry in the ofbiz database
	 * 
	 * @param audioDataResourceToBeAdded
	 *            the AudioDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAudioDataResource(@RequestBody AudioDataResource audioDataResourceToBeAdded) throws Exception {

		AddAudioDataResource command = new AddAudioDataResource(audioDataResourceToBeAdded);
		AudioDataResource audioDataResource = ((AudioDataResourceAdded) Scheduler.execute(command).data()).getAddedAudioDataResource();
		
		if (audioDataResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(audioDataResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AudioDataResource could not be created.");
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
	public boolean updateAudioDataResource(HttpServletRequest request) throws Exception {

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

		AudioDataResource audioDataResourceToBeUpdated = new AudioDataResource();

		try {
			audioDataResourceToBeUpdated = AudioDataResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAudioDataResource(audioDataResourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAudioDataResource(@RequestBody AudioDataResource audioDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		audioDataResourceToBeUpdated.setnull(null);

		UpdateAudioDataResource command = new UpdateAudioDataResource(audioDataResourceToBeUpdated);

		try {
			if(((AudioDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{audioDataResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String audioDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("audioDataResourceId", audioDataResourceId);
		try {

			Object foundAudioDataResource = findAudioDataResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAudioDataResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{audioDataResourceId}")
	public ResponseEntity<Object> deleteAudioDataResourceByIdUpdated(@PathVariable String audioDataResourceId) throws Exception {
		DeleteAudioDataResource command = new DeleteAudioDataResource(audioDataResourceId);

		try {
			if (((AudioDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AudioDataResource could not be deleted");

	}

}
