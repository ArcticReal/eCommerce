package com.skytala.eCommerce.domain.content.relations.dataTemplateType;

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
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.command.AddDataTemplateType;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.command.DeleteDataTemplateType;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.command.UpdateDataTemplateType;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeAdded;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeFound;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.event.DataTemplateTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.mapper.DataTemplateTypeMapper;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.query.FindDataTemplateTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/dataTemplateTypes")
public class DataTemplateTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataTemplateTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataTemplateType
	 * @return a List with the DataTemplateTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDataTemplateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataTemplateTypesBy query = new FindDataTemplateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataTemplateType> dataTemplateTypes =((DataTemplateTypeFound) Scheduler.execute(query).data()).getDataTemplateTypes();

		if (dataTemplateTypes.size() == 1) {
			return ResponseEntity.ok().body(dataTemplateTypes.get(0));
		}

		return ResponseEntity.ok().body(dataTemplateTypes);

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
	public ResponseEntity<Object> createDataTemplateType(HttpServletRequest request) throws Exception {

		DataTemplateType dataTemplateTypeToBeAdded = new DataTemplateType();
		try {
			dataTemplateTypeToBeAdded = DataTemplateTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataTemplateType(dataTemplateTypeToBeAdded);

	}

	/**
	 * creates a new DataTemplateType entry in the ofbiz database
	 * 
	 * @param dataTemplateTypeToBeAdded
	 *            the DataTemplateType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataTemplateType(@RequestBody DataTemplateType dataTemplateTypeToBeAdded) throws Exception {

		AddDataTemplateType command = new AddDataTemplateType(dataTemplateTypeToBeAdded);
		DataTemplateType dataTemplateType = ((DataTemplateTypeAdded) Scheduler.execute(command).data()).getAddedDataTemplateType();
		
		if (dataTemplateType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataTemplateType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataTemplateType could not be created.");
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
	public boolean updateDataTemplateType(HttpServletRequest request) throws Exception {

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

		DataTemplateType dataTemplateTypeToBeUpdated = new DataTemplateType();

		try {
			dataTemplateTypeToBeUpdated = DataTemplateTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataTemplateType(dataTemplateTypeToBeUpdated, dataTemplateTypeToBeUpdated.getDataTemplateTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DataTemplateType with the specific Id
	 * 
	 * @param dataTemplateTypeToBeUpdated
	 *            the DataTemplateType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{dataTemplateTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDataTemplateType(@RequestBody DataTemplateType dataTemplateTypeToBeUpdated,
			@PathVariable String dataTemplateTypeId) throws Exception {

		dataTemplateTypeToBeUpdated.setDataTemplateTypeId(dataTemplateTypeId);

		UpdateDataTemplateType command = new UpdateDataTemplateType(dataTemplateTypeToBeUpdated);

		try {
			if(((DataTemplateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dataTemplateTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String dataTemplateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataTemplateTypeId", dataTemplateTypeId);
		try {

			Object foundDataTemplateType = findDataTemplateTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataTemplateType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{dataTemplateTypeId}")
	public ResponseEntity<Object> deleteDataTemplateTypeByIdUpdated(@PathVariable String dataTemplateTypeId) throws Exception {
		DeleteDataTemplateType command = new DeleteDataTemplateType(dataTemplateTypeId);

		try {
			if (((DataTemplateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataTemplateType could not be deleted");

	}

}
