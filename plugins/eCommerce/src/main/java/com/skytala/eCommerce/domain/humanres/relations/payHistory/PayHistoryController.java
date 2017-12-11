package com.skytala.eCommerce.domain.humanres.relations.payHistory;

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
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.AddPayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.DeletePayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.UpdatePayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryAdded;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryDeleted;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryFound;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryUpdated;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper.PayHistoryMapper;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.query.FindPayHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/payHistorys")
public class PayHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayHistory
	 * @return a List with the PayHistorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPayHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayHistorysBy query = new FindPayHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayHistory> payHistorys =((PayHistoryFound) Scheduler.execute(query).data()).getPayHistorys();

		if (payHistorys.size() == 1) {
			return ResponseEntity.ok().body(payHistorys.get(0));
		}

		return ResponseEntity.ok().body(payHistorys);

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
	public ResponseEntity<Object> createPayHistory(HttpServletRequest request) throws Exception {

		PayHistory payHistoryToBeAdded = new PayHistory();
		try {
			payHistoryToBeAdded = PayHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPayHistory(payHistoryToBeAdded);

	}

	/**
	 * creates a new PayHistory entry in the ofbiz database
	 * 
	 * @param payHistoryToBeAdded
	 *            the PayHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPayHistory(@RequestBody PayHistory payHistoryToBeAdded) throws Exception {

		AddPayHistory command = new AddPayHistory(payHistoryToBeAdded);
		PayHistory payHistory = ((PayHistoryAdded) Scheduler.execute(command).data()).getAddedPayHistory();
		
		if (payHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(payHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PayHistory could not be created.");
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
	public boolean updatePayHistory(HttpServletRequest request) throws Exception {

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

		PayHistory payHistoryToBeUpdated = new PayHistory();

		try {
			payHistoryToBeUpdated = PayHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePayHistory(payHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PayHistory with the specific Id
	 * 
	 * @param payHistoryToBeUpdated
	 *            the PayHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePayHistory(@RequestBody PayHistory payHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payHistoryToBeUpdated.setnull(null);

		UpdatePayHistory command = new UpdatePayHistory(payHistoryToBeUpdated);

		try {
			if(((PayHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{payHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String payHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payHistoryId", payHistoryId);
		try {

			Object foundPayHistory = findPayHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPayHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{payHistoryId}")
	public ResponseEntity<Object> deletePayHistoryByIdUpdated(@PathVariable String payHistoryId) throws Exception {
		DeletePayHistory command = new DeletePayHistory(payHistoryId);

		try {
			if (((PayHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PayHistory could not be deleted");

	}

}
