package com.skytala.eCommerce.domain.humanres.relations.perfRatingType;

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
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.command.AddPerfRatingType;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.command.DeletePerfRatingType;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.command.UpdatePerfRatingType;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event.PerfRatingTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event.PerfRatingTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event.PerfRatingTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event.PerfRatingTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.mapper.PerfRatingTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.query.FindPerfRatingTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/perfRatingTypes")
public class PerfRatingTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerfRatingTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerfRatingType
	 * @return a List with the PerfRatingTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PerfRatingType>> findPerfRatingTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfRatingTypesBy query = new FindPerfRatingTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfRatingType> perfRatingTypes =((PerfRatingTypeFound) Scheduler.execute(query).data()).getPerfRatingTypes();

		return ResponseEntity.ok().body(perfRatingTypes);

	}

	/**
	 * creates a new PerfRatingType entry in the ofbiz database
	 * 
	 * @param perfRatingTypeToBeAdded
	 *            the PerfRatingType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PerfRatingType> createPerfRatingType(@RequestBody PerfRatingType perfRatingTypeToBeAdded) throws Exception {

		AddPerfRatingType command = new AddPerfRatingType(perfRatingTypeToBeAdded);
		PerfRatingType perfRatingType = ((PerfRatingTypeAdded) Scheduler.execute(command).data()).getAddedPerfRatingType();
		
		if (perfRatingType != null) 
			return successful(perfRatingType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PerfRatingType with the specific Id
	 * 
	 * @param perfRatingTypeToBeUpdated
	 *            the PerfRatingType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{perfRatingTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePerfRatingType(@RequestBody PerfRatingType perfRatingTypeToBeUpdated,
			@PathVariable String perfRatingTypeId) throws Exception {

		perfRatingTypeToBeUpdated.setPerfRatingTypeId(perfRatingTypeId);

		UpdatePerfRatingType command = new UpdatePerfRatingType(perfRatingTypeToBeUpdated);

		try {
			if(((PerfRatingTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{perfRatingTypeId}")
	public ResponseEntity<PerfRatingType> findById(@PathVariable String perfRatingTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfRatingTypeId", perfRatingTypeId);
		try {

			List<PerfRatingType> foundPerfRatingType = findPerfRatingTypesBy(requestParams).getBody();
			if(foundPerfRatingType.size()==1){				return successful(foundPerfRatingType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{perfRatingTypeId}")
	public ResponseEntity<String> deletePerfRatingTypeByIdUpdated(@PathVariable String perfRatingTypeId) throws Exception {
		DeletePerfRatingType command = new DeletePerfRatingType(perfRatingTypeId);

		try {
			if (((PerfRatingTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
