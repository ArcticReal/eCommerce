package com.skytala.eCommerce.domain.content.relations.dataCategory;

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
import com.skytala.eCommerce.domain.content.relations.dataCategory.command.AddDataCategory;
import com.skytala.eCommerce.domain.content.relations.dataCategory.command.DeleteDataCategory;
import com.skytala.eCommerce.domain.content.relations.dataCategory.command.UpdateDataCategory;
import com.skytala.eCommerce.domain.content.relations.dataCategory.event.DataCategoryAdded;
import com.skytala.eCommerce.domain.content.relations.dataCategory.event.DataCategoryDeleted;
import com.skytala.eCommerce.domain.content.relations.dataCategory.event.DataCategoryFound;
import com.skytala.eCommerce.domain.content.relations.dataCategory.event.DataCategoryUpdated;
import com.skytala.eCommerce.domain.content.relations.dataCategory.mapper.DataCategoryMapper;
import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;
import com.skytala.eCommerce.domain.content.relations.dataCategory.query.FindDataCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/dataCategorys")
public class DataCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataCategory
	 * @return a List with the DataCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDataCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataCategorysBy query = new FindDataCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataCategory> dataCategorys =((DataCategoryFound) Scheduler.execute(query).data()).getDataCategorys();

		if (dataCategorys.size() == 1) {
			return ResponseEntity.ok().body(dataCategorys.get(0));
		}

		return ResponseEntity.ok().body(dataCategorys);

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
	public ResponseEntity<Object> createDataCategory(HttpServletRequest request) throws Exception {

		DataCategory dataCategoryToBeAdded = new DataCategory();
		try {
			dataCategoryToBeAdded = DataCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataCategory(dataCategoryToBeAdded);

	}

	/**
	 * creates a new DataCategory entry in the ofbiz database
	 * 
	 * @param dataCategoryToBeAdded
	 *            the DataCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataCategory(@RequestBody DataCategory dataCategoryToBeAdded) throws Exception {

		AddDataCategory command = new AddDataCategory(dataCategoryToBeAdded);
		DataCategory dataCategory = ((DataCategoryAdded) Scheduler.execute(command).data()).getAddedDataCategory();
		
		if (dataCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataCategory could not be created.");
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
	public boolean updateDataCategory(HttpServletRequest request) throws Exception {

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

		DataCategory dataCategoryToBeUpdated = new DataCategory();

		try {
			dataCategoryToBeUpdated = DataCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataCategory(dataCategoryToBeUpdated, dataCategoryToBeUpdated.getDataCategoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DataCategory with the specific Id
	 * 
	 * @param dataCategoryToBeUpdated
	 *            the DataCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{dataCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDataCategory(@RequestBody DataCategory dataCategoryToBeUpdated,
			@PathVariable String dataCategoryId) throws Exception {

		dataCategoryToBeUpdated.setDataCategoryId(dataCategoryId);

		UpdateDataCategory command = new UpdateDataCategory(dataCategoryToBeUpdated);

		try {
			if(((DataCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dataCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String dataCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataCategoryId", dataCategoryId);
		try {

			Object foundDataCategory = findDataCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{dataCategoryId}")
	public ResponseEntity<Object> deleteDataCategoryByIdUpdated(@PathVariable String dataCategoryId) throws Exception {
		DeleteDataCategory command = new DeleteDataCategory(dataCategoryId);

		try {
			if (((DataCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataCategory could not be deleted");

	}

}
