package com.skytala.eCommerce.domain.product.relations.quantityBreak.control.type;

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
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.type.AddQuantityBreakType;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.type.DeleteQuantityBreakType;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.command.type.UpdateQuantityBreakType;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeAdded;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeFound;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper.type.QuantityBreakTypeMapper;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.query.type.FindQuantityBreakTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/quantityBreak/quantityBreakTypes")
public class QuantityBreakTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuantityBreakTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuantityBreakType
	 * @return a List with the QuantityBreakTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findQuantityBreakTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuantityBreakTypesBy query = new FindQuantityBreakTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuantityBreakType> quantityBreakTypes =((QuantityBreakTypeFound) Scheduler.execute(query).data()).getQuantityBreakTypes();

		if (quantityBreakTypes.size() == 1) {
			return ResponseEntity.ok().body(quantityBreakTypes.get(0));
		}

		return ResponseEntity.ok().body(quantityBreakTypes);

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
	public ResponseEntity<Object> createQuantityBreakType(HttpServletRequest request) throws Exception {

		QuantityBreakType quantityBreakTypeToBeAdded = new QuantityBreakType();
		try {
			quantityBreakTypeToBeAdded = QuantityBreakTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuantityBreakType(quantityBreakTypeToBeAdded);

	}

	/**
	 * creates a new QuantityBreakType entry in the ofbiz database
	 * 
	 * @param quantityBreakTypeToBeAdded
	 *            the QuantityBreakType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuantityBreakType(@RequestBody QuantityBreakType quantityBreakTypeToBeAdded) throws Exception {

		AddQuantityBreakType command = new AddQuantityBreakType(quantityBreakTypeToBeAdded);
		QuantityBreakType quantityBreakType = ((QuantityBreakTypeAdded) Scheduler.execute(command).data()).getAddedQuantityBreakType();
		
		if (quantityBreakType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quantityBreakType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuantityBreakType could not be created.");
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
	public boolean updateQuantityBreakType(HttpServletRequest request) throws Exception {

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

		QuantityBreakType quantityBreakTypeToBeUpdated = new QuantityBreakType();

		try {
			quantityBreakTypeToBeUpdated = QuantityBreakTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuantityBreakType(quantityBreakTypeToBeUpdated, quantityBreakTypeToBeUpdated.getQuantityBreakTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuantityBreakType with the specific Id
	 * 
	 * @param quantityBreakTypeToBeUpdated
	 *            the QuantityBreakType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quantityBreakTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuantityBreakType(@RequestBody QuantityBreakType quantityBreakTypeToBeUpdated,
			@PathVariable String quantityBreakTypeId) throws Exception {

		quantityBreakTypeToBeUpdated.setQuantityBreakTypeId(quantityBreakTypeId);

		UpdateQuantityBreakType command = new UpdateQuantityBreakType(quantityBreakTypeToBeUpdated);

		try {
			if(((QuantityBreakTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{quantityBreakTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String quantityBreakTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quantityBreakTypeId", quantityBreakTypeId);
		try {

			Object foundQuantityBreakType = findQuantityBreakTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuantityBreakType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{quantityBreakTypeId}")
	public ResponseEntity<Object> deleteQuantityBreakTypeByIdUpdated(@PathVariable String quantityBreakTypeId) throws Exception {
		DeleteQuantityBreakType command = new DeleteQuantityBreakType(quantityBreakTypeId);

		try {
			if (((QuantityBreakTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuantityBreakType could not be deleted");

	}

}
