package com.skytala.eCommerce.domain.order.relations.orderHeaderNote;

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
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.command.AddOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.command.DeleteOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.command.UpdateOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event.OrderHeaderNoteAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event.OrderHeaderNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event.OrderHeaderNoteFound;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event.OrderHeaderNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.mapper.OrderHeaderNoteMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.query.FindOrderHeaderNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderHeaderNotes")
public class OrderHeaderNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderHeaderNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeaderNote
	 * @return a List with the OrderHeaderNotes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderHeaderNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeaderNotesBy query = new FindOrderHeaderNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeaderNote> orderHeaderNotes =((OrderHeaderNoteFound) Scheduler.execute(query).data()).getOrderHeaderNotes();

		if (orderHeaderNotes.size() == 1) {
			return ResponseEntity.ok().body(orderHeaderNotes.get(0));
		}

		return ResponseEntity.ok().body(orderHeaderNotes);

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
	public ResponseEntity<Object> createOrderHeaderNote(HttpServletRequest request) throws Exception {

		OrderHeaderNote orderHeaderNoteToBeAdded = new OrderHeaderNote();
		try {
			orderHeaderNoteToBeAdded = OrderHeaderNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderHeaderNote(orderHeaderNoteToBeAdded);

	}

	/**
	 * creates a new OrderHeaderNote entry in the ofbiz database
	 * 
	 * @param orderHeaderNoteToBeAdded
	 *            the OrderHeaderNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderHeaderNote(@RequestBody OrderHeaderNote orderHeaderNoteToBeAdded) throws Exception {

		AddOrderHeaderNote command = new AddOrderHeaderNote(orderHeaderNoteToBeAdded);
		OrderHeaderNote orderHeaderNote = ((OrderHeaderNoteAdded) Scheduler.execute(command).data()).getAddedOrderHeaderNote();
		
		if (orderHeaderNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderHeaderNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderHeaderNote could not be created.");
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
	public boolean updateOrderHeaderNote(HttpServletRequest request) throws Exception {

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

		OrderHeaderNote orderHeaderNoteToBeUpdated = new OrderHeaderNote();

		try {
			orderHeaderNoteToBeUpdated = OrderHeaderNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderHeaderNote(orderHeaderNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderHeaderNote with the specific Id
	 * 
	 * @param orderHeaderNoteToBeUpdated
	 *            the OrderHeaderNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderHeaderNote(@RequestBody OrderHeaderNote orderHeaderNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderNoteToBeUpdated.setnull(null);

		UpdateOrderHeaderNote command = new UpdateOrderHeaderNote(orderHeaderNoteToBeUpdated);

		try {
			if(((OrderHeaderNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderHeaderNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String orderHeaderNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderNoteId", orderHeaderNoteId);
		try {

			Object foundOrderHeaderNote = findOrderHeaderNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderHeaderNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderHeaderNoteId}")
	public ResponseEntity<Object> deleteOrderHeaderNoteByIdUpdated(@PathVariable String orderHeaderNoteId) throws Exception {
		DeleteOrderHeaderNote command = new DeleteOrderHeaderNote(orderHeaderNoteId);

		try {
			if (((OrderHeaderNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderHeaderNote could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderHeaderNote/\" plus one of the following: "
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
