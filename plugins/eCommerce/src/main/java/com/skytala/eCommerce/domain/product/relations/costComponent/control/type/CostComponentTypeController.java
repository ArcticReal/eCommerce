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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findCostComponentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentTypesBy query = new FindCostComponentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentType> costComponentTypes =((CostComponentTypeFound) Scheduler.execute(query).data()).getCostComponentTypes();

		if (costComponentTypes.size() == 1) {
			return ResponseEntity.ok().body(costComponentTypes.get(0));
		}

		return ResponseEntity.ok().body(costComponentTypes);

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
	public ResponseEntity<Object> createCostComponentType(HttpServletRequest request) throws Exception {

		CostComponentType costComponentTypeToBeAdded = new CostComponentType();
		try {
			costComponentTypeToBeAdded = CostComponentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCostComponentType(costComponentTypeToBeAdded);

	}

	/**
	 * creates a new CostComponentType entry in the ofbiz database
	 * 
	 * @param costComponentTypeToBeAdded
	 *            the CostComponentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCostComponentType(@RequestBody CostComponentType costComponentTypeToBeAdded) throws Exception {

		AddCostComponentType command = new AddCostComponentType(costComponentTypeToBeAdded);
		CostComponentType costComponentType = ((CostComponentTypeAdded) Scheduler.execute(command).data()).getAddedCostComponentType();
		
		if (costComponentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(costComponentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CostComponentType could not be created.");
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
	public boolean updateCostComponentType(HttpServletRequest request) throws Exception {

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

		CostComponentType costComponentTypeToBeUpdated = new CostComponentType();

		try {
			costComponentTypeToBeUpdated = CostComponentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCostComponentType(costComponentTypeToBeUpdated, costComponentTypeToBeUpdated.getCostComponentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCostComponentType(@RequestBody CostComponentType costComponentTypeToBeUpdated,
			@PathVariable String costComponentTypeId) throws Exception {

		costComponentTypeToBeUpdated.setCostComponentTypeId(costComponentTypeId);

		UpdateCostComponentType command = new UpdateCostComponentType(costComponentTypeToBeUpdated);

		try {
			if(((CostComponentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{costComponentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String costComponentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentTypeId", costComponentTypeId);
		try {

			Object foundCostComponentType = findCostComponentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCostComponentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{costComponentTypeId}")
	public ResponseEntity<Object> deleteCostComponentTypeByIdUpdated(@PathVariable String costComponentTypeId) throws Exception {
		DeleteCostComponentType command = new DeleteCostComponentType(costComponentTypeId);

		try {
			if (((CostComponentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CostComponentType could not be deleted");

	}

}
