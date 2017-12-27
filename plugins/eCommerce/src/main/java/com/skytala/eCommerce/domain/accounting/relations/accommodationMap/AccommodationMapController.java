package com.skytala.eCommerce.domain.accounting.relations.accommodationMap;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.AddAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.DeleteAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.UpdateAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.AccommodationMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.AccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.query.FindAccommodationMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/accommodationMaps")
public class AccommodationMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationMap
	 * @return a List with the AccommodationMaps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AccommodationMap>> findAccommodationMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationMapsBy query = new FindAccommodationMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationMap> accommodationMaps =((AccommodationMapFound) Scheduler.execute(query).data()).getAccommodationMaps();

		return ResponseEntity.ok().body(accommodationMaps);

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
	public ResponseEntity<AccommodationMap> createAccommodationMap(HttpServletRequest request) throws Exception {

		AccommodationMap accommodationMapToBeAdded = new AccommodationMap();
		try {
			accommodationMapToBeAdded = AccommodationMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAccommodationMap(accommodationMapToBeAdded);

	}

	/**
	 * creates a new AccommodationMap entry in the ofbiz database
	 * 
	 * @param accommodationMapToBeAdded
	 *            the AccommodationMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccommodationMap> createAccommodationMap(@RequestBody AccommodationMap accommodationMapToBeAdded) throws Exception {

		AddAccommodationMap command = new AddAccommodationMap(accommodationMapToBeAdded);
		AccommodationMap accommodationMap = ((AccommodationMapAdded) Scheduler.execute(command).data()).getAddedAccommodationMap();
		
		if (accommodationMap != null) 
			return successful(accommodationMap);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AccommodationMap with the specific Id
	 * 
	 * @param accommodationMapToBeUpdated
	 *            the AccommodationMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationMapId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAccommodationMap(@RequestBody AccommodationMap accommodationMapToBeUpdated,
			@PathVariable String accommodationMapId) throws Exception {

		accommodationMapToBeUpdated.setAccommodationMapId(accommodationMapId);

		UpdateAccommodationMap command = new UpdateAccommodationMap(accommodationMapToBeUpdated);

		try {
			if(((AccommodationMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{accommodationMapId}")
	public ResponseEntity<AccommodationMap> findById(@PathVariable String accommodationMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationMapId", accommodationMapId);
		try {

			List<AccommodationMap> foundAccommodationMap = findAccommodationMapsBy(requestParams).getBody();
			if(foundAccommodationMap.size()==1){				return successful(foundAccommodationMap.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{accommodationMapId}")
	public ResponseEntity<String> deleteAccommodationMapByIdUpdated(@PathVariable String accommodationMapId) throws Exception {
		DeleteAccommodationMap command = new DeleteAccommodationMap(accommodationMapId);

		try {
			if (((AccommodationMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
