package com.skytala.eCommerce.domain.order.relations.custRequest.control.status;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.AddCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.DeleteCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.UpdateCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.status.CustRequestStatusMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.status.FindCustRequestStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/custRequest/custRequestStatuss")
public class CustRequestStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestStatus
	 * @return a List with the CustRequestStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestStatussBy query = new FindCustRequestStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestStatus> custRequestStatuss =((CustRequestStatusFound) Scheduler.execute(query).data()).getCustRequestStatuss();

		if (custRequestStatuss.size() == 1) {
			return ResponseEntity.ok().body(custRequestStatuss.get(0));
		}

		return ResponseEntity.ok().body(custRequestStatuss);

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
	public ResponseEntity<Object> createCustRequestStatus(HttpServletRequest request) throws Exception {

		CustRequestStatus custRequestStatusToBeAdded = new CustRequestStatus();
		try {
			custRequestStatusToBeAdded = CustRequestStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestStatus(custRequestStatusToBeAdded);

	}

	/**
	 * creates a new CustRequestStatus entry in the ofbiz database
	 * 
	 * @param custRequestStatusToBeAdded
	 *            the CustRequestStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestStatus(@RequestBody CustRequestStatus custRequestStatusToBeAdded) throws Exception {

		AddCustRequestStatus command = new AddCustRequestStatus(custRequestStatusToBeAdded);
		CustRequestStatus custRequestStatus = ((CustRequestStatusAdded) Scheduler.execute(command).data()).getAddedCustRequestStatus();
		
		if (custRequestStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestStatus could not be created.");
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
	public boolean updateCustRequestStatus(HttpServletRequest request) throws Exception {

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

		CustRequestStatus custRequestStatusToBeUpdated = new CustRequestStatus();

		try {
			custRequestStatusToBeUpdated = CustRequestStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestStatus(custRequestStatusToBeUpdated, custRequestStatusToBeUpdated.getCustRequestStatusId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestStatus with the specific Id
	 * 
	 * @param custRequestStatusToBeUpdated
	 *            the CustRequestStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestStatus(@RequestBody CustRequestStatus custRequestStatusToBeUpdated,
			@PathVariable String custRequestStatusId) throws Exception {

		custRequestStatusToBeUpdated.setCustRequestStatusId(custRequestStatusId);

		UpdateCustRequestStatus command = new UpdateCustRequestStatus(custRequestStatusToBeUpdated);

		try {
			if(((CustRequestStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestStatusId", custRequestStatusId);
		try {

			Object foundCustRequestStatus = findCustRequestStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestStatusId}")
	public ResponseEntity<Object> deleteCustRequestStatusByIdUpdated(@PathVariable String custRequestStatusId) throws Exception {
		DeleteCustRequestStatus command = new DeleteCustRequestStatus(custRequestStatusId);

		try {
			if (((CustRequestStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestStatus could not be deleted");

	}

}
