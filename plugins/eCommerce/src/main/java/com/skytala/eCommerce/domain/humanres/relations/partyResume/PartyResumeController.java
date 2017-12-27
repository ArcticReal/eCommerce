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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/partyResumes")
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
	@GetMapping("/find")
	public ResponseEntity<List<PartyResume>> findPartyResumesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyResumesBy query = new FindPartyResumesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyResume> partyResumes =((PartyResumeFound) Scheduler.execute(query).data()).getPartyResumes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PartyResume> createPartyResume(HttpServletRequest request) throws Exception {

		PartyResume partyResumeToBeAdded = new PartyResume();
		try {
			partyResumeToBeAdded = PartyResumeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartyResume> createPartyResume(@RequestBody PartyResume partyResumeToBeAdded) throws Exception {

		AddPartyResume command = new AddPartyResume(partyResumeToBeAdded);
		PartyResume partyResume = ((PartyResumeAdded) Scheduler.execute(command).data()).getAddedPartyResume();
		
		if (partyResume != null) 
			return successful(partyResume);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyResume(@RequestBody PartyResume partyResumeToBeUpdated,
			@PathVariable String resumeId) throws Exception {

		partyResumeToBeUpdated.setResumeId(resumeId);

		UpdatePartyResume command = new UpdatePartyResume(partyResumeToBeUpdated);

		try {
			if(((PartyResumeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyResumeId}")
	public ResponseEntity<PartyResume> findById(@PathVariable String partyResumeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyResumeId", partyResumeId);
		try {

			List<PartyResume> foundPartyResume = findPartyResumesBy(requestParams).getBody();
			if(foundPartyResume.size()==1){				return successful(foundPartyResume.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyResumeId}")
	public ResponseEntity<String> deletePartyResumeByIdUpdated(@PathVariable String partyResumeId) throws Exception {
		DeletePartyResume command = new DeletePartyResume(partyResumeId);

		try {
			if (((PartyResumeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
