package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType;

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
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.command.AddPaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.command.DeletePaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.command.UpdatePaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event.PaymentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event.PaymentGatewayConfigTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event.PaymentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event.PaymentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.mapper.PaymentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.model.PaymentGatewayConfigType;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.query.FindPaymentGatewayConfigTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/paymentGatewayConfigTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayConfigTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayConfigTypesBy query = new FindPaymentGatewayConfigTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayConfigType> paymentGatewayConfigTypes =((PaymentGatewayConfigTypeFound) Scheduler.execute(query).data()).getPaymentGatewayConfigTypes();

		if (paymentGatewayConfigTypes.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayConfigTypes.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayConfigTypes);

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
	public ResponseEntity<Object> createPaymentGatewayConfigType(HttpServletRequest request) throws Exception {

		PaymentGatewayConfigType paymentGatewayConfigTypeToBeAdded = new PaymentGatewayConfigType();
		try {
			paymentGatewayConfigTypeToBeAdded = PaymentGatewayConfigTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayConfigType(paymentGatewayConfigTypeToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayConfigType entry in the ofbiz database
	 * 
	 * @param paymentGatewayConfigTypeToBeAdded
	 *            the PaymentGatewayConfigType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayConfigType(@RequestBody PaymentGatewayConfigType paymentGatewayConfigTypeToBeAdded) throws Exception {

		AddPaymentGatewayConfigType command = new AddPaymentGatewayConfigType(paymentGatewayConfigTypeToBeAdded);
		PaymentGatewayConfigType paymentGatewayConfigType = ((PaymentGatewayConfigTypeAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayConfigType();
		
		if (paymentGatewayConfigType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayConfigType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayConfigType could not be created.");
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
	public boolean updatePaymentGatewayConfigType(HttpServletRequest request) throws Exception {

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

		PaymentGatewayConfigType paymentGatewayConfigTypeToBeUpdated = new PaymentGatewayConfigType();

		try {
			paymentGatewayConfigTypeToBeUpdated = PaymentGatewayConfigTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayConfigType(paymentGatewayConfigTypeToBeUpdated, paymentGatewayConfigTypeToBeUpdated.getPaymentGatewayConfigTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayConfigType(@RequestBody PaymentGatewayConfigType paymentGatewayConfigTypeToBeUpdated,
			@PathVariable String paymentGatewayConfigTypeId) throws Exception {

		paymentGatewayConfigTypeToBeUpdated.setPaymentGatewayConfigTypeId(paymentGatewayConfigTypeId);

		UpdatePaymentGatewayConfigType command = new UpdatePaymentGatewayConfigType(paymentGatewayConfigTypeToBeUpdated);

		try {
			if(((PaymentGatewayConfigTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayConfigTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayConfigTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayConfigTypeId", paymentGatewayConfigTypeId);
		try {

			Object foundPaymentGatewayConfigType = findPaymentGatewayConfigTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayConfigType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayConfigTypeId}")
	public ResponseEntity<Object> deletePaymentGatewayConfigTypeByIdUpdated(@PathVariable String paymentGatewayConfigTypeId) throws Exception {
		DeletePaymentGatewayConfigType command = new DeletePaymentGatewayConfigType(paymentGatewayConfigTypeId);

		try {
			if (((PaymentGatewayConfigTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayConfigType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayConfigType/\" plus one of the following: "
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
