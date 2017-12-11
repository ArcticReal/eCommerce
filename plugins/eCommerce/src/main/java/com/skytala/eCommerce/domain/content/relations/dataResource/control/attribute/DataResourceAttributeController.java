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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findDataResourceAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceAttributesBy query = new FindDataResourceAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceAttribute> dataResourceAttributes =((DataResourceAttributeFound) Scheduler.execute(query).data()).getDataResourceAttributes();

		if (dataResourceAttributes.size() == 1) {
			return ResponseEntity.ok().body(dataResourceAttributes.get(0));
		}

		return ResponseEntity.ok().body(dataResourceAttributes);

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
	public ResponseEntity<Object> createDataResourceAttribute(HttpServletRequest request) throws Exception {

		DataResourceAttribute dataResourceAttributeToBeAdded = new DataResourceAttribute();
		try {
			dataResourceAttributeToBeAdded = DataResourceAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResourceAttribute(dataResourceAttributeToBeAdded);

	}

	/**
	 * creates a new DataResourceAttribute entry in the ofbiz database
	 * 
	 * @param dataResourceAttributeToBeAdded
	 *            the DataResourceAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResourceAttribute(@RequestBody DataResourceAttribute dataResourceAttributeToBeAdded) throws Exception {

		AddDataResourceAttribute command = new AddDataResourceAttribute(dataResourceAttributeToBeAdded);
		DataResourceAttribute dataResourceAttribute = ((DataResourceAttributeAdded) Scheduler.execute(command).data()).getAddedDataResourceAttribute();
		
		if (dataResourceAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResourceAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResourceAttribute could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateDataResourceAttribute(HttpServletRequest request) throws Exception {

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

		DataResourceAttribute dataResourceAttributeToBeUpdated = new DataResourceAttribute();

		try {
			dataResourceAttributeToBeUpdated = DataResourceAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResourceAttribute(dataResourceAttributeToBeUpdated, dataResourceAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDataResourceAttribute(@RequestBody DataResourceAttribute dataResourceAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		dataResourceAttributeToBeUpdated.setAttrName(attrName);

		UpdateDataResourceAttribute command = new UpdateDataResourceAttribute(dataResourceAttributeToBeUpdated);

		try {
			if(((DataResourceAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{dataResourceAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourceAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceAttributeId", dataResourceAttributeId);
		try {

			Object foundDataResourceAttribute = findDataResourceAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResourceAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{dataResourceAttributeId}")
	public ResponseEntity<Object> deleteDataResourceAttributeByIdUpdated(@PathVariable String dataResourceAttributeId) throws Exception {
		DeleteDataResourceAttribute command = new DeleteDataResourceAttribute(dataResourceAttributeId);

		try {
			if (((DataResourceAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResourceAttribute could not be deleted");

	}

}
