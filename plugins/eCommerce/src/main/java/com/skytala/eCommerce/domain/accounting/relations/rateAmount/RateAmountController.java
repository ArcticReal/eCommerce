package com.skytala.eCommerce.domain.accounting.relations.rateAmount;

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
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.command.AddRateAmount;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.command.DeleteRateAmount;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.command.UpdateRateAmount;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountAdded;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountFound;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.mapper.RateAmountMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.query.FindRateAmountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/rateAmounts")
public class RateAmountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RateAmountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RateAmount
	 * @return a List with the RateAmounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findRateAmountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRateAmountsBy query = new FindRateAmountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RateAmount> rateAmounts =((RateAmountFound) Scheduler.execute(query).data()).getRateAmounts();

		if (rateAmounts.size() == 1) {
			return ResponseEntity.ok().body(rateAmounts.get(0));
		}

		return ResponseEntity.ok().body(rateAmounts);

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
	public ResponseEntity<Object> createRateAmount(HttpServletRequest request) throws Exception {

		RateAmount rateAmountToBeAdded = new RateAmount();
		try {
			rateAmountToBeAdded = RateAmountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRateAmount(rateAmountToBeAdded);

	}

	/**
	 * creates a new RateAmount entry in the ofbiz database
	 * 
	 * @param rateAmountToBeAdded
	 *            the RateAmount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRateAmount(@RequestBody RateAmount rateAmountToBeAdded) throws Exception {

		AddRateAmount command = new AddRateAmount(rateAmountToBeAdded);
		RateAmount rateAmount = ((RateAmountAdded) Scheduler.execute(command).data()).getAddedRateAmount();
		
		if (rateAmount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(rateAmount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RateAmount could not be created.");
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
	public boolean updateRateAmount(HttpServletRequest request) throws Exception {

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

		RateAmount rateAmountToBeUpdated = new RateAmount();

		try {
			rateAmountToBeUpdated = RateAmountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRateAmount(rateAmountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RateAmount with the specific Id
	 * 
	 * @param rateAmountToBeUpdated
	 *            the RateAmount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRateAmount(@RequestBody RateAmount rateAmountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		rateAmountToBeUpdated.setnull(null);

		UpdateRateAmount command = new UpdateRateAmount(rateAmountToBeUpdated);

		try {
			if(((RateAmountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{rateAmountId}")
	public ResponseEntity<Object> findById(@PathVariable String rateAmountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rateAmountId", rateAmountId);
		try {

			Object foundRateAmount = findRateAmountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRateAmount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{rateAmountId}")
	public ResponseEntity<Object> deleteRateAmountByIdUpdated(@PathVariable String rateAmountId) throws Exception {
		DeleteRateAmount command = new DeleteRateAmount(rateAmountId);

		try {
			if (((RateAmountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RateAmount could not be deleted");

	}

}
