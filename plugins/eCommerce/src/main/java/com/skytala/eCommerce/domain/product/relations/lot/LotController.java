package com.skytala.eCommerce.domain.product.relations.lot;

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
import com.skytala.eCommerce.domain.product.relations.lot.command.AddLot;
import com.skytala.eCommerce.domain.product.relations.lot.command.DeleteLot;
import com.skytala.eCommerce.domain.product.relations.lot.command.UpdateLot;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotAdded;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotDeleted;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotFound;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotUpdated;
import com.skytala.eCommerce.domain.product.relations.lot.mapper.LotMapper;
import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;
import com.skytala.eCommerce.domain.product.relations.lot.query.FindLotsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/lots")
public class LotController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public LotController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Lot
	 * @return a List with the Lots
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Lot>> findLotsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindLotsBy query = new FindLotsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Lot> lots =((LotFound) Scheduler.execute(query).data()).getLots();

		return ResponseEntity.ok().body(lots);

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
	public ResponseEntity<Lot> createLot(HttpServletRequest request) throws Exception {

		Lot lotToBeAdded = new Lot();
		try {
			lotToBeAdded = LotMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createLot(lotToBeAdded);

	}

	/**
	 * creates a new Lot entry in the ofbiz database
	 * 
	 * @param lotToBeAdded
	 *            the Lot thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Lot> createLot(@RequestBody Lot lotToBeAdded) throws Exception {

		AddLot command = new AddLot(lotToBeAdded);
		Lot lot = ((LotAdded) Scheduler.execute(command).data()).getAddedLot();
		
		if (lot != null) 
			return successful(lot);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Lot with the specific Id
	 * 
	 * @param lotToBeUpdated
	 *            the Lot thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{lotId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateLot(@RequestBody Lot lotToBeUpdated,
			@PathVariable String lotId) throws Exception {

		lotToBeUpdated.setLotId(lotId);

		UpdateLot command = new UpdateLot(lotToBeUpdated);

		try {
			if(((LotUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{lotId}")
	public ResponseEntity<Lot> findById(@PathVariable String lotId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("lotId", lotId);
		try {

			List<Lot> foundLot = findLotsBy(requestParams).getBody();
			if(foundLot.size()==1){				return successful(foundLot.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{lotId}")
	public ResponseEntity<String> deleteLotByIdUpdated(@PathVariable String lotId) throws Exception {
		DeleteLot command = new DeleteLot(lotId);

		try {
			if (((LotDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
