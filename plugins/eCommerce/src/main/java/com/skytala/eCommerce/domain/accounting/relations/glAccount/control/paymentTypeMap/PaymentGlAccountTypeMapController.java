package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.paymentTypeMap;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentTypeMap.AddPaymentGlAccountTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentTypeMap.DeletePaymentGlAccountTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentTypeMap.UpdatePaymentGlAccountTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentTypeMap.PaymentGlAccountTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentTypeMap.FindPaymentGlAccountTypeMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/glAccount/paymentGlAccountTypeMaps")
public class PaymentGlAccountTypeMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGlAccountTypeMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGlAccountTypeMap
	 * @return a List with the PaymentGlAccountTypeMaps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPaymentGlAccountTypeMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGlAccountTypeMapsBy query = new FindPaymentGlAccountTypeMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGlAccountTypeMap> paymentGlAccountTypeMaps =((PaymentGlAccountTypeMapFound) Scheduler.execute(query).data()).getPaymentGlAccountTypeMaps();

		if (paymentGlAccountTypeMaps.size() == 1) {
			return ResponseEntity.ok().body(paymentGlAccountTypeMaps.get(0));
		}

		return ResponseEntity.ok().body(paymentGlAccountTypeMaps);

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
	public ResponseEntity<Object> createPaymentGlAccountTypeMap(HttpServletRequest request) throws Exception {

		PaymentGlAccountTypeMap paymentGlAccountTypeMapToBeAdded = new PaymentGlAccountTypeMap();
		try {
			paymentGlAccountTypeMapToBeAdded = PaymentGlAccountTypeMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGlAccountTypeMap(paymentGlAccountTypeMapToBeAdded);

	}

	/**
	 * creates a new PaymentGlAccountTypeMap entry in the ofbiz database
	 * 
	 * @param paymentGlAccountTypeMapToBeAdded
	 *            the PaymentGlAccountTypeMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGlAccountTypeMap(@RequestBody PaymentGlAccountTypeMap paymentGlAccountTypeMapToBeAdded) throws Exception {

		AddPaymentGlAccountTypeMap command = new AddPaymentGlAccountTypeMap(paymentGlAccountTypeMapToBeAdded);
		PaymentGlAccountTypeMap paymentGlAccountTypeMap = ((PaymentGlAccountTypeMapAdded) Scheduler.execute(command).data()).getAddedPaymentGlAccountTypeMap();
		
		if (paymentGlAccountTypeMap != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGlAccountTypeMap);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGlAccountTypeMap could not be created.");
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
	public boolean updatePaymentGlAccountTypeMap(HttpServletRequest request) throws Exception {

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

		PaymentGlAccountTypeMap paymentGlAccountTypeMapToBeUpdated = new PaymentGlAccountTypeMap();

		try {
			paymentGlAccountTypeMapToBeUpdated = PaymentGlAccountTypeMapMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGlAccountTypeMap(paymentGlAccountTypeMapToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentGlAccountTypeMap with the specific Id
	 * 
	 * @param paymentGlAccountTypeMapToBeUpdated
	 *            the PaymentGlAccountTypeMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentGlAccountTypeMap(@RequestBody PaymentGlAccountTypeMap paymentGlAccountTypeMapToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGlAccountTypeMapToBeUpdated.setnull(null);

		UpdatePaymentGlAccountTypeMap command = new UpdatePaymentGlAccountTypeMap(paymentGlAccountTypeMapToBeUpdated);

		try {
			if(((PaymentGlAccountTypeMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentGlAccountTypeMapId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGlAccountTypeMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGlAccountTypeMapId", paymentGlAccountTypeMapId);
		try {

			Object foundPaymentGlAccountTypeMap = findPaymentGlAccountTypeMapsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGlAccountTypeMap);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentGlAccountTypeMapId}")
	public ResponseEntity<Object> deletePaymentGlAccountTypeMapByIdUpdated(@PathVariable String paymentGlAccountTypeMapId) throws Exception {
		DeletePaymentGlAccountTypeMap command = new DeletePaymentGlAccountTypeMap(paymentGlAccountTypeMapId);

		try {
			if (((PaymentGlAccountTypeMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGlAccountTypeMap could not be deleted");

	}

}
