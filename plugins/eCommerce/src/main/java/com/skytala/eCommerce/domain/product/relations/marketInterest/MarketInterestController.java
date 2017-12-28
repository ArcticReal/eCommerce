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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/marketInterests")
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
	@GetMapping("/find")
	public ResponseEntity<List<MarketInterest>> findMarketInterestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketInterestsBy query = new FindMarketInterestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketInterest> marketInterests =((MarketInterestFound) Scheduler.execute(query).data()).getMarketInterests();

		return ResponseEntity.ok().body(marketInterests);

	}

	/**
	 * creates a new MarketInterest entry in the ofbiz database
	 * 
	 * @param marketInterestToBeAdded
	 *            the MarketInterest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MarketInterest> createMarketInterest(@RequestBody MarketInterest marketInterestToBeAdded) throws Exception {

		AddMarketInterest command = new AddMarketInterest(marketInterestToBeAdded);
		MarketInterest marketInterest = ((MarketInterestAdded) Scheduler.execute(command).data()).getAddedMarketInterest();
		
		if (marketInterest != null) 
			return successful(marketInterest);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateMarketInterest(@RequestBody MarketInterest marketInterestToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketInterestToBeUpdated.setnull(null);

		UpdateMarketInterest command = new UpdateMarketInterest(marketInterestToBeUpdated);

		try {
			if(((MarketInterestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketInterestId}")
	public ResponseEntity<MarketInterest> findById(@PathVariable String marketInterestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketInterestId", marketInterestId);
		try {

			List<MarketInterest> foundMarketInterest = findMarketInterestsBy(requestParams).getBody();
			if(foundMarketInterest.size()==1){				return successful(foundMarketInterest.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketInterestId}")
	public ResponseEntity<String> deleteMarketInterestByIdUpdated(@PathVariable String marketInterestId) throws Exception {
		DeleteMarketInterest command = new DeleteMarketInterest(marketInterestId);

		try {
			if (((MarketInterestDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
