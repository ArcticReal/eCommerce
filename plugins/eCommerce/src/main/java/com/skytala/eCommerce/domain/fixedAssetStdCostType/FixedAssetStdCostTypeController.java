package com.skytala.eCommerce.domain.fixedAssetStdCostType;

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
import com.skytala.eCommerce.domain.fixedAssetStdCostType.command.AddFixedAssetStdCostType;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.command.DeleteFixedAssetStdCostType;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.command.UpdateFixedAssetStdCostType;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.event.FixedAssetStdCostTypeAdded;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.event.FixedAssetStdCostTypeDeleted;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.event.FixedAssetStdCostTypeFound;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.event.FixedAssetStdCostTypeUpdated;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.mapper.FixedAssetStdCostTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.model.FixedAssetStdCostType;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.query.FindFixedAssetStdCostTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetStdCostTypes")
public class FixedAssetStdCostTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetStdCostTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetStdCostType
	 * @return a List with the FixedAssetStdCostTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetStdCostTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetStdCostTypesBy query = new FindFixedAssetStdCostTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetStdCostType> fixedAssetStdCostTypes =((FixedAssetStdCostTypeFound) Scheduler.execute(query).data()).getFixedAssetStdCostTypes();

		if (fixedAssetStdCostTypes.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetStdCostTypes.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetStdCostTypes);

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
	public ResponseEntity<Object> createFixedAssetStdCostType(HttpServletRequest request) throws Exception {

		FixedAssetStdCostType fixedAssetStdCostTypeToBeAdded = new FixedAssetStdCostType();
		try {
			fixedAssetStdCostTypeToBeAdded = FixedAssetStdCostTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetStdCostType(fixedAssetStdCostTypeToBeAdded);

	}

	/**
	 * creates a new FixedAssetStdCostType entry in the ofbiz database
	 * 
	 * @param fixedAssetStdCostTypeToBeAdded
	 *            the FixedAssetStdCostType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetStdCostType(@RequestBody FixedAssetStdCostType fixedAssetStdCostTypeToBeAdded) throws Exception {

		AddFixedAssetStdCostType command = new AddFixedAssetStdCostType(fixedAssetStdCostTypeToBeAdded);
		FixedAssetStdCostType fixedAssetStdCostType = ((FixedAssetStdCostTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetStdCostType();
		
		if (fixedAssetStdCostType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetStdCostType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetStdCostType could not be created.");
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
	public boolean updateFixedAssetStdCostType(HttpServletRequest request) throws Exception {

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

		FixedAssetStdCostType fixedAssetStdCostTypeToBeUpdated = new FixedAssetStdCostType();

		try {
			fixedAssetStdCostTypeToBeUpdated = FixedAssetStdCostTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetStdCostType(fixedAssetStdCostTypeToBeUpdated, fixedAssetStdCostTypeToBeUpdated.getFixedAssetStdCostTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetStdCostType with the specific Id
	 * 
	 * @param fixedAssetStdCostTypeToBeUpdated
	 *            the FixedAssetStdCostType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetStdCostTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetStdCostType(@RequestBody FixedAssetStdCostType fixedAssetStdCostTypeToBeUpdated,
			@PathVariable String fixedAssetStdCostTypeId) throws Exception {

		fixedAssetStdCostTypeToBeUpdated.setFixedAssetStdCostTypeId(fixedAssetStdCostTypeId);

		UpdateFixedAssetStdCostType command = new UpdateFixedAssetStdCostType(fixedAssetStdCostTypeToBeUpdated);

		try {
			if(((FixedAssetStdCostTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetStdCostTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetStdCostTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetStdCostTypeId", fixedAssetStdCostTypeId);
		try {

			Object foundFixedAssetStdCostType = findFixedAssetStdCostTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetStdCostType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetStdCostTypeId}")
	public ResponseEntity<Object> deleteFixedAssetStdCostTypeByIdUpdated(@PathVariable String fixedAssetStdCostTypeId) throws Exception {
		DeleteFixedAssetStdCostType command = new DeleteFixedAssetStdCostType(fixedAssetStdCostTypeId);

		try {
			if (((FixedAssetStdCostTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetStdCostType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetStdCostType/\" plus one of the following: "
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