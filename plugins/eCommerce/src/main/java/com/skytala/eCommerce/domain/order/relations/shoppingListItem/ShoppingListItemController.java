package com.skytala.eCommerce.domain.order.relations.shoppingListItem;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.command.AddShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.command.DeleteShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.command.UpdateShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.event.ShoppingListItemAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.event.ShoppingListItemDeleted;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.event.ShoppingListItemFound;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.event.ShoppingListItemUpdated;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.mapper.ShoppingListItemMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.model.ShoppingListItem;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.query.FindShoppingListItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shoppingListItems")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShoppingListItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShoppingListItemsBy query = new FindShoppingListItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShoppingListItem> shoppingListItems =((ShoppingListItemFound) Scheduler.execute(query).data()).getShoppingListItems();

		if (shoppingListItems.size() == 1) {
			return ResponseEntity.ok().body(shoppingListItems.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShoppingListItem(HttpServletRequest request) throws Exception {

		ShoppingListItem shoppingListItemToBeAdded = new ShoppingListItem();
		try {
			shoppingListItemToBeAdded = ShoppingListItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createShoppingListItem(@RequestBody ShoppingListItem shoppingListItemToBeAdded) throws Exception {

		AddShoppingListItem command = new AddShoppingListItem(shoppingListItemToBeAdded);
		ShoppingListItem shoppingListItem = ((ShoppingListItemAdded) Scheduler.execute(command).data()).getAddedShoppingListItem();
		
		if (shoppingListItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shoppingListItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShoppingListItem could not be created.");
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
	public boolean updateShoppingListItem(HttpServletRequest request) throws Exception {

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

		ShoppingListItem shoppingListItemToBeUpdated = new ShoppingListItem();

		try {
			shoppingListItemToBeUpdated = ShoppingListItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShoppingListItem(shoppingListItemToBeUpdated, shoppingListItemToBeUpdated.getShoppingListItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShoppingListItem(@RequestBody ShoppingListItem shoppingListItemToBeUpdated,
			@PathVariable String shoppingListItemSeqId) throws Exception {

		shoppingListItemToBeUpdated.setShoppingListItemSeqId(shoppingListItemSeqId);

		UpdateShoppingListItem command = new UpdateShoppingListItem(shoppingListItemToBeUpdated);

		try {
			if(((ShoppingListItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shoppingListItemId}")
	public ResponseEntity<Object> findById(@PathVariable String shoppingListItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shoppingListItemId", shoppingListItemId);
		try {

			Object foundShoppingListItem = findShoppingListItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShoppingListItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shoppingListItemId}")
	public ResponseEntity<Object> deleteShoppingListItemByIdUpdated(@PathVariable String shoppingListItemId) throws Exception {
		DeleteShoppingListItem command = new DeleteShoppingListItem(shoppingListItemId);

		try {
			if (((ShoppingListItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShoppingListItem could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shoppingListItem/\" plus one of the following: "
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
