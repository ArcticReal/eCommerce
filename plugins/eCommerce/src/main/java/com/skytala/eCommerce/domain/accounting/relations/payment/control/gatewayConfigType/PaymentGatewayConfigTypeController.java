package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayConfigType;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfigType.AddPaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfigType.DeletePaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfigType.UpdatePaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType.PaymentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType.PaymentGatewayConfigTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType.PaymentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType.PaymentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayConfigType.PaymentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfigType.PaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayConfigType.FindPaymentGatewayConfigTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayConfigTypes")
public class PaymentGatewayConfigTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayConfigTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayConfigType
	 * @return a List with the PaymentGatewayConfigTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayConfigType>> findPaymentGatewayConfigTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayConfigTypesBy query = new FindPaymentGatewayConfigTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayConfigType> paymentGatewayConfigTypes =((PaymentGatewayConfigTypeFound) Scheduler.execute(query).data()).getPaymentGatewayConfigTypes();

		return ResponseEntity.ok().body(paymentGatewayConfigTypes);

	}

	/**
	 * creates a new PaymentGatewayConfigType entry in the ofbiz database
	 * 
	 * @param paymentGatewayConfigTypeToBeAdded
	 *            the PaymentGatewayConfigType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayConfigType> createPaymentGatewayConfigType(@RequestBody PaymentGatewayConfigType paymentGatewayConfigTypeToBeAdded) throws Exception {

		AddPaymentGatewayConfigType command = new AddPaymentGatewayConfigType(paymentGatewayConfigTypeToBeAdded);
		PaymentGatewayConfigType paymentGatewayConfigType = ((PaymentGatewayConfigTypeAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayConfigType();
		
		if (paymentGatewayConfigType != null) 
			return successful(paymentGatewayConfigType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayConfigType with the specific Id
	 * 
	 * @param paymentGatewayConfigTypeToBeUpdated
	 *            the PaymentGatewayConfigType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGatewayConfigTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayConfigType(@RequestBody PaymentGatewayConfigType paymentGatewayConfigTypeToBeUpdated,
			@PathVariable String paymentGatewayConfigTypeId) throws Exception {

		paymentGatewayConfigTypeToBeUpdated.setPaymentGatewayConfigTypeId(paymentGatewayConfigTypeId);

		UpdatePaymentGatewayConfigType command = new UpdatePaymentGatewayConfigType(paymentGatewayConfigTypeToBeUpdated);

		try {
			if(((PaymentGatewayConfigTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayConfigTypeId}")
	public ResponseEntity<PaymentGatewayConfigType> findById(@PathVariable String paymentGatewayConfigTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayConfigTypeId", paymentGatewayConfigTypeId);
		try {

			List<PaymentGatewayConfigType> foundPaymentGatewayConfigType = findPaymentGatewayConfigTypesBy(requestParams).getBody();
			if(foundPaymentGatewayConfigType.size()==1){				return successful(foundPaymentGatewayConfigType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayConfigTypeId}")
	public ResponseEntity<String> deletePaymentGatewayConfigTypeByIdUpdated(@PathVariable String paymentGatewayConfigTypeId) throws Exception {
		DeletePaymentGatewayConfigType command = new DeletePaymentGatewayConfigType(paymentGatewayConfigTypeId);

		try {
			if (((PaymentGatewayConfigTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
