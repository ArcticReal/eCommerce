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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourcePurposes")
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
	@GetMapping("/find")
	public ResponseEntity<List<DataResourcePurpose>> findDataResourcePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourcePurposesBy query = new FindDataResourcePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourcePurpose> dataResourcePurposes =((DataResourcePurposeFound) Scheduler.execute(query).data()).getDataResourcePurposes();

		return ResponseEntity.ok().body(dataResourcePurposes);

	}

	/**
	 * creates a new DataResourcePurpose entry in the ofbiz database
	 * 
	 * @param dataResourcePurposeToBeAdded
	 *            the DataResourcePurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourcePurpose> createDataResourcePurpose(@RequestBody DataResourcePurpose dataResourcePurposeToBeAdded) throws Exception {

		AddDataResourcePurpose command = new AddDataResourcePurpose(dataResourcePurposeToBeAdded);
		DataResourcePurpose dataResourcePurpose = ((DataResourcePurposeAdded) Scheduler.execute(command).data()).getAddedDataResourcePurpose();
		
		if (dataResourcePurpose != null) 
			return successful(dataResourcePurpose);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDataResourcePurpose(@RequestBody DataResourcePurpose dataResourcePurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		dataResourcePurposeToBeUpdated.setnull(null);

		UpdateDataResourcePurpose command = new UpdateDataResourcePurpose(dataResourcePurposeToBeUpdated);

		try {
			if(((DataResourcePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourcePurposeId}")
	public ResponseEntity<DataResourcePurpose> findById(@PathVariable String dataResourcePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourcePurposeId", dataResourcePurposeId);
		try {

			List<DataResourcePurpose> foundDataResourcePurpose = findDataResourcePurposesBy(requestParams).getBody();
			if(foundDataResourcePurpose.size()==1){				return successful(foundDataResourcePurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourcePurposeId}")
	public ResponseEntity<String> deleteDataResourcePurposeByIdUpdated(@PathVariable String dataResourcePurposeId) throws Exception {
		DeleteDataResourcePurpose command = new DeleteDataResourcePurpose(dataResourcePurposeId);

		try {
			if (((DataResourcePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
