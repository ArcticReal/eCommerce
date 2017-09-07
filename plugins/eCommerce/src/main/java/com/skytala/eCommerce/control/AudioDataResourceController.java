package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddAudioDataResource;
import com.skytala.eCommerce.command.DeleteAudioDataResource;
import com.skytala.eCommerce.command.UpdateAudioDataResource;
import com.skytala.eCommerce.entity.AudioDataResource;
import com.skytala.eCommerce.entity.AudioDataResourceMapper;
import com.skytala.eCommerce.event.AudioDataResourceAdded;
import com.skytala.eCommerce.event.AudioDataResourceDeleted;
import com.skytala.eCommerce.event.AudioDataResourceFound;
import com.skytala.eCommerce.event.AudioDataResourceUpdated;
import com.skytala.eCommerce.query.FindAudioDataResourcesBy;

@RestController
@RequestMapping("/api/audioDataResource")
public class AudioDataResourceController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<AudioDataResource>> queryReturnVal = new HashMap<>();

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AudioDataResource
	 * @return a List with the AudioDataResources
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/findBy" })
	public List<AudioDataResource> findAudioDataResourcesBy(@RequestParam Map<String, String> allRequestParams){

		FindAudioDataResourcesBy query = new FindAudioDataResourcesBy(allRequestParams);

		int usedTicketId;

		synchronized (AudioDataResourceController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(AudioDataResourceFound.class,
				event -> sendAudioDataResourcesFoundMessage(((AudioDataResourceFound) event).getAudioDataResources(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	public void sendAudioDataResourcesFoundMessage(List<AudioDataResource> audioDataResources, int usedTicketId) {
		queryReturnVal.put(usedTicketId, audioDataResources);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/create",
			"/insert", "/add" }, consumes = "application/x-www-form-urlencoded")
	public boolean createAudioDataResource(HttpServletRequest request) {

		AudioDataResource audioDataResourceToBeAdded = new AudioDataResource();
		try {
			audioDataResourceToBeAdded = AudioDataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
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
	public boolean createAudioDataResource(AudioDataResource audioDataResourceToBeAdded) {

		AddAudioDataResource com = new AddAudioDataResource(audioDataResourceToBeAdded);
		int usedTicketId;

		synchronized (AudioDataResourceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(AudioDataResourceAdded.class,
				event -> sendAudioDataResourceChangedMessage(((AudioDataResourceAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = { "/update",
			"/change" }, consumes = "application/x-www-form-urlencoded")
	public boolean updateAudioDataResource(HttpServletRequest request) {

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

		return updateAudioDataResource(audioDataResourceToBeUpdated);

	}

	/**
	 * Updates the AudioDataResource with the specific Id
	 * 
	 * @param audioDataResourceToBeUpdated the AudioDataResource thats to be updated
	 * @return true on success, false on fail
	 */
	public boolean updateAudioDataResource(AudioDataResource audioDataResourceToBeUpdated) {

		UpdateAudioDataResource com = new UpdateAudioDataResource(audioDataResourceToBeUpdated);

		int usedTicketId;

		synchronized (AudioDataResourceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(AudioDataResourceUpdated.class,
				event -> sendAudioDataResourceChangedMessage(((AudioDataResourceUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	/**
	 * removes a AudioDataResource from the database
	 * 
	 * @param audioDataResourceId:
	 *            the id of the AudioDataResource thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = { "/removeById", "/removeBy" })
	public boolean deleteaudioDataResourceById(@RequestParam(value = "audioDataResourceId") String audioDataResourceId) {

		DeleteAudioDataResource com = new DeleteAudioDataResource(audioDataResourceId);

		int usedTicketId;

		synchronized (AudioDataResourceController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(AudioDataResourceDeleted.class,
				event -> sendAudioDataResourceChangedMessage(((AudioDataResourceDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	public void sendAudioDataResourceChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(value = (" * "))
	public String returnErrorPage(HttpServletRequest request) {

		return "Error 404: Page not found! Check your spelling and/or your request method.";

}}
