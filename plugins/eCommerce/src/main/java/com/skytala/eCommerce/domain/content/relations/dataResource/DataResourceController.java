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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResources")
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
	@GetMapping("/find")
	public ResponseEntity<List<DataResource>> findDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourcesBy query = new FindDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResource> dataResources =((DataResourceFound) Scheduler.execute(query).data()).getDataResources();

		return ResponseEntity.ok().body(dataResources);

	}

	/**
	 * creates a new DataResource entry in the ofbiz database
	 * 
	 * @param dataResourceToBeAdded
	 *            the DataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResource> createDataResource(@RequestBody DataResource dataResourceToBeAdded) throws Exception {

		AddDataResource command = new AddDataResource(dataResourceToBeAdded);
		DataResource dataResource = ((DataResourceAdded) Scheduler.execute(command).data()).getAddedDataResource();
		
		if (dataResource != null) 
			return successful(dataResource);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDataResource(@RequestBody DataResource dataResourceToBeUpdated,
			@PathVariable String dataResourceId) throws Exception {

		dataResourceToBeUpdated.setDataResourceId(dataResourceId);

		UpdateDataResource command = new UpdateDataResource(dataResourceToBeUpdated);

		try {
			if(((DataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceId}")
	public ResponseEntity<DataResource> findById(@PathVariable String dataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceId", dataResourceId);
		try {

			List<DataResource> foundDataResource = findDataResourcesBy(requestParams).getBody();
			if(foundDataResource.size()==1){				return successful(foundDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceId}")
	public ResponseEntity<String> deleteDataResourceByIdUpdated(@PathVariable String dataResourceId) throws Exception {
		DeleteDataResource command = new DeleteDataResource(dataResourceId);

		try {
			if (((DataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
