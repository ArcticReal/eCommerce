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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<QuantityBreakType>> findQuantityBreakTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuantityBreakTypesBy query = new FindQuantityBreakTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuantityBreakType> quantityBreakTypes =((QuantityBreakTypeFound) Scheduler.execute(query).data()).getQuantityBreakTypes();

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
	public ResponseEntity<QuantityBreakType> createQuantityBreakType(HttpServletRequest request) throws Exception {

		QuantityBreakType quantityBreakTypeToBeAdded = new QuantityBreakType();
		try {
			quantityBreakTypeToBeAdded = QuantityBreakTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<QuantityBreakType> createQuantityBreakType(@RequestBody QuantityBreakType quantityBreakTypeToBeAdded) throws Exception {

		AddQuantityBreakType command = new AddQuantityBreakType(quantityBreakTypeToBeAdded);
		QuantityBreakType quantityBreakType = ((QuantityBreakTypeAdded) Scheduler.execute(command).data()).getAddedQuantityBreakType();
		
		if (quantityBreakType != null) 
			return successful(quantityBreakType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuantityBreakType(@RequestBody QuantityBreakType quantityBreakTypeToBeUpdated,
			@PathVariable String quantityBreakTypeId) throws Exception {

		quantityBreakTypeToBeUpdated.setQuantityBreakTypeId(quantityBreakTypeId);

		UpdateQuantityBreakType command = new UpdateQuantityBreakType(quantityBreakTypeToBeUpdated);

		try {
			if(((QuantityBreakTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quantityBreakTypeId}")
	public ResponseEntity<QuantityBreakType> findById(@PathVariable String quantityBreakTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quantityBreakTypeId", quantityBreakTypeId);
		try {

			List<QuantityBreakType> foundQuantityBreakType = findQuantityBreakTypesBy(requestParams).getBody();
			if(foundQuantityBreakType.size()==1){				return successful(foundQuantityBreakType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quantityBreakTypeId}")
	public ResponseEntity<String> deleteQuantityBreakTypeByIdUpdated(@PathVariable String quantityBreakTypeId) throws Exception {
		DeleteQuantityBreakType command = new DeleteQuantityBreakType(quantityBreakTypeId);

		try {
			if (((QuantityBreakTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
