package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.AddFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.DeleteFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.UpdateFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.type.FinAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.type.FinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.type.FindFinAccountTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTypes")
public class FinAccountTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountType
	 * @return a List with the FinAccountTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountType>> findFinAccountTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypesBy query = new FindFinAccountTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountType> finAccountTypes =((FinAccountTypeFound) Scheduler.execute(query).data()).getFinAccountTypes();

		return ResponseEntity.ok().body(finAccountTypes);

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
	public ResponseEntity<FinAccountType> createFinAccountType(HttpServletRequest request) throws Exception {

		FinAccountType finAccountTypeToBeAdded = new FinAccountType();
		try {
			finAccountTypeToBeAdded = FinAccountTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFinAccountType(finAccountTypeToBeAdded);

	}

	/**
	 * creates a new FinAccountType entry in the ofbiz database
	 * 
	 * @param finAccountTypeToBeAdded
	 *            the FinAccountType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountType> createFinAccountType(@RequestBody FinAccountType finAccountTypeToBeAdded) throws Exception {

		AddFinAccountType command = new AddFinAccountType(finAccountTypeToBeAdded);
		FinAccountType finAccountType = ((FinAccountTypeAdded) Scheduler.execute(command).data()).getAddedFinAccountType();
		
		if (finAccountType != null) 
			return successful(finAccountType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountType with the specific Id
	 * 
	 * @param finAccountTypeToBeUpdated
	 *            the FinAccountType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountType(@RequestBody FinAccountType finAccountTypeToBeUpdated,
			@PathVariable String finAccountTypeId) throws Exception {

		finAccountTypeToBeUpdated.setFinAccountTypeId(finAccountTypeId);

		UpdateFinAccountType command = new UpdateFinAccountType(finAccountTypeToBeUpdated);

		try {
			if(((FinAccountTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTypeId}")
	public ResponseEntity<FinAccountType> findById(@PathVariable String finAccountTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeId", finAccountTypeId);
		try {

			List<FinAccountType> foundFinAccountType = findFinAccountTypesBy(requestParams).getBody();
			if(foundFinAccountType.size()==1){				return successful(foundFinAccountType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTypeId}")
	public ResponseEntity<String> deleteFinAccountTypeByIdUpdated(@PathVariable String finAccountTypeId) throws Exception {
		DeleteFinAccountType command = new DeleteFinAccountType(finAccountTypeId);

		try {
			if (((FinAccountTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
