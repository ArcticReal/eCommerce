package com.skytala.eCommerce.domain.accounting.relations.billingAccount.control.termAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.termAttr.AddBillingAccountTermAttr;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.termAttr.DeleteBillingAccountTermAttr;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.termAttr.UpdateBillingAccountTermAttr;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr.BillingAccountTermAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr.BillingAccountTermAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr.BillingAccountTermAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr.BillingAccountTermAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.termAttr.BillingAccountTermAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.termAttr.BillingAccountTermAttr;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.termAttr.FindBillingAccountTermAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/billingAccount/billingAccountTermAttrs")
public class BillingAccountTermAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BillingAccountTermAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BillingAccountTermAttr
	 * @return a List with the BillingAccountTermAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BillingAccountTermAttr>> findBillingAccountTermAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountTermAttrsBy query = new FindBillingAccountTermAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccountTermAttr> billingAccountTermAttrs =((BillingAccountTermAttrFound) Scheduler.execute(query).data()).getBillingAccountTermAttrs();

		return ResponseEntity.ok().body(billingAccountTermAttrs);

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
	public ResponseEntity<BillingAccountTermAttr> createBillingAccountTermAttr(HttpServletRequest request) throws Exception {

		BillingAccountTermAttr billingAccountTermAttrToBeAdded = new BillingAccountTermAttr();
		try {
			billingAccountTermAttrToBeAdded = BillingAccountTermAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBillingAccountTermAttr(billingAccountTermAttrToBeAdded);

	}

	/**
	 * creates a new BillingAccountTermAttr entry in the ofbiz database
	 * 
	 * @param billingAccountTermAttrToBeAdded
	 *            the BillingAccountTermAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BillingAccountTermAttr> createBillingAccountTermAttr(@RequestBody BillingAccountTermAttr billingAccountTermAttrToBeAdded) throws Exception {

		AddBillingAccountTermAttr command = new AddBillingAccountTermAttr(billingAccountTermAttrToBeAdded);
		BillingAccountTermAttr billingAccountTermAttr = ((BillingAccountTermAttrAdded) Scheduler.execute(command).data()).getAddedBillingAccountTermAttr();
		
		if (billingAccountTermAttr != null) 
			return successful(billingAccountTermAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BillingAccountTermAttr with the specific Id
	 * 
	 * @param billingAccountTermAttrToBeUpdated
	 *            the BillingAccountTermAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBillingAccountTermAttr(@RequestBody BillingAccountTermAttr billingAccountTermAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		billingAccountTermAttrToBeUpdated.setAttrName(attrName);

		UpdateBillingAccountTermAttr command = new UpdateBillingAccountTermAttr(billingAccountTermAttrToBeUpdated);

		try {
			if(((BillingAccountTermAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{billingAccountTermAttrId}")
	public ResponseEntity<BillingAccountTermAttr> findById(@PathVariable String billingAccountTermAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountTermAttrId", billingAccountTermAttrId);
		try {

			List<BillingAccountTermAttr> foundBillingAccountTermAttr = findBillingAccountTermAttrsBy(requestParams).getBody();
			if(foundBillingAccountTermAttr.size()==1){				return successful(foundBillingAccountTermAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{billingAccountTermAttrId}")
	public ResponseEntity<String> deleteBillingAccountTermAttrByIdUpdated(@PathVariable String billingAccountTermAttrId) throws Exception {
		DeleteBillingAccountTermAttr command = new DeleteBillingAccountTermAttr(billingAccountTermAttrId);

		try {
			if (((BillingAccountTermAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
