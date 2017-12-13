package com.skytala.eCommerce.domain.accounting.relations.accommodationSpot;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.command.AddAccommodationSpot;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.command.DeleteAccommodationSpot;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.command.UpdateAccommodationSpot;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event.AccommodationSpotAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event.AccommodationSpotDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event.AccommodationSpotFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event.AccommodationSpotUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.mapper.AccommodationSpotMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.model.AccommodationSpot;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.query.FindAccommodationSpotsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/accommodationSpots")
public class AccommodationSpotController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationSpotController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationSpot
	 * @return a List with the AccommodationSpots
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findAccommodationSpotsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationSpotsBy query = new FindAccommodationSpotsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationSpot> accommodationSpots =((AccommodationSpotFound) Scheduler.execute(query).data()).getAccommodationSpots();

		if (accommodationSpots.size() == 1) {
			return ResponseEntity.ok().body(accommodationSpots.get(0));
		}

		return ResponseEntity.ok().body(accommodationSpots);

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
	public ResponseEntity<Object> createAccommodationSpot(HttpServletRequest request) throws Exception {

		AccommodationSpot accommodationSpotToBeAdded = new AccommodationSpot();
		try {
			accommodationSpotToBeAdded = AccommodationSpotMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAccommodationSpot(accommodationSpotToBeAdded);

	}

	/**
	 * creates a new AccommodationSpot entry in the ofbiz database
	 * 
	 * @param accommodationSpotToBeAdded
	 *            the AccommodationSpot thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAccommodationSpot(@RequestBody AccommodationSpot accommodationSpotToBeAdded) throws Exception {

		AddAccommodationSpot command = new AddAccommodationSpot(accommodationSpotToBeAdded);
		AccommodationSpot accommodationSpot = ((AccommodationSpotAdded) Scheduler.execute(command).data()).getAddedAccommodationSpot();
		
		if (accommodationSpot != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(accommodationSpot);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AccommodationSpot could not be created.");
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
	public boolean updateAccommodationSpot(HttpServletRequest request) throws Exception {

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

		AccommodationSpot accommodationSpotToBeUpdated = new AccommodationSpot();

		try {
			accommodationSpotToBeUpdated = AccommodationSpotMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAccommodationSpot(accommodationSpotToBeUpdated, accommodationSpotToBeUpdated.getAccommodationSpotId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AccommodationSpot with the specific Id
	 * 
	 * @param accommodationSpotToBeUpdated
	 *            the AccommodationSpot thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationSpotId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAccommodationSpot(@RequestBody AccommodationSpot accommodationSpotToBeUpdated,
			@PathVariable String accommodationSpotId) throws Exception {

		accommodationSpotToBeUpdated.setAccommodationSpotId(accommodationSpotId);

		UpdateAccommodationSpot command = new UpdateAccommodationSpot(accommodationSpotToBeUpdated);

		try {
			if(((AccommodationSpotUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{accommodationSpotId}")
	public ResponseEntity<Object> findById(@PathVariable String accommodationSpotId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationSpotId", accommodationSpotId);
		try {

			Object foundAccommodationSpot = findAccommodationSpotsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAccommodationSpot);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{accommodationSpotId}")
	public ResponseEntity<Object> deleteAccommodationSpotByIdUpdated(@PathVariable String accommodationSpotId) throws Exception {
		DeleteAccommodationSpot command = new DeleteAccommodationSpot(accommodationSpotId);

		try {
			if (((AccommodationSpotDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AccommodationSpot could not be deleted");

	}

}
