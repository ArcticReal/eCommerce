package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.labelAppl;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.AddInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.DeleteInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.UpdateInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelAppl.InventoryItemLabelApplMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelAppl.InventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.labelAppl.FindInventoryItemLabelApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemLabelAppls")
public class InventoryItemLabelApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabelAppl
	 * @return a List with the InventoryItemLabelAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findInventoryItemLabelApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelApplsBy query = new FindInventoryItemLabelApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabelAppl> inventoryItemLabelAppls =((InventoryItemLabelApplFound) Scheduler.execute(query).data()).getInventoryItemLabelAppls();

		if (inventoryItemLabelAppls.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemLabelAppls.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemLabelAppls);

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
	public ResponseEntity<Object> createInventoryItemLabelAppl(HttpServletRequest request) throws Exception {

		InventoryItemLabelAppl inventoryItemLabelApplToBeAdded = new InventoryItemLabelAppl();
		try {
			inventoryItemLabelApplToBeAdded = InventoryItemLabelApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemLabelAppl(inventoryItemLabelApplToBeAdded);

	}

	/**
	 * creates a new InventoryItemLabelAppl entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelApplToBeAdded
	 *            the InventoryItemLabelAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemLabelAppl(@RequestBody InventoryItemLabelAppl inventoryItemLabelApplToBeAdded) throws Exception {

		AddInventoryItemLabelAppl command = new AddInventoryItemLabelAppl(inventoryItemLabelApplToBeAdded);
		InventoryItemLabelAppl inventoryItemLabelAppl = ((InventoryItemLabelApplAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabelAppl();
		
		if (inventoryItemLabelAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemLabelAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemLabelAppl could not be created.");
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
	public boolean updateInventoryItemLabelAppl(HttpServletRequest request) throws Exception {

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

		InventoryItemLabelAppl inventoryItemLabelApplToBeUpdated = new InventoryItemLabelAppl();

		try {
			inventoryItemLabelApplToBeUpdated = InventoryItemLabelApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemLabelAppl(inventoryItemLabelApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemLabelAppl with the specific Id
	 * 
	 * @param inventoryItemLabelApplToBeUpdated
	 *            the InventoryItemLabelAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemLabelAppl(@RequestBody InventoryItemLabelAppl inventoryItemLabelApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemLabelApplToBeUpdated.setnull(null);

		UpdateInventoryItemLabelAppl command = new UpdateInventoryItemLabelAppl(inventoryItemLabelApplToBeUpdated);

		try {
			if(((InventoryItemLabelApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{inventoryItemLabelApplId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemLabelApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelApplId", inventoryItemLabelApplId);
		try {

			Object foundInventoryItemLabelAppl = findInventoryItemLabelApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemLabelAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{inventoryItemLabelApplId}")
	public ResponseEntity<Object> deleteInventoryItemLabelApplByIdUpdated(@PathVariable String inventoryItemLabelApplId) throws Exception {
		DeleteInventoryItemLabelAppl command = new DeleteInventoryItemLabelAppl(inventoryItemLabelApplId);

		try {
			if (((InventoryItemLabelApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemLabelAppl could not be deleted");

	}

}
