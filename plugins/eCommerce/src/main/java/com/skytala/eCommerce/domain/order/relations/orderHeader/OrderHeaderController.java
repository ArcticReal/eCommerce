package com.skytala.eCommerce.domain.order.relations.orderHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.framework.util.AuthorizeMethods;
import com.skytala.eCommerce.service.login.LoginServicesController;
import com.skytala.eCommerce.service.login.dto.UserDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.AddOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.DeleteOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.UpdateOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.query.FindOrderHeadersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.util.AuthorizeMethods.*;
import static com.skytala.eCommerce.service.login.util.SecurityGroups.ADMIN;

@RestController
@RequestMapping("/order/orderHeaders")
@PreAuthorize(HAS_ADMIN_AUTHORITY)
public class OrderHeaderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();
	@Resource
	private LoginServicesController loginServicesController;

	public OrderHeaderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeader
	 * @return a List with the OrderHeaders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	@PreAuthorize(AUTHENTICATED)
	public ResponseEntity<List<OrderHeader>> findOrderHeadersBy(@RequestParam(required = false) Map<String, String> allRequestParams,
																Principal principal) throws Exception {

		UserDetailsDTO userDetails = loginServicesController.getLoggedInPerson(principal).getBody();
		if(!userDetails.getAuthorities().contains(ADMIN))
			allRequestParams.put("partyIdTo", userDetails.getPartyId());

		FindOrderHeadersBy query = new FindOrderHeadersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeader> orderHeaders =((OrderHeaderFound) Scheduler.execute(query).data()).getOrderHeaders();



		return ResponseEntity.ok().body(orderHeaders);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PreAuthorize(HAS_USER_AUTHORITY)
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<OrderHeader> createOrderHeader(HttpServletRequest request) throws Exception {

		OrderHeader orderHeaderToBeAdded = new OrderHeader();
		try {
			orderHeaderToBeAdded = OrderHeaderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createOrderHeader(orderHeaderToBeAdded);

	}

	/**
	 * creates a new OrderHeader entry in the ofbiz database
	 * 
	 * @param orderHeaderToBeAdded
	 *            the OrderHeader thats to be added
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize(HAS_USER_AUTHORITY)
	public ResponseEntity<OrderHeader> createOrderHeader(@RequestBody OrderHeader orderHeaderToBeAdded) throws Exception {

		AddOrderHeader command = new AddOrderHeader(orderHeaderToBeAdded);
		OrderHeader orderHeader = ((OrderHeaderAdded) Scheduler.execute(command).data()).getAddedOrderHeader();
		
		if (orderHeader != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderHeader);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
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
	@PreAuthorize(AUTHENTICATED)
	public boolean updateOrderHeader(HttpServletRequest request) throws Exception {

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

		OrderHeader orderHeaderToBeUpdated = new OrderHeader();

		try {
			orderHeaderToBeUpdated = OrderHeaderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderHeader(orderHeaderToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderHeader with the specific Id
	 * 
	 * @param orderHeaderToBeUpdated
	 *            the OrderHeader thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize(AUTHENTICATED)
	public ResponseEntity<OrderHeader> updateOrderHeader(@RequestBody OrderHeader orderHeaderToBeUpdated,
			@PathVariable String orderId) throws Exception {

		orderHeaderToBeUpdated.setOrderId(orderId);

		UpdateOrderHeader command = new UpdateOrderHeader(orderHeaderToBeUpdated);

		try {
			if(((OrderHeaderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderHeaderId}")
	@PreAuthorize(AUTHENTICATED)
	public ResponseEntity<OrderHeader> findById(@PathVariable String orderHeaderId, Principal principal) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderId", orderHeaderId);
		try {

			List<OrderHeader> foundOrderHeader = findOrderHeadersBy(requestParams, principal).getBody();

			if(foundOrderHeader.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundOrderHeader.get(0));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderHeaderId}")
	@PreAuthorize(DENY_ALL)
	public ResponseEntity<Object> deleteOrderHeaderByIdUpdated(@PathVariable String orderHeaderId) throws Exception {
		DeleteOrderHeader command = new DeleteOrderHeader(orderHeaderId);

		try {
			if (((OrderHeaderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderHeader could not be deleted");

	}

}
