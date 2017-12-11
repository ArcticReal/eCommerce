package com.skytala.eCommerce.domain.product.relations.inventoryTransfer;

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
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.AddInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.DeleteInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.UpdateInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferFound;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.mapper.InventoryTransferMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model.InventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.query.FindInventoryTransfersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/inventoryTransfers")
public class InventoryTransferController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryTransferController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryTransfer
	 * @return a List with the InventoryTransfers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findInventoryTransfersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryTransfersBy query = new FindInventoryTransfersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryTransfer> inventoryTransfers =((InventoryTransferFound) Scheduler.execute(query).data()).getInventoryTransfers();

		if (inventoryTransfers.size() == 1) {
			return ResponseEntity.ok().body(inventoryTransfers.get(0));
		}

		return ResponseEntity.ok().body(inventoryTransfers);

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
	public ResponseEntity<Object> createInventoryTransfer(HttpServletRequest request) throws Exception {

		InventoryTransfer inventoryTransferToBeAdded = new InventoryTransfer();
		try {
			inventoryTransferToBeAdded = InventoryTransferMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryTransfer(inventoryTransferToBeAdded);

	}

	/**
	 * creates a new InventoryTransfer entry in the ofbiz database
	 * 
	 * @param inventoryTransferToBeAdded
	 *            the InventoryTransfer thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryTransfer(@RequestBody InventoryTransfer inventoryTransferToBeAdded) throws Exception {

		AddInventoryTransfer command = new AddInventoryTransfer(inventoryTransferToBeAdded);
		InventoryTransfer inventoryTransfer = ((InventoryTransferAdded) Scheduler.execute(command).data()).getAddedInventoryTransfer();
		
		if (inventoryTransfer != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryTransfer);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryTransfer could not be created.");
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
	public boolean updateInventoryTransfer(HttpServletRequest request) throws Exception {

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

		InventoryTransfer inventoryTransferToBeUpdated = new InventoryTransfer();

		try {
			inventoryTransferToBeUpdated = InventoryTransferMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryTransfer(inventoryTransferToBeUpdated, inventoryTransferToBeUpdated.getInventoryTransferId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryTransfer with the specific Id
	 * 
	 * @param inventoryTransferToBeUpdated
	 *            the InventoryTransfer thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryTransferId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryTransfer(@RequestBody InventoryTransfer inventoryTransferToBeUpdated,
			@PathVariable String inventoryTransferId) throws Exception {

		inventoryTransferToBeUpdated.setInventoryTransferId(inventoryTransferId);

		UpdateInventoryTransfer command = new UpdateInventoryTransfer(inventoryTransferToBeUpdated);

		try {
			if(((InventoryTransferUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{inventoryTransferId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryTransferId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryTransferId", inventoryTransferId);
		try {

			Object foundInventoryTransfer = findInventoryTransfersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryTransfer);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{inventoryTransferId}")
	public ResponseEntity<Object> deleteInventoryTransferByIdUpdated(@PathVariable String inventoryTransferId) throws Exception {
		DeleteInventoryTransfer command = new DeleteInventoryTransfer(inventoryTransferId);

		try {
			if (((InventoryTransferDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryTransfer could not be deleted");

	}

}
