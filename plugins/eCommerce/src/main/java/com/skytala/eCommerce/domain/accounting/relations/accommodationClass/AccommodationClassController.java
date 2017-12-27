package com.skytala.eCommerce.domain.accounting.relations.accommodationClass;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.AddAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.DeleteAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.UpdateAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.mapper.AccommodationClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.query.FindAccommodationClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/accommodationClasss")
public class AccommodationClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationClass
	 * @return a List with the AccommodationClasss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AccommodationClass>> findAccommodationClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationClasssBy query = new FindAccommodationClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationClass> accommodationClasss =((AccommodationClassFound) Scheduler.execute(query).data()).getAccommodationClasss();

		return ResponseEntity.ok().body(accommodationClasss);

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
	public ResponseEntity<AccommodationClass> createAccommodationClass(HttpServletRequest request) throws Exception {

		AccommodationClass accommodationClassToBeAdded = new AccommodationClass();
		try {
			accommodationClassToBeAdded = AccommodationClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAccommodationClass(accommodationClassToBeAdded);

	}

	/**
	 * creates a new AccommodationClass entry in the ofbiz database
	 * 
	 * @param accommodationClassToBeAdded
	 *            the AccommodationClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AccommodationClass> createAccommodationClass(@RequestBody AccommodationClass accommodationClassToBeAdded) throws Exception {

		AddAccommodationClass command = new AddAccommodationClass(accommodationClassToBeAdded);
		AccommodationClass accommodationClass = ((AccommodationClassAdded) Scheduler.execute(command).data()).getAddedAccommodationClass();
		
		if (accommodationClass != null) 
			return successful(accommodationClass);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AccommodationClass with the specific Id
	 * 
	 * @param accommodationClassToBeUpdated
	 *            the AccommodationClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAccommodationClass(@RequestBody AccommodationClass accommodationClassToBeUpdated,
			@PathVariable String accommodationClassId) throws Exception {

		accommodationClassToBeUpdated.setAccommodationClassId(accommodationClassId);

		UpdateAccommodationClass command = new UpdateAccommodationClass(accommodationClassToBeUpdated);

		try {
			if(((AccommodationClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{accommodationClassId}")
	public ResponseEntity<AccommodationClass> findById(@PathVariable String accommodationClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationClassId", accommodationClassId);
		try {

			List<AccommodationClass> foundAccommodationClass = findAccommodationClasssBy(requestParams).getBody();
			if(foundAccommodationClass.size()==1){				return successful(foundAccommodationClass.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{accommodationClassId}")
	public ResponseEntity<String> deleteAccommodationClassByIdUpdated(@PathVariable String accommodationClassId) throws Exception {
		DeleteAccommodationClass command = new DeleteAccommodationClass(accommodationClassId);

		try {
			if (((AccommodationClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
