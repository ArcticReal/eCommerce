package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.AddInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.DeleteInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.UpdateInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.typeAttr.InventoryItemTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.typeAttr.FindInventoryItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemTypeAttrs")
public class InventoryItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemTypeAttr
	 * @return a List with the InventoryItemTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTypeAttrsBy query = new FindInventoryItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemTypeAttr> inventoryItemTypeAttrs =((InventoryItemTypeAttrFound) Scheduler.execute(query).data()).getInventoryItemTypeAttrs();

		if (inventoryItemTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemTypeAttrs);

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
	public ResponseEntity<Object> createInventoryItemTypeAttr(HttpServletRequest request) throws Exception {

		InventoryItemTypeAttr inventoryItemTypeAttrToBeAdded = new InventoryItemTypeAttr();
		try {
			inventoryItemTypeAttrToBeAdded = InventoryItemTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemTypeAttr(inventoryItemTypeAttrToBeAdded);

	}

	/**
	 * creates a new InventoryItemTypeAttr entry in the ofbiz database
	 * 
	 * @param inventoryItemTypeAttrToBeAdded
	 *            the InventoryItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemTypeAttr(@RequestBody InventoryItemTypeAttr inventoryItemTypeAttrToBeAdded) throws Exception {

		AddInventoryItemTypeAttr command = new AddInventoryItemTypeAttr(inventoryItemTypeAttrToBeAdded);
		InventoryItemTypeAttr inventoryItemTypeAttr = ((InventoryItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedInventoryItemTypeAttr();
		
		if (inventoryItemTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemTypeAttr could not be created.");
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
	public boolean updateInventoryItemTypeAttr(HttpServletRequest request) throws Exception {

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

		InventoryItemTypeAttr inventoryItemTypeAttrToBeUpdated = new InventoryItemTypeAttr();

		try {
			inventoryItemTypeAttrToBeUpdated = InventoryItemTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemTypeAttr(inventoryItemTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemTypeAttr with the specific Id
	 * 
	 * @param inventoryItemTypeAttrToBeUpdated
	 *            the InventoryItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemTypeAttr(@RequestBody InventoryItemTypeAttr inventoryItemTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemTypeAttrToBeUpdated.setnull(null);

		UpdateInventoryItemTypeAttr command = new UpdateInventoryItemTypeAttr(inventoryItemTypeAttrToBeUpdated);

		try {
			if(((InventoryItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTypeAttrId", inventoryItemTypeAttrId);
		try {

			Object foundInventoryItemTypeAttr = findInventoryItemTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemTypeAttrId}")
	public ResponseEntity<Object> deleteInventoryItemTypeAttrByIdUpdated(@PathVariable String inventoryItemTypeAttrId) throws Exception {
		DeleteInventoryItemTypeAttr command = new DeleteInventoryItemTypeAttr(inventoryItemTypeAttrId);

		try {
			if (((InventoryItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemTypeAttr/\" plus one of the following: "
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
