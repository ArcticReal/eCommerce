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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShoppingListTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListTypesBy query = new FindShoppingListTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListType> shoppingListTypes =((ShoppingListTypeFound) Scheduler.execute(query).data()).getShoppingListTypes();

		if (shoppingListTypes.size() == 1) {
			return ResponseEntity.ok().body(shoppingListTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShoppingListType(HttpServletRequest request) throws Exception {

		ShoppingListType shoppingListTypeToBeAdded = new ShoppingListType();
		try {
			shoppingListTypeToBeAdded = ShoppingListTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createShoppingListType(@RequestBody ShoppingListType shoppingListTypeToBeAdded) throws Exception {

		AddShoppingListType command = new AddShoppingListType(shoppingListTypeToBeAdded);
		ShoppingListType shoppingListType = ((ShoppingListTypeAdded) Scheduler.execute(command).data()).getAddedShoppingListType();
		
		if (shoppingListType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shoppingListType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShoppingListType could not be created.");
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
	public boolean updateShoppingListType(HttpServletRequest request) throws Exception {

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

		ShoppingListType shoppingListTypeToBeUpdated = new ShoppingListType();

		try {
			shoppingListTypeToBeUpdated = ShoppingListTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShoppingListType(shoppingListTypeToBeUpdated, shoppingListTypeToBeUpdated.getShoppingListTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShoppingListType(@RequestBody ShoppingListType shoppingListTypeToBeUpdated,
			@PathVariable String shoppingListTypeId) throws Exception {

		shoppingListTypeToBeUpdated.setShoppingListTypeId(shoppingListTypeId);

		UpdateShoppingListType command = new UpdateShoppingListType(shoppingListTypeToBeUpdated);

		try {
			if(((ShoppingListTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shoppingListTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shoppingListTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListTypeId", shoppingListTypeId);
		try {

			Object foundShoppingListType = findShoppingListTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShoppingListType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shoppingListTypeId}")
	public ResponseEntity<Object> deleteShoppingListTypeByIdUpdated(@PathVariable String shoppingListTypeId) throws Exception {
		DeleteShoppingListType command = new DeleteShoppingListType(shoppingListTypeId);

		try {
			if (((ShoppingListTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShoppingListType could not be deleted");

	}

}
