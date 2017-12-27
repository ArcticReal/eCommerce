package com.skytala.eCommerce.domain.order.relations.shoppingList.control.type;

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
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.type.AddShoppingListType;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.type.DeleteShoppingListType;
import com.skytala.eCommerce.domain.order.relations.shoppingList.command.type.UpdateShoppingListType;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.type.ShoppingListTypeAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.type.ShoppingListTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.type.ShoppingListTypeFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.type.ShoppingListTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.type.ShoppingListTypeMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.type.ShoppingListType;
import com.skytala.eCommerce.domain.order.relations.shoppingList.query.type.FindShoppingListTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/shoppingList/shoppingListTypes")
public class ShoppingListTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShoppingListTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShoppingListType
	 * @return a List with the ShoppingListTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShoppingListType>> findShoppingListTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListTypesBy query = new FindShoppingListTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListType> shoppingListTypes =((ShoppingListTypeFound) Scheduler.execute(query).data()).getShoppingListTypes();

		return ResponseEntity.ok().body(shoppingListTypes);

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
	public ResponseEntity<ShoppingListType> createShoppingListType(HttpServletRequest request) throws Exception {

		ShoppingListType shoppingListTypeToBeAdded = new ShoppingListType();
		try {
			shoppingListTypeToBeAdded = ShoppingListTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShoppingListType(shoppingListTypeToBeAdded);

	}

	/**
	 * creates a new ShoppingListType entry in the ofbiz database
	 * 
	 * @param shoppingListTypeToBeAdded
	 *            the ShoppingListType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingListType> createShoppingListType(@RequestBody ShoppingListType shoppingListTypeToBeAdded) throws Exception {

		AddShoppingListType command = new AddShoppingListType(shoppingListTypeToBeAdded);
		ShoppingListType shoppingListType = ((ShoppingListTypeAdded) Scheduler.execute(command).data()).getAddedShoppingListType();
		
		if (shoppingListType != null) 
			return successful(shoppingListType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShoppingListType with the specific Id
	 * 
	 * @param shoppingListTypeToBeUpdated
	 *            the ShoppingListType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shoppingListTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShoppingListType(@RequestBody ShoppingListType shoppingListTypeToBeUpdated,
			@PathVariable String shoppingListTypeId) throws Exception {

		shoppingListTypeToBeUpdated.setShoppingListTypeId(shoppingListTypeId);

		UpdateShoppingListType command = new UpdateShoppingListType(shoppingListTypeToBeUpdated);

		try {
			if(((ShoppingListTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shoppingListTypeId}")
	public ResponseEntity<ShoppingListType> findById(@PathVariable String shoppingListTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListTypeId", shoppingListTypeId);
		try {

			List<ShoppingListType> foundShoppingListType = findShoppingListTypesBy(requestParams).getBody();
			if(foundShoppingListType.size()==1){				return successful(foundShoppingListType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shoppingListTypeId}")
	public ResponseEntity<String> deleteShoppingListTypeByIdUpdated(@PathVariable String shoppingListTypeId) throws Exception {
		DeleteShoppingListType command = new DeleteShoppingListType(shoppingListTypeId);

		try {
			if (((ShoppingListTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
