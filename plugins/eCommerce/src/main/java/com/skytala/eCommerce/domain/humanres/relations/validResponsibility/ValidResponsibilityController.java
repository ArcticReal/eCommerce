package com.skytala.eCommerce.domain.humanres.relations.validResponsibility;

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
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.command.AddValidResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.command.DeleteValidResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.command.UpdateValidResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityAdded;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityDeleted;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityFound;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityUpdated;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.mapper.ValidResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.query.FindValidResponsibilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/validResponsibilitys")
public class ValidResponsibilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ValidResponsibilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ValidResponsibility
	 * @return a List with the ValidResponsibilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ValidResponsibility>> findValidResponsibilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindValidResponsibilitysBy query = new FindValidResponsibilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ValidResponsibility> validResponsibilitys =((ValidResponsibilityFound) Scheduler.execute(query).data()).getValidResponsibilitys();

		return ResponseEntity.ok().body(validResponsibilitys);

	}

	/**
	 * creates a new ValidResponsibility entry in the ofbiz database
	 * 
	 * @param validResponsibilityToBeAdded
	 *            the ValidResponsibility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ValidResponsibility> createValidResponsibility(@RequestBody ValidResponsibility validResponsibilityToBeAdded) throws Exception {

		AddValidResponsibility command = new AddValidResponsibility(validResponsibilityToBeAdded);
		ValidResponsibility validResponsibility = ((ValidResponsibilityAdded) Scheduler.execute(command).data()).getAddedValidResponsibility();
		
		if (validResponsibility != null) 
			return successful(validResponsibility);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ValidResponsibility with the specific Id
	 * 
	 * @param validResponsibilityToBeUpdated
	 *            the ValidResponsibility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateValidResponsibility(@RequestBody ValidResponsibility validResponsibilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		validResponsibilityToBeUpdated.setnull(null);

		UpdateValidResponsibility command = new UpdateValidResponsibility(validResponsibilityToBeUpdated);

		try {
			if(((ValidResponsibilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{validResponsibilityId}")
	public ResponseEntity<ValidResponsibility> findById(@PathVariable String validResponsibilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("validResponsibilityId", validResponsibilityId);
		try {

			List<ValidResponsibility> foundValidResponsibility = findValidResponsibilitysBy(requestParams).getBody();
			if(foundValidResponsibility.size()==1){				return successful(foundValidResponsibility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{validResponsibilityId}")
	public ResponseEntity<String> deleteValidResponsibilityByIdUpdated(@PathVariable String validResponsibilityId) throws Exception {
		DeleteValidResponsibility command = new DeleteValidResponsibility(validResponsibilityId);

		try {
			if (((ValidResponsibilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
