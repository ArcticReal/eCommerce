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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PartySkill>> findPartySkillsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartySkillsBy query = new FindPartySkillsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartySkill> partySkills =((PartySkillFound) Scheduler.execute(query).data()).getPartySkills();

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
	public ResponseEntity<PartySkill> createPartySkill(HttpServletRequest request) throws Exception {

		PartySkill partySkillToBeAdded = new PartySkill();
		try {
			partySkillToBeAdded = PartySkillMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartySkill> createPartySkill(@RequestBody PartySkill partySkillToBeAdded) throws Exception {

		AddPartySkill command = new AddPartySkill(partySkillToBeAdded);
		PartySkill partySkill = ((PartySkillAdded) Scheduler.execute(command).data()).getAddedPartySkill();
		
		if (partySkill != null) 
			return successful(partySkill);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartySkill(@RequestBody PartySkill partySkillToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partySkillToBeUpdated.setnull(null);

		UpdatePartySkill command = new UpdatePartySkill(partySkillToBeUpdated);

		try {
			if(((PartySkillUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partySkillId}")
	public ResponseEntity<PartySkill> findById(@PathVariable String partySkillId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partySkillId", partySkillId);
		try {

			List<PartySkill> foundPartySkill = findPartySkillsBy(requestParams).getBody();
			if(foundPartySkill.size()==1){				return successful(foundPartySkill.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partySkillId}")
	public ResponseEntity<String> deletePartySkillByIdUpdated(@PathVariable String partySkillId) throws Exception {
		DeletePartySkill command = new DeletePartySkill(partySkillId);

		try {
			if (((PartySkillDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
