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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/communicationEventOrders")
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
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventOrder>> findCommunicationEventOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventOrdersBy query = new FindCommunicationEventOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventOrder> communicationEventOrders =((CommunicationEventOrderFound) Scheduler.execute(query).data()).getCommunicationEventOrders();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CommunicationEventOrder> createCommunicationEventOrder(HttpServletRequest request) throws Exception {

		CommunicationEventOrder communicationEventOrderToBeAdded = new CommunicationEventOrder();
		try {
			communicationEventOrderToBeAdded = CommunicationEventOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CommunicationEventOrder> createCommunicationEventOrder(@RequestBody CommunicationEventOrder communicationEventOrderToBeAdded) throws Exception {

		AddCommunicationEventOrder command = new AddCommunicationEventOrder(communicationEventOrderToBeAdded);
		CommunicationEventOrder communicationEventOrder = ((CommunicationEventOrderAdded) Scheduler.execute(command).data()).getAddedCommunicationEventOrder();
		
		if (communicationEventOrder != null) 
			return successful(communicationEventOrder);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCommunicationEventOrder(@RequestBody CommunicationEventOrder communicationEventOrderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventOrderToBeUpdated.setnull(null);

		UpdateCommunicationEventOrder command = new UpdateCommunicationEventOrder(communicationEventOrderToBeUpdated);

		try {
			if(((CommunicationEventOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventOrderId}")
	public ResponseEntity<CommunicationEventOrder> findById(@PathVariable String communicationEventOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventOrderId", communicationEventOrderId);
		try {

			List<CommunicationEventOrder> foundCommunicationEventOrder = findCommunicationEventOrdersBy(requestParams).getBody();
			if(foundCommunicationEventOrder.size()==1){				return successful(foundCommunicationEventOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventOrderId}")
	public ResponseEntity<String> deleteCommunicationEventOrderByIdUpdated(@PathVariable String communicationEventOrderId) throws Exception {
		DeleteCommunicationEventOrder command = new DeleteCommunicationEventOrder(communicationEventOrderId);

		try {
			if (((CommunicationEventOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
