package com.skytala.eCommerce.domain.order.relations.shoppingList.control.item;

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
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.item.AddShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.item.DeleteShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.item.UpdateShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.item.ShoppingListItemUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.item.ShoppingListItemMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.item.ShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingList.query.item.FindShoppingListItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/shoppingList/shoppingListItems")
public class ShoppingListItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShoppingListItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShoppingListItem
	 * @return a List with the ShoppingListItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShoppingListItem>> findShoppingListItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListItemsBy query = new FindShoppingListItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListItem> shoppingListItems =((ShoppingListItemFound) Scheduler.execute(query).data()).getShoppingListItems();

		return ResponseEntity.ok().body(shoppingListItems);

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
	public ResponseEntity<ShoppingListItem> createShoppingListItem(HttpServletRequest request) throws Exception {

		ShoppingListItem shoppingListItemToBeAdded = new ShoppingListItem();
		try {
			shoppingListItemToBeAdded = ShoppingListItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShoppingListItem(shoppingListItemToBeAdded);

	}

	/**
	 * creates a new ShoppingListItem entry in the ofbiz database
	 * 
	 * @param shoppingListItemToBeAdded
	 *            the ShoppingListItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingListItem> createShoppingListItem(@RequestBody ShoppingListItem shoppingListItemToBeAdded) throws Exception {

		AddShoppingListItem command = new AddShoppingListItem(shoppingListItemToBeAdded);
		ShoppingListItem shoppingListItem = ((ShoppingListItemAdded) Scheduler.execute(command).data()).getAddedShoppingListItem();
		
		if (shoppingListItem != null) 
			return successful(shoppingListItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShoppingListItem with the specific Id
	 * 
	 * @param shoppingListItemToBeUpdated
	 *            the ShoppingListItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shoppingListItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShoppingListItem(@RequestBody ShoppingListItem shoppingListItemToBeUpdated,
			@PathVariable String shoppingListItemSeqId) throws Exception {

		shoppingListItemToBeUpdated.setShoppingListItemSeqId(shoppingListItemSeqId);

		UpdateShoppingListItem command = new UpdateShoppingListItem(shoppingListItemToBeUpdated);

		try {
			if(((ShoppingListItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shoppingListItemId}")
	public ResponseEntity<ShoppingListItem> findById(@PathVariable String shoppingListItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListItemId", shoppingListItemId);
		try {

			List<ShoppingListItem> foundShoppingListItem = findShoppingListItemsBy(requestParams).getBody();
			if(foundShoppingListItem.size()==1){				return successful(foundShoppingListItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shoppingListItemId}")
	public ResponseEntity<String> deleteShoppingListItemByIdUpdated(@PathVariable String shoppingListItemId) throws Exception {
		DeleteShoppingListItem command = new DeleteShoppingListItem(shoppingListItemId);

		try {
			if (((ShoppingListItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
