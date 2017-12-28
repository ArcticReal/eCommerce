package com.skytala.eCommerce.domain.accounting.relations.creditCard;

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
import com.skytala.eCommerce.domain.accounting.relations.creditCard.command.AddCreditCard;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.command.DeleteCreditCard;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.command.UpdateCreditCard;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardAdded;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardDeleted;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardFound;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardUpdated;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.mapper.CreditCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.query.FindCreditCardsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/creditCards")
public class CreditCardController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CreditCardController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CreditCard
	 * @return a List with the CreditCards
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CreditCard>> findCreditCardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCreditCardsBy query = new FindCreditCardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CreditCard> creditCards =((CreditCardFound) Scheduler.execute(query).data()).getCreditCards();

		return ResponseEntity.ok().body(creditCards);

	}

	/**
	 * creates a new CreditCard entry in the ofbiz database
	 * 
	 * @param creditCardToBeAdded
	 *            the CreditCard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCardToBeAdded) throws Exception {

		AddCreditCard command = new AddCreditCard(creditCardToBeAdded);
		CreditCard creditCard = ((CreditCardAdded) Scheduler.execute(command).data()).getAddedCreditCard();
		
		if (creditCard != null) 
			return successful(creditCard);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CreditCard with the specific Id
	 * 
	 * @param creditCardToBeUpdated
	 *            the CreditCard thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCreditCard(@RequestBody CreditCard creditCardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		creditCardToBeUpdated.setnull(null);

		UpdateCreditCard command = new UpdateCreditCard(creditCardToBeUpdated);

		try {
			if(((CreditCardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{creditCardId}")
	public ResponseEntity<CreditCard> findById(@PathVariable String creditCardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("creditCardId", creditCardId);
		try {

			List<CreditCard> foundCreditCard = findCreditCardsBy(requestParams).getBody();
			if(foundCreditCard.size()==1){				return successful(foundCreditCard.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{creditCardId}")
	public ResponseEntity<String> deleteCreditCardByIdUpdated(@PathVariable String creditCardId) throws Exception {
		DeleteCreditCard command = new DeleteCreditCard(creditCardId);

		try {
			if (((CreditCardDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
