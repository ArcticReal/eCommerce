package com.skytala.eCommerce.domain.order.relations.custRequest.control.item;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.item.AddCustRequestItem;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.item.DeleteCustRequestItem;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.item.UpdateCustRequestItem;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.item.CustRequestItemAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.item.CustRequestItemDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.item.CustRequestItemFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.item.CustRequestItemUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.item.CustRequestItemMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.item.CustRequestItem;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.item.FindCustRequestItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/custRequest/custRequestItems")
public class CustRequestItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestItem
	 * @return a List with the CustRequestItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCustRequestItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemsBy query = new FindCustRequestItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItem> custRequestItems =((CustRequestItemFound) Scheduler.execute(query).data()).getCustRequestItems();

		if (custRequestItems.size() == 1) {
			return ResponseEntity.ok().body(custRequestItems.get(0));
		}

		return ResponseEntity.ok().body(custRequestItems);

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
	public ResponseEntity<Object> createCustRequestItem(HttpServletRequest request) throws Exception {

		CustRequestItem custRequestItemToBeAdded = new CustRequestItem();
		try {
			custRequestItemToBeAdded = CustRequestItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestItem(custRequestItemToBeAdded);

	}

	/**
	 * creates a new CustRequestItem entry in the ofbiz database
	 * 
	 * @param custRequestItemToBeAdded
	 *            the CustRequestItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestItem(@RequestBody CustRequestItem custRequestItemToBeAdded) throws Exception {

		AddCustRequestItem command = new AddCustRequestItem(custRequestItemToBeAdded);
		CustRequestItem custRequestItem = ((CustRequestItemAdded) Scheduler.execute(command).data()).getAddedCustRequestItem();
		
		if (custRequestItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestItem could not be created.");
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
	public boolean updateCustRequestItem(HttpServletRequest request) throws Exception {

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

		CustRequestItem custRequestItemToBeUpdated = new CustRequestItem();

		try {
			custRequestItemToBeUpdated = CustRequestItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestItem(custRequestItemToBeUpdated, custRequestItemToBeUpdated.getCustRequestItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestItem with the specific Id
	 * 
	 * @param custRequestItemToBeUpdated
	 *            the CustRequestItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestItem(@RequestBody CustRequestItem custRequestItemToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItem command = new UpdateCustRequestItem(custRequestItemToBeUpdated);

		try {
			if(((CustRequestItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{custRequestItemId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemId", custRequestItemId);
		try {

			Object foundCustRequestItem = findCustRequestItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{custRequestItemId}")
	public ResponseEntity<Object> deleteCustRequestItemByIdUpdated(@PathVariable String custRequestItemId) throws Exception {
		DeleteCustRequestItem command = new DeleteCustRequestItem(custRequestItemId);

		try {
			if (((CustRequestItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestItem could not be deleted");

	}

}
