package com.skytala.eCommerce.domain.order.relations.returnContactMech;

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
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.AddReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.DeleteReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.UpdateReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechDeleted;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechFound;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.mapper.ReturnContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.query.FindReturnContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnContactMechs")
public class ReturnContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnContactMech
	 * @return a List with the ReturnContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnContactMech>> findReturnContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnContactMechsBy query = new FindReturnContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnContactMech> returnContactMechs =((ReturnContactMechFound) Scheduler.execute(query).data()).getReturnContactMechs();

		return ResponseEntity.ok().body(returnContactMechs);

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
	public ResponseEntity<ReturnContactMech> createReturnContactMech(HttpServletRequest request) throws Exception {

		ReturnContactMech returnContactMechToBeAdded = new ReturnContactMech();
		try {
			returnContactMechToBeAdded = ReturnContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createReturnContactMech(returnContactMechToBeAdded);

	}

	/**
	 * creates a new ReturnContactMech entry in the ofbiz database
	 * 
	 * @param returnContactMechToBeAdded
	 *            the ReturnContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnContactMech> createReturnContactMech(@RequestBody ReturnContactMech returnContactMechToBeAdded) throws Exception {

		AddReturnContactMech command = new AddReturnContactMech(returnContactMechToBeAdded);
		ReturnContactMech returnContactMech = ((ReturnContactMechAdded) Scheduler.execute(command).data()).getAddedReturnContactMech();
		
		if (returnContactMech != null) 
			return successful(returnContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnContactMech with the specific Id
	 * 
	 * @param returnContactMechToBeUpdated
	 *            the ReturnContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnContactMech(@RequestBody ReturnContactMech returnContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnContactMechToBeUpdated.setnull(null);

		UpdateReturnContactMech command = new UpdateReturnContactMech(returnContactMechToBeUpdated);

		try {
			if(((ReturnContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnContactMechId}")
	public ResponseEntity<ReturnContactMech> findById(@PathVariable String returnContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnContactMechId", returnContactMechId);
		try {

			List<ReturnContactMech> foundReturnContactMech = findReturnContactMechsBy(requestParams).getBody();
			if(foundReturnContactMech.size()==1){				return successful(foundReturnContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnContactMechId}")
	public ResponseEntity<String> deleteReturnContactMechByIdUpdated(@PathVariable String returnContactMechId) throws Exception {
		DeleteReturnContactMech command = new DeleteReturnContactMech(returnContactMechId);

		try {
			if (((ReturnContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
