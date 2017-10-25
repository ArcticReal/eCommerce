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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/billingAccountTerms")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBillingAccountTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountTermsBy query = new FindBillingAccountTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccountTerm> billingAccountTerms =((BillingAccountTermFound) Scheduler.execute(query).data()).getBillingAccountTerms();

		if (billingAccountTerms.size() == 1) {
			return ResponseEntity.ok().body(billingAccountTerms.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createBillingAccountTerm(HttpServletRequest request) throws Exception {

		BillingAccountTerm billingAccountTermToBeAdded = new BillingAccountTerm();
		try {
			billingAccountTermToBeAdded = BillingAccountTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBillingAccountTerm(@RequestBody BillingAccountTerm billingAccountTermToBeAdded) throws Exception {

		AddBillingAccountTerm command = new AddBillingAccountTerm(billingAccountTermToBeAdded);
		BillingAccountTerm billingAccountTerm = ((BillingAccountTermAdded) Scheduler.execute(command).data()).getAddedBillingAccountTerm();
		
		if (billingAccountTerm != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(billingAccountTerm);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BillingAccountTerm could not be created.");
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
	public boolean updateBillingAccountTerm(HttpServletRequest request) throws Exception {

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

		BillingAccountTerm billingAccountTermToBeUpdated = new BillingAccountTerm();

		try {
			billingAccountTermToBeUpdated = BillingAccountTermMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBillingAccountTerm(billingAccountTermToBeUpdated, billingAccountTermToBeUpdated.getBillingAccountTermId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBillingAccountTerm(@RequestBody BillingAccountTerm billingAccountTermToBeUpdated,
			@PathVariable String billingAccountTermId) throws Exception {

		billingAccountTermToBeUpdated.setBillingAccountTermId(billingAccountTermId);

		UpdateBillingAccountTerm command = new UpdateBillingAccountTerm(billingAccountTermToBeUpdated);

		try {
			if(((BillingAccountTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{billingAccountTermId}")
	public ResponseEntity<Object> findById(@PathVariable String billingAccountTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountTermId", billingAccountTermId);
		try {

			Object foundBillingAccountTerm = findBillingAccountTermsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBillingAccountTerm);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{billingAccountTermId}")
	public ResponseEntity<Object> deleteBillingAccountTermByIdUpdated(@PathVariable String billingAccountTermId) throws Exception {
		DeleteBillingAccountTerm command = new DeleteBillingAccountTerm(billingAccountTermId);

		try {
			if (((BillingAccountTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BillingAccountTerm could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/billingAccountTerm/\" plus one of the following: "
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
