package com.skytala.eCommerce.domain.accounting.relations.payment.control.group;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.group.AddPaymentGroup;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.group.DeletePaymentGroup;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.group.UpdatePaymentGroup;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.group.PaymentGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.group.FindPaymentGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGroups")
public class PaymentGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGroup
	 * @return a List with the PaymentGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGroup>> findPaymentGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGroupsBy query = new FindPaymentGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGroup> paymentGroups =((PaymentGroupFound) Scheduler.execute(query).data()).getPaymentGroups();

		return ResponseEntity.ok().body(paymentGroups);

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
	public ResponseEntity<PaymentGroup> createPaymentGroup(HttpServletRequest request) throws Exception {

		PaymentGroup paymentGroupToBeAdded = new PaymentGroup();
		try {
			paymentGroupToBeAdded = PaymentGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentGroup(paymentGroupToBeAdded);

	}

	/**
	 * creates a new PaymentGroup entry in the ofbiz database
	 * 
	 * @param paymentGroupToBeAdded
	 *            the PaymentGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGroup> createPaymentGroup(@RequestBody PaymentGroup paymentGroupToBeAdded) throws Exception {

		AddPaymentGroup command = new AddPaymentGroup(paymentGroupToBeAdded);
		PaymentGroup paymentGroup = ((PaymentGroupAdded) Scheduler.execute(command).data()).getAddedPaymentGroup();
		
		if (paymentGroup != null) 
			return successful(paymentGroup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGroup with the specific Id
	 * 
	 * @param paymentGroupToBeUpdated
	 *            the PaymentGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGroup(@RequestBody PaymentGroup paymentGroupToBeUpdated,
			@PathVariable String paymentGroupId) throws Exception {

		paymentGroupToBeUpdated.setPaymentGroupId(paymentGroupId);

		UpdatePaymentGroup command = new UpdatePaymentGroup(paymentGroupToBeUpdated);

		try {
			if(((PaymentGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGroupId}")
	public ResponseEntity<PaymentGroup> findById(@PathVariable String paymentGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGroupId", paymentGroupId);
		try {

			List<PaymentGroup> foundPaymentGroup = findPaymentGroupsBy(requestParams).getBody();
			if(foundPaymentGroup.size()==1){				return successful(foundPaymentGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGroupId}")
	public ResponseEntity<String> deletePaymentGroupByIdUpdated(@PathVariable String paymentGroupId) throws Exception {
		DeletePaymentGroup command = new DeletePaymentGroup(paymentGroupId);

		try {
			if (((PaymentGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
