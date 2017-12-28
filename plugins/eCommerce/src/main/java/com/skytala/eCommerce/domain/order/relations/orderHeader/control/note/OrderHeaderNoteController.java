package com.skytala.eCommerce.domain.order.relations.orderHeader.control.note;

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
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.note.AddOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.note.DeleteOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.note.UpdateOrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.note.OrderHeaderNoteMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.note.OrderHeaderNote;
import com.skytala.eCommerce.domain.order.relations.orderHeader.query.note.FindOrderHeaderNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderHeader/orderHeaderNotes")
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
	@GetMapping("/find")
	public ResponseEntity<List<OrderHeaderNote>> findOrderHeaderNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeaderNotesBy query = new FindOrderHeaderNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeaderNote> orderHeaderNotes =((OrderHeaderNoteFound) Scheduler.execute(query).data()).getOrderHeaderNotes();

		return ResponseEntity.ok().body(orderHeaderNotes);

	}

	/**
	 * creates a new OrderHeaderNote entry in the ofbiz database
	 * 
	 * @param orderHeaderNoteToBeAdded
	 *            the OrderHeaderNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderHeaderNote> createOrderHeaderNote(@RequestBody OrderHeaderNote orderHeaderNoteToBeAdded) throws Exception {

		AddOrderHeaderNote command = new AddOrderHeaderNote(orderHeaderNoteToBeAdded);
		OrderHeaderNote orderHeaderNote = ((OrderHeaderNoteAdded) Scheduler.execute(command).data()).getAddedOrderHeaderNote();
		
		if (orderHeaderNote != null) 
			return successful(orderHeaderNote);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderHeaderNote(@RequestBody OrderHeaderNote orderHeaderNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderNoteToBeUpdated.setnull(null);

		UpdateOrderHeaderNote command = new UpdateOrderHeaderNote(orderHeaderNoteToBeUpdated);

		try {
			if(((OrderHeaderNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderHeaderNoteId}")
	public ResponseEntity<OrderHeaderNote> findById(@PathVariable String orderHeaderNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderNoteId", orderHeaderNoteId);
		try {

			List<OrderHeaderNote> foundOrderHeaderNote = findOrderHeaderNotesBy(requestParams).getBody();
			if(foundOrderHeaderNote.size()==1){				return successful(foundOrderHeaderNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderHeaderNoteId}")
	public ResponseEntity<String> deleteOrderHeaderNoteByIdUpdated(@PathVariable String orderHeaderNoteId) throws Exception {
		DeleteOrderHeaderNote command = new DeleteOrderHeaderNote(orderHeaderNoteId);

		try {
			if (((OrderHeaderNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
