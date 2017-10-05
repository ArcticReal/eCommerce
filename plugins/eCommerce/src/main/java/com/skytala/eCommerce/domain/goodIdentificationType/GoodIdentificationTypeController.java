package com.skytala.eCommerce.domain.goodIdentificationType;

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
import com.skytala.eCommerce.domain.goodIdentificationType.command.AddGoodIdentificationType;
import com.skytala.eCommerce.domain.goodIdentificationType.command.DeleteGoodIdentificationType;
import com.skytala.eCommerce.domain.goodIdentificationType.command.UpdateGoodIdentificationType;
import com.skytala.eCommerce.domain.goodIdentificationType.event.GoodIdentificationTypeAdded;
import com.skytala.eCommerce.domain.goodIdentificationType.event.GoodIdentificationTypeDeleted;
import com.skytala.eCommerce.domain.goodIdentificationType.event.GoodIdentificationTypeFound;
import com.skytala.eCommerce.domain.goodIdentificationType.event.GoodIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.goodIdentificationType.mapper.GoodIdentificationTypeMapper;
import com.skytala.eCommerce.domain.goodIdentificationType.model.GoodIdentificationType;
import com.skytala.eCommerce.domain.goodIdentificationType.query.FindGoodIdentificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/goodIdentificationTypes")
public class GoodIdentificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GoodIdentificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GoodIdentificationType
	 * @return a List with the GoodIdentificationTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGoodIdentificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGoodIdentificationTypesBy query = new FindGoodIdentificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GoodIdentificationType> goodIdentificationTypes =((GoodIdentificationTypeFound) Scheduler.execute(query).data()).getGoodIdentificationTypes();

		if (goodIdentificationTypes.size() == 1) {
			return ResponseEntity.ok().body(goodIdentificationTypes.get(0));
		}

		return ResponseEntity.ok().body(goodIdentificationTypes);

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
	public ResponseEntity<Object> createGoodIdentificationType(HttpServletRequest request) throws Exception {

		GoodIdentificationType goodIdentificationTypeToBeAdded = new GoodIdentificationType();
		try {
			goodIdentificationTypeToBeAdded = GoodIdentificationTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGoodIdentificationType(goodIdentificationTypeToBeAdded);

	}

	/**
	 * creates a new GoodIdentificationType entry in the ofbiz database
	 * 
	 * @param goodIdentificationTypeToBeAdded
	 *            the GoodIdentificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGoodIdentificationType(@RequestBody GoodIdentificationType goodIdentificationTypeToBeAdded) throws Exception {

		AddGoodIdentificationType command = new AddGoodIdentificationType(goodIdentificationTypeToBeAdded);
		GoodIdentificationType goodIdentificationType = ((GoodIdentificationTypeAdded) Scheduler.execute(command).data()).getAddedGoodIdentificationType();
		
		if (goodIdentificationType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(goodIdentificationType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GoodIdentificationType could not be created.");
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
	public boolean updateGoodIdentificationType(HttpServletRequest request) throws Exception {

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

		GoodIdentificationType goodIdentificationTypeToBeUpdated = new GoodIdentificationType();

		try {
			goodIdentificationTypeToBeUpdated = GoodIdentificationTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGoodIdentificationType(goodIdentificationTypeToBeUpdated, goodIdentificationTypeToBeUpdated.getGoodIdentificationTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GoodIdentificationType with the specific Id
	 * 
	 * @param goodIdentificationTypeToBeUpdated
	 *            the GoodIdentificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{goodIdentificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGoodIdentificationType(@RequestBody GoodIdentificationType goodIdentificationTypeToBeUpdated,
			@PathVariable String goodIdentificationTypeId) throws Exception {

		goodIdentificationTypeToBeUpdated.setGoodIdentificationTypeId(goodIdentificationTypeId);

		UpdateGoodIdentificationType command = new UpdateGoodIdentificationType(goodIdentificationTypeToBeUpdated);

		try {
			if(((GoodIdentificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{goodIdentificationTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String goodIdentificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("goodIdentificationTypeId", goodIdentificationTypeId);
		try {

			Object foundGoodIdentificationType = findGoodIdentificationTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGoodIdentificationType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{goodIdentificationTypeId}")
	public ResponseEntity<Object> deleteGoodIdentificationTypeByIdUpdated(@PathVariable String goodIdentificationTypeId) throws Exception {
		DeleteGoodIdentificationType command = new DeleteGoodIdentificationType(goodIdentificationTypeId);

		try {
			if (((GoodIdentificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GoodIdentificationType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/goodIdentificationType/\" plus one of the following: "
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
