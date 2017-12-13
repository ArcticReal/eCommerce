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
	public ResponseEntity<Object> findPartyBenefitsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyBenefitsBy query = new FindPartyBenefitsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyBenefit> partyBenefits =((PartyBenefitFound) Scheduler.execute(query).data()).getPartyBenefits();

		if (partyBenefits.size() == 1) {
			return ResponseEntity.ok().body(partyBenefits.get(0));
		}

		return ResponseEntity.ok().body(partyBenefits);

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
	public ResponseEntity<Object> createPartyBenefit(HttpServletRequest request) throws Exception {

		PartyBenefit partyBenefitToBeAdded = new PartyBenefit();
		try {
			partyBenefitToBeAdded = PartyBenefitMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyBenefit(partyBenefitToBeAdded);

	}

	/**
	 * creates a new PartyBenefit entry in the ofbiz database
	 * 
	 * @param partyBenefitToBeAdded
	 *            the PartyBenefit thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyBenefit(@RequestBody PartyBenefit partyBenefitToBeAdded) throws Exception {

		AddPartyBenefit command = new AddPartyBenefit(partyBenefitToBeAdded);
		PartyBenefit partyBenefit = ((PartyBenefitAdded) Scheduler.execute(command).data()).getAddedPartyBenefit();
		
		if (partyBenefit != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyBenefit);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyBenefit could not be created.");
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
	public boolean updatePartyBenefit(HttpServletRequest request) throws Exception {

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

		PartyBenefit partyBenefitToBeUpdated = new PartyBenefit();

		try {
			partyBenefitToBeUpdated = PartyBenefitMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyBenefit(partyBenefitToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyBenefit(@RequestBody PartyBenefit partyBenefitToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyBenefitToBeUpdated.setnull(null);

		UpdatePartyBenefit command = new UpdatePartyBenefit(partyBenefitToBeUpdated);

		try {
			if(((PartyBenefitUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyBenefitId}")
	public ResponseEntity<Object> findById(@PathVariable String partyBenefitId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyBenefitId", partyBenefitId);
		try {

			Object foundPartyBenefit = findPartyBenefitsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyBenefit);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyBenefitId}")
	public ResponseEntity<Object> deletePartyBenefitByIdUpdated(@PathVariable String partyBenefitId) throws Exception {
		DeletePartyBenefit command = new DeletePartyBenefit(partyBenefitId);

		try {
			if (((PartyBenefitDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyBenefit could not be deleted");

	}

}
