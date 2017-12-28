package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayCyberSource;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayCyberSource.AddPaymentGatewayCyberSource;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayCyberSource.DeletePaymentGatewayCyberSource;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayCyberSource.UpdatePaymentGatewayCyberSource;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayCyberSource.PaymentGatewayCyberSourceMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayCyberSource.FindPaymentGatewayCyberSourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayCyberSources")
public class PaymentGatewayCyberSourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayCyberSourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayCyberSource
	 * @return a List with the PaymentGatewayCyberSources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayCyberSource>> findPaymentGatewayCyberSourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayCyberSourcesBy query = new FindPaymentGatewayCyberSourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayCyberSource> paymentGatewayCyberSources =((PaymentGatewayCyberSourceFound) Scheduler.execute(query).data()).getPaymentGatewayCyberSources();

		return ResponseEntity.ok().body(paymentGatewayCyberSources);

	}

	/**
	 * creates a new PaymentGatewayCyberSource entry in the ofbiz database
	 * 
	 * @param paymentGatewayCyberSourceToBeAdded
	 *            the PaymentGatewayCyberSource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayCyberSource> createPaymentGatewayCyberSource(@RequestBody PaymentGatewayCyberSource paymentGatewayCyberSourceToBeAdded) throws Exception {

		AddPaymentGatewayCyberSource command = new AddPaymentGatewayCyberSource(paymentGatewayCyberSourceToBeAdded);
		PaymentGatewayCyberSource paymentGatewayCyberSource = ((PaymentGatewayCyberSourceAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayCyberSource();
		
		if (paymentGatewayCyberSource != null) 
			return successful(paymentGatewayCyberSource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayCyberSource with the specific Id
	 * 
	 * @param paymentGatewayCyberSourceToBeUpdated
	 *            the PaymentGatewayCyberSource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayCyberSource(@RequestBody PaymentGatewayCyberSource paymentGatewayCyberSourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayCyberSourceToBeUpdated.setnull(null);

		UpdatePaymentGatewayCyberSource command = new UpdatePaymentGatewayCyberSource(paymentGatewayCyberSourceToBeUpdated);

		try {
			if(((PaymentGatewayCyberSourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayCyberSourceId}")
	public ResponseEntity<PaymentGatewayCyberSource> findById(@PathVariable String paymentGatewayCyberSourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayCyberSourceId", paymentGatewayCyberSourceId);
		try {

			List<PaymentGatewayCyberSource> foundPaymentGatewayCyberSource = findPaymentGatewayCyberSourcesBy(requestParams).getBody();
			if(foundPaymentGatewayCyberSource.size()==1){				return successful(foundPaymentGatewayCyberSource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayCyberSourceId}")
	public ResponseEntity<String> deletePaymentGatewayCyberSourceByIdUpdated(@PathVariable String paymentGatewayCyberSourceId) throws Exception {
		DeletePaymentGatewayCyberSource command = new DeletePaymentGatewayCyberSource(paymentGatewayCyberSourceId);

		try {
			if (((PaymentGatewayCyberSourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
