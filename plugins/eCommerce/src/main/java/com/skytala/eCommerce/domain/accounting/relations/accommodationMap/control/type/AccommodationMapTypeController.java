package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.type.AddAccommodationMapType;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.type.DeleteAccommodationMapType;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.type.UpdateAccommodationMapType;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type.AccommodationMapTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type.AccommodationMapTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type.AccommodationMapTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type.AccommodationMapTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.type.AccommodationMapTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type.AccommodationMapType;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.query.type.FindAccommodationMapTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/accommodationMap/accommodationMapTypes")
public class AccommodationMapTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationMapTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationMapType
	 * @return a List with the AccommodationMapTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AccommodationMapType>> findAccommodationMapTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationMapTypesBy query = new FindAccommodationMapTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationMapType> accommodationMapTypes =((AccommodationMapTypeFound) Scheduler.execute(query).data()).getAccommodationMapTypes();

		return ResponseEntity.ok().body(accommodationMapTypes);

	}

	/**
	 * creates a new AccommodationMapType entry in the ofbiz database
	 * 
	 * @param accommodationMapTypeToBeAdded
	 *            the AccommodationMapType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccommodationMapType> createAccommodationMapType(@RequestBody AccommodationMapType accommodationMapTypeToBeAdded) throws Exception {

		AddAccommodationMapType command = new AddAccommodationMapType(accommodationMapTypeToBeAdded);
		AccommodationMapType accommodationMapType = ((AccommodationMapTypeAdded) Scheduler.execute(command).data()).getAddedAccommodationMapType();
		
		if (accommodationMapType != null) 
			return successful(accommodationMapType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AccommodationMapType with the specific Id
	 * 
	 * @param accommodationMapTypeToBeUpdated
	 *            the AccommodationMapType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationMapTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAccommodationMapType(@RequestBody AccommodationMapType accommodationMapTypeToBeUpdated,
			@PathVariable String accommodationMapTypeId) throws Exception {

		accommodationMapTypeToBeUpdated.setAccommodationMapTypeId(accommodationMapTypeId);

		UpdateAccommodationMapType command = new UpdateAccommodationMapType(accommodationMapTypeToBeUpdated);

		try {
			if(((AccommodationMapTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{accommodationMapTypeId}")
	public ResponseEntity<AccommodationMapType> findById(@PathVariable String accommodationMapTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationMapTypeId", accommodationMapTypeId);
		try {

			List<AccommodationMapType> foundAccommodationMapType = findAccommodationMapTypesBy(requestParams).getBody();
			if(foundAccommodationMapType.size()==1){				return successful(foundAccommodationMapType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{accommodationMapTypeId}")
	public ResponseEntity<String> deleteAccommodationMapTypeByIdUpdated(@PathVariable String accommodationMapTypeId) throws Exception {
		DeleteAccommodationMapType command = new DeleteAccommodationMapType(accommodationMapTypeId);

		try {
			if (((AccommodationMapTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
