package com.skytala.eCommerce.domain.order.relations.custRequest.control.workEffort;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.workEffort.AddCustRequestWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.workEffort.DeleteCustRequestWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.workEffort.UpdateCustRequestWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort.CustRequestWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort.CustRequestWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort.CustRequestWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.workEffort.CustRequestWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.workEffort.CustRequestWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.workEffort.CustRequestWorkEffort;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.workEffort.FindCustRequestWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestWorkEfforts")
public class CustRequestWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestWorkEffort
	 * @return a List with the CustRequestWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestWorkEffort>> findCustRequestWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestWorkEffortsBy query = new FindCustRequestWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestWorkEffort> custRequestWorkEfforts =((CustRequestWorkEffortFound) Scheduler.execute(query).data()).getCustRequestWorkEfforts();

		return ResponseEntity.ok().body(custRequestWorkEfforts);

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
	public ResponseEntity<CustRequestWorkEffort> createCustRequestWorkEffort(HttpServletRequest request) throws Exception {

		CustRequestWorkEffort custRequestWorkEffortToBeAdded = new CustRequestWorkEffort();
		try {
			custRequestWorkEffortToBeAdded = CustRequestWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCustRequestWorkEffort(custRequestWorkEffortToBeAdded);

	}

	/**
	 * creates a new CustRequestWorkEffort entry in the ofbiz database
	 * 
	 * @param custRequestWorkEffortToBeAdded
	 *            the CustRequestWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestWorkEffort> createCustRequestWorkEffort(@RequestBody CustRequestWorkEffort custRequestWorkEffortToBeAdded) throws Exception {

		AddCustRequestWorkEffort command = new AddCustRequestWorkEffort(custRequestWorkEffortToBeAdded);
		CustRequestWorkEffort custRequestWorkEffort = ((CustRequestWorkEffortAdded) Scheduler.execute(command).data()).getAddedCustRequestWorkEffort();
		
		if (custRequestWorkEffort != null) 
			return successful(custRequestWorkEffort);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestWorkEffort with the specific Id
	 * 
	 * @param custRequestWorkEffortToBeUpdated
	 *            the CustRequestWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestWorkEffort(@RequestBody CustRequestWorkEffort custRequestWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestWorkEffortToBeUpdated.setnull(null);

		UpdateCustRequestWorkEffort command = new UpdateCustRequestWorkEffort(custRequestWorkEffortToBeUpdated);

		try {
			if(((CustRequestWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestWorkEffortId}")
	public ResponseEntity<CustRequestWorkEffort> findById(@PathVariable String custRequestWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestWorkEffortId", custRequestWorkEffortId);
		try {

			List<CustRequestWorkEffort> foundCustRequestWorkEffort = findCustRequestWorkEffortsBy(requestParams).getBody();
			if(foundCustRequestWorkEffort.size()==1){				return successful(foundCustRequestWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestWorkEffortId}")
	public ResponseEntity<String> deleteCustRequestWorkEffortByIdUpdated(@PathVariable String custRequestWorkEffortId) throws Exception {
		DeleteCustRequestWorkEffort command = new DeleteCustRequestWorkEffort(custRequestWorkEffortId);

		try {
			if (((CustRequestWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
