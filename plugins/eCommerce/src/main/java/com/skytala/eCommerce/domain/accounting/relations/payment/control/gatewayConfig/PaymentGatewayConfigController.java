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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/paymentGatewayConfigs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayConfigsBy query = new FindPaymentGatewayConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayConfig> paymentGatewayConfigs =((PaymentGatewayConfigFound) Scheduler.execute(query).data()).getPaymentGatewayConfigs();

		if (paymentGatewayConfigs.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayConfigs.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayConfigs);

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
	public ResponseEntity<Object> createPaymentGatewayConfig(HttpServletRequest request) throws Exception {

		PaymentGatewayConfig paymentGatewayConfigToBeAdded = new PaymentGatewayConfig();
		try {
			paymentGatewayConfigToBeAdded = PaymentGatewayConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayConfig(paymentGatewayConfigToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayConfig entry in the ofbiz database
	 * 
	 * @param paymentGatewayConfigToBeAdded
	 *            the PaymentGatewayConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayConfig(@RequestBody PaymentGatewayConfig paymentGatewayConfigToBeAdded) throws Exception {

		AddPaymentGatewayConfig command = new AddPaymentGatewayConfig(paymentGatewayConfigToBeAdded);
		PaymentGatewayConfig paymentGatewayConfig = ((PaymentGatewayConfigAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayConfig();
		
		if (paymentGatewayConfig != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayConfig);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayConfig could not be created.");
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
	public boolean updatePaymentGatewayConfig(HttpServletRequest request) throws Exception {

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

		PaymentGatewayConfig paymentGatewayConfigToBeUpdated = new PaymentGatewayConfig();

		try {
			paymentGatewayConfigToBeUpdated = PaymentGatewayConfigMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayConfig(paymentGatewayConfigToBeUpdated, paymentGatewayConfigToBeUpdated.getPaymentGatewayConfigId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayConfig(@RequestBody PaymentGatewayConfig paymentGatewayConfigToBeUpdated,
			@PathVariable String paymentGatewayConfigId) throws Exception {

		paymentGatewayConfigToBeUpdated.setPaymentGatewayConfigId(paymentGatewayConfigId);

		UpdatePaymentGatewayConfig command = new UpdatePaymentGatewayConfig(paymentGatewayConfigToBeUpdated);

		try {
			if(((PaymentGatewayConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayConfigId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayConfigId", paymentGatewayConfigId);
		try {

			Object foundPaymentGatewayConfig = findPaymentGatewayConfigsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayConfig);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayConfigId}")
	public ResponseEntity<Object> deletePaymentGatewayConfigByIdUpdated(@PathVariable String paymentGatewayConfigId) throws Exception {
		DeletePaymentGatewayConfig command = new DeletePaymentGatewayConfig(paymentGatewayConfigId);

		try {
			if (((PaymentGatewayConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayConfig could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayConfig/\" plus one of the following: "
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