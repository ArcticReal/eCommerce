package com.skytala.eCommerce.domain.product.relations.costComponent.control.type;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.type.AddCostComponentType;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.type.DeleteCostComponentType;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.type.UpdateCostComponentType;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.type.CostComponentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.type.CostComponentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.type.CostComponentTypeFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.type.CostComponentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.type.CostComponentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.type.CostComponentType;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.type.FindCostComponentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/costComponent/costComponentTypes")
public class CostComponentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponentType
	 * @return a List with the CostComponentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CostComponentType>> findCostComponentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentTypesBy query = new FindCostComponentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentType> costComponentTypes =((CostComponentTypeFound) Scheduler.execute(query).data()).getCostComponentTypes();

		return ResponseEntity.ok().body(costComponentTypes);

	}

	/**
	 * creates a new CostComponentType entry in the ofbiz database
	 * 
	 * @param costComponentTypeToBeAdded
	 *            the CostComponentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CostComponentType> createCostComponentType(@RequestBody CostComponentType costComponentTypeToBeAdded) throws Exception {

		AddCostComponentType command = new AddCostComponentType(costComponentTypeToBeAdded);
		CostComponentType costComponentType = ((CostComponentTypeAdded) Scheduler.execute(command).data()).getAddedCostComponentType();
		
		if (costComponentType != null) 
			return successful(costComponentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CostComponentType with the specific Id
	 * 
	 * @param costComponentTypeToBeUpdated
	 *            the CostComponentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{costComponentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCostComponentType(@RequestBody CostComponentType costComponentTypeToBeUpdated,
			@PathVariable String costComponentTypeId) throws Exception {

		costComponentTypeToBeUpdated.setCostComponentTypeId(costComponentTypeId);

		UpdateCostComponentType command = new UpdateCostComponentType(costComponentTypeToBeUpdated);

		try {
			if(((CostComponentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{costComponentTypeId}")
	public ResponseEntity<CostComponentType> findById(@PathVariable String costComponentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentTypeId", costComponentTypeId);
		try {

			List<CostComponentType> foundCostComponentType = findCostComponentTypesBy(requestParams).getBody();
			if(foundCostComponentType.size()==1){				return successful(foundCostComponentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{costComponentTypeId}")
	public ResponseEntity<String> deleteCostComponentTypeByIdUpdated(@PathVariable String costComponentTypeId) throws Exception {
		DeleteCostComponentType command = new DeleteCostComponentType(costComponentTypeId);

		try {
			if (((CostComponentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
