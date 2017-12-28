package com.skytala.eCommerce.domain.content.relations.dataResource.control.typeAttr;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.typeAttr.AddDataResourceTypeAttr;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.typeAttr.DeleteDataResourceTypeAttr;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.typeAttr.UpdateDataResourceTypeAttr;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr.DataResourceTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr.DataResourceTypeAttrDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr.DataResourceTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr.DataResourceTypeAttrUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.typeAttr.DataResourceTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.typeAttr.DataResourceTypeAttr;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.typeAttr.FindDataResourceTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourceTypeAttrs")
public class DataResourceTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourceTypeAttr
	 * @return a List with the DataResourceTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DataResourceTypeAttr>> findDataResourceTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceTypeAttrsBy query = new FindDataResourceTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceTypeAttr> dataResourceTypeAttrs =((DataResourceTypeAttrFound) Scheduler.execute(query).data()).getDataResourceTypeAttrs();

		return ResponseEntity.ok().body(dataResourceTypeAttrs);

	}

	/**
	 * creates a new DataResourceTypeAttr entry in the ofbiz database
	 * 
	 * @param dataResourceTypeAttrToBeAdded
	 *            the DataResourceTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourceTypeAttr> createDataResourceTypeAttr(@RequestBody DataResourceTypeAttr dataResourceTypeAttrToBeAdded) throws Exception {

		AddDataResourceTypeAttr command = new AddDataResourceTypeAttr(dataResourceTypeAttrToBeAdded);
		DataResourceTypeAttr dataResourceTypeAttr = ((DataResourceTypeAttrAdded) Scheduler.execute(command).data()).getAddedDataResourceTypeAttr();
		
		if (dataResourceTypeAttr != null) 
			return successful(dataResourceTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DataResourceTypeAttr with the specific Id
	 * 
	 * @param dataResourceTypeAttrToBeUpdated
	 *            the DataResourceTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDataResourceTypeAttr(@RequestBody DataResourceTypeAttr dataResourceTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		dataResourceTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateDataResourceTypeAttr command = new UpdateDataResourceTypeAttr(dataResourceTypeAttrToBeUpdated);

		try {
			if(((DataResourceTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceTypeAttrId}")
	public ResponseEntity<DataResourceTypeAttr> findById(@PathVariable String dataResourceTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceTypeAttrId", dataResourceTypeAttrId);
		try {

			List<DataResourceTypeAttr> foundDataResourceTypeAttr = findDataResourceTypeAttrsBy(requestParams).getBody();
			if(foundDataResourceTypeAttr.size()==1){				return successful(foundDataResourceTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceTypeAttrId}")
	public ResponseEntity<String> deleteDataResourceTypeAttrByIdUpdated(@PathVariable String dataResourceTypeAttrId) throws Exception {
		DeleteDataResourceTypeAttr command = new DeleteDataResourceTypeAttr(dataResourceTypeAttrId);

		try {
			if (((DataResourceTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
