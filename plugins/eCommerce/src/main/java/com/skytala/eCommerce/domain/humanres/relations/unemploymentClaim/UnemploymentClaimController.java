package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim;

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
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.command.AddUnemploymentClaim;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.command.DeleteUnemploymentClaim;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.command.UpdateUnemploymentClaim;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimAdded;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimDeleted;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimFound;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimUpdated;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.mapper.UnemploymentClaimMapper;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.query.FindUnemploymentClaimsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/unemploymentClaims")
public class UnemploymentClaimController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UnemploymentClaimController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UnemploymentClaim
	 * @return a List with the UnemploymentClaims
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<UnemploymentClaim>> findUnemploymentClaimsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUnemploymentClaimsBy query = new FindUnemploymentClaimsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UnemploymentClaim> unemploymentClaims =((UnemploymentClaimFound) Scheduler.execute(query).data()).getUnemploymentClaims();

		return ResponseEntity.ok().body(unemploymentClaims);

	}

	/**
	 * creates a new UnemploymentClaim entry in the ofbiz database
	 * 
	 * @param unemploymentClaimToBeAdded
	 *            the UnemploymentClaim thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UnemploymentClaim> createUnemploymentClaim(@RequestBody UnemploymentClaim unemploymentClaimToBeAdded) throws Exception {

		AddUnemploymentClaim command = new AddUnemploymentClaim(unemploymentClaimToBeAdded);
		UnemploymentClaim unemploymentClaim = ((UnemploymentClaimAdded) Scheduler.execute(command).data()).getAddedUnemploymentClaim();
		
		if (unemploymentClaim != null) 
			return successful(unemploymentClaim);
		else 
			return conflict(null);
	}

	/**
	 * Updates the UnemploymentClaim with the specific Id
	 * 
	 * @param unemploymentClaimToBeUpdated
	 *            the UnemploymentClaim thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{unemploymentClaimId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateUnemploymentClaim(@RequestBody UnemploymentClaim unemploymentClaimToBeUpdated,
			@PathVariable String unemploymentClaimId) throws Exception {

		unemploymentClaimToBeUpdated.setUnemploymentClaimId(unemploymentClaimId);

		UpdateUnemploymentClaim command = new UpdateUnemploymentClaim(unemploymentClaimToBeUpdated);

		try {
			if(((UnemploymentClaimUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{unemploymentClaimId}")
	public ResponseEntity<UnemploymentClaim> findById(@PathVariable String unemploymentClaimId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("unemploymentClaimId", unemploymentClaimId);
		try {

			List<UnemploymentClaim> foundUnemploymentClaim = findUnemploymentClaimsBy(requestParams).getBody();
			if(foundUnemploymentClaim.size()==1){				return successful(foundUnemploymentClaim.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{unemploymentClaimId}")
	public ResponseEntity<String> deleteUnemploymentClaimByIdUpdated(@PathVariable String unemploymentClaimId) throws Exception {
		DeleteUnemploymentClaim command = new DeleteUnemploymentClaim(unemploymentClaimId);

		try {
			if (((UnemploymentClaimDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
