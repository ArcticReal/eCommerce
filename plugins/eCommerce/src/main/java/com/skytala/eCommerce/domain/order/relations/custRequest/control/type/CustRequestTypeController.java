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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestTypesBy query = new FindCustRequestTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestType> custRequestTypes =((CustRequestTypeFound) Scheduler.execute(query).data()).getCustRequestTypes();

		if (custRequestTypes.size() == 1) {
			return ResponseEntity.ok().body(custRequestTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createCustRequestType(HttpServletRequest request) throws Exception {

		CustRequestType custRequestTypeToBeAdded = new CustRequestType();
		try {
			custRequestTypeToBeAdded = CustRequestTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createCustRequestType(@RequestBody CustRequestType custRequestTypeToBeAdded) throws Exception {

		AddCustRequestType command = new AddCustRequestType(custRequestTypeToBeAdded);
		CustRequestType custRequestType = ((CustRequestTypeAdded) Scheduler.execute(command).data()).getAddedCustRequestType();
		
		if (custRequestType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestType could not be created.");
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
	public boolean updateCustRequestType(HttpServletRequest request) throws Exception {

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

		CustRequestType custRequestTypeToBeUpdated = new CustRequestType();

		try {
			custRequestTypeToBeUpdated = CustRequestTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestType(custRequestTypeToBeUpdated, custRequestTypeToBeUpdated.getCustRequestTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCustRequestType(@RequestBody CustRequestType custRequestTypeToBeUpdated,
			@PathVariable String custRequestTypeId) throws Exception {

		custRequestTypeToBeUpdated.setCustRequestTypeId(custRequestTypeId);

		UpdateCustRequestType command = new UpdateCustRequestType(custRequestTypeToBeUpdated);

		try {
			if(((CustRequestTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestTypeId", custRequestTypeId);
		try {

			Object foundCustRequestType = findCustRequestTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestTypeId}")
	public ResponseEntity<Object> deleteCustRequestTypeByIdUpdated(@PathVariable String custRequestTypeId) throws Exception {
		DeleteCustRequestType command = new DeleteCustRequestType(custRequestTypeId);

		try {
			if (((CustRequestTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestType could not be deleted");

	}

}
