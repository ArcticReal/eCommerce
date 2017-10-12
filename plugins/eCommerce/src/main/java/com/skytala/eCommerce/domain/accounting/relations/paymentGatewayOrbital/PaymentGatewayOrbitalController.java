package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital;

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
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.command.AddPaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.command.DeletePaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.command.UpdatePaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalDeleted;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.mapper.PaymentGatewayOrbitalMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.query.FindPaymentGatewayOrbitalsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/paymentGatewayOrbitals")
public class PaymentGatewayOrbitalController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayOrbitalController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayOrbital
	 * @return a List with the PaymentGatewayOrbitals
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayOrbitalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayOrbitalsBy query = new FindPaymentGatewayOrbitalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayOrbital> paymentGatewayOrbitals =((PaymentGatewayOrbitalFound) Scheduler.execute(query).data()).getPaymentGatewayOrbitals();

		if (paymentGatewayOrbitals.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayOrbitals.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayOrbitals);

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
	public ResponseEntity<Object> createPaymentGatewayOrbital(HttpServletRequest request) throws Exception {

		PaymentGatewayOrbital paymentGatewayOrbitalToBeAdded = new PaymentGatewayOrbital();
		try {
			paymentGatewayOrbitalToBeAdded = PaymentGatewayOrbitalMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayOrbital(paymentGatewayOrbitalToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayOrbital entry in the ofbiz database
	 * 
	 * @param paymentGatewayOrbitalToBeAdded
	 *            the PaymentGatewayOrbital thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayOrbital(@RequestBody PaymentGatewayOrbital paymentGatewayOrbitalToBeAdded) throws Exception {

		AddPaymentGatewayOrbital command = new AddPaymentGatewayOrbital(paymentGatewayOrbitalToBeAdded);
		PaymentGatewayOrbital paymentGatewayOrbital = ((PaymentGatewayOrbitalAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayOrbital();
		
		if (paymentGatewayOrbital != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayOrbital);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayOrbital could not be created.");
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
	public boolean updatePaymentGatewayOrbital(HttpServletRequest request) throws Exception {

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

		PaymentGatewayOrbital paymentGatewayOrbitalToBeUpdated = new PaymentGatewayOrbital();

		try {
			paymentGatewayOrbitalToBeUpdated = PaymentGatewayOrbitalMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayOrbital(paymentGatewayOrbitalToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentGatewayOrbital with the specific Id
	 * 
	 * @param paymentGatewayOrbitalToBeUpdated
	 *            the PaymentGatewayOrbital thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentGatewayOrbital(@RequestBody PaymentGatewayOrbital paymentGatewayOrbitalToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayOrbitalToBeUpdated.setnull(null);

		UpdatePaymentGatewayOrbital command = new UpdatePaymentGatewayOrbital(paymentGatewayOrbitalToBeUpdated);

		try {
			if(((PaymentGatewayOrbitalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayOrbitalId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayOrbitalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayOrbitalId", paymentGatewayOrbitalId);
		try {

			Object foundPaymentGatewayOrbital = findPaymentGatewayOrbitalsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayOrbital);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayOrbitalId}")
	public ResponseEntity<Object> deletePaymentGatewayOrbitalByIdUpdated(@PathVariable String paymentGatewayOrbitalId) throws Exception {
		DeletePaymentGatewayOrbital command = new DeletePaymentGatewayOrbital(paymentGatewayOrbitalId);

		try {
			if (((PaymentGatewayOrbitalDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayOrbital could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayOrbital/\" plus one of the following: "
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
