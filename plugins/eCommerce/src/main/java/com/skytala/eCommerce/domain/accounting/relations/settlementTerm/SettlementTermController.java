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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/settlementTerms")
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
	@GetMapping("/find")
	public ResponseEntity<List<SettlementTerm>> findSettlementTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSettlementTermsBy query = new FindSettlementTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SettlementTerm> settlementTerms =((SettlementTermFound) Scheduler.execute(query).data()).getSettlementTerms();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SettlementTerm> createSettlementTerm(HttpServletRequest request) throws Exception {

		SettlementTerm settlementTermToBeAdded = new SettlementTerm();
		try {
			settlementTermToBeAdded = SettlementTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SettlementTerm> createSettlementTerm(@RequestBody SettlementTerm settlementTermToBeAdded) throws Exception {

		AddSettlementTerm command = new AddSettlementTerm(settlementTermToBeAdded);
		SettlementTerm settlementTerm = ((SettlementTermAdded) Scheduler.execute(command).data()).getAddedSettlementTerm();
		
		if (settlementTerm != null) 
			return successful(settlementTerm);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSettlementTerm(@RequestBody SettlementTerm settlementTermToBeUpdated,
			@PathVariable String settlementTermId) throws Exception {

		settlementTermToBeUpdated.setSettlementTermId(settlementTermId);

		UpdateSettlementTerm command = new UpdateSettlementTerm(settlementTermToBeUpdated);

		try {
			if(((SettlementTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{settlementTermId}")
	public ResponseEntity<SettlementTerm> findById(@PathVariable String settlementTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("settlementTermId", settlementTermId);
		try {

			List<SettlementTerm> foundSettlementTerm = findSettlementTermsBy(requestParams).getBody();
			if(foundSettlementTerm.size()==1){				return successful(foundSettlementTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{settlementTermId}")
	public ResponseEntity<String> deleteSettlementTermByIdUpdated(@PathVariable String settlementTermId) throws Exception {
		DeleteSettlementTerm command = new DeleteSettlementTerm(settlementTermId);

		try {
			if (((SettlementTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
