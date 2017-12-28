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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<AccommodationSpot>> findAccommodationSpotsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationSpotsBy query = new FindAccommodationSpotsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationSpot> accommodationSpots =((AccommodationSpotFound) Scheduler.execute(query).data()).getAccommodationSpots();

		return ResponseEntity.ok().body(accommodationSpots);

	}

	/**
	 * creates a new AccommodationSpot entry in the ofbiz database
	 * 
	 * @param accommodationSpotToBeAdded
	 *            the AccommodationSpot thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccommodationSpot> createAccommodationSpot(@RequestBody AccommodationSpot accommodationSpotToBeAdded) throws Exception {

		AddAccommodationSpot command = new AddAccommodationSpot(accommodationSpotToBeAdded);
		AccommodationSpot accommodationSpot = ((AccommodationSpotAdded) Scheduler.execute(command).data()).getAddedAccommodationSpot();
		
		if (accommodationSpot != null) 
			return successful(accommodationSpot);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAccommodationSpot(@RequestBody AccommodationSpot accommodationSpotToBeUpdated,
			@PathVariable String accommodationSpotId) throws Exception {

		accommodationSpotToBeUpdated.setAccommodationSpotId(accommodationSpotId);

		UpdateAccommodationSpot command = new UpdateAccommodationSpot(accommodationSpotToBeUpdated);

		try {
			if(((AccommodationSpotUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{accommodationSpotId}")
	public ResponseEntity<AccommodationSpot> findById(@PathVariable String accommodationSpotId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationSpotId", accommodationSpotId);
		try {

			List<AccommodationSpot> foundAccommodationSpot = findAccommodationSpotsBy(requestParams).getBody();
			if(foundAccommodationSpot.size()==1){				return successful(foundAccommodationSpot.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{accommodationSpotId}")
	public ResponseEntity<String> deleteAccommodationSpotByIdUpdated(@PathVariable String accommodationSpotId) throws Exception {
		DeleteAccommodationSpot command = new DeleteAccommodationSpot(accommodationSpotId);

		try {
			if (((AccommodationSpotDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
