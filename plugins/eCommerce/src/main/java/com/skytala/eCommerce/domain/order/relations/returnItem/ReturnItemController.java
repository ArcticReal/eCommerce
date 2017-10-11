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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/returnItems")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemsBy query = new FindReturnItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItem> returnItems =((ReturnItemFound) Scheduler.execute(query).data()).getReturnItems();

		if (returnItems.size() == 1) {
			return ResponseEntity.ok().body(returnItems.get(0));
		}

		return ResponseEntity.ok().body(returnItems);

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
	public ResponseEntity<Object> createReturnItem(HttpServletRequest request) throws Exception {

		ReturnItem returnItemToBeAdded = new ReturnItem();
		try {
			returnItemToBeAdded = ReturnItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnItem(returnItemToBeAdded);

	}

	/**
	 * creates a new ReturnItem entry in the ofbiz database
	 * 
	 * @param returnItemToBeAdded
	 *            the ReturnItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnItem(@RequestBody ReturnItem returnItemToBeAdded) throws Exception {

		AddReturnItem command = new AddReturnItem(returnItemToBeAdded);
		ReturnItem returnItem = ((ReturnItemAdded) Scheduler.execute(command).data()).getAddedReturnItem();
		
		if (returnItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnItem could not be created.");
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
	public boolean updateReturnItem(HttpServletRequest request) throws Exception {

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

		ReturnItem returnItemToBeUpdated = new ReturnItem();

		try {
			returnItemToBeUpdated = ReturnItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnItem(returnItemToBeUpdated, returnItemToBeUpdated.getReturnItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateReturnItem(@RequestBody ReturnItem returnItemToBeUpdated,
			@PathVariable String returnItemSeqId) throws Exception {

		returnItemToBeUpdated.setReturnItemSeqId(returnItemSeqId);

		UpdateReturnItem command = new UpdateReturnItem(returnItemToBeUpdated);

		try {
			if(((ReturnItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnItemId}")
	public ResponseEntity<Object> findById(@PathVariable String returnItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemId", returnItemId);
		try {

			Object foundReturnItem = findReturnItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnItemId}")
	public ResponseEntity<Object> deleteReturnItemByIdUpdated(@PathVariable String returnItemId) throws Exception {
		DeleteReturnItem command = new DeleteReturnItem(returnItemId);

		try {
			if (((ReturnItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnItem could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/returnItem/\" plus one of the following: "
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