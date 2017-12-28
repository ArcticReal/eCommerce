package com.skytala.eCommerce.domain.accounting.relations.rateType;

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
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.AddRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.DeleteRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.command.UpdateRateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.rateType.mapper.RateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;
import com.skytala.eCommerce.domain.accounting.relations.rateType.query.FindRateTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/rateTypes")
public class RateTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RateTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RateType
	 * @return a List with the RateTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RateType>> findRateTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRateTypesBy query = new FindRateTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RateType> rateTypes =((RateTypeFound) Scheduler.execute(query).data()).getRateTypes();

		return ResponseEntity.ok().body(rateTypes);

	}

	/**
	 * creates a new RateType entry in the ofbiz database
	 * 
	 * @param rateTypeToBeAdded
	 *            the RateType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RateType> createRateType(@RequestBody RateType rateTypeToBeAdded) throws Exception {

		AddRateType command = new AddRateType(rateTypeToBeAdded);
		RateType rateType = ((RateTypeAdded) Scheduler.execute(command).data()).getAddedRateType();
		
		if (rateType != null) 
			return successful(rateType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RateType with the specific Id
	 * 
	 * @param rateTypeToBeUpdated
	 *            the RateType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{rateTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRateType(@RequestBody RateType rateTypeToBeUpdated,
			@PathVariable String rateTypeId) throws Exception {

		rateTypeToBeUpdated.setRateTypeId(rateTypeId);

		UpdateRateType command = new UpdateRateType(rateTypeToBeUpdated);

		try {
			if(((RateTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{rateTypeId}")
	public ResponseEntity<RateType> findById(@PathVariable String rateTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rateTypeId", rateTypeId);
		try {

			List<RateType> foundRateType = findRateTypesBy(requestParams).getBody();
			if(foundRateType.size()==1){				return successful(foundRateType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{rateTypeId}")
	public ResponseEntity<String> deleteRateTypeByIdUpdated(@PathVariable String rateTypeId) throws Exception {
		DeleteRateType command = new DeleteRateType(rateTypeId);

		try {
			if (((RateTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
