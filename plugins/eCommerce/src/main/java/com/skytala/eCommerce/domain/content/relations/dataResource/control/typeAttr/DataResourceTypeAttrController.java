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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findDataResourceTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceTypeAttrsBy query = new FindDataResourceTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceTypeAttr> dataResourceTypeAttrs =((DataResourceTypeAttrFound) Scheduler.execute(query).data()).getDataResourceTypeAttrs();

		if (dataResourceTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(dataResourceTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(dataResourceTypeAttrs);

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
	public ResponseEntity<Object> createDataResourceTypeAttr(HttpServletRequest request) throws Exception {

		DataResourceTypeAttr dataResourceTypeAttrToBeAdded = new DataResourceTypeAttr();
		try {
			dataResourceTypeAttrToBeAdded = DataResourceTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResourceTypeAttr(dataResourceTypeAttrToBeAdded);

	}

	/**
	 * creates a new DataResourceTypeAttr entry in the ofbiz database
	 * 
	 * @param dataResourceTypeAttrToBeAdded
	 *            the DataResourceTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResourceTypeAttr(@RequestBody DataResourceTypeAttr dataResourceTypeAttrToBeAdded) throws Exception {

		AddDataResourceTypeAttr command = new AddDataResourceTypeAttr(dataResourceTypeAttrToBeAdded);
		DataResourceTypeAttr dataResourceTypeAttr = ((DataResourceTypeAttrAdded) Scheduler.execute(command).data()).getAddedDataResourceTypeAttr();
		
		if (dataResourceTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResourceTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResourceTypeAttr could not be created.");
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
	public boolean updateDataResourceTypeAttr(HttpServletRequest request) throws Exception {

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

		DataResourceTypeAttr dataResourceTypeAttrToBeUpdated = new DataResourceTypeAttr();

		try {
			dataResourceTypeAttrToBeUpdated = DataResourceTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResourceTypeAttr(dataResourceTypeAttrToBeUpdated, dataResourceTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDataResourceTypeAttr(@RequestBody DataResourceTypeAttr dataResourceTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		dataResourceTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateDataResourceTypeAttr command = new UpdateDataResourceTypeAttr(dataResourceTypeAttrToBeUpdated);

		try {
			if(((DataResourceTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{dataResourceTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourceTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceTypeAttrId", dataResourceTypeAttrId);
		try {

			Object foundDataResourceTypeAttr = findDataResourceTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResourceTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{dataResourceTypeAttrId}")
	public ResponseEntity<Object> deleteDataResourceTypeAttrByIdUpdated(@PathVariable String dataResourceTypeAttrId) throws Exception {
		DeleteDataResourceTypeAttr command = new DeleteDataResourceTypeAttr(dataResourceTypeAttrId);

		try {
			if (((DataResourceTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResourceTypeAttr could not be deleted");

	}

}
