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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourceTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<DataResourceType>> findDataResourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceTypesBy query = new FindDataResourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceType> dataResourceTypes =((DataResourceTypeFound) Scheduler.execute(query).data()).getDataResourceTypes();

		return ResponseEntity.ok().body(dataResourceTypes);

	}

	/**
	 * creates a new DataResourceType entry in the ofbiz database
	 * 
	 * @param dataResourceTypeToBeAdded
	 *            the DataResourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourceType> createDataResourceType(@RequestBody DataResourceType dataResourceTypeToBeAdded) throws Exception {

		AddDataResourceType command = new AddDataResourceType(dataResourceTypeToBeAdded);
		DataResourceType dataResourceType = ((DataResourceTypeAdded) Scheduler.execute(command).data()).getAddedDataResourceType();
		
		if (dataResourceType != null) 
			return successful(dataResourceType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDataResourceType(@RequestBody DataResourceType dataResourceTypeToBeUpdated,
			@PathVariable String dataResourceTypeId) throws Exception {

		dataResourceTypeToBeUpdated.setDataResourceTypeId(dataResourceTypeId);

		UpdateDataResourceType command = new UpdateDataResourceType(dataResourceTypeToBeUpdated);

		try {
			if(((DataResourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceTypeId}")
	public ResponseEntity<DataResourceType> findById(@PathVariable String dataResourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceTypeId", dataResourceTypeId);
		try {

			List<DataResourceType> foundDataResourceType = findDataResourceTypesBy(requestParams).getBody();
			if(foundDataResourceType.size()==1){				return successful(foundDataResourceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceTypeId}")
	public ResponseEntity<String> deleteDataResourceTypeByIdUpdated(@PathVariable String dataResourceTypeId) throws Exception {
		DeleteDataResourceType command = new DeleteDataResourceType(dataResourceTypeId);

		try {
			if (((DataResourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
