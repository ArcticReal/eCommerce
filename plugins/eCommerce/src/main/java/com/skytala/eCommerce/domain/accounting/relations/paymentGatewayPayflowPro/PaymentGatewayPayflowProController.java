package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.command.AddPaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.command.DeletePaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.command.UpdatePaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProDeleted;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.mapper.PaymentGatewayPayflowProMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model.PaymentGatewayPayflowPro;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.query.FindPaymentGatewayPayflowProsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/paymentGatewayPayflowPros")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayPayflowProsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayPayflowProsBy query = new FindPaymentGatewayPayflowProsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayPayflowPro> paymentGatewayPayflowPros =((PaymentGatewayPayflowProFound) Scheduler.execute(query).data()).getPaymentGatewayPayflowPros();

		if (paymentGatewayPayflowPros.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayPayflowPros.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayPayflowPros);

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
	public ResponseEntity<Object> createPaymentGatewayPayflowPro(HttpServletRequest request) throws Exception {

		PaymentGatewayPayflowPro paymentGatewayPayflowProToBeAdded = new PaymentGatewayPayflowPro();
		try {
			paymentGatewayPayflowProToBeAdded = PaymentGatewayPayflowProMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayPayflowPro(paymentGatewayPayflowProToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayPayflowPro entry in the ofbiz database
	 * 
	 * @param paymentGatewayPayflowProToBeAdded
	 *            the PaymentGatewayPayflowPro thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayPayflowPro(@RequestBody PaymentGatewayPayflowPro paymentGatewayPayflowProToBeAdded) throws Exception {

		AddPaymentGatewayPayflowPro command = new AddPaymentGatewayPayflowPro(paymentGatewayPayflowProToBeAdded);
		PaymentGatewayPayflowPro paymentGatewayPayflowPro = ((PaymentGatewayPayflowProAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayPayflowPro();
		
		if (paymentGatewayPayflowPro != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayPayflowPro);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayPayflowPro could not be created.");
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
	public boolean updatePaymentGatewayPayflowPro(HttpServletRequest request) throws Exception {

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

		PaymentGatewayPayflowPro paymentGatewayPayflowProToBeUpdated = new PaymentGatewayPayflowPro();

		try {
			paymentGatewayPayflowProToBeUpdated = PaymentGatewayPayflowProMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayPayflowPro(paymentGatewayPayflowProToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayPayflowPro(@RequestBody PaymentGatewayPayflowPro paymentGatewayPayflowProToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayPayflowProToBeUpdated.setnull(null);

		UpdatePaymentGatewayPayflowPro command = new UpdatePaymentGatewayPayflowPro(paymentGatewayPayflowProToBeUpdated);

		try {
			if(((PaymentGatewayPayflowProUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayPayflowProId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayPayflowProId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayPayflowProId", paymentGatewayPayflowProId);
		try {

			Object foundPaymentGatewayPayflowPro = findPaymentGatewayPayflowProsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayPayflowPro);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayPayflowProId}")
	public ResponseEntity<Object> deletePaymentGatewayPayflowProByIdUpdated(@PathVariable String paymentGatewayPayflowProId) throws Exception {
		DeletePaymentGatewayPayflowPro command = new DeletePaymentGatewayPayflowPro(paymentGatewayPayflowProId);

		try {
			if (((PaymentGatewayPayflowProDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayPayflowPro could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayPayflowPro/\" plus one of the following: "
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