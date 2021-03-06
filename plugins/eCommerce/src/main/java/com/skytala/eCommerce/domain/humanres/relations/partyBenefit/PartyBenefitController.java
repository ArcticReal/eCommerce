package com.skytala.eCommerce.domain.humanres.relations.partyBenefit;

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
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.command.AddPartyBenefit;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.command.DeletePartyBenefit;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.command.UpdatePartyBenefit;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitFound;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.mapper.PartyBenefitMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.query.FindPartyBenefitsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/partyBenefits")
public class PartyBenefitController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyBenefitController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyBenefit
	 * @return a List with the PartyBenefits
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyBenefit>> findPartyBenefitsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyBenefitsBy query = new FindPartyBenefitsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyBenefit> partyBenefits =((PartyBenefitFound) Scheduler.execute(query).data()).getPartyBenefits();

		return ResponseEntity.ok().body(partyBenefits);

	}

	/**
	 * creates a new PartyBenefit entry in the ofbiz database
	 * 
	 * @param partyBenefitToBeAdded
	 *            the PartyBenefit thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyBenefit> createPartyBenefit(@RequestBody PartyBenefit partyBenefitToBeAdded) throws Exception {

		AddPartyBenefit command = new AddPartyBenefit(partyBenefitToBeAdded);
		PartyBenefit partyBenefit = ((PartyBenefitAdded) Scheduler.execute(command).data()).getAddedPartyBenefit();
		
		if (partyBenefit != null) 
			return successful(partyBenefit);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyBenefit with the specific Id
	 * 
	 * @param partyBenefitToBeUpdated
	 *            the PartyBenefit thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyBenefit(@RequestBody PartyBenefit partyBenefitToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyBenefitToBeUpdated.setnull(null);

		UpdatePartyBenefit command = new UpdatePartyBenefit(partyBenefitToBeUpdated);

		try {
			if(((PartyBenefitUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyBenefitId}")
	public ResponseEntity<PartyBenefit> findById(@PathVariable String partyBenefitId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyBenefitId", partyBenefitId);
		try {

			List<PartyBenefit> foundPartyBenefit = findPartyBenefitsBy(requestParams).getBody();
			if(foundPartyBenefit.size()==1){				return successful(foundPartyBenefit.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyBenefitId}")
	public ResponseEntity<String> deletePartyBenefitByIdUpdated(@PathVariable String partyBenefitId) throws Exception {
		DeletePartyBenefit command = new DeletePartyBenefit(partyBenefitId);

		try {
			if (((PartyBenefitDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
