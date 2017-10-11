package com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.command.AddInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.command.DeleteInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.command.UpdateInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event.InventoryItemTempResAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event.InventoryItemTempResDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event.InventoryItemTempResFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event.InventoryItemTempResUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.mapper.InventoryItemTempResMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.model.InventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.query.FindInventoryItemTempRessBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemTempRess")
public class InventoryItemTempResController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemTempResController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemTempRes
	 * @return a List with the InventoryItemTempRess
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemTempRessBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTempRessBy query = new FindInventoryItemTempRessBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemTempRes> inventoryItemTempRess =((InventoryItemTempResFound) Scheduler.execute(query).data()).getInventoryItemTempRess();

		if (inventoryItemTempRess.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemTempRess.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemTempRess);

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
	public ResponseEntity<Object> createInventoryItemTempRes(HttpServletRequest request) throws Exception {

		InventoryItemTempRes inventoryItemTempResToBeAdded = new InventoryItemTempRes();
		try {
			inventoryItemTempResToBeAdded = InventoryItemTempResMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemTempRes(inventoryItemTempResToBeAdded);

	}

	/**
	 * creates a new InventoryItemTempRes entry in the ofbiz database
	 * 
	 * @param inventoryItemTempResToBeAdded
	 *            the InventoryItemTempRes thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemTempRes(@RequestBody InventoryItemTempRes inventoryItemTempResToBeAdded) throws Exception {

		AddInventoryItemTempRes command = new AddInventoryItemTempRes(inventoryItemTempResToBeAdded);
		InventoryItemTempRes inventoryItemTempRes = ((InventoryItemTempResAdded) Scheduler.execute(command).data()).getAddedInventoryItemTempRes();
		
		if (inventoryItemTempRes != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemTempRes);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemTempRes could not be created.");
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
	public boolean updateInventoryItemTempRes(HttpServletRequest request) throws Exception {

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

		InventoryItemTempRes inventoryItemTempResToBeUpdated = new InventoryItemTempRes();

		try {
			inventoryItemTempResToBeUpdated = InventoryItemTempResMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemTempRes(inventoryItemTempResToBeUpdated, inventoryItemTempResToBeUpdated.getVisitId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemTempRes with the specific Id
	 * 
	 * @param inventoryItemTempResToBeUpdated
	 *            the InventoryItemTempRes thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{visitId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemTempRes(@RequestBody InventoryItemTempRes inventoryItemTempResToBeUpdated,
			@PathVariable String visitId) throws Exception {

		inventoryItemTempResToBeUpdated.setVisitId(visitId);

		UpdateInventoryItemTempRes command = new UpdateInventoryItemTempRes(inventoryItemTempResToBeUpdated);

		try {
			if(((InventoryItemTempResUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemTempResId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemTempResId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTempResId", inventoryItemTempResId);
		try {

			Object foundInventoryItemTempRes = findInventoryItemTempRessBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemTempRes);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemTempResId}")
	public ResponseEntity<Object> deleteInventoryItemTempResByIdUpdated(@PathVariable String inventoryItemTempResId) throws Exception {
		DeleteInventoryItemTempRes command = new DeleteInventoryItemTempRes(inventoryItemTempResId);

		try {
			if (((InventoryItemTempResDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemTempRes could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemTempRes/\" plus one of the following: "
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
