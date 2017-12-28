package com.skytala.eCommerce.domain.product.relations.subscription.control.fulfillmentPiece;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.fulfillmentPiece.AddSubscriptionFulfillmentPiece;
import com.skytala.eCommerce.domain.product.relations.subscription.command.fulfillmentPiece.DeleteSubscriptionFulfillmentPiece;
import com.skytala.eCommerce.domain.product.relations.subscription.command.fulfillmentPiece.UpdateSubscriptionFulfillmentPiece;
import com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece.SubscriptionFulfillmentPieceAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece.SubscriptionFulfillmentPieceDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece.SubscriptionFulfillmentPieceFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece.SubscriptionFulfillmentPieceUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.fulfillmentPiece.SubscriptionFulfillmentPieceMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.fulfillmentPiece.SubscriptionFulfillmentPiece;
import com.skytala.eCommerce.domain.product.relations.subscription.query.fulfillmentPiece.FindSubscriptionFulfillmentPiecesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionFulfillmentPieces")
public class SubscriptionFulfillmentPieceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionFulfillmentPieceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionFulfillmentPiece
	 * @return a List with the SubscriptionFulfillmentPieces
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionFulfillmentPiece>> findSubscriptionFulfillmentPiecesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionFulfillmentPiecesBy query = new FindSubscriptionFulfillmentPiecesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionFulfillmentPiece> subscriptionFulfillmentPieces =((SubscriptionFulfillmentPieceFound) Scheduler.execute(query).data()).getSubscriptionFulfillmentPieces();

		return ResponseEntity.ok().body(subscriptionFulfillmentPieces);

	}

	/**
	 * creates a new SubscriptionFulfillmentPiece entry in the ofbiz database
	 * 
	 * @param subscriptionFulfillmentPieceToBeAdded
	 *            the SubscriptionFulfillmentPiece thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SubscriptionFulfillmentPiece> createSubscriptionFulfillmentPiece(@RequestBody SubscriptionFulfillmentPiece subscriptionFulfillmentPieceToBeAdded) throws Exception {

		AddSubscriptionFulfillmentPiece command = new AddSubscriptionFulfillmentPiece(subscriptionFulfillmentPieceToBeAdded);
		SubscriptionFulfillmentPiece subscriptionFulfillmentPiece = ((SubscriptionFulfillmentPieceAdded) Scheduler.execute(command).data()).getAddedSubscriptionFulfillmentPiece();
		
		if (subscriptionFulfillmentPiece != null) 
			return successful(subscriptionFulfillmentPiece);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SubscriptionFulfillmentPiece with the specific Id
	 * 
	 * @param subscriptionFulfillmentPieceToBeUpdated
	 *            the SubscriptionFulfillmentPiece thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSubscriptionFulfillmentPiece(@RequestBody SubscriptionFulfillmentPiece subscriptionFulfillmentPieceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		subscriptionFulfillmentPieceToBeUpdated.setnull(null);

		UpdateSubscriptionFulfillmentPiece command = new UpdateSubscriptionFulfillmentPiece(subscriptionFulfillmentPieceToBeUpdated);

		try {
			if(((SubscriptionFulfillmentPieceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionFulfillmentPieceId}")
	public ResponseEntity<SubscriptionFulfillmentPiece> findById(@PathVariable String subscriptionFulfillmentPieceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionFulfillmentPieceId", subscriptionFulfillmentPieceId);
		try {

			List<SubscriptionFulfillmentPiece> foundSubscriptionFulfillmentPiece = findSubscriptionFulfillmentPiecesBy(requestParams).getBody();
			if(foundSubscriptionFulfillmentPiece.size()==1){				return successful(foundSubscriptionFulfillmentPiece.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionFulfillmentPieceId}")
	public ResponseEntity<String> deleteSubscriptionFulfillmentPieceByIdUpdated(@PathVariable String subscriptionFulfillmentPieceId) throws Exception {
		DeleteSubscriptionFulfillmentPiece command = new DeleteSubscriptionFulfillmentPiece(subscriptionFulfillmentPieceId);

		try {
			if (((SubscriptionFulfillmentPieceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
