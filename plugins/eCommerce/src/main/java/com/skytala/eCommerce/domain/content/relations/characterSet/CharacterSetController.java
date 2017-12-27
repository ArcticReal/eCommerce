package com.skytala.eCommerce.domain.content.relations.characterSet;

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
import com.skytala.eCommerce.domain.content.relations.characterSet.command.AddCharacterSet;
import com.skytala.eCommerce.domain.content.relations.characterSet.command.DeleteCharacterSet;
import com.skytala.eCommerce.domain.content.relations.characterSet.command.UpdateCharacterSet;
import com.skytala.eCommerce.domain.content.relations.characterSet.event.CharacterSetAdded;
import com.skytala.eCommerce.domain.content.relations.characterSet.event.CharacterSetDeleted;
import com.skytala.eCommerce.domain.content.relations.characterSet.event.CharacterSetFound;
import com.skytala.eCommerce.domain.content.relations.characterSet.event.CharacterSetUpdated;
import com.skytala.eCommerce.domain.content.relations.characterSet.mapper.CharacterSetMapper;
import com.skytala.eCommerce.domain.content.relations.characterSet.model.CharacterSet;
import com.skytala.eCommerce.domain.content.relations.characterSet.query.FindCharacterSetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/characterSets")
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
	@GetMapping("/find")
	public ResponseEntity<List<CharacterSet>> findCharacterSetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCharacterSetsBy query = new FindCharacterSetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CharacterSet> characterSets =((CharacterSetFound) Scheduler.execute(query).data()).getCharacterSets();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CharacterSet> createCharacterSet(HttpServletRequest request) throws Exception {

		CharacterSet characterSetToBeAdded = new CharacterSet();
		try {
			characterSetToBeAdded = CharacterSetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CharacterSet> createCharacterSet(@RequestBody CharacterSet characterSetToBeAdded) throws Exception {

		AddCharacterSet command = new AddCharacterSet(characterSetToBeAdded);
		CharacterSet characterSet = ((CharacterSetAdded) Scheduler.execute(command).data()).getAddedCharacterSet();
		
		if (characterSet != null) 
			return successful(characterSet);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCharacterSet(@RequestBody CharacterSet characterSetToBeUpdated,
			@PathVariable String characterSetId) throws Exception {

		characterSetToBeUpdated.setCharacterSetId(characterSetId);

		UpdateCharacterSet command = new UpdateCharacterSet(characterSetToBeUpdated);

		try {
			if(((CharacterSetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{characterSetId}")
	public ResponseEntity<CharacterSet> findById(@PathVariable String characterSetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("characterSetId", characterSetId);
		try {

			List<CharacterSet> foundCharacterSet = findCharacterSetsBy(requestParams).getBody();
			if(foundCharacterSet.size()==1){				return successful(foundCharacterSet.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{characterSetId}")
	public ResponseEntity<String> deleteCharacterSetByIdUpdated(@PathVariable String characterSetId) throws Exception {
		DeleteCharacterSet command = new DeleteCharacterSet(characterSetId);

		try {
			if (((CharacterSetDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
