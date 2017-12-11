package com.skytala.eCommerce.domain.humanres.relations.partySkill;

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
import com.skytala.eCommerce.domain.humanres.relations.partySkill.command.AddPartySkill;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.command.DeletePartySkill;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.command.UpdatePartySkill;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillAdded;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillFound;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.mapper.PartySkillMapper;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.query.FindPartySkillsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/partySkills")
public class PartySkillController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartySkillController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartySkill
	 * @return a List with the PartySkills
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartySkillsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartySkillsBy query = new FindPartySkillsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartySkill> partySkills =((PartySkillFound) Scheduler.execute(query).data()).getPartySkills();

		if (partySkills.size() == 1) {
			return ResponseEntity.ok().body(partySkills.get(0));
		}

		return ResponseEntity.ok().body(partySkills);

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
	public ResponseEntity<Object> createPartySkill(HttpServletRequest request) throws Exception {

		PartySkill partySkillToBeAdded = new PartySkill();
		try {
			partySkillToBeAdded = PartySkillMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartySkill(partySkillToBeAdded);

	}

	/**
	 * creates a new PartySkill entry in the ofbiz database
	 * 
	 * @param partySkillToBeAdded
	 *            the PartySkill thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartySkill(@RequestBody PartySkill partySkillToBeAdded) throws Exception {

		AddPartySkill command = new AddPartySkill(partySkillToBeAdded);
		PartySkill partySkill = ((PartySkillAdded) Scheduler.execute(command).data()).getAddedPartySkill();
		
		if (partySkill != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partySkill);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartySkill could not be created.");
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
	public boolean updatePartySkill(HttpServletRequest request) throws Exception {

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

		PartySkill partySkillToBeUpdated = new PartySkill();

		try {
			partySkillToBeUpdated = PartySkillMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartySkill(partySkillToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartySkill with the specific Id
	 * 
	 * @param partySkillToBeUpdated
	 *            the PartySkill thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartySkill(@RequestBody PartySkill partySkillToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partySkillToBeUpdated.setnull(null);

		UpdatePartySkill command = new UpdatePartySkill(partySkillToBeUpdated);

		try {
			if(((PartySkillUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partySkillId}")
	public ResponseEntity<Object> findById(@PathVariable String partySkillId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partySkillId", partySkillId);
		try {

			Object foundPartySkill = findPartySkillsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartySkill);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partySkillId}")
	public ResponseEntity<Object> deletePartySkillByIdUpdated(@PathVariable String partySkillId) throws Exception {
		DeletePartySkill command = new DeletePartySkill(partySkillId);

		try {
			if (((PartySkillDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartySkill could not be deleted");

	}

}
