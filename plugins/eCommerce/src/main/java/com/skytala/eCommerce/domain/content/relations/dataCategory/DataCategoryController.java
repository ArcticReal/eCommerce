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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<DataCategory>> findDataCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataCategorysBy query = new FindDataCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataCategory> dataCategorys =((DataCategoryFound) Scheduler.execute(query).data()).getDataCategorys();

		return ResponseEntity.ok().body(dataCategorys);

	}

	/**
	 * creates a new DataCategory entry in the ofbiz database
	 * 
	 * @param dataCategoryToBeAdded
	 *            the DataCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataCategory> createDataCategory(@RequestBody DataCategory dataCategoryToBeAdded) throws Exception {

		AddDataCategory command = new AddDataCategory(dataCategoryToBeAdded);
		DataCategory dataCategory = ((DataCategoryAdded) Scheduler.execute(command).data()).getAddedDataCategory();
		
		if (dataCategory != null) 
			return successful(dataCategory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDataCategory(@RequestBody DataCategory dataCategoryToBeUpdated,
			@PathVariable String dataCategoryId) throws Exception {

		dataCategoryToBeUpdated.setDataCategoryId(dataCategoryId);

		UpdateDataCategory command = new UpdateDataCategory(dataCategoryToBeUpdated);

		try {
			if(((DataCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataCategoryId}")
	public ResponseEntity<DataCategory> findById(@PathVariable String dataCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataCategoryId", dataCategoryId);
		try {

			List<DataCategory> foundDataCategory = findDataCategorysBy(requestParams).getBody();
			if(foundDataCategory.size()==1){				return successful(foundDataCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataCategoryId}")
	public ResponseEntity<String> deleteDataCategoryByIdUpdated(@PathVariable String dataCategoryId) throws Exception {
		DeleteDataCategory command = new DeleteDataCategory(dataCategoryId);

		try {
			if (((DataCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
