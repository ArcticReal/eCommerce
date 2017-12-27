package com.skytala.eCommerce.domain.content.relations.dataResource.control.metaData;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.metaData.AddDataResourceMetaData;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.metaData.DeleteDataResourceMetaData;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.metaData.UpdateDataResourceMetaData;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.metaData.DataResourceMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.metaData.FindDataResourceMetaDatasBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourceMetaDatas")
public class DataResourceMetaDataController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceMetaDataController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourceMetaData
	 * @return a List with the DataResourceMetaDatas
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DataResourceMetaData>> findDataResourceMetaDatasBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceMetaDatasBy query = new FindDataResourceMetaDatasBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceMetaData> dataResourceMetaDatas =((DataResourceMetaDataFound) Scheduler.execute(query).data()).getDataResourceMetaDatas();

		return ResponseEntity.ok().body(dataResourceMetaDatas);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<DataResourceMetaData> createDataResourceMetaData(HttpServletRequest request) throws Exception {

		DataResourceMetaData dataResourceMetaDataToBeAdded = new DataResourceMetaData();
		try {
			dataResourceMetaDataToBeAdded = DataResourceMetaDataMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createDataResourceMetaData(dataResourceMetaDataToBeAdded);

	}

	/**
	 * creates a new DataResourceMetaData entry in the ofbiz database
	 * 
	 * @param dataResourceMetaDataToBeAdded
	 *            the DataResourceMetaData thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourceMetaData> createDataResourceMetaData(@RequestBody DataResourceMetaData dataResourceMetaDataToBeAdded) throws Exception {

		AddDataResourceMetaData command = new AddDataResourceMetaData(dataResourceMetaDataToBeAdded);
		DataResourceMetaData dataResourceMetaData = ((DataResourceMetaDataAdded) Scheduler.execute(command).data()).getAddedDataResourceMetaData();
		
		if (dataResourceMetaData != null) 
			return successful(dataResourceMetaData);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DataResourceMetaData with the specific Id
	 * 
	 * @param dataResourceMetaDataToBeUpdated
	 *            the DataResourceMetaData thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDataResourceMetaData(@RequestBody DataResourceMetaData dataResourceMetaDataToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		dataResourceMetaDataToBeUpdated.setnull(null);

		UpdateDataResourceMetaData command = new UpdateDataResourceMetaData(dataResourceMetaDataToBeUpdated);

		try {
			if(((DataResourceMetaDataUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceMetaDataId}")
	public ResponseEntity<DataResourceMetaData> findById(@PathVariable String dataResourceMetaDataId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceMetaDataId", dataResourceMetaDataId);
		try {

			List<DataResourceMetaData> foundDataResourceMetaData = findDataResourceMetaDatasBy(requestParams).getBody();
			if(foundDataResourceMetaData.size()==1){				return successful(foundDataResourceMetaData.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceMetaDataId}")
	public ResponseEntity<String> deleteDataResourceMetaDataByIdUpdated(@PathVariable String dataResourceMetaDataId) throws Exception {
		DeleteDataResourceMetaData command = new DeleteDataResourceMetaData(dataResourceMetaDataId);

		try {
			if (((DataResourceMetaDataDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
