package com.skytala.eCommerce.domain.characterSet;

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
import com.skytala.eCommerce.domain.characterSet.command.AddCharacterSet;
import com.skytala.eCommerce.domain.characterSet.command.DeleteCharacterSet;
import com.skytala.eCommerce.domain.characterSet.command.UpdateCharacterSet;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetAdded;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetDeleted;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetFound;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetUpdated;
import com.skytala.eCommerce.domain.characterSet.mapper.CharacterSetMapper;
import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
import com.skytala.eCommerce.domain.characterSet.query.FindCharacterSetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/characterSets")
public class CharacterSetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CharacterSetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CharacterSet
	 * @return a List with the CharacterSets
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCharacterSetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCharacterSetsBy query = new FindCharacterSetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CharacterSet> characterSets =((CharacterSetFound) Scheduler.execute(query).data()).getCharacterSets();

		if (characterSets.size() == 1) {
			return ResponseEntity.ok().body(characterSets.get(0));
		}

		return ResponseEntity.ok().body(characterSets);

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
	public ResponseEntity<Object> createCharacterSet(HttpServletRequest request) throws Exception {

		CharacterSet characterSetToBeAdded = new CharacterSet();
		try {
			characterSetToBeAdded = CharacterSetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCharacterSet(characterSetToBeAdded);

	}

	/**
	 * creates a new CharacterSet entry in the ofbiz database
	 * 
	 * @param characterSetToBeAdded
	 *            the CharacterSet thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCharacterSet(@RequestBody CharacterSet characterSetToBeAdded) throws Exception {

		AddCharacterSet command = new AddCharacterSet(characterSetToBeAdded);
		CharacterSet characterSet = ((CharacterSetAdded) Scheduler.execute(command).data()).getAddedCharacterSet();
		
		if (characterSet != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(characterSet);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CharacterSet could not be created.");
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
	public boolean updateCharacterSet(HttpServletRequest request) throws Exception {

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

		CharacterSet characterSetToBeUpdated = new CharacterSet();

		try {
			characterSetToBeUpdated = CharacterSetMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCharacterSet(characterSetToBeUpdated, characterSetToBeUpdated.getCharacterSetId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CharacterSet with the specific Id
	 * 
	 * @param characterSetToBeUpdated
	 *            the CharacterSet thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{characterSetId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCharacterSet(@RequestBody CharacterSet characterSetToBeUpdated,
			@PathVariable String characterSetId) throws Exception {

		characterSetToBeUpdated.setCharacterSetId(characterSetId);

		UpdateCharacterSet command = new UpdateCharacterSet(characterSetToBeUpdated);

		try {
			if(((CharacterSetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{characterSetId}")
	public ResponseEntity<Object> findById(@PathVariable String characterSetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("characterSetId", characterSetId);
		try {

			Object foundCharacterSet = findCharacterSetsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCharacterSet);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{characterSetId}")
	public ResponseEntity<Object> deleteCharacterSetByIdUpdated(@PathVariable String characterSetId) throws Exception {
		DeleteCharacterSet command = new DeleteCharacterSet(characterSetId);

		try {
			if (((CharacterSetDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CharacterSet could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/characterSet/\" plus one of the following: "
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
