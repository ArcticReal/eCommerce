package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.registration;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.registration.AddFixedAssetRegistration;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.registration.DeleteFixedAssetRegistration;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.registration.UpdateFixedAssetRegistration;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration.FixedAssetRegistrationAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration.FixedAssetRegistrationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration.FixedAssetRegistrationFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration.FixedAssetRegistrationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.registration.FixedAssetRegistrationMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration.FixedAssetRegistration;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.registration.FindFixedAssetRegistrationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetRegistrations")
public class FixedAssetRegistrationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetRegistrationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetRegistration
	 * @return a List with the FixedAssetRegistrations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFixedAssetRegistrationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetRegistrationsBy query = new FindFixedAssetRegistrationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetRegistration> fixedAssetRegistrations =((FixedAssetRegistrationFound) Scheduler.execute(query).data()).getFixedAssetRegistrations();

		if (fixedAssetRegistrations.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetRegistrations.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetRegistrations);

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
	public ResponseEntity<Object> createFixedAssetRegistration(HttpServletRequest request) throws Exception {

		FixedAssetRegistration fixedAssetRegistrationToBeAdded = new FixedAssetRegistration();
		try {
			fixedAssetRegistrationToBeAdded = FixedAssetRegistrationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetRegistration(fixedAssetRegistrationToBeAdded);

	}

	/**
	 * creates a new FixedAssetRegistration entry in the ofbiz database
	 * 
	 * @param fixedAssetRegistrationToBeAdded
	 *            the FixedAssetRegistration thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetRegistration(@RequestBody FixedAssetRegistration fixedAssetRegistrationToBeAdded) throws Exception {

		AddFixedAssetRegistration command = new AddFixedAssetRegistration(fixedAssetRegistrationToBeAdded);
		FixedAssetRegistration fixedAssetRegistration = ((FixedAssetRegistrationAdded) Scheduler.execute(command).data()).getAddedFixedAssetRegistration();
		
		if (fixedAssetRegistration != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetRegistration);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetRegistration could not be created.");
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
	public boolean updateFixedAssetRegistration(HttpServletRequest request) throws Exception {

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

		FixedAssetRegistration fixedAssetRegistrationToBeUpdated = new FixedAssetRegistration();

		try {
			fixedAssetRegistrationToBeUpdated = FixedAssetRegistrationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetRegistration(fixedAssetRegistrationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetRegistration with the specific Id
	 * 
	 * @param fixedAssetRegistrationToBeUpdated
	 *            the FixedAssetRegistration thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetRegistration(@RequestBody FixedAssetRegistration fixedAssetRegistrationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetRegistrationToBeUpdated.setnull(null);

		UpdateFixedAssetRegistration command = new UpdateFixedAssetRegistration(fixedAssetRegistrationToBeUpdated);

		try {
			if(((FixedAssetRegistrationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetRegistrationId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetRegistrationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetRegistrationId", fixedAssetRegistrationId);
		try {

			Object foundFixedAssetRegistration = findFixedAssetRegistrationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetRegistration);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetRegistrationId}")
	public ResponseEntity<Object> deleteFixedAssetRegistrationByIdUpdated(@PathVariable String fixedAssetRegistrationId) throws Exception {
		DeleteFixedAssetRegistration command = new DeleteFixedAssetRegistration(fixedAssetRegistrationId);

		try {
			if (((FixedAssetRegistrationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetRegistration could not be deleted");

	}

}
