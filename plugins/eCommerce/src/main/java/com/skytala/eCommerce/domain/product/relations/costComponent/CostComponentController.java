package com.skytala.eCommerce.domain.product.relations.costComponent;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.AddCostComponent;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.DeleteCostComponent;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.UpdateCostComponent;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.CostComponentMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.FindCostComponentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/costComponents")
public class CostComponentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponent
	 * @return a List with the CostComponents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCostComponentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentsBy query = new FindCostComponentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponent> costComponents =((CostComponentFound) Scheduler.execute(query).data()).getCostComponents();

		if (costComponents.size() == 1) {
			return ResponseEntity.ok().body(costComponents.get(0));
		}

		return ResponseEntity.ok().body(costComponents);

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
	public ResponseEntity<Object> createCostComponent(HttpServletRequest request) throws Exception {

		CostComponent costComponentToBeAdded = new CostComponent();
		try {
			costComponentToBeAdded = CostComponentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCostComponent(costComponentToBeAdded);

	}

	/**
	 * creates a new CostComponent entry in the ofbiz database
	 * 
	 * @param costComponentToBeAdded
	 *            the CostComponent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCostComponent(@RequestBody CostComponent costComponentToBeAdded) throws Exception {

		AddCostComponent command = new AddCostComponent(costComponentToBeAdded);
		CostComponent costComponent = ((CostComponentAdded) Scheduler.execute(command).data()).getAddedCostComponent();
		
		if (costComponent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(costComponent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CostComponent could not be created.");
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
	public boolean updateCostComponent(HttpServletRequest request) throws Exception {

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

		CostComponent costComponentToBeUpdated = new CostComponent();

		try {
			costComponentToBeUpdated = CostComponentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCostComponent(costComponentToBeUpdated, costComponentToBeUpdated.getCostComponentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CostComponent with the specific Id
	 * 
	 * @param costComponentToBeUpdated
	 *            the CostComponent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{costComponentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCostComponent(@RequestBody CostComponent costComponentToBeUpdated,
			@PathVariable String costComponentId) throws Exception {

		costComponentToBeUpdated.setCostComponentId(costComponentId);

		UpdateCostComponent command = new UpdateCostComponent(costComponentToBeUpdated);

		try {
			if(((CostComponentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{costComponentId}")
	public ResponseEntity<Object> findById(@PathVariable String costComponentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentId", costComponentId);
		try {

			Object foundCostComponent = findCostComponentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCostComponent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{costComponentId}")
	public ResponseEntity<Object> deleteCostComponentByIdUpdated(@PathVariable String costComponentId) throws Exception {
		DeleteCostComponent command = new DeleteCostComponent(costComponentId);

		try {
			if (((CostComponentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CostComponent could not be deleted");

	}

}
