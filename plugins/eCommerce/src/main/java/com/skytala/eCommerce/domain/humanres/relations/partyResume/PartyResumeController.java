package com.skytala.eCommerce.domain.humanres.relations.partyResume;

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
import com.skytala.eCommerce.domain.humanres.relations.partyResume.command.AddPartyResume;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.command.DeletePartyResume;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.command.UpdatePartyResume;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeFound;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.mapper.PartyResumeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.query.FindPartyResumesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyResumes")
public class PartyResumeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyResumeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyResume
	 * @return a List with the PartyResumes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyResumesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyResumesBy query = new FindPartyResumesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyResume> partyResumes =((PartyResumeFound) Scheduler.execute(query).data()).getPartyResumes();

		if (partyResumes.size() == 1) {
			return ResponseEntity.ok().body(partyResumes.get(0));
		}

		return ResponseEntity.ok().body(partyResumes);

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
	public ResponseEntity<Object> createPartyResume(HttpServletRequest request) throws Exception {

		PartyResume partyResumeToBeAdded = new PartyResume();
		try {
			partyResumeToBeAdded = PartyResumeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyResume(partyResumeToBeAdded);

	}

	/**
	 * creates a new PartyResume entry in the ofbiz database
	 * 
	 * @param partyResumeToBeAdded
	 *            the PartyResume thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyResume(@RequestBody PartyResume partyResumeToBeAdded) throws Exception {

		AddPartyResume command = new AddPartyResume(partyResumeToBeAdded);
		PartyResume partyResume = ((PartyResumeAdded) Scheduler.execute(command).data()).getAddedPartyResume();
		
		if (partyResume != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyResume);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyResume could not be created.");
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
	public boolean updatePartyResume(HttpServletRequest request) throws Exception {

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

		PartyResume partyResumeToBeUpdated = new PartyResume();

		try {
			partyResumeToBeUpdated = PartyResumeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyResume(partyResumeToBeUpdated, partyResumeToBeUpdated.getResumeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyResume with the specific Id
	 * 
	 * @param partyResumeToBeUpdated
	 *            the PartyResume thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{resumeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyResume(@RequestBody PartyResume partyResumeToBeUpdated,
			@PathVariable String resumeId) throws Exception {

		partyResumeToBeUpdated.setResumeId(resumeId);

		UpdatePartyResume command = new UpdatePartyResume(partyResumeToBeUpdated);

		try {
			if(((PartyResumeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyResumeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyResumeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyResumeId", partyResumeId);
		try {

			Object foundPartyResume = findPartyResumesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyResume);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyResumeId}")
	public ResponseEntity<Object> deletePartyResumeByIdUpdated(@PathVariable String partyResumeId) throws Exception {
		DeletePartyResume command = new DeletePartyResume(partyResumeId);

		try {
			if (((PartyResumeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyResume could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyResume/\" plus one of the following: "
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
