package com.skytala.eCommerce.domain.picklistBin;

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
import com.skytala.eCommerce.domain.picklistBin.command.AddPicklistBin;
import com.skytala.eCommerce.domain.picklistBin.command.DeletePicklistBin;
import com.skytala.eCommerce.domain.picklistBin.command.UpdatePicklistBin;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinAdded;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinDeleted;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinFound;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinUpdated;
import com.skytala.eCommerce.domain.picklistBin.mapper.PicklistBinMapper;
import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
import com.skytala.eCommerce.domain.picklistBin.query.FindPicklistBinsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/picklistBins")
public class PicklistBinController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PicklistBinController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PicklistBin
	 * @return a List with the PicklistBins
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPicklistBinsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistBinsBy query = new FindPicklistBinsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistBin> picklistBins =((PicklistBinFound) Scheduler.execute(query).data()).getPicklistBins();

		if (picklistBins.size() == 1) {
			return ResponseEntity.ok().body(picklistBins.get(0));
		}

		return ResponseEntity.ok().body(picklistBins);

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
	public ResponseEntity<Object> createPicklistBin(HttpServletRequest request) throws Exception {

		PicklistBin picklistBinToBeAdded = new PicklistBin();
		try {
			picklistBinToBeAdded = PicklistBinMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPicklistBin(picklistBinToBeAdded);

	}

	/**
	 * creates a new PicklistBin entry in the ofbiz database
	 * 
	 * @param picklistBinToBeAdded
	 *            the PicklistBin thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPicklistBin(@RequestBody PicklistBin picklistBinToBeAdded) throws Exception {

		AddPicklistBin command = new AddPicklistBin(picklistBinToBeAdded);
		PicklistBin picklistBin = ((PicklistBinAdded) Scheduler.execute(command).data()).getAddedPicklistBin();
		
		if (picklistBin != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(picklistBin);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PicklistBin could not be created.");
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
	public boolean updatePicklistBin(HttpServletRequest request) throws Exception {

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

		PicklistBin picklistBinToBeUpdated = new PicklistBin();

		try {
			picklistBinToBeUpdated = PicklistBinMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePicklistBin(picklistBinToBeUpdated, picklistBinToBeUpdated.getPicklistBinId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PicklistBin with the specific Id
	 * 
	 * @param picklistBinToBeUpdated
	 *            the PicklistBin thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{picklistBinId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePicklistBin(@RequestBody PicklistBin picklistBinToBeUpdated,
			@PathVariable String picklistBinId) throws Exception {

		picklistBinToBeUpdated.setPicklistBinId(picklistBinId);

		UpdatePicklistBin command = new UpdatePicklistBin(picklistBinToBeUpdated);

		try {
			if(((PicklistBinUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{picklistBinId}")
	public ResponseEntity<Object> findById(@PathVariable String picklistBinId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistBinId", picklistBinId);
		try {

			Object foundPicklistBin = findPicklistBinsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPicklistBin);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{picklistBinId}")
	public ResponseEntity<Object> deletePicklistBinByIdUpdated(@PathVariable String picklistBinId) throws Exception {
		DeletePicklistBin command = new DeletePicklistBin(picklistBinId);

		try {
			if (((PicklistBinDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PicklistBin could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/picklistBin/\" plus one of the following: "
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
