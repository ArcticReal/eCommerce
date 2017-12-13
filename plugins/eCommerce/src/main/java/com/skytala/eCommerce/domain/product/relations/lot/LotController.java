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
	public ResponseEntity<Object> findLotsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindLotsBy query = new FindLotsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Lot> lots =((LotFound) Scheduler.execute(query).data()).getLots();

		if (lots.size() == 1) {
			return ResponseEntity.ok().body(lots.get(0));
		}

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
	public ResponseEntity<Object> createLot(HttpServletRequest request) throws Exception {

		Lot lotToBeAdded = new Lot();
		try {
			lotToBeAdded = LotMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createLot(@RequestBody Lot lotToBeAdded) throws Exception {

		AddLot command = new AddLot(lotToBeAdded);
		Lot lot = ((LotAdded) Scheduler.execute(command).data()).getAddedLot();
		
		if (lot != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(lot);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Lot could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateLot(HttpServletRequest request) throws Exception {

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

		Lot lotToBeUpdated = new Lot();

		try {
			lotToBeUpdated = LotMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateLot(lotToBeUpdated, lotToBeUpdated.getLotId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateLot(@RequestBody Lot lotToBeUpdated,
			@PathVariable String lotId) throws Exception {

		lotToBeUpdated.setLotId(lotId);

		UpdateLot command = new UpdateLot(lotToBeUpdated);

		try {
			if(((LotUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{lotId}")
	public ResponseEntity<Object> findById(@PathVariable String lotId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("lotId", lotId);
		try {

			Object foundLot = findLotsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundLot);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{lotId}")
	public ResponseEntity<Object> deleteLotByIdUpdated(@PathVariable String lotId) throws Exception {
		DeleteLot command = new DeleteLot(lotId);

		try {
			if (((LotDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Lot could not be deleted");

	}

}
