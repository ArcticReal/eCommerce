package com.skytala.eCommerce.domain.accounting.relations.billingAccount.control.term;

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
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.term.AddBillingAccountTerm;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.term.DeleteBillingAccountTerm;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.term.UpdateBillingAccountTerm;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term.BillingAccountTermAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term.BillingAccountTermDeleted;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term.BillingAccountTermFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term.BillingAccountTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.term.BillingAccountTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.term.BillingAccountTerm;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.term.FindBillingAccountTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/billingAccount/billingAccountTerms")
public class BillingAccountTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BillingAccountTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BillingAccountTerm
	 * @return a List with the BillingAccountTerms
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BillingAccountTerm>> findBillingAccountTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountTermsBy query = new FindBillingAccountTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccountTerm> billingAccountTerms =((BillingAccountTermFound) Scheduler.execute(query).data()).getBillingAccountTerms();

		return ResponseEntity.ok().body(billingAccountTerms);

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
	public ResponseEntity<BillingAccountTerm> createBillingAccountTerm(HttpServletRequest request) throws Exception {

		BillingAccountTerm billingAccountTermToBeAdded = new BillingAccountTerm();
		try {
			billingAccountTermToBeAdded = BillingAccountTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBillingAccountTerm(billingAccountTermToBeAdded);

	}

	/**
	 * creates a new BillingAccountTerm entry in the ofbiz database
	 * 
	 * @param billingAccountTermToBeAdded
	 *            the BillingAccountTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BillingAccountTerm> createBillingAccountTerm(@RequestBody BillingAccountTerm billingAccountTermToBeAdded) throws Exception {

		AddBillingAccountTerm command = new AddBillingAccountTerm(billingAccountTermToBeAdded);
		BillingAccountTerm billingAccountTerm = ((BillingAccountTermAdded) Scheduler.execute(command).data()).getAddedBillingAccountTerm();
		
		if (billingAccountTerm != null) 
			return successful(billingAccountTerm);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BillingAccountTerm with the specific Id
	 * 
	 * @param billingAccountTermToBeUpdated
	 *            the BillingAccountTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{billingAccountTermId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBillingAccountTerm(@RequestBody BillingAccountTerm billingAccountTermToBeUpdated,
			@PathVariable String billingAccountTermId) throws Exception {

		billingAccountTermToBeUpdated.setBillingAccountTermId(billingAccountTermId);

		UpdateBillingAccountTerm command = new UpdateBillingAccountTerm(billingAccountTermToBeUpdated);

		try {
			if(((BillingAccountTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{billingAccountTermId}")
	public ResponseEntity<BillingAccountTerm> findById(@PathVariable String billingAccountTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountTermId", billingAccountTermId);
		try {

			List<BillingAccountTerm> foundBillingAccountTerm = findBillingAccountTermsBy(requestParams).getBody();
			if(foundBillingAccountTerm.size()==1){				return successful(foundBillingAccountTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{billingAccountTermId}")
	public ResponseEntity<String> deleteBillingAccountTermByIdUpdated(@PathVariable String billingAccountTermId) throws Exception {
		DeleteBillingAccountTerm command = new DeleteBillingAccountTerm(billingAccountTermId);

		try {
			if (((BillingAccountTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
