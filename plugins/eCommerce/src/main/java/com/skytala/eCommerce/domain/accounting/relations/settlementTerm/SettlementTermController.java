package com.skytala.eCommerce.domain.accounting.relations.settlementTerm;

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
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.command.AddSettlementTerm;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.command.DeleteSettlementTerm;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.command.UpdateSettlementTerm;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event.SettlementTermAdded;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event.SettlementTermDeleted;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event.SettlementTermFound;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event.SettlementTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.mapper.SettlementTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.query.FindSettlementTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/settlementTerms")
public class SettlementTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SettlementTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SettlementTerm
	 * @return a List with the SettlementTerms
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSettlementTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSettlementTermsBy query = new FindSettlementTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SettlementTerm> settlementTerms =((SettlementTermFound) Scheduler.execute(query).data()).getSettlementTerms();

		if (settlementTerms.size() == 1) {
			return ResponseEntity.ok().body(settlementTerms.get(0));
		}

		return ResponseEntity.ok().body(settlementTerms);

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
	public ResponseEntity<Object> createSettlementTerm(HttpServletRequest request) throws Exception {

		SettlementTerm settlementTermToBeAdded = new SettlementTerm();
		try {
			settlementTermToBeAdded = SettlementTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSettlementTerm(settlementTermToBeAdded);

	}

	/**
	 * creates a new SettlementTerm entry in the ofbiz database
	 * 
	 * @param settlementTermToBeAdded
	 *            the SettlementTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSettlementTerm(@RequestBody SettlementTerm settlementTermToBeAdded) throws Exception {

		AddSettlementTerm command = new AddSettlementTerm(settlementTermToBeAdded);
		SettlementTerm settlementTerm = ((SettlementTermAdded) Scheduler.execute(command).data()).getAddedSettlementTerm();
		
		if (settlementTerm != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(settlementTerm);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SettlementTerm could not be created.");
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
	public boolean updateSettlementTerm(HttpServletRequest request) throws Exception {

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

		SettlementTerm settlementTermToBeUpdated = new SettlementTerm();

		try {
			settlementTermToBeUpdated = SettlementTermMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSettlementTerm(settlementTermToBeUpdated, settlementTermToBeUpdated.getSettlementTermId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SettlementTerm with the specific Id
	 * 
	 * @param settlementTermToBeUpdated
	 *            the SettlementTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{settlementTermId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSettlementTerm(@RequestBody SettlementTerm settlementTermToBeUpdated,
			@PathVariable String settlementTermId) throws Exception {

		settlementTermToBeUpdated.setSettlementTermId(settlementTermId);

		UpdateSettlementTerm command = new UpdateSettlementTerm(settlementTermToBeUpdated);

		try {
			if(((SettlementTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{settlementTermId}")
	public ResponseEntity<Object> findById(@PathVariable String settlementTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("settlementTermId", settlementTermId);
		try {

			Object foundSettlementTerm = findSettlementTermsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSettlementTerm);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{settlementTermId}")
	public ResponseEntity<Object> deleteSettlementTermByIdUpdated(@PathVariable String settlementTermId) throws Exception {
		DeleteSettlementTerm command = new DeleteSettlementTerm(settlementTermId);

		try {
			if (((SettlementTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SettlementTerm could not be deleted");

	}

}
