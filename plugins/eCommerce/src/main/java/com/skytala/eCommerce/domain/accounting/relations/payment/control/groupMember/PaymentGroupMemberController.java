package com.skytala.eCommerce.domain.accounting.relations.payment.control.groupMember;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupMember.AddPaymentGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupMember.DeletePaymentGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupMember.UpdatePaymentGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupMember.PaymentGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.groupMember.FindPaymentGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGroupMembers")
public class PaymentGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGroupMember
	 * @return a List with the PaymentGroupMembers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGroupMember>> findPaymentGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGroupMembersBy query = new FindPaymentGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGroupMember> paymentGroupMembers =((PaymentGroupMemberFound) Scheduler.execute(query).data()).getPaymentGroupMembers();

		return ResponseEntity.ok().body(paymentGroupMembers);

	}

	/**
	 * creates a new PaymentGroupMember entry in the ofbiz database
	 * 
	 * @param paymentGroupMemberToBeAdded
	 *            the PaymentGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGroupMember> createPaymentGroupMember(@RequestBody PaymentGroupMember paymentGroupMemberToBeAdded) throws Exception {

		AddPaymentGroupMember command = new AddPaymentGroupMember(paymentGroupMemberToBeAdded);
		PaymentGroupMember paymentGroupMember = ((PaymentGroupMemberAdded) Scheduler.execute(command).data()).getAddedPaymentGroupMember();
		
		if (paymentGroupMember != null) 
			return successful(paymentGroupMember);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGroupMember with the specific Id
	 * 
	 * @param paymentGroupMemberToBeUpdated
	 *            the PaymentGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGroupMember(@RequestBody PaymentGroupMember paymentGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGroupMemberToBeUpdated.setnull(null);

		UpdatePaymentGroupMember command = new UpdatePaymentGroupMember(paymentGroupMemberToBeUpdated);

		try {
			if(((PaymentGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGroupMemberId}")
	public ResponseEntity<PaymentGroupMember> findById(@PathVariable String paymentGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGroupMemberId", paymentGroupMemberId);
		try {

			List<PaymentGroupMember> foundPaymentGroupMember = findPaymentGroupMembersBy(requestParams).getBody();
			if(foundPaymentGroupMember.size()==1){				return successful(foundPaymentGroupMember.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGroupMemberId}")
	public ResponseEntity<String> deletePaymentGroupMemberByIdUpdated(@PathVariable String paymentGroupMemberId) throws Exception {
		DeletePaymentGroupMember command = new DeletePaymentGroupMember(paymentGroupMemberId);

		try {
			if (((PaymentGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
