package com.skytala.eCommerce.domain.accounting.relations.payment.control.groupType;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupType.AddPaymentGroupType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupType.DeletePaymentGroupType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.groupType.UpdatePaymentGroupType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType.PaymentGroupTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType.PaymentGroupTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType.PaymentGroupTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType.PaymentGroupTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupType.PaymentGroupTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupType.PaymentGroupType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.groupType.FindPaymentGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/paymentGroupTypes")
public class PaymentGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGroupType
	 * @return a List with the PaymentGroupTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGroupTypesBy query = new FindPaymentGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGroupType> paymentGroupTypes =((PaymentGroupTypeFound) Scheduler.execute(query).data()).getPaymentGroupTypes();

		if (paymentGroupTypes.size() == 1) {
			return ResponseEntity.ok().body(paymentGroupTypes.get(0));
		}

		return ResponseEntity.ok().body(paymentGroupTypes);

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
	public ResponseEntity<Object> createPaymentGroupType(HttpServletRequest request) throws Exception {

		PaymentGroupType paymentGroupTypeToBeAdded = new PaymentGroupType();
		try {
			paymentGroupTypeToBeAdded = PaymentGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGroupType(paymentGroupTypeToBeAdded);

	}

	/**
	 * creates a new PaymentGroupType entry in the ofbiz database
	 * 
	 * @param paymentGroupTypeToBeAdded
	 *            the PaymentGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGroupType(@RequestBody PaymentGroupType paymentGroupTypeToBeAdded) throws Exception {

		AddPaymentGroupType command = new AddPaymentGroupType(paymentGroupTypeToBeAdded);
		PaymentGroupType paymentGroupType = ((PaymentGroupTypeAdded) Scheduler.execute(command).data()).getAddedPaymentGroupType();
		
		if (paymentGroupType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGroupType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGroupType could not be created.");
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
	public boolean updatePaymentGroupType(HttpServletRequest request) throws Exception {

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

		PaymentGroupType paymentGroupTypeToBeUpdated = new PaymentGroupType();

		try {
			paymentGroupTypeToBeUpdated = PaymentGroupTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGroupType(paymentGroupTypeToBeUpdated, paymentGroupTypeToBeUpdated.getPaymentGroupTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentGroupType with the specific Id
	 * 
	 * @param paymentGroupTypeToBeUpdated
	 *            the PaymentGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentGroupType(@RequestBody PaymentGroupType paymentGroupTypeToBeUpdated,
			@PathVariable String paymentGroupTypeId) throws Exception {

		paymentGroupTypeToBeUpdated.setPaymentGroupTypeId(paymentGroupTypeId);

		UpdatePaymentGroupType command = new UpdatePaymentGroupType(paymentGroupTypeToBeUpdated);

		try {
			if(((PaymentGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGroupTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGroupTypeId", paymentGroupTypeId);
		try {

			Object foundPaymentGroupType = findPaymentGroupTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGroupType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGroupTypeId}")
	public ResponseEntity<Object> deletePaymentGroupTypeByIdUpdated(@PathVariable String paymentGroupTypeId) throws Exception {
		DeletePaymentGroupType command = new DeletePaymentGroupType(paymentGroupTypeId);

		try {
			if (((PaymentGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGroupType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGroupType/\" plus one of the following: "
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
