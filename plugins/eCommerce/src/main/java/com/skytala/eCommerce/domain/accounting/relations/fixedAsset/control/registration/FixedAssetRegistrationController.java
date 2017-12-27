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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<FixedAssetRegistration>> findFixedAssetRegistrationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetRegistrationsBy query = new FindFixedAssetRegistrationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetRegistration> fixedAssetRegistrations =((FixedAssetRegistrationFound) Scheduler.execute(query).data()).getFixedAssetRegistrations();

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
	public ResponseEntity<FixedAssetRegistration> createFixedAssetRegistration(HttpServletRequest request) throws Exception {

		FixedAssetRegistration fixedAssetRegistrationToBeAdded = new FixedAssetRegistration();
		try {
			fixedAssetRegistrationToBeAdded = FixedAssetRegistrationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<FixedAssetRegistration> createFixedAssetRegistration(@RequestBody FixedAssetRegistration fixedAssetRegistrationToBeAdded) throws Exception {

		AddFixedAssetRegistration command = new AddFixedAssetRegistration(fixedAssetRegistrationToBeAdded);
		FixedAssetRegistration fixedAssetRegistration = ((FixedAssetRegistrationAdded) Scheduler.execute(command).data()).getAddedFixedAssetRegistration();
		
		if (fixedAssetRegistration != null) 
			return successful(fixedAssetRegistration);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFixedAssetRegistration(@RequestBody FixedAssetRegistration fixedAssetRegistrationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetRegistrationToBeUpdated.setnull(null);

		UpdateFixedAssetRegistration command = new UpdateFixedAssetRegistration(fixedAssetRegistrationToBeUpdated);

		try {
			if(((FixedAssetRegistrationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetRegistrationId}")
	public ResponseEntity<FixedAssetRegistration> findById(@PathVariable String fixedAssetRegistrationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetRegistrationId", fixedAssetRegistrationId);
		try {

			List<FixedAssetRegistration> foundFixedAssetRegistration = findFixedAssetRegistrationsBy(requestParams).getBody();
			if(foundFixedAssetRegistration.size()==1){				return successful(foundFixedAssetRegistration.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetRegistrationId}")
	public ResponseEntity<String> deleteFixedAssetRegistrationByIdUpdated(@PathVariable String fixedAssetRegistrationId) throws Exception {
		DeleteFixedAssetRegistration command = new DeleteFixedAssetRegistration(fixedAssetRegistrationId);

		try {
			if (((FixedAssetRegistrationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
