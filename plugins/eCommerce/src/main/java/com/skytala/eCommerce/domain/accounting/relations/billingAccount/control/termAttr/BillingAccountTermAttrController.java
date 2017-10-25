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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/billingAccountTermAttrs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBillingAccountTermAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountTermAttrsBy query = new FindBillingAccountTermAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccountTermAttr> billingAccountTermAttrs =((BillingAccountTermAttrFound) Scheduler.execute(query).data()).getBillingAccountTermAttrs();

		if (billingAccountTermAttrs.size() == 1) {
			return ResponseEntity.ok().body(billingAccountTermAttrs.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createBillingAccountTermAttr(HttpServletRequest request) throws Exception {

		BillingAccountTermAttr billingAccountTermAttrToBeAdded = new BillingAccountTermAttr();
		try {
			billingAccountTermAttrToBeAdded = BillingAccountTermAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBillingAccountTermAttr(@RequestBody BillingAccountTermAttr billingAccountTermAttrToBeAdded) throws Exception {

		AddBillingAccountTermAttr command = new AddBillingAccountTermAttr(billingAccountTermAttrToBeAdded);
		BillingAccountTermAttr billingAccountTermAttr = ((BillingAccountTermAttrAdded) Scheduler.execute(command).data()).getAddedBillingAccountTermAttr();
		
		if (billingAccountTermAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(billingAccountTermAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BillingAccountTermAttr could not be created.");
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
	public boolean updateBillingAccountTermAttr(HttpServletRequest request) throws Exception {

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

		BillingAccountTermAttr billingAccountTermAttrToBeUpdated = new BillingAccountTermAttr();

		try {
			billingAccountTermAttrToBeUpdated = BillingAccountTermAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBillingAccountTermAttr(billingAccountTermAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BillingAccountTermAttr with the specific Id
	 * 
	 * @param billingAccountTermAttrToBeUpdated
	 *            the BillingAccountTermAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBillingAccountTermAttr(@RequestBody BillingAccountTermAttr billingAccountTermAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		billingAccountTermAttrToBeUpdated.setnull(null);

		UpdateBillingAccountTermAttr command = new UpdateBillingAccountTermAttr(billingAccountTermAttrToBeUpdated);

		try {
			if(((BillingAccountTermAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{billingAccountTermAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String billingAccountTermAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountTermAttrId", billingAccountTermAttrId);
		try {

			Object foundBillingAccountTermAttr = findBillingAccountTermAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBillingAccountTermAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{billingAccountTermAttrId}")
	public ResponseEntity<Object> deleteBillingAccountTermAttrByIdUpdated(@PathVariable String billingAccountTermAttrId) throws Exception {
		DeleteBillingAccountTermAttr command = new DeleteBillingAccountTermAttr(billingAccountTermAttrId);

		try {
			if (((BillingAccountTermAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BillingAccountTermAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/billingAccountTermAttr/\" plus one of the following: "
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
