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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestPartysBy query = new FindCustRequestPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestParty> custRequestPartys =((CustRequestPartyFound) Scheduler.execute(query).data()).getCustRequestPartys();

		if (custRequestPartys.size() == 1) {
			return ResponseEntity.ok().body(custRequestPartys.get(0));
		}

		return ResponseEntity.ok().body(custRequestPartys);

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
	public ResponseEntity<Object> createCustRequestParty(HttpServletRequest request) throws Exception {

		CustRequestParty custRequestPartyToBeAdded = new CustRequestParty();
		try {
			custRequestPartyToBeAdded = CustRequestPartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestParty(custRequestPartyToBeAdded);

	}

	/**
	 * creates a new CustRequestParty entry in the ofbiz database
	 * 
	 * @param custRequestPartyToBeAdded
	 *            the CustRequestParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestParty(@RequestBody CustRequestParty custRequestPartyToBeAdded) throws Exception {

		AddCustRequestParty command = new AddCustRequestParty(custRequestPartyToBeAdded);
		CustRequestParty custRequestParty = ((CustRequestPartyAdded) Scheduler.execute(command).data()).getAddedCustRequestParty();
		
		if (custRequestParty != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestParty);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestParty could not be created.");
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
	public boolean updateCustRequestParty(HttpServletRequest request) throws Exception {

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

		CustRequestParty custRequestPartyToBeUpdated = new CustRequestParty();

		try {
			custRequestPartyToBeUpdated = CustRequestPartyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestParty(custRequestPartyToBeUpdated, custRequestPartyToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCustRequestParty(@RequestBody CustRequestParty custRequestPartyToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		custRequestPartyToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateCustRequestParty command = new UpdateCustRequestParty(custRequestPartyToBeUpdated);

		try {
			if(((CustRequestPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestPartyId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestPartyId", custRequestPartyId);
		try {

			Object foundCustRequestParty = findCustRequestPartysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestParty);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestPartyId}")
	public ResponseEntity<Object> deleteCustRequestPartyByIdUpdated(@PathVariable String custRequestPartyId) throws Exception {
		DeleteCustRequestParty command = new DeleteCustRequestParty(custRequestPartyId);

		try {
			if (((CustRequestPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestParty could not be deleted");

	}

}
