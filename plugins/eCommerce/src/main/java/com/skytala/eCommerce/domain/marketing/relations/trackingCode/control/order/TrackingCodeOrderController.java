package com.skytala.eCommerce.domain.marketing.relations.trackingCode.control.order;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.order.AddTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.order.DeleteTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.order.UpdateTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order.TrackingCodeOrderAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order.TrackingCodeOrderDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order.TrackingCodeOrderFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.order.TrackingCodeOrderUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.order.TrackingCodeOrderMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.order.TrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.order.FindTrackingCodeOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/trackingCode/trackingCodeOrders")
public class TrackingCodeOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrackingCodeOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrackingCodeOrder
	 * @return a List with the TrackingCodeOrders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TrackingCodeOrder>> findTrackingCodeOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeOrdersBy query = new FindTrackingCodeOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeOrder> trackingCodeOrders =((TrackingCodeOrderFound) Scheduler.execute(query).data()).getTrackingCodeOrders();

		return ResponseEntity.ok().body(trackingCodeOrders);

	}

	/**
	 * creates a new TrackingCodeOrder entry in the ofbiz database
	 * 
	 * @param trackingCodeOrderToBeAdded
	 *            the TrackingCodeOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrackingCodeOrder> createTrackingCodeOrder(@RequestBody TrackingCodeOrder trackingCodeOrderToBeAdded) throws Exception {

		AddTrackingCodeOrder command = new AddTrackingCodeOrder(trackingCodeOrderToBeAdded);
		TrackingCodeOrder trackingCodeOrder = ((TrackingCodeOrderAdded) Scheduler.execute(command).data()).getAddedTrackingCodeOrder();
		
		if (trackingCodeOrder != null) 
			return successful(trackingCodeOrder);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TrackingCodeOrder with the specific Id
	 * 
	 * @param trackingCodeOrderToBeUpdated
	 *            the TrackingCodeOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTrackingCodeOrder(@RequestBody TrackingCodeOrder trackingCodeOrderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		trackingCodeOrderToBeUpdated.setnull(null);

		UpdateTrackingCodeOrder command = new UpdateTrackingCodeOrder(trackingCodeOrderToBeUpdated);

		try {
			if(((TrackingCodeOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trackingCodeOrderId}")
	public ResponseEntity<TrackingCodeOrder> findById(@PathVariable String trackingCodeOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeOrderId", trackingCodeOrderId);
		try {

			List<TrackingCodeOrder> foundTrackingCodeOrder = findTrackingCodeOrdersBy(requestParams).getBody();
			if(foundTrackingCodeOrder.size()==1){				return successful(foundTrackingCodeOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trackingCodeOrderId}")
	public ResponseEntity<String> deleteTrackingCodeOrderByIdUpdated(@PathVariable String trackingCodeOrderId) throws Exception {
		DeleteTrackingCodeOrder command = new DeleteTrackingCodeOrder(trackingCodeOrderId);

		try {
			if (((TrackingCodeOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
