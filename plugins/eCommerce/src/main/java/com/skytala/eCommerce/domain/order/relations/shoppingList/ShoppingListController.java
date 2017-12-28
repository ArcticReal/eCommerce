package com.skytala.eCommerce.domain.order.relations.shoppingList;

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
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.AddShoppingList;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.DeleteShoppingList;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.UpdateShoppingList;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.ShoppingListUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.ShoppingListMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;
import com.skytala.eCommerce.domain.order.relations.shoppingList.query.FindShoppingListsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/shoppingLists")
public class ShoppingListController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShoppingListController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShoppingList
	 * @return a List with the ShoppingLists
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShoppingList>> findShoppingListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListsBy query = new FindShoppingListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingList> shoppingLists =((ShoppingListFound) Scheduler.execute(query).data()).getShoppingLists();

		return ResponseEntity.ok().body(shoppingLists);

	}

	/**
	 * creates a new ShoppingList entry in the ofbiz database
	 * 
	 * @param shoppingListToBeAdded
	 *            the ShoppingList thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingList shoppingListToBeAdded) throws Exception {

		AddShoppingList command = new AddShoppingList(shoppingListToBeAdded);
		ShoppingList shoppingList = ((ShoppingListAdded) Scheduler.execute(command).data()).getAddedShoppingList();
		
		if (shoppingList != null) 
			return successful(shoppingList);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShoppingList with the specific Id
	 * 
	 * @param shoppingListToBeUpdated
	 *            the ShoppingList thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shoppingListId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShoppingList(@RequestBody ShoppingList shoppingListToBeUpdated,
			@PathVariable String shoppingListId) throws Exception {

		shoppingListToBeUpdated.setShoppingListId(shoppingListId);

		UpdateShoppingList command = new UpdateShoppingList(shoppingListToBeUpdated);

		try {
			if(((ShoppingListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shoppingListId}")
	public ResponseEntity<ShoppingList> findById(@PathVariable String shoppingListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListId", shoppingListId);
		try {

			List<ShoppingList> foundShoppingList = findShoppingListsBy(requestParams).getBody();
			if(foundShoppingList.size()==1){				return successful(foundShoppingList.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shoppingListId}")
	public ResponseEntity<String> deleteShoppingListByIdUpdated(@PathVariable String shoppingListId) throws Exception {
		DeleteShoppingList command = new DeleteShoppingList(shoppingListId);

		try {
			if (((ShoppingListDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
