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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGroupTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGroupType>> findPaymentGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGroupTypesBy query = new FindPaymentGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGroupType> paymentGroupTypes =((PaymentGroupTypeFound) Scheduler.execute(query).data()).getPaymentGroupTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PaymentGroupType> createPaymentGroupType(HttpServletRequest request) throws Exception {

		PaymentGroupType paymentGroupTypeToBeAdded = new PaymentGroupType();
		try {
			paymentGroupTypeToBeAdded = PaymentGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PaymentGroupType> createPaymentGroupType(@RequestBody PaymentGroupType paymentGroupTypeToBeAdded) throws Exception {

		AddPaymentGroupType command = new AddPaymentGroupType(paymentGroupTypeToBeAdded);
		PaymentGroupType paymentGroupType = ((PaymentGroupTypeAdded) Scheduler.execute(command).data()).getAddedPaymentGroupType();
		
		if (paymentGroupType != null) 
			return successful(paymentGroupType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentGroupType(@RequestBody PaymentGroupType paymentGroupTypeToBeUpdated,
			@PathVariable String paymentGroupTypeId) throws Exception {

		paymentGroupTypeToBeUpdated.setPaymentGroupTypeId(paymentGroupTypeId);

		UpdatePaymentGroupType command = new UpdatePaymentGroupType(paymentGroupTypeToBeUpdated);

		try {
			if(((PaymentGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGroupTypeId}")
	public ResponseEntity<PaymentGroupType> findById(@PathVariable String paymentGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGroupTypeId", paymentGroupTypeId);
		try {

			List<PaymentGroupType> foundPaymentGroupType = findPaymentGroupTypesBy(requestParams).getBody();
			if(foundPaymentGroupType.size()==1){				return successful(foundPaymentGroupType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGroupTypeId}")
	public ResponseEntity<String> deletePaymentGroupTypeByIdUpdated(@PathVariable String paymentGroupTypeId) throws Exception {
		DeletePaymentGroupType command = new DeletePaymentGroupType(paymentGroupTypeId);

		try {
			if (((PaymentGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
