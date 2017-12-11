package com.skytala.eCommerce.domain.accounting.relations.payment.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.AddPaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.DeletePaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.UpdatePaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.type.PaymentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.type.FindPaymentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentTypes")
public class PaymentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentType
	 * @return a List with the PaymentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentTypesBy query = new FindPaymentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentType> paymentTypes =((PaymentTypeFound) Scheduler.execute(query).data()).getPaymentTypes();

		if (paymentTypes.size() == 1) {
			return ResponseEntity.ok().body(paymentTypes.get(0));
		}

		return ResponseEntity.ok().body(paymentTypes);

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
	public ResponseEntity<Object> createPaymentType(HttpServletRequest request) throws Exception {

		PaymentType paymentTypeToBeAdded = new PaymentType();
		try {
			paymentTypeToBeAdded = PaymentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentType(paymentTypeToBeAdded);

	}

	/**
	 * creates a new PaymentType entry in the ofbiz database
	 * 
	 * @param paymentTypeToBeAdded
	 *            the PaymentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentType(@RequestBody PaymentType paymentTypeToBeAdded) throws Exception {

		AddPaymentType command = new AddPaymentType(paymentTypeToBeAdded);
		PaymentType paymentType = ((PaymentTypeAdded) Scheduler.execute(command).data()).getAddedPaymentType();
		
		if (paymentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentType could not be created.");
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
	public boolean updatePaymentType(HttpServletRequest request) throws Exception {

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

		PaymentType paymentTypeToBeUpdated = new PaymentType();

		try {
			paymentTypeToBeUpdated = PaymentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentType(paymentTypeToBeUpdated, paymentTypeToBeUpdated.getPaymentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentType with the specific Id
	 * 
	 * @param paymentTypeToBeUpdated
	 *            the PaymentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentType(@RequestBody PaymentType paymentTypeToBeUpdated,
			@PathVariable String paymentTypeId) throws Exception {

		paymentTypeToBeUpdated.setPaymentTypeId(paymentTypeId);

		UpdatePaymentType command = new UpdatePaymentType(paymentTypeToBeUpdated);

		try {
			if(((PaymentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentTypeId", paymentTypeId);
		try {

			Object foundPaymentType = findPaymentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentTypeId}")
	public ResponseEntity<Object> deletePaymentTypeByIdUpdated(@PathVariable String paymentTypeId) throws Exception {
		DeletePaymentType command = new DeletePaymentType(paymentTypeId);

		try {
			if (((PaymentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentType could not be deleted");

	}

}
