package com.skytala.eCommerce.domain.content.relations.dataResource.control.type;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.type.AddDataResourceType;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.type.DeleteDataResourceType;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.type.UpdateDataResourceType;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.type.DataResourceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.type.FindDataResourceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/dataResourceTypes")
public class DataResourceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourceType
	 * @return a List with the DataResourceTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDataResourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceTypesBy query = new FindDataResourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceType> dataResourceTypes =((DataResourceTypeFound) Scheduler.execute(query).data()).getDataResourceTypes();

		if (dataResourceTypes.size() == 1) {
			return ResponseEntity.ok().body(dataResourceTypes.get(0));
		}

		return ResponseEntity.ok().body(dataResourceTypes);

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
	public ResponseEntity<Object> createDataResourceType(HttpServletRequest request) throws Exception {

		DataResourceType dataResourceTypeToBeAdded = new DataResourceType();
		try {
			dataResourceTypeToBeAdded = DataResourceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResourceType(dataResourceTypeToBeAdded);

	}

	/**
	 * creates a new DataResourceType entry in the ofbiz database
	 * 
	 * @param dataResourceTypeToBeAdded
	 *            the DataResourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResourceType(@RequestBody DataResourceType dataResourceTypeToBeAdded) throws Exception {

		AddDataResourceType command = new AddDataResourceType(dataResourceTypeToBeAdded);
		DataResourceType dataResourceType = ((DataResourceTypeAdded) Scheduler.execute(command).data()).getAddedDataResourceType();
		
		if (dataResourceType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResourceType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResourceType could not be created.");
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
	public boolean updateDataResourceType(HttpServletRequest request) throws Exception {

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

		DataResourceType dataResourceTypeToBeUpdated = new DataResourceType();

		try {
			dataResourceTypeToBeUpdated = DataResourceTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResourceType(dataResourceTypeToBeUpdated, dataResourceTypeToBeUpdated.getDataResourceTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DataResourceType with the specific Id
	 * 
	 * @param dataResourceTypeToBeUpdated
	 *            the DataResourceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{dataResourceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDataResourceType(@RequestBody DataResourceType dataResourceTypeToBeUpdated,
			@PathVariable String dataResourceTypeId) throws Exception {

		dataResourceTypeToBeUpdated.setDataResourceTypeId(dataResourceTypeId);

		UpdateDataResourceType command = new UpdateDataResourceType(dataResourceTypeToBeUpdated);

		try {
			if(((DataResourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dataResourceTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceTypeId", dataResourceTypeId);
		try {

			Object foundDataResourceType = findDataResourceTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResourceType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{dataResourceTypeId}")
	public ResponseEntity<Object> deleteDataResourceTypeByIdUpdated(@PathVariable String dataResourceTypeId) throws Exception {
		DeleteDataResourceType command = new DeleteDataResourceType(dataResourceTypeId);

		try {
			if (((DataResourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResourceType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/dataResourceType/\" plus one of the following: "
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
