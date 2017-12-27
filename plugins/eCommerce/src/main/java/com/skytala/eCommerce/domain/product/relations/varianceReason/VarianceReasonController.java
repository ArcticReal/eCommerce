package com.skytala.eCommerce.domain.product.relations.varianceReason;

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
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.AddVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.DeleteVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.UpdateVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonAdded;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonDeleted;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonFound;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonUpdated;
import com.skytala.eCommerce.domain.product.relations.varianceReason.mapper.VarianceReasonMapper;
import com.skytala.eCommerce.domain.product.relations.varianceReason.model.VarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.query.FindVarianceReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/varianceReasons")
public class VarianceReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VarianceReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VarianceReason
	 * @return a List with the VarianceReasons
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<VarianceReason>> findVarianceReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVarianceReasonsBy query = new FindVarianceReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VarianceReason> varianceReasons =((VarianceReasonFound) Scheduler.execute(query).data()).getVarianceReasons();

		return ResponseEntity.ok().body(varianceReasons);

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
	public ResponseEntity<VarianceReason> createVarianceReason(HttpServletRequest request) throws Exception {

		VarianceReason varianceReasonToBeAdded = new VarianceReason();
		try {
			varianceReasonToBeAdded = VarianceReasonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createVarianceReason(varianceReasonToBeAdded);

	}

	/**
	 * creates a new VarianceReason entry in the ofbiz database
	 * 
	 * @param varianceReasonToBeAdded
	 *            the VarianceReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<VarianceReason> createVarianceReason(@RequestBody VarianceReason varianceReasonToBeAdded) throws Exception {

		AddVarianceReason command = new AddVarianceReason(varianceReasonToBeAdded);
		VarianceReason varianceReason = ((VarianceReasonAdded) Scheduler.execute(command).data()).getAddedVarianceReason();
		
		if (varianceReason != null) 
			return successful(varianceReason);
		else 
			return conflict(null);
	}

	/**
	 * Updates the VarianceReason with the specific Id
	 * 
	 * @param varianceReasonToBeUpdated
	 *            the VarianceReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{varianceReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateVarianceReason(@RequestBody VarianceReason varianceReasonToBeUpdated,
			@PathVariable String varianceReasonId) throws Exception {

		varianceReasonToBeUpdated.setVarianceReasonId(varianceReasonId);

		UpdateVarianceReason command = new UpdateVarianceReason(varianceReasonToBeUpdated);

		try {
			if(((VarianceReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{varianceReasonId}")
	public ResponseEntity<VarianceReason> findById(@PathVariable String varianceReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("varianceReasonId", varianceReasonId);
		try {

			List<VarianceReason> foundVarianceReason = findVarianceReasonsBy(requestParams).getBody();
			if(foundVarianceReason.size()==1){				return successful(foundVarianceReason.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{varianceReasonId}")
	public ResponseEntity<String> deleteVarianceReasonByIdUpdated(@PathVariable String varianceReasonId) throws Exception {
		DeleteVarianceReason command = new DeleteVarianceReason(varianceReasonId);

		try {
			if (((VarianceReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
