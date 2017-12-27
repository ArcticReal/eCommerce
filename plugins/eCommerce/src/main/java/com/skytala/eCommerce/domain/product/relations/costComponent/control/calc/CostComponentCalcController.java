package com.skytala.eCommerce.domain.product.relations.costComponent.control.calc;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.calc.AddCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.calc.DeleteCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.calc.UpdateCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.calc.CostComponentCalcAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.calc.CostComponentCalcDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.calc.CostComponentCalcFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.calc.CostComponentCalcUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.calc.CostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.calc.FindCostComponentCalcsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/costComponent/costComponentCalcs")
public class CostComponentCalcController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentCalcController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponentCalc
	 * @return a List with the CostComponentCalcs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CostComponentCalc>> findCostComponentCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentCalcsBy query = new FindCostComponentCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentCalc> costComponentCalcs =((CostComponentCalcFound) Scheduler.execute(query).data()).getCostComponentCalcs();

		return ResponseEntity.ok().body(costComponentCalcs);

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
	public ResponseEntity<CostComponentCalc> createCostComponentCalc(HttpServletRequest request) throws Exception {

		CostComponentCalc costComponentCalcToBeAdded = new CostComponentCalc();
		try {
			costComponentCalcToBeAdded = CostComponentCalcMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCostComponentCalc(costComponentCalcToBeAdded);

	}

	/**
	 * creates a new CostComponentCalc entry in the ofbiz database
	 * 
	 * @param costComponentCalcToBeAdded
	 *            the CostComponentCalc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CostComponentCalc> createCostComponentCalc(@RequestBody CostComponentCalc costComponentCalcToBeAdded) throws Exception {

		AddCostComponentCalc command = new AddCostComponentCalc(costComponentCalcToBeAdded);
		CostComponentCalc costComponentCalc = ((CostComponentCalcAdded) Scheduler.execute(command).data()).getAddedCostComponentCalc();
		
		if (costComponentCalc != null) 
			return successful(costComponentCalc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CostComponentCalc with the specific Id
	 * 
	 * @param costComponentCalcToBeUpdated
	 *            the CostComponentCalc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{costComponentCalcId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCostComponentCalc(@RequestBody CostComponentCalc costComponentCalcToBeUpdated,
			@PathVariable String costComponentCalcId) throws Exception {

		costComponentCalcToBeUpdated.setCostComponentCalcId(costComponentCalcId);

		UpdateCostComponentCalc command = new UpdateCostComponentCalc(costComponentCalcToBeUpdated);

		try {
			if(((CostComponentCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{costComponentCalcId}")
	public ResponseEntity<CostComponentCalc> findById(@PathVariable String costComponentCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentCalcId", costComponentCalcId);
		try {

			List<CostComponentCalc> foundCostComponentCalc = findCostComponentCalcsBy(requestParams).getBody();
			if(foundCostComponentCalc.size()==1){				return successful(foundCostComponentCalc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{costComponentCalcId}")
	public ResponseEntity<String> deleteCostComponentCalcByIdUpdated(@PathVariable String costComponentCalcId) throws Exception {
		DeleteCostComponentCalc command = new DeleteCostComponentCalc(costComponentCalcId);

		try {
			if (((CostComponentCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
