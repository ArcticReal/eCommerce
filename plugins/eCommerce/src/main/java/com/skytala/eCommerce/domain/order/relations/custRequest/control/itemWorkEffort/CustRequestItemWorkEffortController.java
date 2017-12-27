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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CustRequestItemWorkEffort>> findCustRequestItemWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemWorkEffortsBy query = new FindCustRequestItemWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItemWorkEffort> custRequestItemWorkEfforts =((CustRequestItemWorkEffortFound) Scheduler.execute(query).data()).getCustRequestItemWorkEfforts();

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
	public ResponseEntity<CustRequestItemWorkEffort> createCustRequestItemWorkEffort(HttpServletRequest request) throws Exception {

		CustRequestItemWorkEffort custRequestItemWorkEffortToBeAdded = new CustRequestItemWorkEffort();
		try {
			custRequestItemWorkEffortToBeAdded = CustRequestItemWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CustRequestItemWorkEffort> createCustRequestItemWorkEffort(@RequestBody CustRequestItemWorkEffort custRequestItemWorkEffortToBeAdded) throws Exception {

		AddCustRequestItemWorkEffort command = new AddCustRequestItemWorkEffort(custRequestItemWorkEffortToBeAdded);
		CustRequestItemWorkEffort custRequestItemWorkEffort = ((CustRequestItemWorkEffortAdded) Scheduler.execute(command).data()).getAddedCustRequestItemWorkEffort();
		
		if (custRequestItemWorkEffort != null) 
			return successful(custRequestItemWorkEffort);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestItemWorkEffort(@RequestBody CustRequestItemWorkEffort custRequestItemWorkEffortToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemWorkEffortToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItemWorkEffort command = new UpdateCustRequestItemWorkEffort(custRequestItemWorkEffortToBeUpdated);

		try {
			if(((CustRequestItemWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestItemWorkEffortId}")
	public ResponseEntity<CustRequestItemWorkEffort> findById(@PathVariable String custRequestItemWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemWorkEffortId", custRequestItemWorkEffortId);
		try {

			List<CustRequestItemWorkEffort> foundCustRequestItemWorkEffort = findCustRequestItemWorkEffortsBy(requestParams).getBody();
			if(foundCustRequestItemWorkEffort.size()==1){				return successful(foundCustRequestItemWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestItemWorkEffortId}")
	public ResponseEntity<String> deleteCustRequestItemWorkEffortByIdUpdated(@PathVariable String custRequestItemWorkEffortId) throws Exception {
		DeleteCustRequestItemWorkEffort command = new DeleteCustRequestItemWorkEffort(custRequestItemWorkEffortId);

		try {
			if (((CustRequestItemWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
