package com.skytala.eCommerce.domain.content.relations.dataResource;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.AddDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.DeleteDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.UpdateDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.DataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.FindDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/dataResources")
public class DataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResource
	 * @return a List with the DataResources
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourcesBy query = new FindDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResource> dataResources =((DataResourceFound) Scheduler.execute(query).data()).getDataResources();

		if (dataResources.size() == 1) {
			return ResponseEntity.ok().body(dataResources.get(0));
		}

		return ResponseEntity.ok().body(dataResources);

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
	public ResponseEntity<Object> createDataResource(HttpServletRequest request) throws Exception {

		DataResource dataResourceToBeAdded = new DataResource();
		try {
			dataResourceToBeAdded = DataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResource(dataResourceToBeAdded);

	}

	/**
	 * creates a new DataResource entry in the ofbiz database
	 * 
	 * @param dataResourceToBeAdded
	 *            the DataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResource(@RequestBody DataResource dataResourceToBeAdded) throws Exception {

		AddDataResource command = new AddDataResource(dataResourceToBeAdded);
		DataResource dataResource = ((DataResourceAdded) Scheduler.execute(command).data()).getAddedDataResource();
		
		if (dataResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResource could not be created.");
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
	public boolean updateDataResource(HttpServletRequest request) throws Exception {

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

		DataResource dataResourceToBeUpdated = new DataResource();

		try {
			dataResourceToBeUpdated = DataResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResource(dataResourceToBeUpdated, dataResourceToBeUpdated.getDataResourceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DataResource with the specific Id
	 * 
	 * @param dataResourceToBeUpdated
	 *            the DataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{dataResourceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDataResource(@RequestBody DataResource dataResourceToBeUpdated,
			@PathVariable String dataResourceId) throws Exception {

		dataResourceToBeUpdated.setDataResourceId(dataResourceId);

		UpdateDataResource command = new UpdateDataResource(dataResourceToBeUpdated);

		try {
			if(((DataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dataResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceId", dataResourceId);
		try {

			Object foundDataResource = findDataResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{dataResourceId}")
	public ResponseEntity<Object> deleteDataResourceByIdUpdated(@PathVariable String dataResourceId) throws Exception {
		DeleteDataResource command = new DeleteDataResource(dataResourceId);

		try {
			if (((DataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResource could not be deleted");

	}

}
