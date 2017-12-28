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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<RateAmount>> findRateAmountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRateAmountsBy query = new FindRateAmountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RateAmount> rateAmounts =((RateAmountFound) Scheduler.execute(query).data()).getRateAmounts();

		return ResponseEntity.ok().body(rateAmounts);

	}

	/**
	 * creates a new RateAmount entry in the ofbiz database
	 * 
	 * @param rateAmountToBeAdded
	 *            the RateAmount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RateAmount> createRateAmount(@RequestBody RateAmount rateAmountToBeAdded) throws Exception {

		AddRateAmount command = new AddRateAmount(rateAmountToBeAdded);
		RateAmount rateAmount = ((RateAmountAdded) Scheduler.execute(command).data()).getAddedRateAmount();
		
		if (rateAmount != null) 
			return successful(rateAmount);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateRateAmount(@RequestBody RateAmount rateAmountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		rateAmountToBeUpdated.setnull(null);

		UpdateRateAmount command = new UpdateRateAmount(rateAmountToBeUpdated);

		try {
			if(((RateAmountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{rateAmountId}")
	public ResponseEntity<RateAmount> findById(@PathVariable String rateAmountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rateAmountId", rateAmountId);
		try {

			List<RateAmount> foundRateAmount = findRateAmountsBy(requestParams).getBody();
			if(foundRateAmount.size()==1){				return successful(foundRateAmount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{rateAmountId}")
	public ResponseEntity<String> deleteRateAmountByIdUpdated(@PathVariable String rateAmountId) throws Exception {
		DeleteRateAmount command = new DeleteRateAmount(rateAmountId);

		try {
			if (((RateAmountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
