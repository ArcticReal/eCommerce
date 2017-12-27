package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.transType;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transType.AddFinAccountTransType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transType.DeleteFinAccountTransType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transType.UpdateFinAccountTransType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transType.FinAccountTransTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transType.FinAccountTransType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transType.FindFinAccountTransTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTransTypes")
public class FinAccountTransTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTransTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTransType
	 * @return a List with the FinAccountTransTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTransType>> findFinAccountTransTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTransTypesBy query = new FindFinAccountTransTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTransType> finAccountTransTypes =((FinAccountTransTypeFound) Scheduler.execute(query).data()).getFinAccountTransTypes();

		return ResponseEntity.ok().body(finAccountTransTypes);

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
	public ResponseEntity<FinAccountTransType> createFinAccountTransType(HttpServletRequest request) throws Exception {

		FinAccountTransType finAccountTransTypeToBeAdded = new FinAccountTransType();
		try {
			finAccountTransTypeToBeAdded = FinAccountTransTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFinAccountTransType(finAccountTransTypeToBeAdded);

	}

	/**
	 * creates a new FinAccountTransType entry in the ofbiz database
	 * 
	 * @param finAccountTransTypeToBeAdded
	 *            the FinAccountTransType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTransType> createFinAccountTransType(@RequestBody FinAccountTransType finAccountTransTypeToBeAdded) throws Exception {

		AddFinAccountTransType command = new AddFinAccountTransType(finAccountTransTypeToBeAdded);
		FinAccountTransType finAccountTransType = ((FinAccountTransTypeAdded) Scheduler.execute(command).data()).getAddedFinAccountTransType();
		
		if (finAccountTransType != null) 
			return successful(finAccountTransType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTransType with the specific Id
	 * 
	 * @param finAccountTransTypeToBeUpdated
	 *            the FinAccountTransType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountTransTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTransType(@RequestBody FinAccountTransType finAccountTransTypeToBeUpdated,
			@PathVariable String finAccountTransTypeId) throws Exception {

		finAccountTransTypeToBeUpdated.setFinAccountTransTypeId(finAccountTransTypeId);

		UpdateFinAccountTransType command = new UpdateFinAccountTransType(finAccountTransTypeToBeUpdated);

		try {
			if(((FinAccountTransTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTransTypeId}")
	public ResponseEntity<FinAccountTransType> findById(@PathVariable String finAccountTransTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransTypeId", finAccountTransTypeId);
		try {

			List<FinAccountTransType> foundFinAccountTransType = findFinAccountTransTypesBy(requestParams).getBody();
			if(foundFinAccountTransType.size()==1){				return successful(foundFinAccountTransType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTransTypeId}")
	public ResponseEntity<String> deleteFinAccountTransTypeByIdUpdated(@PathVariable String finAccountTransTypeId) throws Exception {
		DeleteFinAccountTransType command = new DeleteFinAccountTransType(finAccountTransTypeId);

		try {
			if (((FinAccountTransTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
