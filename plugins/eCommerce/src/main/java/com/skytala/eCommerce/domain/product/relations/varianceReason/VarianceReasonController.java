package com.skytala.eCommerce.domain.product.relations.varianceReason;

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
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.AddVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.DeleteVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.command.UpdateVarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonAdded;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonDeleted;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonFound;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonUpdated;
import com.skytala.eCommerce.domain.product.relations.varianceReason.mapper.VarianceReasonMapper;
import com.skytala.eCommerce.domain.product.relations.varianceReason.model.VarianceReason;
import com.skytala.eCommerce.domain.product.relations.varianceReason.query.FindVarianceReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/varianceReasons")
public class VarianceReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VarianceReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VarianceReason
	 * @return a List with the VarianceReasons
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findVarianceReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVarianceReasonsBy query = new FindVarianceReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VarianceReason> varianceReasons =((VarianceReasonFound) Scheduler.execute(query).data()).getVarianceReasons();

		if (varianceReasons.size() == 1) {
			return ResponseEntity.ok().body(varianceReasons.get(0));
		}

		return ResponseEntity.ok().body(varianceReasons);

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
	public ResponseEntity<Object> createVarianceReason(HttpServletRequest request) throws Exception {

		VarianceReason varianceReasonToBeAdded = new VarianceReason();
		try {
			varianceReasonToBeAdded = VarianceReasonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createVarianceReason(varianceReasonToBeAdded);

	}

	/**
	 * creates a new VarianceReason entry in the ofbiz database
	 * 
	 * @param varianceReasonToBeAdded
	 *            the VarianceReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createVarianceReason(@RequestBody VarianceReason varianceReasonToBeAdded) throws Exception {

		AddVarianceReason command = new AddVarianceReason(varianceReasonToBeAdded);
		VarianceReason varianceReason = ((VarianceReasonAdded) Scheduler.execute(command).data()).getAddedVarianceReason();
		
		if (varianceReason != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(varianceReason);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("VarianceReason could not be created.");
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
	public boolean updateVarianceReason(HttpServletRequest request) throws Exception {

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

		VarianceReason varianceReasonToBeUpdated = new VarianceReason();

		try {
			varianceReasonToBeUpdated = VarianceReasonMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateVarianceReason(varianceReasonToBeUpdated, varianceReasonToBeUpdated.getVarianceReasonId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the VarianceReason with the specific Id
	 * 
	 * @param varianceReasonToBeUpdated
	 *            the VarianceReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{varianceReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateVarianceReason(@RequestBody VarianceReason varianceReasonToBeUpdated,
			@PathVariable String varianceReasonId) throws Exception {

		varianceReasonToBeUpdated.setVarianceReasonId(varianceReasonId);

		UpdateVarianceReason command = new UpdateVarianceReason(varianceReasonToBeUpdated);

		try {
			if(((VarianceReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{varianceReasonId}")
	public ResponseEntity<Object> findById(@PathVariable String varianceReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("varianceReasonId", varianceReasonId);
		try {

			Object foundVarianceReason = findVarianceReasonsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundVarianceReason);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{varianceReasonId}")
	public ResponseEntity<Object> deleteVarianceReasonByIdUpdated(@PathVariable String varianceReasonId) throws Exception {
		DeleteVarianceReason command = new DeleteVarianceReason(varianceReasonId);

		try {
			if (((VarianceReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("VarianceReason could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/varianceReason/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
