package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayConfig;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfig.AddPaymentGatewayConfig;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfig.DeletePaymentGatewayConfig;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayConfig.UpdatePaymentGatewayConfig;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig.PaymentGatewayConfigAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig.PaymentGatewayConfigDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig.PaymentGatewayConfigFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig.PaymentGatewayConfigUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayConfig.PaymentGatewayConfigMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfig.PaymentGatewayConfig;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayConfig.FindPaymentGatewayConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayConfigs")
public class PaymentGatewayConfigController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayConfigController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayConfig
	 * @return a List with the PaymentGatewayConfigs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayConfig>> findPaymentGatewayConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayConfigsBy query = new FindPaymentGatewayConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayConfig> paymentGatewayConfigs =((PaymentGatewayConfigFound) Scheduler.execute(query).data()).getPaymentGatewayConfigs();

		return ResponseEntity.ok().body(paymentGatewayConfigs);

	}

	/**
	 * creates a new PaymentGatewayConfig entry in the ofbiz database
	 * 
	 * @param paymentGatewayConfigToBeAdded
	 *            the PaymentGatewayConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayConfig> createPaymentGatewayConfig(@RequestBody PaymentGatewayConfig paymentGatewayConfigToBeAdded) throws Exception {

		AddPaymentGatewayConfig command = new AddPaymentGatewayConfig(paymentGatewayConfigToBeAdded);
		PaymentGatewayConfig paymentGatewayConfig = ((PaymentGatewayConfigAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayConfig();
		
		if (paymentGatewayConfig != null) 
			return successful(paymentGatewayConfig);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayConfig with the specific Id
	 * 
	 * @param paymentGatewayConfigToBeUpdated
	 *            the PaymentGatewayConfig thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGatewayConfigId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayConfig(@RequestBody PaymentGatewayConfig paymentGatewayConfigToBeUpdated,
			@PathVariable String paymentGatewayConfigId) throws Exception {

		paymentGatewayConfigToBeUpdated.setPaymentGatewayConfigId(paymentGatewayConfigId);

		UpdatePaymentGatewayConfig command = new UpdatePaymentGatewayConfig(paymentGatewayConfigToBeUpdated);

		try {
			if(((PaymentGatewayConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayConfigId}")
	public ResponseEntity<PaymentGatewayConfig> findById(@PathVariable String paymentGatewayConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayConfigId", paymentGatewayConfigId);
		try {

			List<PaymentGatewayConfig> foundPaymentGatewayConfig = findPaymentGatewayConfigsBy(requestParams).getBody();
			if(foundPaymentGatewayConfig.size()==1){				return successful(foundPaymentGatewayConfig.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayConfigId}")
	public ResponseEntity<String> deletePaymentGatewayConfigByIdUpdated(@PathVariable String paymentGatewayConfigId) throws Exception {
		DeletePaymentGatewayConfig command = new DeletePaymentGatewayConfig(paymentGatewayConfigId);

		try {
			if (((PaymentGatewayConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
