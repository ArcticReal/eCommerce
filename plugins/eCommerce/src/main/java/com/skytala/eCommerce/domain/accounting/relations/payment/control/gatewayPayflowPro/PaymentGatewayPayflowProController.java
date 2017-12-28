package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayPayflowPro;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayflowPro.AddPaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayflowPro.DeletePaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayflowPro.UpdatePaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayflowPro.PaymentGatewayPayflowProMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayPayflowPro.FindPaymentGatewayPayflowProsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayPayflowPros")
public class PaymentGatewayPayflowProController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayPayflowProController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayPayflowPro
	 * @return a List with the PaymentGatewayPayflowPros
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayPayflowPro>> findPaymentGatewayPayflowProsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayPayflowProsBy query = new FindPaymentGatewayPayflowProsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayPayflowPro> paymentGatewayPayflowPros =((PaymentGatewayPayflowProFound) Scheduler.execute(query).data()).getPaymentGatewayPayflowPros();

		return ResponseEntity.ok().body(paymentGatewayPayflowPros);

	}

	/**
	 * creates a new PaymentGatewayPayflowPro entry in the ofbiz database
	 * 
	 * @param paymentGatewayPayflowProToBeAdded
	 *            the PaymentGatewayPayflowPro thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayPayflowPro> createPaymentGatewayPayflowPro(@RequestBody PaymentGatewayPayflowPro paymentGatewayPayflowProToBeAdded) throws Exception {

		AddPaymentGatewayPayflowPro command = new AddPaymentGatewayPayflowPro(paymentGatewayPayflowProToBeAdded);
		PaymentGatewayPayflowPro paymentGatewayPayflowPro = ((PaymentGatewayPayflowProAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayPayflowPro();
		
		if (paymentGatewayPayflowPro != null) 
			return successful(paymentGatewayPayflowPro);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayPayflowPro with the specific Id
	 * 
	 * @param paymentGatewayPayflowProToBeUpdated
	 *            the PaymentGatewayPayflowPro thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayPayflowPro(@RequestBody PaymentGatewayPayflowPro paymentGatewayPayflowProToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayPayflowProToBeUpdated.setnull(null);

		UpdatePaymentGatewayPayflowPro command = new UpdatePaymentGatewayPayflowPro(paymentGatewayPayflowProToBeUpdated);

		try {
			if(((PaymentGatewayPayflowProUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayPayflowProId}")
	public ResponseEntity<PaymentGatewayPayflowPro> findById(@PathVariable String paymentGatewayPayflowProId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayPayflowProId", paymentGatewayPayflowProId);
		try {

			List<PaymentGatewayPayflowPro> foundPaymentGatewayPayflowPro = findPaymentGatewayPayflowProsBy(requestParams).getBody();
			if(foundPaymentGatewayPayflowPro.size()==1){				return successful(foundPaymentGatewayPayflowPro.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayPayflowProId}")
	public ResponseEntity<String> deletePaymentGatewayPayflowProByIdUpdated(@PathVariable String paymentGatewayPayflowProId) throws Exception {
		DeletePaymentGatewayPayflowPro command = new DeletePaymentGatewayPayflowPro(paymentGatewayPayflowProId);

		try {
			if (((PaymentGatewayPayflowProDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
