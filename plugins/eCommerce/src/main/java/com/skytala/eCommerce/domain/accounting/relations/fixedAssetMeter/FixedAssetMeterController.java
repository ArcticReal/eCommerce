package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.command.AddFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.command.DeleteFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.command.UpdateFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event.FixedAssetMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event.FixedAssetMeterDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event.FixedAssetMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event.FixedAssetMeterUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.mapper.FixedAssetMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model.FixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.query.FindFixedAssetMetersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetMeters")
public class FixedAssetMeterController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMeterController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMeter
	 * @return a List with the FixedAssetMeters
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetMetersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMetersBy query = new FindFixedAssetMetersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMeter> fixedAssetMeters =((FixedAssetMeterFound) Scheduler.execute(query).data()).getFixedAssetMeters();

		if (fixedAssetMeters.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetMeters.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetMeters);

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
	public ResponseEntity<Object> createFixedAssetMeter(HttpServletRequest request) throws Exception {

		FixedAssetMeter fixedAssetMeterToBeAdded = new FixedAssetMeter();
		try {
			fixedAssetMeterToBeAdded = FixedAssetMeterMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetMeter(fixedAssetMeterToBeAdded);

	}

	/**
	 * creates a new FixedAssetMeter entry in the ofbiz database
	 * 
	 * @param fixedAssetMeterToBeAdded
	 *            the FixedAssetMeter thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetMeter(@RequestBody FixedAssetMeter fixedAssetMeterToBeAdded) throws Exception {

		AddFixedAssetMeter command = new AddFixedAssetMeter(fixedAssetMeterToBeAdded);
		FixedAssetMeter fixedAssetMeter = ((FixedAssetMeterAdded) Scheduler.execute(command).data()).getAddedFixedAssetMeter();
		
		if (fixedAssetMeter != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetMeter);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetMeter could not be created.");
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
	public boolean updateFixedAssetMeter(HttpServletRequest request) throws Exception {

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

		FixedAssetMeter fixedAssetMeterToBeUpdated = new FixedAssetMeter();

		try {
			fixedAssetMeterToBeUpdated = FixedAssetMeterMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetMeter(fixedAssetMeterToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetMeter with the specific Id
	 * 
	 * @param fixedAssetMeterToBeUpdated
	 *            the FixedAssetMeter thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetMeter(@RequestBody FixedAssetMeter fixedAssetMeterToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetMeterToBeUpdated.setnull(null);

		UpdateFixedAssetMeter command = new UpdateFixedAssetMeter(fixedAssetMeterToBeUpdated);

		try {
			if(((FixedAssetMeterUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetMeterId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetMeterId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMeterId", fixedAssetMeterId);
		try {

			Object foundFixedAssetMeter = findFixedAssetMetersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetMeter);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetMeterId}")
	public ResponseEntity<Object> deleteFixedAssetMeterByIdUpdated(@PathVariable String fixedAssetMeterId) throws Exception {
		DeleteFixedAssetMeter command = new DeleteFixedAssetMeter(fixedAssetMeterId);

		try {
			if (((FixedAssetMeterDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetMeter could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetMeter/\" plus one of the following: "
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