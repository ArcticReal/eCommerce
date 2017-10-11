package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.command.AddInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.command.DeleteInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.command.UpdateInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.mapper.InventoryItemDetailMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model.InventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.query.FindInventoryItemDetailsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemDetails")
public class InventoryItemDetailController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemDetailController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemDetail
	 * @return a List with the InventoryItemDetails
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemDetailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemDetailsBy query = new FindInventoryItemDetailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemDetail> inventoryItemDetails =((InventoryItemDetailFound) Scheduler.execute(query).data()).getInventoryItemDetails();

		if (inventoryItemDetails.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemDetails.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemDetails);

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
	public ResponseEntity<Object> createInventoryItemDetail(HttpServletRequest request) throws Exception {

		InventoryItemDetail inventoryItemDetailToBeAdded = new InventoryItemDetail();
		try {
			inventoryItemDetailToBeAdded = InventoryItemDetailMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemDetail(inventoryItemDetailToBeAdded);

	}

	/**
	 * creates a new InventoryItemDetail entry in the ofbiz database
	 * 
	 * @param inventoryItemDetailToBeAdded
	 *            the InventoryItemDetail thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemDetail(@RequestBody InventoryItemDetail inventoryItemDetailToBeAdded) throws Exception {

		AddInventoryItemDetail command = new AddInventoryItemDetail(inventoryItemDetailToBeAdded);
		InventoryItemDetail inventoryItemDetail = ((InventoryItemDetailAdded) Scheduler.execute(command).data()).getAddedInventoryItemDetail();
		
		if (inventoryItemDetail != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemDetail);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemDetail could not be created.");
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
	public boolean updateInventoryItemDetail(HttpServletRequest request) throws Exception {

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

		InventoryItemDetail inventoryItemDetailToBeUpdated = new InventoryItemDetail();

		try {
			inventoryItemDetailToBeUpdated = InventoryItemDetailMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemDetail(inventoryItemDetailToBeUpdated, inventoryItemDetailToBeUpdated.getInventoryItemDetailSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemDetail with the specific Id
	 * 
	 * @param inventoryItemDetailToBeUpdated
	 *            the InventoryItemDetail thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemDetailSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemDetail(@RequestBody InventoryItemDetail inventoryItemDetailToBeUpdated,
			@PathVariable String inventoryItemDetailSeqId) throws Exception {

		inventoryItemDetailToBeUpdated.setInventoryItemDetailSeqId(inventoryItemDetailSeqId);

		UpdateInventoryItemDetail command = new UpdateInventoryItemDetail(inventoryItemDetailToBeUpdated);

		try {
			if(((InventoryItemDetailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemDetailId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemDetailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemDetailId", inventoryItemDetailId);
		try {

			Object foundInventoryItemDetail = findInventoryItemDetailsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemDetail);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemDetailId}")
	public ResponseEntity<Object> deleteInventoryItemDetailByIdUpdated(@PathVariable String inventoryItemDetailId) throws Exception {
		DeleteInventoryItemDetail command = new DeleteInventoryItemDetail(inventoryItemDetailId);

		try {
			if (((InventoryItemDetailDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemDetail could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemDetail/\" plus one of the following: "
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
