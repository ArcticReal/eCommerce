package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayRespMsg;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayRespMsg.AddPaymentGatewayRespMsg;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayRespMsg.DeletePaymentGatewayRespMsg;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayRespMsg.UpdatePaymentGatewayRespMsg;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg.PaymentGatewayRespMsgAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg.PaymentGatewayRespMsgDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg.PaymentGatewayRespMsgFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg.PaymentGatewayRespMsgUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayRespMsg.PaymentGatewayRespMsgMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayRespMsg.FindPaymentGatewayRespMsgsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayRespMsgs")
public class PaymentGatewayRespMsgController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayRespMsgController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayRespMsg
	 * @return a List with the PaymentGatewayRespMsgs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayRespMsg>> findPaymentGatewayRespMsgsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayRespMsgsBy query = new FindPaymentGatewayRespMsgsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayRespMsg> paymentGatewayRespMsgs =((PaymentGatewayRespMsgFound) Scheduler.execute(query).data()).getPaymentGatewayRespMsgs();

		return ResponseEntity.ok().body(paymentGatewayRespMsgs);

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
	public ResponseEntity<PaymentGatewayRespMsg> createPaymentGatewayRespMsg(HttpServletRequest request) throws Exception {

		PaymentGatewayRespMsg paymentGatewayRespMsgToBeAdded = new PaymentGatewayRespMsg();
		try {
			paymentGatewayRespMsgToBeAdded = PaymentGatewayRespMsgMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentGatewayRespMsg(paymentGatewayRespMsgToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayRespMsg entry in the ofbiz database
	 * 
	 * @param paymentGatewayRespMsgToBeAdded
	 *            the PaymentGatewayRespMsg thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayRespMsg> createPaymentGatewayRespMsg(@RequestBody PaymentGatewayRespMsg paymentGatewayRespMsgToBeAdded) throws Exception {

		AddPaymentGatewayRespMsg command = new AddPaymentGatewayRespMsg(paymentGatewayRespMsgToBeAdded);
		PaymentGatewayRespMsg paymentGatewayRespMsg = ((PaymentGatewayRespMsgAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayRespMsg();
		
		if (paymentGatewayRespMsg != null) 
			return successful(paymentGatewayRespMsg);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayRespMsg with the specific Id
	 * 
	 * @param paymentGatewayRespMsgToBeUpdated
	 *            the PaymentGatewayRespMsg thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGatewayRespMsgId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayRespMsg(@RequestBody PaymentGatewayRespMsg paymentGatewayRespMsgToBeUpdated,
			@PathVariable String paymentGatewayRespMsgId) throws Exception {

		paymentGatewayRespMsgToBeUpdated.setPaymentGatewayRespMsgId(paymentGatewayRespMsgId);

		UpdatePaymentGatewayRespMsg command = new UpdatePaymentGatewayRespMsg(paymentGatewayRespMsgToBeUpdated);

		try {
			if(((PaymentGatewayRespMsgUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayRespMsgId}")
	public ResponseEntity<PaymentGatewayRespMsg> findById(@PathVariable String paymentGatewayRespMsgId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayRespMsgId", paymentGatewayRespMsgId);
		try {

			List<PaymentGatewayRespMsg> foundPaymentGatewayRespMsg = findPaymentGatewayRespMsgsBy(requestParams).getBody();
			if(foundPaymentGatewayRespMsg.size()==1){				return successful(foundPaymentGatewayRespMsg.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayRespMsgId}")
	public ResponseEntity<String> deletePaymentGatewayRespMsgByIdUpdated(@PathVariable String paymentGatewayRespMsgId) throws Exception {
		DeletePaymentGatewayRespMsg command = new DeletePaymentGatewayRespMsg(paymentGatewayRespMsgId);

		try {
			if (((PaymentGatewayRespMsgDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
