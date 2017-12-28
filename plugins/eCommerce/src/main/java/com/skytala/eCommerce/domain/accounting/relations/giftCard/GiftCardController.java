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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/giftCards")
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
	@GetMapping("/find")
	public ResponseEntity<List<GiftCard>> findGiftCardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGiftCardsBy query = new FindGiftCardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GiftCard> giftCards =((GiftCardFound) Scheduler.execute(query).data()).getGiftCards();

		return ResponseEntity.ok().body(giftCards);

	}

	/**
	 * creates a new GiftCard entry in the ofbiz database
	 * 
	 * @param giftCardToBeAdded
	 *            the GiftCard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GiftCard> createGiftCard(@RequestBody GiftCard giftCardToBeAdded) throws Exception {

		AddGiftCard command = new AddGiftCard(giftCardToBeAdded);
		GiftCard giftCard = ((GiftCardAdded) Scheduler.execute(command).data()).getAddedGiftCard();
		
		if (giftCard != null) 
			return successful(giftCard);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGiftCard(@RequestBody GiftCard giftCardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		giftCardToBeUpdated.setnull(null);

		UpdateGiftCard command = new UpdateGiftCard(giftCardToBeUpdated);

		try {
			if(((GiftCardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{giftCardId}")
	public ResponseEntity<GiftCard> findById(@PathVariable String giftCardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("giftCardId", giftCardId);
		try {

			List<GiftCard> foundGiftCard = findGiftCardsBy(requestParams).getBody();
			if(foundGiftCard.size()==1){				return successful(foundGiftCard.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{giftCardId}")
	public ResponseEntity<String> deleteGiftCardByIdUpdated(@PathVariable String giftCardId) throws Exception {
		DeleteGiftCard command = new DeleteGiftCard(giftCardId);

		try {
			if (((GiftCardDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
