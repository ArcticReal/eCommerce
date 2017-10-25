package com.skytala.eCommerce.domain.accounting.relations.giftCard.control.fulfillment;

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
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.fulfillment.AddGiftCardFulfillment;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.fulfillment.DeleteGiftCardFulfillment;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.fulfillment.UpdateGiftCardFulfillment;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentAdded;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentFound;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.fulfillment.GiftCardFulfillmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.query.fulfillment.FindGiftCardFulfillmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/giftCardFulfillments")
public class GiftCardFulfillmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GiftCardFulfillmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GiftCardFulfillment
	 * @return a List with the GiftCardFulfillments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGiftCardFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGiftCardFulfillmentsBy query = new FindGiftCardFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GiftCardFulfillment> giftCardFulfillments =((GiftCardFulfillmentFound) Scheduler.execute(query).data()).getGiftCardFulfillments();

		if (giftCardFulfillments.size() == 1) {
			return ResponseEntity.ok().body(giftCardFulfillments.get(0));
		}

		return ResponseEntity.ok().body(giftCardFulfillments);

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
	public ResponseEntity<Object> createGiftCardFulfillment(HttpServletRequest request) throws Exception {

		GiftCardFulfillment giftCardFulfillmentToBeAdded = new GiftCardFulfillment();
		try {
			giftCardFulfillmentToBeAdded = GiftCardFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGiftCardFulfillment(giftCardFulfillmentToBeAdded);

	}

	/**
	 * creates a new GiftCardFulfillment entry in the ofbiz database
	 * 
	 * @param giftCardFulfillmentToBeAdded
	 *            the GiftCardFulfillment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGiftCardFulfillment(@RequestBody GiftCardFulfillment giftCardFulfillmentToBeAdded) throws Exception {

		AddGiftCardFulfillment command = new AddGiftCardFulfillment(giftCardFulfillmentToBeAdded);
		GiftCardFulfillment giftCardFulfillment = ((GiftCardFulfillmentAdded) Scheduler.execute(command).data()).getAddedGiftCardFulfillment();
		
		if (giftCardFulfillment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(giftCardFulfillment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GiftCardFulfillment could not be created.");
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
	public boolean updateGiftCardFulfillment(HttpServletRequest request) throws Exception {

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

		GiftCardFulfillment giftCardFulfillmentToBeUpdated = new GiftCardFulfillment();

		try {
			giftCardFulfillmentToBeUpdated = GiftCardFulfillmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGiftCardFulfillment(giftCardFulfillmentToBeUpdated, giftCardFulfillmentToBeUpdated.getFulfillmentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GiftCardFulfillment with the specific Id
	 * 
	 * @param giftCardFulfillmentToBeUpdated
	 *            the GiftCardFulfillment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fulfillmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGiftCardFulfillment(@RequestBody GiftCardFulfillment giftCardFulfillmentToBeUpdated,
			@PathVariable String fulfillmentId) throws Exception {

		giftCardFulfillmentToBeUpdated.setFulfillmentId(fulfillmentId);

		UpdateGiftCardFulfillment command = new UpdateGiftCardFulfillment(giftCardFulfillmentToBeUpdated);

		try {
			if(((GiftCardFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{giftCardFulfillmentId}")
	public ResponseEntity<Object> findById(@PathVariable String giftCardFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("giftCardFulfillmentId", giftCardFulfillmentId);
		try {

			Object foundGiftCardFulfillment = findGiftCardFulfillmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGiftCardFulfillment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{giftCardFulfillmentId}")
	public ResponseEntity<Object> deleteGiftCardFulfillmentByIdUpdated(@PathVariable String giftCardFulfillmentId) throws Exception {
		DeleteGiftCardFulfillment command = new DeleteGiftCardFulfillment(giftCardFulfillmentId);

		try {
			if (((GiftCardFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GiftCardFulfillment could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/giftCardFulfillment/\" plus one of the following: "
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
