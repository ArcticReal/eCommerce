package com.skytala.eCommerce.domain.order.relations.shoppingList.control.workEffort;

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
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.workEffort.AddShoppingListWorkEffort;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.workEffort.DeleteShoppingListWorkEffort;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.workEffort.UpdateShoppingListWorkEffort;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort.ShoppingListWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.workEffort.ShoppingListWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
import com.skytala.eCommerce.domain.order.relations.shoppingList.query.workEffort.FindShoppingListWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/shoppingList/shoppingListWorkEfforts")
public class ShoppingListWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShoppingListWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShoppingListWorkEffort
	 * @return a List with the ShoppingListWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShoppingListWorkEffort>> findShoppingListWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListWorkEffortsBy query = new FindShoppingListWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListWorkEffort> shoppingListWorkEfforts =((ShoppingListWorkEffortFound) Scheduler.execute(query).data()).getShoppingListWorkEfforts();

		return ResponseEntity.ok().body(shoppingListWorkEfforts);

	}

	/**
	 * creates a new ShoppingListWorkEffort entry in the ofbiz database
	 * 
	 * @param shoppingListWorkEffortToBeAdded
	 *            the ShoppingListWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingListWorkEffort> createShoppingListWorkEffort(@RequestBody ShoppingListWorkEffort shoppingListWorkEffortToBeAdded) throws Exception {

		AddShoppingListWorkEffort command = new AddShoppingListWorkEffort(shoppingListWorkEffortToBeAdded);
		ShoppingListWorkEffort shoppingListWorkEffort = ((ShoppingListWorkEffortAdded) Scheduler.execute(command).data()).getAddedShoppingListWorkEffort();
		
		if (shoppingListWorkEffort != null) 
			return successful(shoppingListWorkEffort);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShoppingListWorkEffort with the specific Id
	 * 
	 * @param shoppingListWorkEffortToBeUpdated
	 *            the ShoppingListWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShoppingListWorkEffort(@RequestBody ShoppingListWorkEffort shoppingListWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shoppingListWorkEffortToBeUpdated.setnull(null);

		UpdateShoppingListWorkEffort command = new UpdateShoppingListWorkEffort(shoppingListWorkEffortToBeUpdated);

		try {
			if(((ShoppingListWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shoppingListWorkEffortId}")
	public ResponseEntity<ShoppingListWorkEffort> findById(@PathVariable String shoppingListWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListWorkEffortId", shoppingListWorkEffortId);
		try {

			List<ShoppingListWorkEffort> foundShoppingListWorkEffort = findShoppingListWorkEffortsBy(requestParams).getBody();
			if(foundShoppingListWorkEffort.size()==1){				return successful(foundShoppingListWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shoppingListWorkEffortId}")
	public ResponseEntity<String> deleteShoppingListWorkEffortByIdUpdated(@PathVariable String shoppingListWorkEffortId) throws Exception {
		DeleteShoppingListWorkEffort command = new DeleteShoppingListWorkEffort(shoppingListWorkEffortId);

		try {
			if (((ShoppingListWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
