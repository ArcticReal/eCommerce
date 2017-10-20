package com.skytala.eCommerce.domain.product.relations.costComponent.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.AddCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.DeleteCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.typeAttr.UpdateCostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr.CostComponentTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.typeAttr.CostComponentTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.typeAttr.FindCostComponentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/costComponentTypeAttrs")
public class CostComponentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CostComponentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CostComponentTypeAttr
	 * @return a List with the CostComponentTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCostComponentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCostComponentTypeAttrsBy query = new FindCostComponentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CostComponentTypeAttr> costComponentTypeAttrs =((CostComponentTypeAttrFound) Scheduler.execute(query).data()).getCostComponentTypeAttrs();

		if (costComponentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(costComponentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(costComponentTypeAttrs);

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
	public ResponseEntity<Object> createCostComponentTypeAttr(HttpServletRequest request) throws Exception {

		CostComponentTypeAttr costComponentTypeAttrToBeAdded = new CostComponentTypeAttr();
		try {
			costComponentTypeAttrToBeAdded = CostComponentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCostComponentTypeAttr(costComponentTypeAttrToBeAdded);

	}

	/**
	 * creates a new CostComponentTypeAttr entry in the ofbiz database
	 * 
	 * @param costComponentTypeAttrToBeAdded
	 *            the CostComponentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCostComponentTypeAttr(@RequestBody CostComponentTypeAttr costComponentTypeAttrToBeAdded) throws Exception {

		AddCostComponentTypeAttr command = new AddCostComponentTypeAttr(costComponentTypeAttrToBeAdded);
		CostComponentTypeAttr costComponentTypeAttr = ((CostComponentTypeAttrAdded) Scheduler.execute(command).data()).getAddedCostComponentTypeAttr();
		
		if (costComponentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(costComponentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CostComponentTypeAttr could not be created.");
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
	public boolean updateCostComponentTypeAttr(HttpServletRequest request) throws Exception {

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

		CostComponentTypeAttr costComponentTypeAttrToBeUpdated = new CostComponentTypeAttr();

		try {
			costComponentTypeAttrToBeUpdated = CostComponentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCostComponentTypeAttr(costComponentTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CostComponentTypeAttr with the specific Id
	 * 
	 * @param costComponentTypeAttrToBeUpdated
	 *            the CostComponentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCostComponentTypeAttr(@RequestBody CostComponentTypeAttr costComponentTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		costComponentTypeAttrToBeUpdated.setnull(null);

		UpdateCostComponentTypeAttr command = new UpdateCostComponentTypeAttr(costComponentTypeAttrToBeUpdated);

		try {
			if(((CostComponentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{costComponentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String costComponentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("costComponentTypeAttrId", costComponentTypeAttrId);
		try {

			Object foundCostComponentTypeAttr = findCostComponentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCostComponentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{costComponentTypeAttrId}")
	public ResponseEntity<Object> deleteCostComponentTypeAttrByIdUpdated(@PathVariable String costComponentTypeAttrId) throws Exception {
		DeleteCostComponentTypeAttr command = new DeleteCostComponentTypeAttr(costComponentTypeAttrId);

		try {
			if (((CostComponentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CostComponentTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/costComponentTypeAttr/\" plus one of the following: "
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
