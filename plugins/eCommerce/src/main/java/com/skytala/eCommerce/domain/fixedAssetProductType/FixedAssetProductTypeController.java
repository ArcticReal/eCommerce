package com.skytala.eCommerce.domain.fixedAssetProductType;

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
import com.skytala.eCommerce.domain.fixedAssetProductType.command.AddFixedAssetProductType;
import com.skytala.eCommerce.domain.fixedAssetProductType.command.DeleteFixedAssetProductType;
import com.skytala.eCommerce.domain.fixedAssetProductType.command.UpdateFixedAssetProductType;
import com.skytala.eCommerce.domain.fixedAssetProductType.event.FixedAssetProductTypeAdded;
import com.skytala.eCommerce.domain.fixedAssetProductType.event.FixedAssetProductTypeDeleted;
import com.skytala.eCommerce.domain.fixedAssetProductType.event.FixedAssetProductTypeFound;
import com.skytala.eCommerce.domain.fixedAssetProductType.event.FixedAssetProductTypeUpdated;
import com.skytala.eCommerce.domain.fixedAssetProductType.mapper.FixedAssetProductTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetProductType.model.FixedAssetProductType;
import com.skytala.eCommerce.domain.fixedAssetProductType.query.FindFixedAssetProductTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetProductTypes")
public class FixedAssetProductTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetProductTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetProductType
	 * @return a List with the FixedAssetProductTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetProductTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetProductTypesBy query = new FindFixedAssetProductTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetProductType> fixedAssetProductTypes =((FixedAssetProductTypeFound) Scheduler.execute(query).data()).getFixedAssetProductTypes();

		if (fixedAssetProductTypes.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetProductTypes.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetProductTypes);

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
	public ResponseEntity<Object> createFixedAssetProductType(HttpServletRequest request) throws Exception {

		FixedAssetProductType fixedAssetProductTypeToBeAdded = new FixedAssetProductType();
		try {
			fixedAssetProductTypeToBeAdded = FixedAssetProductTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetProductType(fixedAssetProductTypeToBeAdded);

	}

	/**
	 * creates a new FixedAssetProductType entry in the ofbiz database
	 * 
	 * @param fixedAssetProductTypeToBeAdded
	 *            the FixedAssetProductType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetProductType(@RequestBody FixedAssetProductType fixedAssetProductTypeToBeAdded) throws Exception {

		AddFixedAssetProductType command = new AddFixedAssetProductType(fixedAssetProductTypeToBeAdded);
		FixedAssetProductType fixedAssetProductType = ((FixedAssetProductTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetProductType();
		
		if (fixedAssetProductType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetProductType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetProductType could not be created.");
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
	public boolean updateFixedAssetProductType(HttpServletRequest request) throws Exception {

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

		FixedAssetProductType fixedAssetProductTypeToBeUpdated = new FixedAssetProductType();

		try {
			fixedAssetProductTypeToBeUpdated = FixedAssetProductTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetProductType(fixedAssetProductTypeToBeUpdated, fixedAssetProductTypeToBeUpdated.getFixedAssetProductTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetProductType with the specific Id
	 * 
	 * @param fixedAssetProductTypeToBeUpdated
	 *            the FixedAssetProductType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetProductTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetProductType(@RequestBody FixedAssetProductType fixedAssetProductTypeToBeUpdated,
			@PathVariable String fixedAssetProductTypeId) throws Exception {

		fixedAssetProductTypeToBeUpdated.setFixedAssetProductTypeId(fixedAssetProductTypeId);

		UpdateFixedAssetProductType command = new UpdateFixedAssetProductType(fixedAssetProductTypeToBeUpdated);

		try {
			if(((FixedAssetProductTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetProductTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetProductTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetProductTypeId", fixedAssetProductTypeId);
		try {

			Object foundFixedAssetProductType = findFixedAssetProductTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetProductType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetProductTypeId}")
	public ResponseEntity<Object> deleteFixedAssetProductTypeByIdUpdated(@PathVariable String fixedAssetProductTypeId) throws Exception {
		DeleteFixedAssetProductType command = new DeleteFixedAssetProductType(fixedAssetProductTypeId);

		try {
			if (((FixedAssetProductTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetProductType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetProductType/\" plus one of the following: "
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
