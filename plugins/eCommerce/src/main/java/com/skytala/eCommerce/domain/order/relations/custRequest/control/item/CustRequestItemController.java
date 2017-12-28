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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CustRequestItem>> findCustRequestItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemsBy query = new FindCustRequestItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItem> custRequestItems =((CustRequestItemFound) Scheduler.execute(query).data()).getCustRequestItems();

		return ResponseEntity.ok().body(custRequestItems);

	}

	/**
	 * creates a new CustRequestItem entry in the ofbiz database
	 * 
	 * @param custRequestItemToBeAdded
	 *            the CustRequestItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestItem> createCustRequestItem(@RequestBody CustRequestItem custRequestItemToBeAdded) throws Exception {

		AddCustRequestItem command = new AddCustRequestItem(custRequestItemToBeAdded);
		CustRequestItem custRequestItem = ((CustRequestItemAdded) Scheduler.execute(command).data()).getAddedCustRequestItem();
		
		if (custRequestItem != null) 
			return successful(custRequestItem);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestItem(@RequestBody CustRequestItem custRequestItemToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItem command = new UpdateCustRequestItem(custRequestItemToBeUpdated);

		try {
			if(((CustRequestItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestItemId}")
	public ResponseEntity<CustRequestItem> findById(@PathVariable String custRequestItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemId", custRequestItemId);
		try {

			List<CustRequestItem> foundCustRequestItem = findCustRequestItemsBy(requestParams).getBody();
			if(foundCustRequestItem.size()==1){				return successful(foundCustRequestItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestItemId}")
	public ResponseEntity<String> deleteCustRequestItemByIdUpdated(@PathVariable String custRequestItemId) throws Exception {
		DeleteCustRequestItem command = new DeleteCustRequestItem(custRequestItemId);

		try {
			if (((CustRequestItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
