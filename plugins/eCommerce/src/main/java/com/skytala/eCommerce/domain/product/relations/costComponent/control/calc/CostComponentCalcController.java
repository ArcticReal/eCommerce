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
	public ResponseEntity<Object> findCostComponentCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentCalcsBy query = new FindCostComponentCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentCalc> costComponentCalcs =((CostComponentCalcFound) Scheduler.execute(query).data()).getCostComponentCalcs();

		if (costComponentCalcs.size() == 1) {
			return ResponseEntity.ok().body(costComponentCalcs.get(0));
		}

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
	public ResponseEntity<Object> createCostComponentCalc(HttpServletRequest request) throws Exception {

		CostComponentCalc costComponentCalcToBeAdded = new CostComponentCalc();
		try {
			costComponentCalcToBeAdded = CostComponentCalcMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createCostComponentCalc(@RequestBody CostComponentCalc costComponentCalcToBeAdded) throws Exception {

		AddCostComponentCalc command = new AddCostComponentCalc(costComponentCalcToBeAdded);
		CostComponentCalc costComponentCalc = ((CostComponentCalcAdded) Scheduler.execute(command).data()).getAddedCostComponentCalc();
		
		if (costComponentCalc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(costComponentCalc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CostComponentCalc could not be created.");
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
	public boolean updateCostComponentCalc(HttpServletRequest request) throws Exception {

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

		CostComponentCalc costComponentCalcToBeUpdated = new CostComponentCalc();

		try {
			costComponentCalcToBeUpdated = CostComponentCalcMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCostComponentCalc(costComponentCalcToBeUpdated, costComponentCalcToBeUpdated.getCostComponentCalcId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCostComponentCalc(@RequestBody CostComponentCalc costComponentCalcToBeUpdated,
			@PathVariable String costComponentCalcId) throws Exception {

		costComponentCalcToBeUpdated.setCostComponentCalcId(costComponentCalcId);

		UpdateCostComponentCalc command = new UpdateCostComponentCalc(costComponentCalcToBeUpdated);

		try {
			if(((CostComponentCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{costComponentCalcId}")
	public ResponseEntity<Object> findById(@PathVariable String costComponentCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentCalcId", costComponentCalcId);
		try {

			Object foundCostComponentCalc = findCostComponentCalcsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCostComponentCalc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{costComponentCalcId}")
	public ResponseEntity<Object> deleteCostComponentCalcByIdUpdated(@PathVariable String costComponentCalcId) throws Exception {
		DeleteCostComponentCalc command = new DeleteCostComponentCalc(costComponentCalcId);

		try {
			if (((CostComponentCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CostComponentCalc could not be deleted");

	}

}
