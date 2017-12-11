package com.skytala.eCommerce.domain.accounting.relations.payment.control.methodType;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.AddPaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.DeletePaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.UpdatePaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.methodType.PaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.methodType.PaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.methodType.FindPaymentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentMethodTypes")
public class PaymentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentMethodType
	 * @return a List with the PaymentMethodTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodTypesBy query = new FindPaymentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethodType> paymentMethodTypes =((PaymentMethodTypeFound) Scheduler.execute(query).data()).getPaymentMethodTypes();

		if (paymentMethodTypes.size() == 1) {
			return ResponseEntity.ok().body(paymentMethodTypes.get(0));
		}

		return ResponseEntity.ok().body(paymentMethodTypes);

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
	public ResponseEntity<Object> createPaymentMethodType(HttpServletRequest request) throws Exception {

		PaymentMethodType paymentMethodTypeToBeAdded = new PaymentMethodType();
		try {
			paymentMethodTypeToBeAdded = PaymentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentMethodType(paymentMethodTypeToBeAdded);

	}

	/**
	 * creates a new PaymentMethodType entry in the ofbiz database
	 * 
	 * @param paymentMethodTypeToBeAdded
	 *            the PaymentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentMethodType(@RequestBody PaymentMethodType paymentMethodTypeToBeAdded) throws Exception {

		AddPaymentMethodType command = new AddPaymentMethodType(paymentMethodTypeToBeAdded);
		PaymentMethodType paymentMethodType = ((PaymentMethodTypeAdded) Scheduler.execute(command).data()).getAddedPaymentMethodType();
		
		if (paymentMethodType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentMethodType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentMethodType could not be created.");
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
	public boolean updatePaymentMethodType(HttpServletRequest request) throws Exception {

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

		PaymentMethodType paymentMethodTypeToBeUpdated = new PaymentMethodType();

		try {
			paymentMethodTypeToBeUpdated = PaymentMethodTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentMethodType(paymentMethodTypeToBeUpdated, paymentMethodTypeToBeUpdated.getPaymentMethodTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentMethodType with the specific Id
	 * 
	 * @param paymentMethodTypeToBeUpdated
	 *            the PaymentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentMethodTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentMethodType(@RequestBody PaymentMethodType paymentMethodTypeToBeUpdated,
			@PathVariable String paymentMethodTypeId) throws Exception {

		paymentMethodTypeToBeUpdated.setPaymentMethodTypeId(paymentMethodTypeId);

		UpdatePaymentMethodType command = new UpdatePaymentMethodType(paymentMethodTypeToBeUpdated);

		try {
			if(((PaymentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentMethodTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodTypeId", paymentMethodTypeId);
		try {

			Object foundPaymentMethodType = findPaymentMethodTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentMethodType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentMethodTypeId}")
	public ResponseEntity<Object> deletePaymentMethodTypeByIdUpdated(@PathVariable String paymentMethodTypeId) throws Exception {
		DeletePaymentMethodType command = new DeletePaymentMethodType(paymentMethodTypeId);

		try {
			if (((PaymentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentMethodType could not be deleted");

	}

}
