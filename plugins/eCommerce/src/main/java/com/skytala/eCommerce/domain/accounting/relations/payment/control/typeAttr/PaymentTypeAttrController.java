package com.skytala.eCommerce.domain.accounting.relations.payment.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.AddPaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.DeletePaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.UpdatePaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.typeAttr.PaymentTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.typeAttr.FindPaymentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentTypeAttrs")
public class PaymentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentTypeAttr
	 * @return a List with the PaymentTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPaymentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentTypeAttrsBy query = new FindPaymentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentTypeAttr> paymentTypeAttrs =((PaymentTypeAttrFound) Scheduler.execute(query).data()).getPaymentTypeAttrs();

		if (paymentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(paymentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(paymentTypeAttrs);

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
	public ResponseEntity<Object> createPaymentTypeAttr(HttpServletRequest request) throws Exception {

		PaymentTypeAttr paymentTypeAttrToBeAdded = new PaymentTypeAttr();
		try {
			paymentTypeAttrToBeAdded = PaymentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentTypeAttr(paymentTypeAttrToBeAdded);

	}

	/**
	 * creates a new PaymentTypeAttr entry in the ofbiz database
	 * 
	 * @param paymentTypeAttrToBeAdded
	 *            the PaymentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentTypeAttr(@RequestBody PaymentTypeAttr paymentTypeAttrToBeAdded) throws Exception {

		AddPaymentTypeAttr command = new AddPaymentTypeAttr(paymentTypeAttrToBeAdded);
		PaymentTypeAttr paymentTypeAttr = ((PaymentTypeAttrAdded) Scheduler.execute(command).data()).getAddedPaymentTypeAttr();
		
		if (paymentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentTypeAttr could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePaymentTypeAttr(HttpServletRequest request) throws Exception {

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

		PaymentTypeAttr paymentTypeAttrToBeUpdated = new PaymentTypeAttr();

		try {
			paymentTypeAttrToBeUpdated = PaymentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentTypeAttr(paymentTypeAttrToBeUpdated, paymentTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentTypeAttr with the specific Id
	 * 
	 * @param paymentTypeAttrToBeUpdated
	 *            the PaymentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentTypeAttr(@RequestBody PaymentTypeAttr paymentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		paymentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdatePaymentTypeAttr command = new UpdatePaymentTypeAttr(paymentTypeAttrToBeUpdated);

		try {
			if(((PaymentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentTypeAttrId", paymentTypeAttrId);
		try {

			Object foundPaymentTypeAttr = findPaymentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentTypeAttrId}")
	public ResponseEntity<Object> deletePaymentTypeAttrByIdUpdated(@PathVariable String paymentTypeAttrId) throws Exception {
		DeletePaymentTypeAttr command = new DeletePaymentTypeAttr(paymentTypeAttrId);

		try {
			if (((PaymentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentTypeAttr could not be deleted");

	}

}
