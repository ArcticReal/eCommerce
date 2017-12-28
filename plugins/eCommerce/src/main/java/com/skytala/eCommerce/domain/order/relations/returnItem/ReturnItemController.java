package com.skytala.eCommerce.domain.order.relations.returnItem;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.AddReturnItem;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.DeleteReturnItem;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.UpdateReturnItem;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.ReturnItemAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.ReturnItemDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.ReturnItemFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.ReturnItemUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.ReturnItemMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.FindReturnItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnItems")
public class ReturnItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItem
	 * @return a List with the ReturnItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItem>> findReturnItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemsBy query = new FindReturnItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItem> returnItems =((ReturnItemFound) Scheduler.execute(query).data()).getReturnItems();

		return ResponseEntity.ok().body(returnItems);

	}

	/**
	 * creates a new ReturnItem entry in the ofbiz database
	 * 
	 * @param returnItemToBeAdded
	 *            the ReturnItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItem> createReturnItem(@RequestBody ReturnItem returnItemToBeAdded) throws Exception {

		AddReturnItem command = new AddReturnItem(returnItemToBeAdded);
		ReturnItem returnItem = ((ReturnItemAdded) Scheduler.execute(command).data()).getAddedReturnItem();
		
		if (returnItem != null) 
			return successful(returnItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnItem with the specific Id
	 * 
	 * @param returnItemToBeUpdated
	 *            the ReturnItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnItem(@RequestBody ReturnItem returnItemToBeUpdated,
			@PathVariable String returnItemSeqId) throws Exception {

		returnItemToBeUpdated.setReturnItemSeqId(returnItemSeqId);

		UpdateReturnItem command = new UpdateReturnItem(returnItemToBeUpdated);

		try {
			if(((ReturnItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemId}")
	public ResponseEntity<ReturnItem> findById(@PathVariable String returnItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemId", returnItemId);
		try {

			List<ReturnItem> foundReturnItem = findReturnItemsBy(requestParams).getBody();
			if(foundReturnItem.size()==1){				return successful(foundReturnItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemId}")
	public ResponseEntity<String> deleteReturnItemByIdUpdated(@PathVariable String returnItemId) throws Exception {
		DeleteReturnItem command = new DeleteReturnItem(returnItemId);

		try {
			if (((ReturnItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
