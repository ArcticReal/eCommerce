package com.skytala.eCommerce.domain.accounting.relations.giftCard;

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
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.AddGiftCard;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.DeleteGiftCard;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.command.UpdateGiftCard;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardAdded;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardDeleted;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardFound;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardUpdated;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.GiftCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.query.FindGiftCardsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/giftCards")
public class GiftCardController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GiftCardController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GiftCard
	 * @return a List with the GiftCards
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGiftCardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGiftCardsBy query = new FindGiftCardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GiftCard> giftCards =((GiftCardFound) Scheduler.execute(query).data()).getGiftCards();

		if (giftCards.size() == 1) {
			return ResponseEntity.ok().body(giftCards.get(0));
		}

		return ResponseEntity.ok().body(giftCards);

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
	public ResponseEntity<Object> createGiftCard(HttpServletRequest request) throws Exception {

		GiftCard giftCardToBeAdded = new GiftCard();
		try {
			giftCardToBeAdded = GiftCardMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGiftCard(giftCardToBeAdded);

	}

	/**
	 * creates a new GiftCard entry in the ofbiz database
	 * 
	 * @param giftCardToBeAdded
	 *            the GiftCard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGiftCard(@RequestBody GiftCard giftCardToBeAdded) throws Exception {

		AddGiftCard command = new AddGiftCard(giftCardToBeAdded);
		GiftCard giftCard = ((GiftCardAdded) Scheduler.execute(command).data()).getAddedGiftCard();
		
		if (giftCard != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(giftCard);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GiftCard could not be created.");
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
	public boolean updateGiftCard(HttpServletRequest request) throws Exception {

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

		GiftCard giftCardToBeUpdated = new GiftCard();

		try {
			giftCardToBeUpdated = GiftCardMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGiftCard(giftCardToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GiftCard with the specific Id
	 * 
	 * @param giftCardToBeUpdated
	 *            the GiftCard thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGiftCard(@RequestBody GiftCard giftCardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		giftCardToBeUpdated.setnull(null);

		UpdateGiftCard command = new UpdateGiftCard(giftCardToBeUpdated);

		try {
			if(((GiftCardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{giftCardId}")
	public ResponseEntity<Object> findById(@PathVariable String giftCardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("giftCardId", giftCardId);
		try {

			Object foundGiftCard = findGiftCardsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGiftCard);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{giftCardId}")
	public ResponseEntity<Object> deleteGiftCardByIdUpdated(@PathVariable String giftCardId) throws Exception {
		DeleteGiftCard command = new DeleteGiftCard(giftCardId);

		try {
			if (((GiftCardDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GiftCard could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/giftCard/\" plus one of the following: "
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
