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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/shoppingLists")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShoppingListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListsBy query = new FindShoppingListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingList> shoppingLists =((ShoppingListFound) Scheduler.execute(query).data()).getShoppingLists();

		if (shoppingLists.size() == 1) {
			return ResponseEntity.ok().body(shoppingLists.get(0));
		}

		return ResponseEntity.ok().body(shoppingLists);

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
	public ResponseEntity<Object> createShoppingList(HttpServletRequest request) throws Exception {

		ShoppingList shoppingListToBeAdded = new ShoppingList();
		try {
			shoppingListToBeAdded = ShoppingListMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShoppingList(shoppingListToBeAdded);

	}

	/**
	 * creates a new ShoppingList entry in the ofbiz database
	 * 
	 * @param shoppingListToBeAdded
	 *            the ShoppingList thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShoppingList(@RequestBody ShoppingList shoppingListToBeAdded) throws Exception {

		AddShoppingList command = new AddShoppingList(shoppingListToBeAdded);
		ShoppingList shoppingList = ((ShoppingListAdded) Scheduler.execute(command).data()).getAddedShoppingList();
		
		if (shoppingList != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shoppingList);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShoppingList could not be created.");
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
	public boolean updateShoppingList(HttpServletRequest request) throws Exception {

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

		ShoppingList shoppingListToBeUpdated = new ShoppingList();

		try {
			shoppingListToBeUpdated = ShoppingListMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShoppingList(shoppingListToBeUpdated, shoppingListToBeUpdated.getShoppingListId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShoppingList(@RequestBody ShoppingList shoppingListToBeUpdated,
			@PathVariable String shoppingListId) throws Exception {

		shoppingListToBeUpdated.setShoppingListId(shoppingListId);

		UpdateShoppingList command = new UpdateShoppingList(shoppingListToBeUpdated);

		try {
			if(((ShoppingListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shoppingListId}")
	public ResponseEntity<Object> findById(@PathVariable String shoppingListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListId", shoppingListId);
		try {

			Object foundShoppingList = findShoppingListsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShoppingList);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shoppingListId}")
	public ResponseEntity<Object> deleteShoppingListByIdUpdated(@PathVariable String shoppingListId) throws Exception {
		DeleteShoppingList command = new DeleteShoppingList(shoppingListId);

		try {
			if (((ShoppingListDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShoppingList could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shoppingList/\" plus one of the following: "
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
