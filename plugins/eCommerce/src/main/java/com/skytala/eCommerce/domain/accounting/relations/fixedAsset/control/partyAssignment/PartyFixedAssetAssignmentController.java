package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.partyAssignment;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.AddPartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.DeletePartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.UpdatePartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment.PartyFixedAssetAssignmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.partyAssignment.FindPartyFixedAssetAssignmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/partyFixedAssetAssignments")
public class PartyFixedAssetAssignmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyFixedAssetAssignmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyFixedAssetAssignment
	 * @return a List with the PartyFixedAssetAssignments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyFixedAssetAssignmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyFixedAssetAssignmentsBy query = new FindPartyFixedAssetAssignmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyFixedAssetAssignment> partyFixedAssetAssignments =((PartyFixedAssetAssignmentFound) Scheduler.execute(query).data()).getPartyFixedAssetAssignments();

		if (partyFixedAssetAssignments.size() == 1) {
			return ResponseEntity.ok().body(partyFixedAssetAssignments.get(0));
		}

		return ResponseEntity.ok().body(partyFixedAssetAssignments);

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
	public ResponseEntity<Object> createPartyFixedAssetAssignment(HttpServletRequest request) throws Exception {

		PartyFixedAssetAssignment partyFixedAssetAssignmentToBeAdded = new PartyFixedAssetAssignment();
		try {
			partyFixedAssetAssignmentToBeAdded = PartyFixedAssetAssignmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyFixedAssetAssignment(partyFixedAssetAssignmentToBeAdded);

	}

	/**
	 * creates a new PartyFixedAssetAssignment entry in the ofbiz database
	 * 
	 * @param partyFixedAssetAssignmentToBeAdded
	 *            the PartyFixedAssetAssignment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyFixedAssetAssignment(@RequestBody PartyFixedAssetAssignment partyFixedAssetAssignmentToBeAdded) throws Exception {

		AddPartyFixedAssetAssignment command = new AddPartyFixedAssetAssignment(partyFixedAssetAssignmentToBeAdded);
		PartyFixedAssetAssignment partyFixedAssetAssignment = ((PartyFixedAssetAssignmentAdded) Scheduler.execute(command).data()).getAddedPartyFixedAssetAssignment();
		
		if (partyFixedAssetAssignment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyFixedAssetAssignment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyFixedAssetAssignment could not be created.");
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
	public boolean updatePartyFixedAssetAssignment(HttpServletRequest request) throws Exception {

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

		PartyFixedAssetAssignment partyFixedAssetAssignmentToBeUpdated = new PartyFixedAssetAssignment();

		try {
			partyFixedAssetAssignmentToBeUpdated = PartyFixedAssetAssignmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyFixedAssetAssignment(partyFixedAssetAssignmentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyFixedAssetAssignment with the specific Id
	 * 
	 * @param partyFixedAssetAssignmentToBeUpdated
	 *            the PartyFixedAssetAssignment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyFixedAssetAssignment(@RequestBody PartyFixedAssetAssignment partyFixedAssetAssignmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyFixedAssetAssignmentToBeUpdated.setnull(null);

		UpdatePartyFixedAssetAssignment command = new UpdatePartyFixedAssetAssignment(partyFixedAssetAssignmentToBeUpdated);

		try {
			if(((PartyFixedAssetAssignmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyFixedAssetAssignmentId}")
	public ResponseEntity<Object> findById(@PathVariable String partyFixedAssetAssignmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyFixedAssetAssignmentId", partyFixedAssetAssignmentId);
		try {

			Object foundPartyFixedAssetAssignment = findPartyFixedAssetAssignmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyFixedAssetAssignment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyFixedAssetAssignmentId}")
	public ResponseEntity<Object> deletePartyFixedAssetAssignmentByIdUpdated(@PathVariable String partyFixedAssetAssignmentId) throws Exception {
		DeletePartyFixedAssetAssignment command = new DeletePartyFixedAssetAssignment(partyFixedAssetAssignmentId);

		try {
			if (((PartyFixedAssetAssignmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyFixedAssetAssignment could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyFixedAssetAssignment/\" plus one of the following: "
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
