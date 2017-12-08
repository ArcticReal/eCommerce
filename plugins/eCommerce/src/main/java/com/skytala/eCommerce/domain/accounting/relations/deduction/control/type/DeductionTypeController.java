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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/deductionTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDeductionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeductionTypesBy query = new FindDeductionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DeductionType> deductionTypes =((DeductionTypeFound) Scheduler.execute(query).data()).getDeductionTypes();

		if (deductionTypes.size() == 1) {
			return ResponseEntity.ok().body(deductionTypes.get(0));
		}

		return ResponseEntity.ok().body(deductionTypes);

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
	public ResponseEntity<Object> createDeductionType(HttpServletRequest request) throws Exception {

		DeductionType deductionTypeToBeAdded = new DeductionType();
		try {
			deductionTypeToBeAdded = DeductionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDeductionType(deductionTypeToBeAdded);

	}

	/**
	 * creates a new DeductionType entry in the ofbiz database
	 * 
	 * @param deductionTypeToBeAdded
	 *            the DeductionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDeductionType(@RequestBody DeductionType deductionTypeToBeAdded) throws Exception {

		AddDeductionType command = new AddDeductionType(deductionTypeToBeAdded);
		DeductionType deductionType = ((DeductionTypeAdded) Scheduler.execute(command).data()).getAddedDeductionType();
		
		if (deductionType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(deductionType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DeductionType could not be created.");
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
	public boolean updateDeductionType(HttpServletRequest request) throws Exception {

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

		DeductionType deductionTypeToBeUpdated = new DeductionType();

		try {
			deductionTypeToBeUpdated = DeductionTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDeductionType(deductionTypeToBeUpdated, deductionTypeToBeUpdated.getDeductionTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDeductionType(@RequestBody DeductionType deductionTypeToBeUpdated,
			@PathVariable String deductionTypeId) throws Exception {

		deductionTypeToBeUpdated.setDeductionTypeId(deductionTypeId);

		UpdateDeductionType command = new UpdateDeductionType(deductionTypeToBeUpdated);

		try {
			if(((DeductionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{deductionTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String deductionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deductionTypeId", deductionTypeId);
		try {

			Object foundDeductionType = findDeductionTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDeductionType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{deductionTypeId}")
	public ResponseEntity<Object> deleteDeductionTypeByIdUpdated(@PathVariable String deductionTypeId) throws Exception {
		DeleteDeductionType command = new DeleteDeductionType(deductionTypeId);

		try {
			if (((DeductionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DeductionType could not be deleted");

	}

}
