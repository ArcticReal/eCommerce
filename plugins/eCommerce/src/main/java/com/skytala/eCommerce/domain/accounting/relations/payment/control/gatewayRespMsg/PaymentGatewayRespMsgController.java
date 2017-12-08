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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentGatewayRespMsgs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayRespMsgsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayRespMsgsBy query = new FindPaymentGatewayRespMsgsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayRespMsg> paymentGatewayRespMsgs =((PaymentGatewayRespMsgFound) Scheduler.execute(query).data()).getPaymentGatewayRespMsgs();

		if (paymentGatewayRespMsgs.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayRespMsgs.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPaymentGatewayRespMsg(HttpServletRequest request) throws Exception {

		PaymentGatewayRespMsg paymentGatewayRespMsgToBeAdded = new PaymentGatewayRespMsg();
		try {
			paymentGatewayRespMsgToBeAdded = PaymentGatewayRespMsgMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPaymentGatewayRespMsg(@RequestBody PaymentGatewayRespMsg paymentGatewayRespMsgToBeAdded) throws Exception {

		AddPaymentGatewayRespMsg command = new AddPaymentGatewayRespMsg(paymentGatewayRespMsgToBeAdded);
		PaymentGatewayRespMsg paymentGatewayRespMsg = ((PaymentGatewayRespMsgAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayRespMsg();
		
		if (paymentGatewayRespMsg != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayRespMsg);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayRespMsg could not be created.");
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
	public boolean updatePaymentGatewayRespMsg(HttpServletRequest request) throws Exception {

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

		PaymentGatewayRespMsg paymentGatewayRespMsgToBeUpdated = new PaymentGatewayRespMsg();

		try {
			paymentGatewayRespMsgToBeUpdated = PaymentGatewayRespMsgMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayRespMsg(paymentGatewayRespMsgToBeUpdated, paymentGatewayRespMsgToBeUpdated.getPaymentGatewayRespMsgId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayRespMsg(@RequestBody PaymentGatewayRespMsg paymentGatewayRespMsgToBeUpdated,
			@PathVariable String paymentGatewayRespMsgId) throws Exception {

		paymentGatewayRespMsgToBeUpdated.setPaymentGatewayRespMsgId(paymentGatewayRespMsgId);

		UpdatePaymentGatewayRespMsg command = new UpdatePaymentGatewayRespMsg(paymentGatewayRespMsgToBeUpdated);

		try {
			if(((PaymentGatewayRespMsgUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayRespMsgId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayRespMsgId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayRespMsgId", paymentGatewayRespMsgId);
		try {

			Object foundPaymentGatewayRespMsg = findPaymentGatewayRespMsgsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayRespMsg);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayRespMsgId}")
	public ResponseEntity<Object> deletePaymentGatewayRespMsgByIdUpdated(@PathVariable String paymentGatewayRespMsgId) throws Exception {
		DeletePaymentGatewayRespMsg command = new DeletePaymentGatewayRespMsg(paymentGatewayRespMsgId);

		try {
			if (((PaymentGatewayRespMsgDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayRespMsg could not be deleted");

	}

}
