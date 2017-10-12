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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/unemploymentClaims")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findUnemploymentClaimsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUnemploymentClaimsBy query = new FindUnemploymentClaimsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UnemploymentClaim> unemploymentClaims =((UnemploymentClaimFound) Scheduler.execute(query).data()).getUnemploymentClaims();

		if (unemploymentClaims.size() == 1) {
			return ResponseEntity.ok().body(unemploymentClaims.get(0));
		}

		return ResponseEntity.ok().body(unemploymentClaims);

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
	public ResponseEntity<Object> createUnemploymentClaim(HttpServletRequest request) throws Exception {

		UnemploymentClaim unemploymentClaimToBeAdded = new UnemploymentClaim();
		try {
			unemploymentClaimToBeAdded = UnemploymentClaimMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUnemploymentClaim(unemploymentClaimToBeAdded);

	}

	/**
	 * creates a new UnemploymentClaim entry in the ofbiz database
	 * 
	 * @param unemploymentClaimToBeAdded
	 *            the UnemploymentClaim thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUnemploymentClaim(@RequestBody UnemploymentClaim unemploymentClaimToBeAdded) throws Exception {

		AddUnemploymentClaim command = new AddUnemploymentClaim(unemploymentClaimToBeAdded);
		UnemploymentClaim unemploymentClaim = ((UnemploymentClaimAdded) Scheduler.execute(command).data()).getAddedUnemploymentClaim();
		
		if (unemploymentClaim != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(unemploymentClaim);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UnemploymentClaim could not be created.");
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
	public boolean updateUnemploymentClaim(HttpServletRequest request) throws Exception {

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

		UnemploymentClaim unemploymentClaimToBeUpdated = new UnemploymentClaim();

		try {
			unemploymentClaimToBeUpdated = UnemploymentClaimMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUnemploymentClaim(unemploymentClaimToBeUpdated, unemploymentClaimToBeUpdated.getUnemploymentClaimId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateUnemploymentClaim(@RequestBody UnemploymentClaim unemploymentClaimToBeUpdated,
			@PathVariable String unemploymentClaimId) throws Exception {

		unemploymentClaimToBeUpdated.setUnemploymentClaimId(unemploymentClaimId);

		UpdateUnemploymentClaim command = new UpdateUnemploymentClaim(unemploymentClaimToBeUpdated);

		try {
			if(((UnemploymentClaimUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{unemploymentClaimId}")
	public ResponseEntity<Object> findById(@PathVariable String unemploymentClaimId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("unemploymentClaimId", unemploymentClaimId);
		try {

			Object foundUnemploymentClaim = findUnemploymentClaimsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUnemploymentClaim);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{unemploymentClaimId}")
	public ResponseEntity<Object> deleteUnemploymentClaimByIdUpdated(@PathVariable String unemploymentClaimId) throws Exception {
		DeleteUnemploymentClaim command = new DeleteUnemploymentClaim(unemploymentClaimId);

		try {
			if (((UnemploymentClaimDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UnemploymentClaim could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/unemploymentClaim/\" plus one of the following: "
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
