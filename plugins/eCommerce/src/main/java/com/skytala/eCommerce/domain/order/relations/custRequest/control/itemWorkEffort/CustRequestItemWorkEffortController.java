package com.skytala.eCommerce.domain.order.relations.custRequest.control.itemWorkEffort;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemWorkEffort.AddCustRequestItemWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemWorkEffort.DeleteCustRequestItemWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemWorkEffort.UpdateCustRequestItemWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemWorkEffort.CustRequestItemWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.itemWorkEffort.FindCustRequestItemWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/custRequest/custRequestItemWorkEfforts")
public class CustRequestItemWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestItemWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestItemWorkEffort
	 * @return a List with the CustRequestItemWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCustRequestItemWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemWorkEffortsBy query = new FindCustRequestItemWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItemWorkEffort> custRequestItemWorkEfforts =((CustRequestItemWorkEffortFound) Scheduler.execute(query).data()).getCustRequestItemWorkEfforts();

		if (custRequestItemWorkEfforts.size() == 1) {
			return ResponseEntity.ok().body(custRequestItemWorkEfforts.get(0));
		}

		return ResponseEntity.ok().body(custRequestItemWorkEfforts);

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
	public ResponseEntity<Object> createCustRequestItemWorkEffort(HttpServletRequest request) throws Exception {

		CustRequestItemWorkEffort custRequestItemWorkEffortToBeAdded = new CustRequestItemWorkEffort();
		try {
			custRequestItemWorkEffortToBeAdded = CustRequestItemWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestItemWorkEffort(custRequestItemWorkEffortToBeAdded);

	}

	/**
	 * creates a new CustRequestItemWorkEffort entry in the ofbiz database
	 * 
	 * @param custRequestItemWorkEffortToBeAdded
	 *            the CustRequestItemWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestItemWorkEffort(@RequestBody CustRequestItemWorkEffort custRequestItemWorkEffortToBeAdded) throws Exception {

		AddCustRequestItemWorkEffort command = new AddCustRequestItemWorkEffort(custRequestItemWorkEffortToBeAdded);
		CustRequestItemWorkEffort custRequestItemWorkEffort = ((CustRequestItemWorkEffortAdded) Scheduler.execute(command).data()).getAddedCustRequestItemWorkEffort();
		
		if (custRequestItemWorkEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestItemWorkEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestItemWorkEffort could not be created.");
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
	public boolean updateCustRequestItemWorkEffort(HttpServletRequest request) throws Exception {

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

		CustRequestItemWorkEffort custRequestItemWorkEffortToBeUpdated = new CustRequestItemWorkEffort();

		try {
			custRequestItemWorkEffortToBeUpdated = CustRequestItemWorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestItemWorkEffort(custRequestItemWorkEffortToBeUpdated, custRequestItemWorkEffortToBeUpdated.getCustRequestItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestItemWorkEffort with the specific Id
	 * 
	 * @param custRequestItemWorkEffortToBeUpdated
	 *            the CustRequestItemWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestItemWorkEffort(@RequestBody CustRequestItemWorkEffort custRequestItemWorkEffortToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemWorkEffortToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItemWorkEffort command = new UpdateCustRequestItemWorkEffort(custRequestItemWorkEffortToBeUpdated);

		try {
			if(((CustRequestItemWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{custRequestItemWorkEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestItemWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemWorkEffortId", custRequestItemWorkEffortId);
		try {

			Object foundCustRequestItemWorkEffort = findCustRequestItemWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestItemWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{custRequestItemWorkEffortId}")
	public ResponseEntity<Object> deleteCustRequestItemWorkEffortByIdUpdated(@PathVariable String custRequestItemWorkEffortId) throws Exception {
		DeleteCustRequestItemWorkEffort command = new DeleteCustRequestItemWorkEffort(custRequestItemWorkEffortId);

		try {
			if (((CustRequestItemWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestItemWorkEffort could not be deleted");

	}

}
