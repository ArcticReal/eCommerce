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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataTemplateTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<DataTemplateType>> findDataTemplateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataTemplateTypesBy query = new FindDataTemplateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataTemplateType> dataTemplateTypes =((DataTemplateTypeFound) Scheduler.execute(query).data()).getDataTemplateTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<DataTemplateType> createDataTemplateType(HttpServletRequest request) throws Exception {

		DataTemplateType dataTemplateTypeToBeAdded = new DataTemplateType();
		try {
			dataTemplateTypeToBeAdded = DataTemplateTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<DataTemplateType> createDataTemplateType(@RequestBody DataTemplateType dataTemplateTypeToBeAdded) throws Exception {

		AddDataTemplateType command = new AddDataTemplateType(dataTemplateTypeToBeAdded);
		DataTemplateType dataTemplateType = ((DataTemplateTypeAdded) Scheduler.execute(command).data()).getAddedDataTemplateType();
		
		if (dataTemplateType != null) 
			return successful(dataTemplateType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDataTemplateType(@RequestBody DataTemplateType dataTemplateTypeToBeUpdated,
			@PathVariable String dataTemplateTypeId) throws Exception {

		dataTemplateTypeToBeUpdated.setDataTemplateTypeId(dataTemplateTypeId);

		UpdateDataTemplateType command = new UpdateDataTemplateType(dataTemplateTypeToBeUpdated);

		try {
			if(((DataTemplateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataTemplateTypeId}")
	public ResponseEntity<DataTemplateType> findById(@PathVariable String dataTemplateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataTemplateTypeId", dataTemplateTypeId);
		try {

			List<DataTemplateType> foundDataTemplateType = findDataTemplateTypesBy(requestParams).getBody();
			if(foundDataTemplateType.size()==1){				return successful(foundDataTemplateType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataTemplateTypeId}")
	public ResponseEntity<String> deleteDataTemplateTypeByIdUpdated(@PathVariable String dataTemplateTypeId) throws Exception {
		DeleteDataTemplateType command = new DeleteDataTemplateType(dataTemplateTypeId);

		try {
			if (((DataTemplateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
