package com.skytala.eCommerce.domain.order.relations.custRequest.control.party;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.party.AddCustRequestParty;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.party.DeleteCustRequestParty;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.party.UpdateCustRequestParty;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.party.CustRequestPartyAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.party.CustRequestPartyDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.party.CustRequestPartyFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.party.CustRequestPartyUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.party.CustRequestPartyMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.party.FindCustRequestPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestPartys")
public class CustRequestPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestParty
	 * @return a List with the CustRequestPartys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestParty>> findCustRequestPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestPartysBy query = new FindCustRequestPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestParty> custRequestPartys =((CustRequestPartyFound) Scheduler.execute(query).data()).getCustRequestPartys();

		return ResponseEntity.ok().body(custRequestPartys);

	}

	/**
	 * creates a new CustRequestParty entry in the ofbiz database
	 * 
	 * @param custRequestPartyToBeAdded
	 *            the CustRequestParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestParty> createCustRequestParty(@RequestBody CustRequestParty custRequestPartyToBeAdded) throws Exception {

		AddCustRequestParty command = new AddCustRequestParty(custRequestPartyToBeAdded);
		CustRequestParty custRequestParty = ((CustRequestPartyAdded) Scheduler.execute(command).data()).getAddedCustRequestParty();
		
		if (custRequestParty != null) 
			return successful(custRequestParty);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestParty with the specific Id
	 * 
	 * @param custRequestPartyToBeUpdated
	 *            the CustRequestParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestParty(@RequestBody CustRequestParty custRequestPartyToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		custRequestPartyToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateCustRequestParty command = new UpdateCustRequestParty(custRequestPartyToBeUpdated);

		try {
			if(((CustRequestPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestPartyId}")
	public ResponseEntity<CustRequestParty> findById(@PathVariable String custRequestPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestPartyId", custRequestPartyId);
		try {

			List<CustRequestParty> foundCustRequestParty = findCustRequestPartysBy(requestParams).getBody();
			if(foundCustRequestParty.size()==1){				return successful(foundCustRequestParty.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestPartyId}")
	public ResponseEntity<String> deleteCustRequestPartyByIdUpdated(@PathVariable String custRequestPartyId) throws Exception {
		DeleteCustRequestParty command = new DeleteCustRequestParty(custRequestPartyId);

		try {
			if (((CustRequestPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
