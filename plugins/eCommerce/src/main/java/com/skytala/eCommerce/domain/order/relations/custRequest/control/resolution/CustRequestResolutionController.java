package com.skytala.eCommerce.domain.order.relations.custRequest.control.resolution;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.AddCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.DeleteCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.resolution.UpdateCustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution.CustRequestResolutionUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.resolution.CustRequestResolutionMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.resolution.FindCustRequestResolutionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestResolutions")
public class CustRequestResolutionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestResolutionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestResolution
	 * @return a List with the CustRequestResolutions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestResolution>> findCustRequestResolutionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestResolutionsBy query = new FindCustRequestResolutionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestResolution> custRequestResolutions =((CustRequestResolutionFound) Scheduler.execute(query).data()).getCustRequestResolutions();

		return ResponseEntity.ok().body(custRequestResolutions);

	}

	/**
	 * creates a new CustRequestResolution entry in the ofbiz database
	 * 
	 * @param custRequestResolutionToBeAdded
	 *            the CustRequestResolution thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestResolution> createCustRequestResolution(@RequestBody CustRequestResolution custRequestResolutionToBeAdded) throws Exception {

		AddCustRequestResolution command = new AddCustRequestResolution(custRequestResolutionToBeAdded);
		CustRequestResolution custRequestResolution = ((CustRequestResolutionAdded) Scheduler.execute(command).data()).getAddedCustRequestResolution();
		
		if (custRequestResolution != null) 
			return successful(custRequestResolution);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestResolution with the specific Id
	 * 
	 * @param custRequestResolutionToBeUpdated
	 *            the CustRequestResolution thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestResolutionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestResolution(@RequestBody CustRequestResolution custRequestResolutionToBeUpdated,
			@PathVariable String custRequestResolutionId) throws Exception {

		custRequestResolutionToBeUpdated.setCustRequestResolutionId(custRequestResolutionId);

		UpdateCustRequestResolution command = new UpdateCustRequestResolution(custRequestResolutionToBeUpdated);

		try {
			if(((CustRequestResolutionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestResolutionId}")
	public ResponseEntity<CustRequestResolution> findById(@PathVariable String custRequestResolutionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestResolutionId", custRequestResolutionId);
		try {

			List<CustRequestResolution> foundCustRequestResolution = findCustRequestResolutionsBy(requestParams).getBody();
			if(foundCustRequestResolution.size()==1){				return successful(foundCustRequestResolution.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestResolutionId}")
	public ResponseEntity<String> deleteCustRequestResolutionByIdUpdated(@PathVariable String custRequestResolutionId) throws Exception {
		DeleteCustRequestResolution command = new DeleteCustRequestResolution(custRequestResolutionId);

		try {
			if (((CustRequestResolutionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
