package com.skytala.eCommerce.domain.humanres.relations.terminationReason;

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
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.AddTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.DeleteTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.UpdateTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonAdded;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonDeleted;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonFound;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.mapper.TerminationReasonMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.query.FindTerminationReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/terminationReasons")
public class TerminationReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TerminationReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TerminationReason
	 * @return a List with the TerminationReasons
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTerminationReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTerminationReasonsBy query = new FindTerminationReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TerminationReason> terminationReasons =((TerminationReasonFound) Scheduler.execute(query).data()).getTerminationReasons();

		if (terminationReasons.size() == 1) {
			return ResponseEntity.ok().body(terminationReasons.get(0));
		}

		return ResponseEntity.ok().body(terminationReasons);

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
	public ResponseEntity<Object> createTerminationReason(HttpServletRequest request) throws Exception {

		TerminationReason terminationReasonToBeAdded = new TerminationReason();
		try {
			terminationReasonToBeAdded = TerminationReasonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTerminationReason(terminationReasonToBeAdded);

	}

	/**
	 * creates a new TerminationReason entry in the ofbiz database
	 * 
	 * @param terminationReasonToBeAdded
	 *            the TerminationReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTerminationReason(@RequestBody TerminationReason terminationReasonToBeAdded) throws Exception {

		AddTerminationReason command = new AddTerminationReason(terminationReasonToBeAdded);
		TerminationReason terminationReason = ((TerminationReasonAdded) Scheduler.execute(command).data()).getAddedTerminationReason();
		
		if (terminationReason != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(terminationReason);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TerminationReason could not be created.");
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
	public boolean updateTerminationReason(HttpServletRequest request) throws Exception {

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

		TerminationReason terminationReasonToBeUpdated = new TerminationReason();

		try {
			terminationReasonToBeUpdated = TerminationReasonMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTerminationReason(terminationReasonToBeUpdated, terminationReasonToBeUpdated.getTerminationReasonId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TerminationReason with the specific Id
	 * 
	 * @param terminationReasonToBeUpdated
	 *            the TerminationReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{terminationReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTerminationReason(@RequestBody TerminationReason terminationReasonToBeUpdated,
			@PathVariable String terminationReasonId) throws Exception {

		terminationReasonToBeUpdated.setTerminationReasonId(terminationReasonId);

		UpdateTerminationReason command = new UpdateTerminationReason(terminationReasonToBeUpdated);

		try {
			if(((TerminationReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{terminationReasonId}")
	public ResponseEntity<Object> findById(@PathVariable String terminationReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("terminationReasonId", terminationReasonId);
		try {

			Object foundTerminationReason = findTerminationReasonsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTerminationReason);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{terminationReasonId}")
	public ResponseEntity<Object> deleteTerminationReasonByIdUpdated(@PathVariable String terminationReasonId) throws Exception {
		DeleteTerminationReason command = new DeleteTerminationReason(terminationReasonId);

		try {
			if (((TerminationReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TerminationReason could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/terminationReason/\" plus one of the following: "
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
