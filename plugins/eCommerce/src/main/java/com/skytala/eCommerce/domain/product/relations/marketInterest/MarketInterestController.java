package com.skytala.eCommerce.domain.product.relations.marketInterest;

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
import com.skytala.eCommerce.domain.product.relations.marketInterest.command.AddMarketInterest;
import com.skytala.eCommerce.domain.product.relations.marketInterest.command.DeleteMarketInterest;
import com.skytala.eCommerce.domain.product.relations.marketInterest.command.UpdateMarketInterest;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestAdded;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestDeleted;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestFound;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestUpdated;
import com.skytala.eCommerce.domain.product.relations.marketInterest.mapper.MarketInterestMapper;
import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
import com.skytala.eCommerce.domain.product.relations.marketInterest.query.FindMarketInterestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketInterests")
public class MarketInterestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketInterestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketInterest
	 * @return a List with the MarketInterests
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketInterestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketInterestsBy query = new FindMarketInterestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketInterest> marketInterests =((MarketInterestFound) Scheduler.execute(query).data()).getMarketInterests();

		if (marketInterests.size() == 1) {
			return ResponseEntity.ok().body(marketInterests.get(0));
		}

		return ResponseEntity.ok().body(marketInterests);

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
	public ResponseEntity<Object> createMarketInterest(HttpServletRequest request) throws Exception {

		MarketInterest marketInterestToBeAdded = new MarketInterest();
		try {
			marketInterestToBeAdded = MarketInterestMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMarketInterest(marketInterestToBeAdded);

	}

	/**
	 * creates a new MarketInterest entry in the ofbiz database
	 * 
	 * @param marketInterestToBeAdded
	 *            the MarketInterest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMarketInterest(@RequestBody MarketInterest marketInterestToBeAdded) throws Exception {

		AddMarketInterest command = new AddMarketInterest(marketInterestToBeAdded);
		MarketInterest marketInterest = ((MarketInterestAdded) Scheduler.execute(command).data()).getAddedMarketInterest();
		
		if (marketInterest != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketInterest);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketInterest could not be created.");
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
	public boolean updateMarketInterest(HttpServletRequest request) throws Exception {

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

		MarketInterest marketInterestToBeUpdated = new MarketInterest();

		try {
			marketInterestToBeUpdated = MarketInterestMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketInterest(marketInterestToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MarketInterest with the specific Id
	 * 
	 * @param marketInterestToBeUpdated
	 *            the MarketInterest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMarketInterest(@RequestBody MarketInterest marketInterestToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketInterestToBeUpdated.setnull(null);

		UpdateMarketInterest command = new UpdateMarketInterest(marketInterestToBeUpdated);

		try {
			if(((MarketInterestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketInterestId}")
	public ResponseEntity<Object> findById(@PathVariable String marketInterestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketInterestId", marketInterestId);
		try {

			Object foundMarketInterest = findMarketInterestsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketInterest);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketInterestId}")
	public ResponseEntity<Object> deleteMarketInterestByIdUpdated(@PathVariable String marketInterestId) throws Exception {
		DeleteMarketInterest command = new DeleteMarketInterest(marketInterestId);

		try {
			if (((MarketInterestDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketInterest could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/marketInterest/\" plus one of the following: "
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
