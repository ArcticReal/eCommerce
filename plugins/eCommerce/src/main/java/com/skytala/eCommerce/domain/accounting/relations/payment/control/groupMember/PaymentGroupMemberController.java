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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentGroupMembers")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGroupMembersBy query = new FindPaymentGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGroupMember> paymentGroupMembers =((PaymentGroupMemberFound) Scheduler.execute(query).data()).getPaymentGroupMembers();

		if (paymentGroupMembers.size() == 1) {
			return ResponseEntity.ok().body(paymentGroupMembers.get(0));
		}

		return ResponseEntity.ok().body(paymentGroupMembers);

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
	public ResponseEntity<Object> createPaymentGroupMember(HttpServletRequest request) throws Exception {

		PaymentGroupMember paymentGroupMemberToBeAdded = new PaymentGroupMember();
		try {
			paymentGroupMemberToBeAdded = PaymentGroupMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGroupMember(paymentGroupMemberToBeAdded);

	}

	/**
	 * creates a new PaymentGroupMember entry in the ofbiz database
	 * 
	 * @param paymentGroupMemberToBeAdded
	 *            the PaymentGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGroupMember(@RequestBody PaymentGroupMember paymentGroupMemberToBeAdded) throws Exception {

		AddPaymentGroupMember command = new AddPaymentGroupMember(paymentGroupMemberToBeAdded);
		PaymentGroupMember paymentGroupMember = ((PaymentGroupMemberAdded) Scheduler.execute(command).data()).getAddedPaymentGroupMember();
		
		if (paymentGroupMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGroupMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGroupMember could not be created.");
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
	public boolean updatePaymentGroupMember(HttpServletRequest request) throws Exception {

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

		PaymentGroupMember paymentGroupMemberToBeUpdated = new PaymentGroupMember();

		try {
			paymentGroupMemberToBeUpdated = PaymentGroupMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGroupMember(paymentGroupMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGroupMember(@RequestBody PaymentGroupMember paymentGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGroupMemberToBeUpdated.setnull(null);

		UpdatePaymentGroupMember command = new UpdatePaymentGroupMember(paymentGroupMemberToBeUpdated);

		try {
			if(((PaymentGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGroupMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGroupMemberId", paymentGroupMemberId);
		try {

			Object foundPaymentGroupMember = findPaymentGroupMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGroupMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGroupMemberId}")
	public ResponseEntity<Object> deletePaymentGroupMemberByIdUpdated(@PathVariable String paymentGroupMemberId) throws Exception {
		DeletePaymentGroupMember command = new DeletePaymentGroupMember(paymentGroupMemberId);

		try {
			if (((PaymentGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGroupMember could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGroupMember/\" plus one of the following: "
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
