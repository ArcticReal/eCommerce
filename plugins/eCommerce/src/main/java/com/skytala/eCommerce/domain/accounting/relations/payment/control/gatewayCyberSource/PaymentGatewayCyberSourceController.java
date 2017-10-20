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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/paymentGatewayCyberSources")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayCyberSourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayCyberSourcesBy query = new FindPaymentGatewayCyberSourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayCyberSource> paymentGatewayCyberSources =((PaymentGatewayCyberSourceFound) Scheduler.execute(query).data()).getPaymentGatewayCyberSources();

		if (paymentGatewayCyberSources.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayCyberSources.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayCyberSources);

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
	public ResponseEntity<Object> createPaymentGatewayCyberSource(HttpServletRequest request) throws Exception {

		PaymentGatewayCyberSource paymentGatewayCyberSourceToBeAdded = new PaymentGatewayCyberSource();
		try {
			paymentGatewayCyberSourceToBeAdded = PaymentGatewayCyberSourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayCyberSource(paymentGatewayCyberSourceToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayCyberSource entry in the ofbiz database
	 * 
	 * @param paymentGatewayCyberSourceToBeAdded
	 *            the PaymentGatewayCyberSource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayCyberSource(@RequestBody PaymentGatewayCyberSource paymentGatewayCyberSourceToBeAdded) throws Exception {

		AddPaymentGatewayCyberSource command = new AddPaymentGatewayCyberSource(paymentGatewayCyberSourceToBeAdded);
		PaymentGatewayCyberSource paymentGatewayCyberSource = ((PaymentGatewayCyberSourceAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayCyberSource();
		
		if (paymentGatewayCyberSource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayCyberSource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayCyberSource could not be created.");
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
	public boolean updatePaymentGatewayCyberSource(HttpServletRequest request) throws Exception {

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

		PaymentGatewayCyberSource paymentGatewayCyberSourceToBeUpdated = new PaymentGatewayCyberSource();

		try {
			paymentGatewayCyberSourceToBeUpdated = PaymentGatewayCyberSourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayCyberSource(paymentGatewayCyberSourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayCyberSource(@RequestBody PaymentGatewayCyberSource paymentGatewayCyberSourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayCyberSourceToBeUpdated.setnull(null);

		UpdatePaymentGatewayCyberSource command = new UpdatePaymentGatewayCyberSource(paymentGatewayCyberSourceToBeUpdated);

		try {
			if(((PaymentGatewayCyberSourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayCyberSourceId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayCyberSourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayCyberSourceId", paymentGatewayCyberSourceId);
		try {

			Object foundPaymentGatewayCyberSource = findPaymentGatewayCyberSourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayCyberSource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayCyberSourceId}")
	public ResponseEntity<Object> deletePaymentGatewayCyberSourceByIdUpdated(@PathVariable String paymentGatewayCyberSourceId) throws Exception {
		DeletePaymentGatewayCyberSource command = new DeletePaymentGatewayCyberSource(paymentGatewayCyberSourceId);

		try {
			if (((PaymentGatewayCyberSourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayCyberSource could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayCyberSource/\" plus one of the following: "
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
