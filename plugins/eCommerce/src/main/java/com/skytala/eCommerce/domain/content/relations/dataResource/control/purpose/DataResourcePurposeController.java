package com.skytala.eCommerce.domain.content.relations.dataResource.control.purpose;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.purpose.AddDataResourcePurpose;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.purpose.DeleteDataResourcePurpose;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.purpose.UpdateDataResourcePurpose;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose.DataResourcePurposeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose.DataResourcePurposeDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose.DataResourcePurposeFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose.DataResourcePurposeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.purpose.DataResourcePurposeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.purpose.DataResourcePurpose;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.purpose.FindDataResourcePurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/dataResourcePurposes")
public class DataResourcePurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourcePurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourcePurpose
	 * @return a List with the DataResourcePurposes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDataResourcePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourcePurposesBy query = new FindDataResourcePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourcePurpose> dataResourcePurposes =((DataResourcePurposeFound) Scheduler.execute(query).data()).getDataResourcePurposes();

		if (dataResourcePurposes.size() == 1) {
			return ResponseEntity.ok().body(dataResourcePurposes.get(0));
		}

		return ResponseEntity.ok().body(dataResourcePurposes);

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
	public ResponseEntity<Object> createDataResourcePurpose(HttpServletRequest request) throws Exception {

		DataResourcePurpose dataResourcePurposeToBeAdded = new DataResourcePurpose();
		try {
			dataResourcePurposeToBeAdded = DataResourcePurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResourcePurpose(dataResourcePurposeToBeAdded);

	}

	/**
	 * creates a new DataResourcePurpose entry in the ofbiz database
	 * 
	 * @param dataResourcePurposeToBeAdded
	 *            the DataResourcePurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResourcePurpose(@RequestBody DataResourcePurpose dataResourcePurposeToBeAdded) throws Exception {

		AddDataResourcePurpose command = new AddDataResourcePurpose(dataResourcePurposeToBeAdded);
		DataResourcePurpose dataResourcePurpose = ((DataResourcePurposeAdded) Scheduler.execute(command).data()).getAddedDataResourcePurpose();
		
		if (dataResourcePurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResourcePurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResourcePurpose could not be created.");
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
	public boolean updateDataResourcePurpose(HttpServletRequest request) throws Exception {

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

		DataResourcePurpose dataResourcePurposeToBeUpdated = new DataResourcePurpose();

		try {
			dataResourcePurposeToBeUpdated = DataResourcePurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResourcePurpose(dataResourcePurposeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DataResourcePurpose with the specific Id
	 * 
	 * @param dataResourcePurposeToBeUpdated
	 *            the DataResourcePurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDataResourcePurpose(@RequestBody DataResourcePurpose dataResourcePurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		dataResourcePurposeToBeUpdated.setnull(null);

		UpdateDataResourcePurpose command = new UpdateDataResourcePurpose(dataResourcePurposeToBeUpdated);

		try {
			if(((DataResourcePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dataResourcePurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourcePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourcePurposeId", dataResourcePurposeId);
		try {

			Object foundDataResourcePurpose = findDataResourcePurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResourcePurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{dataResourcePurposeId}")
	public ResponseEntity<Object> deleteDataResourcePurposeByIdUpdated(@PathVariable String dataResourcePurposeId) throws Exception {
		DeleteDataResourcePurpose command = new DeleteDataResourcePurpose(dataResourcePurposeId);

		try {
			if (((DataResourcePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResourcePurpose could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/dataResourcePurpose/\" plus one of the following: "
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
