package com.skytala.eCommerce.domain.shipment.relations.rejectionReason;

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
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.AddRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.DeleteRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.UpdateRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonAdded;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonDeleted;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonFound;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonUpdated;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper.RejectionReasonMapper;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.query.FindRejectionReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/rejectionReasons")
public class RejectionReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RejectionReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RejectionReason
	 * @return a List with the RejectionReasons
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRejectionReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRejectionReasonsBy query = new FindRejectionReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RejectionReason> rejectionReasons =((RejectionReasonFound) Scheduler.execute(query).data()).getRejectionReasons();

		if (rejectionReasons.size() == 1) {
			return ResponseEntity.ok().body(rejectionReasons.get(0));
		}

		return ResponseEntity.ok().body(rejectionReasons);

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
	public ResponseEntity<Object> createRejectionReason(HttpServletRequest request) throws Exception {

		RejectionReason rejectionReasonToBeAdded = new RejectionReason();
		try {
			rejectionReasonToBeAdded = RejectionReasonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRejectionReason(rejectionReasonToBeAdded);

	}

	/**
	 * creates a new RejectionReason entry in the ofbiz database
	 * 
	 * @param rejectionReasonToBeAdded
	 *            the RejectionReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRejectionReason(@RequestBody RejectionReason rejectionReasonToBeAdded) throws Exception {

		AddRejectionReason command = new AddRejectionReason(rejectionReasonToBeAdded);
		RejectionReason rejectionReason = ((RejectionReasonAdded) Scheduler.execute(command).data()).getAddedRejectionReason();
		
		if (rejectionReason != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(rejectionReason);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RejectionReason could not be created.");
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
	public boolean updateRejectionReason(HttpServletRequest request) throws Exception {

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

		RejectionReason rejectionReasonToBeUpdated = new RejectionReason();

		try {
			rejectionReasonToBeUpdated = RejectionReasonMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRejectionReason(rejectionReasonToBeUpdated, rejectionReasonToBeUpdated.getRejectionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RejectionReason with the specific Id
	 * 
	 * @param rejectionReasonToBeUpdated
	 *            the RejectionReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{rejectionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRejectionReason(@RequestBody RejectionReason rejectionReasonToBeUpdated,
			@PathVariable String rejectionId) throws Exception {

		rejectionReasonToBeUpdated.setRejectionId(rejectionId);

		UpdateRejectionReason command = new UpdateRejectionReason(rejectionReasonToBeUpdated);

		try {
			if(((RejectionReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{rejectionReasonId}")
	public ResponseEntity<Object> findById(@PathVariable String rejectionReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rejectionReasonId", rejectionReasonId);
		try {

			Object foundRejectionReason = findRejectionReasonsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRejectionReason);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{rejectionReasonId}")
	public ResponseEntity<Object> deleteRejectionReasonByIdUpdated(@PathVariable String rejectionReasonId) throws Exception {
		DeleteRejectionReason command = new DeleteRejectionReason(rejectionReasonId);

		try {
			if (((RejectionReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RejectionReason could not be deleted");

	}

}
