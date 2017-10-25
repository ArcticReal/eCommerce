package com.skytala.eCommerce.domain.order.relations.communicationEventOrder;

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
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.command.AddCommunicationEventOrder;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.command.DeleteCommunicationEventOrder;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.command.UpdateCommunicationEventOrder;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderAdded;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderDeleted;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderFound;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderUpdated;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.mapper.CommunicationEventOrderMapper;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.query.FindCommunicationEventOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/communicationEventOrders")
public class CommunicationEventOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventOrder
	 * @return a List with the CommunicationEventOrders
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventOrdersBy query = new FindCommunicationEventOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventOrder> communicationEventOrders =((CommunicationEventOrderFound) Scheduler.execute(query).data()).getCommunicationEventOrders();

		if (communicationEventOrders.size() == 1) {
			return ResponseEntity.ok().body(communicationEventOrders.get(0));
		}

		return ResponseEntity.ok().body(communicationEventOrders);

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
	public ResponseEntity<Object> createCommunicationEventOrder(HttpServletRequest request) throws Exception {

		CommunicationEventOrder communicationEventOrderToBeAdded = new CommunicationEventOrder();
		try {
			communicationEventOrderToBeAdded = CommunicationEventOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventOrder(communicationEventOrderToBeAdded);

	}

	/**
	 * creates a new CommunicationEventOrder entry in the ofbiz database
	 * 
	 * @param communicationEventOrderToBeAdded
	 *            the CommunicationEventOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventOrder(@RequestBody CommunicationEventOrder communicationEventOrderToBeAdded) throws Exception {

		AddCommunicationEventOrder command = new AddCommunicationEventOrder(communicationEventOrderToBeAdded);
		CommunicationEventOrder communicationEventOrder = ((CommunicationEventOrderAdded) Scheduler.execute(command).data()).getAddedCommunicationEventOrder();
		
		if (communicationEventOrder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventOrder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventOrder could not be created.");
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
	public boolean updateCommunicationEventOrder(HttpServletRequest request) throws Exception {

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

		CommunicationEventOrder communicationEventOrderToBeUpdated = new CommunicationEventOrder();

		try {
			communicationEventOrderToBeUpdated = CommunicationEventOrderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventOrder(communicationEventOrderToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventOrder with the specific Id
	 * 
	 * @param communicationEventOrderToBeUpdated
	 *            the CommunicationEventOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventOrder(@RequestBody CommunicationEventOrder communicationEventOrderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventOrderToBeUpdated.setnull(null);

		UpdateCommunicationEventOrder command = new UpdateCommunicationEventOrder(communicationEventOrderToBeUpdated);

		try {
			if(((CommunicationEventOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventOrderId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventOrderId", communicationEventOrderId);
		try {

			Object foundCommunicationEventOrder = findCommunicationEventOrdersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventOrder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventOrderId}")
	public ResponseEntity<Object> deleteCommunicationEventOrderByIdUpdated(@PathVariable String communicationEventOrderId) throws Exception {
		DeleteCommunicationEventOrder command = new DeleteCommunicationEventOrder(communicationEventOrderId);

		try {
			if (((CommunicationEventOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventOrder could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/communicationEventOrder/\" plus one of the following: "
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
