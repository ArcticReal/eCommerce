package com.skytala.eCommerce.domain.content.relations.dataResource.control.attribute;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.attribute.AddDataResourceAttribute;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.attribute.DeleteDataResourceAttribute;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.attribute.UpdateDataResourceAttribute;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute.DataResourceAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.attribute.DataResourceAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.attribute.FindDataResourceAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourceAttributes")
public class DataResourceAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourceAttribute
	 * @return a List with the DataResourceAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DataResourceAttribute>> findDataResourceAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceAttributesBy query = new FindDataResourceAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceAttribute> dataResourceAttributes =((DataResourceAttributeFound) Scheduler.execute(query).data()).getDataResourceAttributes();

		return ResponseEntity.ok().body(dataResourceAttributes);

	}

	/**
	 * creates a new DataResourceAttribute entry in the ofbiz database
	 * 
	 * @param dataResourceAttributeToBeAdded
	 *            the DataResourceAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourceAttribute> createDataResourceAttribute(@RequestBody DataResourceAttribute dataResourceAttributeToBeAdded) throws Exception {

		AddDataResourceAttribute command = new AddDataResourceAttribute(dataResourceAttributeToBeAdded);
		DataResourceAttribute dataResourceAttribute = ((DataResourceAttributeAdded) Scheduler.execute(command).data()).getAddedDataResourceAttribute();
		
		if (dataResourceAttribute != null) 
			return successful(dataResourceAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DataResourceAttribute with the specific Id
	 * 
	 * @param dataResourceAttributeToBeUpdated
	 *            the DataResourceAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDataResourceAttribute(@RequestBody DataResourceAttribute dataResourceAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		dataResourceAttributeToBeUpdated.setAttrName(attrName);

		UpdateDataResourceAttribute command = new UpdateDataResourceAttribute(dataResourceAttributeToBeUpdated);

		try {
			if(((DataResourceAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceAttributeId}")
	public ResponseEntity<DataResourceAttribute> findById(@PathVariable String dataResourceAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceAttributeId", dataResourceAttributeId);
		try {

			List<DataResourceAttribute> foundDataResourceAttribute = findDataResourceAttributesBy(requestParams).getBody();
			if(foundDataResourceAttribute.size()==1){				return successful(foundDataResourceAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceAttributeId}")
	public ResponseEntity<String> deleteDataResourceAttributeByIdUpdated(@PathVariable String dataResourceAttributeId) throws Exception {
		DeleteDataResourceAttribute command = new DeleteDataResourceAttribute(dataResourceAttributeId);

		try {
			if (((DataResourceAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
