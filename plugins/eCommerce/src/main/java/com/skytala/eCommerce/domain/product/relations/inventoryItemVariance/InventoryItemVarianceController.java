package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.command.AddInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.command.DeleteInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.command.UpdateInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.mapper.InventoryItemVarianceMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.query.FindInventoryItemVariancesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemVariances")
public class InventoryItemVarianceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemVarianceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemVariance
	 * @return a List with the InventoryItemVariances
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemVariancesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemVariancesBy query = new FindInventoryItemVariancesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemVariance> inventoryItemVariances =((InventoryItemVarianceFound) Scheduler.execute(query).data()).getInventoryItemVariances();

		if (inventoryItemVariances.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemVariances.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemVariances);

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
	public ResponseEntity<Object> createInventoryItemVariance(HttpServletRequest request) throws Exception {

		InventoryItemVariance inventoryItemVarianceToBeAdded = new InventoryItemVariance();
		try {
			inventoryItemVarianceToBeAdded = InventoryItemVarianceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemVariance(inventoryItemVarianceToBeAdded);

	}

	/**
	 * creates a new InventoryItemVariance entry in the ofbiz database
	 * 
	 * @param inventoryItemVarianceToBeAdded
	 *            the InventoryItemVariance thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemVariance(@RequestBody InventoryItemVariance inventoryItemVarianceToBeAdded) throws Exception {

		AddInventoryItemVariance command = new AddInventoryItemVariance(inventoryItemVarianceToBeAdded);
		InventoryItemVariance inventoryItemVariance = ((InventoryItemVarianceAdded) Scheduler.execute(command).data()).getAddedInventoryItemVariance();
		
		if (inventoryItemVariance != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemVariance);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemVariance could not be created.");
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
	public boolean updateInventoryItemVariance(HttpServletRequest request) throws Exception {

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

		InventoryItemVariance inventoryItemVarianceToBeUpdated = new InventoryItemVariance();

		try {
			inventoryItemVarianceToBeUpdated = InventoryItemVarianceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemVariance(inventoryItemVarianceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemVariance with the specific Id
	 * 
	 * @param inventoryItemVarianceToBeUpdated
	 *            the InventoryItemVariance thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemVariance(@RequestBody InventoryItemVariance inventoryItemVarianceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemVarianceToBeUpdated.setnull(null);

		UpdateInventoryItemVariance command = new UpdateInventoryItemVariance(inventoryItemVarianceToBeUpdated);

		try {
			if(((InventoryItemVarianceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemVarianceId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemVarianceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemVarianceId", inventoryItemVarianceId);
		try {

			Object foundInventoryItemVariance = findInventoryItemVariancesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemVariance);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemVarianceId}")
	public ResponseEntity<Object> deleteInventoryItemVarianceByIdUpdated(@PathVariable String inventoryItemVarianceId) throws Exception {
		DeleteInventoryItemVariance command = new DeleteInventoryItemVariance(inventoryItemVarianceId);

		try {
			if (((InventoryItemVarianceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemVariance could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemVariance/\" plus one of the following: "
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
