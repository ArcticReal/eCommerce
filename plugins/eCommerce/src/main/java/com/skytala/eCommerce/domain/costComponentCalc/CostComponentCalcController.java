package com.skytala.eCommerce.domain.costComponentCalc;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.costComponentCalc.command.AddCostComponentCalc;
import com.skytala.eCommerce.domain.costComponentCalc.command.DeleteCostComponentCalc;
import com.skytala.eCommerce.domain.costComponentCalc.command.UpdateCostComponentCalc;
import com.skytala.eCommerce.domain.costComponentCalc.event.CostComponentCalcAdded;
import com.skytala.eCommerce.domain.costComponentCalc.event.CostComponentCalcDeleted;
import com.skytala.eCommerce.domain.costComponentCalc.event.CostComponentCalcFound;
import com.skytala.eCommerce.domain.costComponentCalc.event.CostComponentCalcUpdated;
import com.skytala.eCommerce.domain.costComponentCalc.mapper.CostComponentCalcMapper;
import com.skytala.eCommerce.domain.costComponentCalc.model.CostComponentCalc;
import com.skytala.eCommerce.domain.costComponentCalc.query.FindCostComponentCalcsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/costComponentCalcs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@RequestMapping(method = RequestMethod.GET, value = "/{costComponentCalcId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{costComponentCalcId}")
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

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/costComponentCalc/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}