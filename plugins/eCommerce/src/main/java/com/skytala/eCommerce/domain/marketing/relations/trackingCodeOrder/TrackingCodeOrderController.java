package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.command.AddTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.command.DeleteTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.command.UpdateTrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.mapper.TrackingCodeOrderMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.query.FindTrackingCodeOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/trackingCodeOrders")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrackingCodeOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeOrdersBy query = new FindTrackingCodeOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeOrder> trackingCodeOrders =((TrackingCodeOrderFound) Scheduler.execute(query).data()).getTrackingCodeOrders();

		if (trackingCodeOrders.size() == 1) {
			return ResponseEntity.ok().body(trackingCodeOrders.get(0));
		}

		return ResponseEntity.ok().body(trackingCodeOrders);

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
	public ResponseEntity<Object> createTrackingCodeOrder(HttpServletRequest request) throws Exception {

		TrackingCodeOrder trackingCodeOrderToBeAdded = new TrackingCodeOrder();
		try {
			trackingCodeOrderToBeAdded = TrackingCodeOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTrackingCodeOrder(trackingCodeOrderToBeAdded);

	}

	/**
	 * creates a new TrackingCodeOrder entry in the ofbiz database
	 * 
	 * @param trackingCodeOrderToBeAdded
	 *            the TrackingCodeOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTrackingCodeOrder(@RequestBody TrackingCodeOrder trackingCodeOrderToBeAdded) throws Exception {

		AddTrackingCodeOrder command = new AddTrackingCodeOrder(trackingCodeOrderToBeAdded);
		TrackingCodeOrder trackingCodeOrder = ((TrackingCodeOrderAdded) Scheduler.execute(command).data()).getAddedTrackingCodeOrder();
		
		if (trackingCodeOrder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trackingCodeOrder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrackingCodeOrder could not be created.");
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
	public boolean updateTrackingCodeOrder(HttpServletRequest request) throws Exception {

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

		TrackingCodeOrder trackingCodeOrderToBeUpdated = new TrackingCodeOrder();

		try {
			trackingCodeOrderToBeUpdated = TrackingCodeOrderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrackingCodeOrder(trackingCodeOrderToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTrackingCodeOrder(@RequestBody TrackingCodeOrder trackingCodeOrderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		trackingCodeOrderToBeUpdated.setnull(null);

		UpdateTrackingCodeOrder command = new UpdateTrackingCodeOrder(trackingCodeOrderToBeUpdated);

		try {
			if(((TrackingCodeOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingCodeOrderId}")
	public ResponseEntity<Object> findById(@PathVariable String trackingCodeOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeOrderId", trackingCodeOrderId);
		try {

			Object foundTrackingCodeOrder = findTrackingCodeOrdersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrackingCodeOrder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingCodeOrderId}")
	public ResponseEntity<Object> deleteTrackingCodeOrderByIdUpdated(@PathVariable String trackingCodeOrderId) throws Exception {
		DeleteTrackingCodeOrder command = new DeleteTrackingCodeOrder(trackingCodeOrderId);

		try {
			if (((TrackingCodeOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrackingCodeOrder could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trackingCodeOrder/\" plus one of the following: "
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
