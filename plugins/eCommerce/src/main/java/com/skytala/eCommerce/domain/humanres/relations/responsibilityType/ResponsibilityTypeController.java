package com.skytala.eCommerce.domain.humanres.relations.responsibilityType;

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
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.command.AddResponsibilityType;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.command.DeleteResponsibilityType;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.command.UpdateResponsibilityType;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.mapper.ResponsibilityTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.query.FindResponsibilityTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/responsibilityTypes")
public class ResponsibilityTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ResponsibilityTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ResponsibilityType
	 * @return a List with the ResponsibilityTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ResponsibilityType>> findResponsibilityTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindResponsibilityTypesBy query = new FindResponsibilityTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ResponsibilityType> responsibilityTypes =((ResponsibilityTypeFound) Scheduler.execute(query).data()).getResponsibilityTypes();

		return ResponseEntity.ok().body(responsibilityTypes);

	}

	/**
	 * creates a new ResponsibilityType entry in the ofbiz database
	 * 
	 * @param responsibilityTypeToBeAdded
	 *            the ResponsibilityType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResponsibilityType> createResponsibilityType(@RequestBody ResponsibilityType responsibilityTypeToBeAdded) throws Exception {

		AddResponsibilityType command = new AddResponsibilityType(responsibilityTypeToBeAdded);
		ResponsibilityType responsibilityType = ((ResponsibilityTypeAdded) Scheduler.execute(command).data()).getAddedResponsibilityType();
		
		if (responsibilityType != null) 
			return successful(responsibilityType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ResponsibilityType with the specific Id
	 * 
	 * @param responsibilityTypeToBeUpdated
	 *            the ResponsibilityType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{responsibilityTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateResponsibilityType(@RequestBody ResponsibilityType responsibilityTypeToBeUpdated,
			@PathVariable String responsibilityTypeId) throws Exception {

		responsibilityTypeToBeUpdated.setResponsibilityTypeId(responsibilityTypeId);

		UpdateResponsibilityType command = new UpdateResponsibilityType(responsibilityTypeToBeUpdated);

		try {
			if(((ResponsibilityTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{responsibilityTypeId}")
	public ResponseEntity<ResponsibilityType> findById(@PathVariable String responsibilityTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("responsibilityTypeId", responsibilityTypeId);
		try {

			List<ResponsibilityType> foundResponsibilityType = findResponsibilityTypesBy(requestParams).getBody();
			if(foundResponsibilityType.size()==1){				return successful(foundResponsibilityType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{responsibilityTypeId}")
	public ResponseEntity<String> deleteResponsibilityTypeByIdUpdated(@PathVariable String responsibilityTypeId) throws Exception {
		DeleteResponsibilityType command = new DeleteResponsibilityType(responsibilityTypeId);

		try {
			if (((ResponsibilityTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
