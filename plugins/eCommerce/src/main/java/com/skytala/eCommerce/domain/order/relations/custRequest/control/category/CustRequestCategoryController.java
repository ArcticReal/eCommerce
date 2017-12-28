package com.skytala.eCommerce.domain.order.relations.custRequest.control.category;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.category.AddCustRequestCategory;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.category.DeleteCustRequestCategory;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.category.UpdateCustRequestCategory;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.category.CustRequestCategoryAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.category.CustRequestCategoryDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.category.CustRequestCategoryFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.category.CustRequestCategoryUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.category.CustRequestCategoryMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.category.CustRequestCategory;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.category.FindCustRequestCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestCategorys")
public class CustRequestCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestCategory
	 * @return a List with the CustRequestCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestCategory>> findCustRequestCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestCategorysBy query = new FindCustRequestCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestCategory> custRequestCategorys =((CustRequestCategoryFound) Scheduler.execute(query).data()).getCustRequestCategorys();

		return ResponseEntity.ok().body(custRequestCategorys);

	}

	/**
	 * creates a new CustRequestCategory entry in the ofbiz database
	 * 
	 * @param custRequestCategoryToBeAdded
	 *            the CustRequestCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestCategory> createCustRequestCategory(@RequestBody CustRequestCategory custRequestCategoryToBeAdded) throws Exception {

		AddCustRequestCategory command = new AddCustRequestCategory(custRequestCategoryToBeAdded);
		CustRequestCategory custRequestCategory = ((CustRequestCategoryAdded) Scheduler.execute(command).data()).getAddedCustRequestCategory();
		
		if (custRequestCategory != null) 
			return successful(custRequestCategory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestCategory with the specific Id
	 * 
	 * @param custRequestCategoryToBeUpdated
	 *            the CustRequestCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestCategory(@RequestBody CustRequestCategory custRequestCategoryToBeUpdated,
			@PathVariable String custRequestCategoryId) throws Exception {

		custRequestCategoryToBeUpdated.setCustRequestCategoryId(custRequestCategoryId);

		UpdateCustRequestCategory command = new UpdateCustRequestCategory(custRequestCategoryToBeUpdated);

		try {
			if(((CustRequestCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestCategoryId}")
	public ResponseEntity<CustRequestCategory> findById(@PathVariable String custRequestCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestCategoryId", custRequestCategoryId);
		try {

			List<CustRequestCategory> foundCustRequestCategory = findCustRequestCategorysBy(requestParams).getBody();
			if(foundCustRequestCategory.size()==1){				return successful(foundCustRequestCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestCategoryId}")
	public ResponseEntity<String> deleteCustRequestCategoryByIdUpdated(@PathVariable String custRequestCategoryId) throws Exception {
		DeleteCustRequestCategory command = new DeleteCustRequestCategory(custRequestCategoryId);

		try {
			if (((CustRequestCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
