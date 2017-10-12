package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.command.AddFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.command.DeleteFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.command.UpdateFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.query.FindFixedAssetStdCostsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetStdCosts")
public class FixedAssetStdCostController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetStdCostController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetStdCost
	 * @return a List with the FixedAssetStdCosts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetStdCostsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetStdCostsBy query = new FindFixedAssetStdCostsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetStdCost> fixedAssetStdCosts =((FixedAssetStdCostFound) Scheduler.execute(query).data()).getFixedAssetStdCosts();

		if (fixedAssetStdCosts.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetStdCosts.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetStdCosts);

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
	public ResponseEntity<Object> createFixedAssetStdCost(HttpServletRequest request) throws Exception {

		FixedAssetStdCost fixedAssetStdCostToBeAdded = new FixedAssetStdCost();
		try {
			fixedAssetStdCostToBeAdded = FixedAssetStdCostMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetStdCost(fixedAssetStdCostToBeAdded);

	}

	/**
	 * creates a new FixedAssetStdCost entry in the ofbiz database
	 * 
	 * @param fixedAssetStdCostToBeAdded
	 *            the FixedAssetStdCost thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetStdCost(@RequestBody FixedAssetStdCost fixedAssetStdCostToBeAdded) throws Exception {

		AddFixedAssetStdCost command = new AddFixedAssetStdCost(fixedAssetStdCostToBeAdded);
		FixedAssetStdCost fixedAssetStdCost = ((FixedAssetStdCostAdded) Scheduler.execute(command).data()).getAddedFixedAssetStdCost();
		
		if (fixedAssetStdCost != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetStdCost);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetStdCost could not be created.");
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
	public boolean updateFixedAssetStdCost(HttpServletRequest request) throws Exception {

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

		FixedAssetStdCost fixedAssetStdCostToBeUpdated = new FixedAssetStdCost();

		try {
			fixedAssetStdCostToBeUpdated = FixedAssetStdCostMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetStdCost(fixedAssetStdCostToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetStdCost with the specific Id
	 * 
	 * @param fixedAssetStdCostToBeUpdated
	 *            the FixedAssetStdCost thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetStdCost(@RequestBody FixedAssetStdCost fixedAssetStdCostToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetStdCostToBeUpdated.setnull(null);

		UpdateFixedAssetStdCost command = new UpdateFixedAssetStdCost(fixedAssetStdCostToBeUpdated);

		try {
			if(((FixedAssetStdCostUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetStdCostId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetStdCostId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetStdCostId", fixedAssetStdCostId);
		try {

			Object foundFixedAssetStdCost = findFixedAssetStdCostsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetStdCost);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetStdCostId}")
	public ResponseEntity<Object> deleteFixedAssetStdCostByIdUpdated(@PathVariable String fixedAssetStdCostId) throws Exception {
		DeleteFixedAssetStdCost command = new DeleteFixedAssetStdCost(fixedAssetStdCostId);

		try {
			if (((FixedAssetStdCostDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetStdCost could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fixedAssetStdCost/\" plus one of the following: "
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
