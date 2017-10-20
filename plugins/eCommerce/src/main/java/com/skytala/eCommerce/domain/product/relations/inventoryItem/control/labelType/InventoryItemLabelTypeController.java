package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.labelType;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.AddInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.DeleteInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.UpdateInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelType.InventoryItemLabelTypeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelType.InventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.labelType.FindInventoryItemLabelTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/inventoryItemLabelTypes")
public class InventoryItemLabelTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabelType
	 * @return a List with the InventoryItemLabelTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemLabelTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelTypesBy query = new FindInventoryItemLabelTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabelType> inventoryItemLabelTypes =((InventoryItemLabelTypeFound) Scheduler.execute(query).data()).getInventoryItemLabelTypes();

		if (inventoryItemLabelTypes.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemLabelTypes.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemLabelTypes);

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
	public ResponseEntity<Object> createInventoryItemLabelType(HttpServletRequest request) throws Exception {

		InventoryItemLabelType inventoryItemLabelTypeToBeAdded = new InventoryItemLabelType();
		try {
			inventoryItemLabelTypeToBeAdded = InventoryItemLabelTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemLabelType(inventoryItemLabelTypeToBeAdded);

	}

	/**
	 * creates a new InventoryItemLabelType entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelTypeToBeAdded
	 *            the InventoryItemLabelType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemLabelType(@RequestBody InventoryItemLabelType inventoryItemLabelTypeToBeAdded) throws Exception {

		AddInventoryItemLabelType command = new AddInventoryItemLabelType(inventoryItemLabelTypeToBeAdded);
		InventoryItemLabelType inventoryItemLabelType = ((InventoryItemLabelTypeAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabelType();
		
		if (inventoryItemLabelType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemLabelType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemLabelType could not be created.");
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
	public boolean updateInventoryItemLabelType(HttpServletRequest request) throws Exception {

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

		InventoryItemLabelType inventoryItemLabelTypeToBeUpdated = new InventoryItemLabelType();

		try {
			inventoryItemLabelTypeToBeUpdated = InventoryItemLabelTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemLabelType(inventoryItemLabelTypeToBeUpdated, inventoryItemLabelTypeToBeUpdated.getInventoryItemLabelTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemLabelType with the specific Id
	 * 
	 * @param inventoryItemLabelTypeToBeUpdated
	 *            the InventoryItemLabelType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemLabelTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemLabelType(@RequestBody InventoryItemLabelType inventoryItemLabelTypeToBeUpdated,
			@PathVariable String inventoryItemLabelTypeId) throws Exception {

		inventoryItemLabelTypeToBeUpdated.setInventoryItemLabelTypeId(inventoryItemLabelTypeId);

		UpdateInventoryItemLabelType command = new UpdateInventoryItemLabelType(inventoryItemLabelTypeToBeUpdated);

		try {
			if(((InventoryItemLabelTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemLabelTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemLabelTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelTypeId", inventoryItemLabelTypeId);
		try {

			Object foundInventoryItemLabelType = findInventoryItemLabelTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemLabelType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemLabelTypeId}")
	public ResponseEntity<Object> deleteInventoryItemLabelTypeByIdUpdated(@PathVariable String inventoryItemLabelTypeId) throws Exception {
		DeleteInventoryItemLabelType command = new DeleteInventoryItemLabelType(inventoryItemLabelTypeId);

		try {
			if (((InventoryItemLabelTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemLabelType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemLabelType/\" plus one of the following: "
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
