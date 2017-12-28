package com.skytala.eCommerce.domain.accounting.relations.deduction.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.type.AddDeductionType;
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.type.DeleteDeductionType;
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.type.UpdateDeductionType;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.type.DeductionTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.type.DeductionTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.type.DeductionTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.type.DeductionTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.deduction.mapper.type.DeductionTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.deduction.model.type.DeductionType;
import com.skytala.eCommerce.domain.accounting.relations.deduction.query.type.FindDeductionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/deduction/deductionTypes")
public class DeductionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DeductionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DeductionType
	 * @return a List with the DeductionTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DeductionType>> findDeductionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeductionTypesBy query = new FindDeductionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DeductionType> deductionTypes =((DeductionTypeFound) Scheduler.execute(query).data()).getDeductionTypes();

		return ResponseEntity.ok().body(deductionTypes);

	}

	/**
	 * creates a new DeductionType entry in the ofbiz database
	 * 
	 * @param deductionTypeToBeAdded
	 *            the DeductionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DeductionType> createDeductionType(@RequestBody DeductionType deductionTypeToBeAdded) throws Exception {

		AddDeductionType command = new AddDeductionType(deductionTypeToBeAdded);
		DeductionType deductionType = ((DeductionTypeAdded) Scheduler.execute(command).data()).getAddedDeductionType();
		
		if (deductionType != null) 
			return successful(deductionType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DeductionType with the specific Id
	 * 
	 * @param deductionTypeToBeUpdated
	 *            the DeductionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{deductionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDeductionType(@RequestBody DeductionType deductionTypeToBeUpdated,
			@PathVariable String deductionTypeId) throws Exception {

		deductionTypeToBeUpdated.setDeductionTypeId(deductionTypeId);

		UpdateDeductionType command = new UpdateDeductionType(deductionTypeToBeUpdated);

		try {
			if(((DeductionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{deductionTypeId}")
	public ResponseEntity<DeductionType> findById(@PathVariable String deductionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deductionTypeId", deductionTypeId);
		try {

			List<DeductionType> foundDeductionType = findDeductionTypesBy(requestParams).getBody();
			if(foundDeductionType.size()==1){				return successful(foundDeductionType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{deductionTypeId}")
	public ResponseEntity<String> deleteDeductionTypeByIdUpdated(@PathVariable String deductionTypeId) throws Exception {
		DeleteDeductionType command = new DeleteDeductionType(deductionTypeId);

		try {
			if (((DeductionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
