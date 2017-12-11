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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findPerfRatingTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfRatingTypesBy query = new FindPerfRatingTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfRatingType> perfRatingTypes =((PerfRatingTypeFound) Scheduler.execute(query).data()).getPerfRatingTypes();

		if (perfRatingTypes.size() == 1) {
			return ResponseEntity.ok().body(perfRatingTypes.get(0));
		}

		return ResponseEntity.ok().body(perfRatingTypes);

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
	public ResponseEntity<Object> createPerfRatingType(HttpServletRequest request) throws Exception {

		PerfRatingType perfRatingTypeToBeAdded = new PerfRatingType();
		try {
			perfRatingTypeToBeAdded = PerfRatingTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPerfRatingType(perfRatingTypeToBeAdded);

	}

	/**
	 * creates a new PerfRatingType entry in the ofbiz database
	 * 
	 * @param perfRatingTypeToBeAdded
	 *            the PerfRatingType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPerfRatingType(@RequestBody PerfRatingType perfRatingTypeToBeAdded) throws Exception {

		AddPerfRatingType command = new AddPerfRatingType(perfRatingTypeToBeAdded);
		PerfRatingType perfRatingType = ((PerfRatingTypeAdded) Scheduler.execute(command).data()).getAddedPerfRatingType();
		
		if (perfRatingType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(perfRatingType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PerfRatingType could not be created.");
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
	public boolean updatePerfRatingType(HttpServletRequest request) throws Exception {

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

		PerfRatingType perfRatingTypeToBeUpdated = new PerfRatingType();

		try {
			perfRatingTypeToBeUpdated = PerfRatingTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePerfRatingType(perfRatingTypeToBeUpdated, perfRatingTypeToBeUpdated.getPerfRatingTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePerfRatingType(@RequestBody PerfRatingType perfRatingTypeToBeUpdated,
			@PathVariable String perfRatingTypeId) throws Exception {

		perfRatingTypeToBeUpdated.setPerfRatingTypeId(perfRatingTypeId);

		UpdatePerfRatingType command = new UpdatePerfRatingType(perfRatingTypeToBeUpdated);

		try {
			if(((PerfRatingTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{perfRatingTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String perfRatingTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfRatingTypeId", perfRatingTypeId);
		try {

			Object foundPerfRatingType = findPerfRatingTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPerfRatingType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{perfRatingTypeId}")
	public ResponseEntity<Object> deletePerfRatingTypeByIdUpdated(@PathVariable String perfRatingTypeId) throws Exception {
		DeletePerfRatingType command = new DeletePerfRatingType(perfRatingTypeId);

		try {
			if (((PerfRatingTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PerfRatingType could not be deleted");

	}

}
