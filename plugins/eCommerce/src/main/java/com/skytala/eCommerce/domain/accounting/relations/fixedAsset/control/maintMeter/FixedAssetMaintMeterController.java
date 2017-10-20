package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.maintMeter;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.AddFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.DeleteFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.UpdateFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintMeter.FixedAssetMaintMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.maintMeter.FindFixedAssetMaintMetersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/fixedAssetMaintMeters")
public class FixedAssetMaintMeterController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMaintMeterController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMaintMeter
	 * @return a List with the FixedAssetMaintMeters
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetMaintMetersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMaintMetersBy query = new FindFixedAssetMaintMetersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMaintMeter> fixedAssetMaintMeters =((FixedAssetMaintMeterFound) Scheduler.execute(query).data()).getFixedAssetMaintMeters();

		if (fixedAssetMaintMeters.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetMaintMeters.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetMaintMeters);

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
	public ResponseEntity<Object> createFixedAssetMaintMeter(HttpServletRequest request) throws Exception {

		FixedAssetMaintMeter fixedAssetMaintMeterToBeAdded = new FixedAssetMaintMeter();
		try {
			fixedAssetMaintMeterToBeAdded = FixedAssetMaintMeterMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetMaintMeter(fixedAssetMaintMeterToBeAdded);

	}

	/**
	 * creates a new FixedAssetMaintMeter entry in the ofbiz database
	 * 
	 * @param fixedAssetMaintMeterToBeAdded
	 *            the FixedAssetMaintMeter thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetMaintMeter(@RequestBody FixedAssetMaintMeter fixedAssetMaintMeterToBeAdded) throws Exception {

		AddFixedAssetMaintMeter command = new AddFixedAssetMaintMeter(fixedAssetMaintMeterToBeAdded);
		FixedAssetMaintMeter fixedAssetMaintMeter = ((FixedAssetMaintMeterAdded) Scheduler.execute(command).data()).getAddedFixedAssetMaintMeter();
		
		if (fixedAssetMaintMeter != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetMaintMeter);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetMaintMeter could not be created.");
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
	public boolean updateFixedAssetMaintMeter(HttpServletRequest request) throws Exception {

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

		FixedAssetMaintMeter fixedAssetMaintMeterToBeUpdated = new FixedAssetMaintMeter();

		try {
			fixedAssetMaintMeterToBeUpdated = FixedAssetMaintMeterMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetMaintMeter(fixedAssetMaintMeterToBeUpdated, fixedAssetMaintMeterToBeUpdated.getMaintHistSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetMaintMeter with the specific Id
	 * 
	 * @param fixedAssetMaintMeterToBeUpdated
	 *            the FixedAssetMaintMeter thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{maintHistSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetMaintMeter(@RequestBody FixedAssetMaintMeter fixedAssetMaintMeterToBeUpdated,
			@PathVariable String maintHistSeqId) throws Exception {

		fixedAssetMaintMeterToBeUpdated.setMaintHistSeqId(maintHistSeqId);

		UpdateFixedAssetMaintMeter command = new UpdateFixedAssetMaintMeter(fixedAssetMaintMeterToBeUpdated);

		try {
			if(((FixedAssetMaintMeterUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetMaintMeterId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetMaintMeterId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMaintMeterId", fixedAssetMaintMeterId);
		try {

			Object foundFixedAssetMaintMeter = findFixedAssetMaintMetersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetMaintMeter);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetMaintMeterId}")
	public ResponseEntity<Object> deleteFixedAssetMaintMeterByIdUpdated(@PathVariable String fixedAssetMaintMeterId) throws Exception {
		DeleteFixedAssetMaintMeter command = new DeleteFixedAssetMaintMeter(fixedAssetMaintMeterId);

		try {
			if (((FixedAssetMaintMeterDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetMaintMeter could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetMaintMeter/\" plus one of the following: "
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
