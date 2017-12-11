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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShoppingListWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListWorkEffortsBy query = new FindShoppingListWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListWorkEffort> shoppingListWorkEfforts =((ShoppingListWorkEffortFound) Scheduler.execute(query).data()).getShoppingListWorkEfforts();

		if (shoppingListWorkEfforts.size() == 1) {
			return ResponseEntity.ok().body(shoppingListWorkEfforts.get(0));
		}

		return ResponseEntity.ok().body(shoppingListWorkEfforts);

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
	public ResponseEntity<Object> createShoppingListWorkEffort(HttpServletRequest request) throws Exception {

		ShoppingListWorkEffort shoppingListWorkEffortToBeAdded = new ShoppingListWorkEffort();
		try {
			shoppingListWorkEffortToBeAdded = ShoppingListWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShoppingListWorkEffort(shoppingListWorkEffortToBeAdded);

	}

	/**
	 * creates a new ShoppingListWorkEffort entry in the ofbiz database
	 * 
	 * @param shoppingListWorkEffortToBeAdded
	 *            the ShoppingListWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShoppingListWorkEffort(@RequestBody ShoppingListWorkEffort shoppingListWorkEffortToBeAdded) throws Exception {

		AddShoppingListWorkEffort command = new AddShoppingListWorkEffort(shoppingListWorkEffortToBeAdded);
		ShoppingListWorkEffort shoppingListWorkEffort = ((ShoppingListWorkEffortAdded) Scheduler.execute(command).data()).getAddedShoppingListWorkEffort();
		
		if (shoppingListWorkEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shoppingListWorkEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShoppingListWorkEffort could not be created.");
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
	public boolean updateShoppingListWorkEffort(HttpServletRequest request) throws Exception {

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

		ShoppingListWorkEffort shoppingListWorkEffortToBeUpdated = new ShoppingListWorkEffort();

		try {
			shoppingListWorkEffortToBeUpdated = ShoppingListWorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShoppingListWorkEffort(shoppingListWorkEffortToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShoppingListWorkEffort(@RequestBody ShoppingListWorkEffort shoppingListWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shoppingListWorkEffortToBeUpdated.setnull(null);

		UpdateShoppingListWorkEffort command = new UpdateShoppingListWorkEffort(shoppingListWorkEffortToBeUpdated);

		try {
			if(((ShoppingListWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shoppingListWorkEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String shoppingListWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListWorkEffortId", shoppingListWorkEffortId);
		try {

			Object foundShoppingListWorkEffort = findShoppingListWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShoppingListWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shoppingListWorkEffortId}")
	public ResponseEntity<Object> deleteShoppingListWorkEffortByIdUpdated(@PathVariable String shoppingListWorkEffortId) throws Exception {
		DeleteShoppingListWorkEffort command = new DeleteShoppingListWorkEffort(shoppingListWorkEffortId);

		try {
			if (((ShoppingListWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShoppingListWorkEffort could not be deleted");

	}

}
