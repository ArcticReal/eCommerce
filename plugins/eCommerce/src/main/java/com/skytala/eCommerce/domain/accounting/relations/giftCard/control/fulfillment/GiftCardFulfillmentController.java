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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/giftCard/giftCardFulfillments")
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
	@GetMapping("/find")
	public ResponseEntity<List<GiftCardFulfillment>> findGiftCardFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGiftCardFulfillmentsBy query = new FindGiftCardFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GiftCardFulfillment> giftCardFulfillments =((GiftCardFulfillmentFound) Scheduler.execute(query).data()).getGiftCardFulfillments();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<GiftCardFulfillment> createGiftCardFulfillment(HttpServletRequest request) throws Exception {

		GiftCardFulfillment giftCardFulfillmentToBeAdded = new GiftCardFulfillment();
		try {
			giftCardFulfillmentToBeAdded = GiftCardFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<GiftCardFulfillment> createGiftCardFulfillment(@RequestBody GiftCardFulfillment giftCardFulfillmentToBeAdded) throws Exception {

		AddGiftCardFulfillment command = new AddGiftCardFulfillment(giftCardFulfillmentToBeAdded);
		GiftCardFulfillment giftCardFulfillment = ((GiftCardFulfillmentAdded) Scheduler.execute(command).data()).getAddedGiftCardFulfillment();
		
		if (giftCardFulfillment != null) 
			return successful(giftCardFulfillment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGiftCardFulfillment(@RequestBody GiftCardFulfillment giftCardFulfillmentToBeUpdated,
			@PathVariable String fulfillmentId) throws Exception {

		giftCardFulfillmentToBeUpdated.setFulfillmentId(fulfillmentId);

		UpdateGiftCardFulfillment command = new UpdateGiftCardFulfillment(giftCardFulfillmentToBeUpdated);

		try {
			if(((GiftCardFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{giftCardFulfillmentId}")
	public ResponseEntity<GiftCardFulfillment> findById(@PathVariable String giftCardFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("giftCardFulfillmentId", giftCardFulfillmentId);
		try {

			List<GiftCardFulfillment> foundGiftCardFulfillment = findGiftCardFulfillmentsBy(requestParams).getBody();
			if(foundGiftCardFulfillment.size()==1){				return successful(foundGiftCardFulfillment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{giftCardFulfillmentId}")
	public ResponseEntity<String> deleteGiftCardFulfillmentByIdUpdated(@PathVariable String giftCardFulfillmentId) throws Exception {
		DeleteGiftCardFulfillment command = new DeleteGiftCardFulfillment(giftCardFulfillmentId);

		try {
			if (((GiftCardFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
