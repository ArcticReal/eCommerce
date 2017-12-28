package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.stdCost;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.stdCost.AddFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.stdCost.DeleteFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.stdCost.UpdateFixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost.FixedAssetStdCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost.FixedAssetStdCostDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost.FixedAssetStdCostFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost.FixedAssetStdCostUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.stdCost.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCost.FixedAssetStdCost;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.stdCost.FindFixedAssetStdCostsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetStdCosts")
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
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetStdCost>> findFixedAssetStdCostsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetStdCostsBy query = new FindFixedAssetStdCostsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetStdCost> fixedAssetStdCosts =((FixedAssetStdCostFound) Scheduler.execute(query).data()).getFixedAssetStdCosts();

		return ResponseEntity.ok().body(fixedAssetStdCosts);

	}

	/**
	 * creates a new FixedAssetStdCost entry in the ofbiz database
	 * 
	 * @param fixedAssetStdCostToBeAdded
	 *            the FixedAssetStdCost thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetStdCost> createFixedAssetStdCost(@RequestBody FixedAssetStdCost fixedAssetStdCostToBeAdded) throws Exception {

		AddFixedAssetStdCost command = new AddFixedAssetStdCost(fixedAssetStdCostToBeAdded);
		FixedAssetStdCost fixedAssetStdCost = ((FixedAssetStdCostAdded) Scheduler.execute(command).data()).getAddedFixedAssetStdCost();
		
		if (fixedAssetStdCost != null) 
			return successful(fixedAssetStdCost);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFixedAssetStdCost(@RequestBody FixedAssetStdCost fixedAssetStdCostToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetStdCostToBeUpdated.setnull(null);

		UpdateFixedAssetStdCost command = new UpdateFixedAssetStdCost(fixedAssetStdCostToBeUpdated);

		try {
			if(((FixedAssetStdCostUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetStdCostId}")
	public ResponseEntity<FixedAssetStdCost> findById(@PathVariable String fixedAssetStdCostId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetStdCostId", fixedAssetStdCostId);
		try {

			List<FixedAssetStdCost> foundFixedAssetStdCost = findFixedAssetStdCostsBy(requestParams).getBody();
			if(foundFixedAssetStdCost.size()==1){				return successful(foundFixedAssetStdCost.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetStdCostId}")
	public ResponseEntity<String> deleteFixedAssetStdCostByIdUpdated(@PathVariable String fixedAssetStdCostId) throws Exception {
		DeleteFixedAssetStdCost command = new DeleteFixedAssetStdCost(fixedAssetStdCostId);

		try {
			if (((FixedAssetStdCostDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
