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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CostComponent>> findCostComponentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentsBy query = new FindCostComponentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponent> costComponents =((CostComponentFound) Scheduler.execute(query).data()).getCostComponents();

		return ResponseEntity.ok().body(costComponents);

	}

	/**
	 * creates a new CostComponent entry in the ofbiz database
	 * 
	 * @param costComponentToBeAdded
	 *            the CostComponent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CostComponent> createCostComponent(@RequestBody CostComponent costComponentToBeAdded) throws Exception {

		AddCostComponent command = new AddCostComponent(costComponentToBeAdded);
		CostComponent costComponent = ((CostComponentAdded) Scheduler.execute(command).data()).getAddedCostComponent();
		
		if (costComponent != null) 
			return successful(costComponent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCostComponent(@RequestBody CostComponent costComponentToBeUpdated,
			@PathVariable String costComponentId) throws Exception {

		costComponentToBeUpdated.setCostComponentId(costComponentId);

		UpdateCostComponent command = new UpdateCostComponent(costComponentToBeUpdated);

		try {
			if(((CostComponentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{costComponentId}")
	public ResponseEntity<CostComponent> findById(@PathVariable String costComponentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentId", costComponentId);
		try {

			List<CostComponent> foundCostComponent = findCostComponentsBy(requestParams).getBody();
			if(foundCostComponent.size()==1){				return successful(foundCostComponent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{costComponentId}")
	public ResponseEntity<String> deleteCostComponentByIdUpdated(@PathVariable String costComponentId) throws Exception {
		DeleteCostComponent command = new DeleteCostComponent(costComponentId);

		try {
			if (((CostComponentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
