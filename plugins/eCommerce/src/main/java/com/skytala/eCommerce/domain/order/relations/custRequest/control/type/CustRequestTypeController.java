package com.skytala.eCommerce.domain.order.relations.custRequest.control.type;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.type.AddCustRequestType;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.type.DeleteCustRequestType;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.type.UpdateCustRequestType;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.type.CustRequestTypeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.type.CustRequestType;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.type.FindCustRequestTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestTypes")
public class CustRequestTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestType
	 * @return a List with the CustRequestTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestType>> findCustRequestTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestTypesBy query = new FindCustRequestTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestType> custRequestTypes =((CustRequestTypeFound) Scheduler.execute(query).data()).getCustRequestTypes();

		return ResponseEntity.ok().body(custRequestTypes);

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
	public ResponseEntity<CustRequestType> createCustRequestType(HttpServletRequest request) throws Exception {

		CustRequestType custRequestTypeToBeAdded = new CustRequestType();
		try {
			custRequestTypeToBeAdded = CustRequestTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCustRequestType(custRequestTypeToBeAdded);

	}

	/**
	 * creates a new CustRequestType entry in the ofbiz database
	 * 
	 * @param custRequestTypeToBeAdded
	 *            the CustRequestType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestType> createCustRequestType(@RequestBody CustRequestType custRequestTypeToBeAdded) throws Exception {

		AddCustRequestType command = new AddCustRequestType(custRequestTypeToBeAdded);
		CustRequestType custRequestType = ((CustRequestTypeAdded) Scheduler.execute(command).data()).getAddedCustRequestType();
		
		if (custRequestType != null) 
			return successful(custRequestType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestType with the specific Id
	 * 
	 * @param custRequestTypeToBeUpdated
	 *            the CustRequestType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestType(@RequestBody CustRequestType custRequestTypeToBeUpdated,
			@PathVariable String custRequestTypeId) throws Exception {

		custRequestTypeToBeUpdated.setCustRequestTypeId(custRequestTypeId);

		UpdateCustRequestType command = new UpdateCustRequestType(custRequestTypeToBeUpdated);

		try {
			if(((CustRequestTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestTypeId}")
	public ResponseEntity<CustRequestType> findById(@PathVariable String custRequestTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestTypeId", custRequestTypeId);
		try {

			List<CustRequestType> foundCustRequestType = findCustRequestTypesBy(requestParams).getBody();
			if(foundCustRequestType.size()==1){				return successful(foundCustRequestType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestTypeId}")
	public ResponseEntity<String> deleteCustRequestTypeByIdUpdated(@PathVariable String custRequestTypeId) throws Exception {
		DeleteCustRequestType command = new DeleteCustRequestType(custRequestTypeId);

		try {
			if (((CustRequestTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
