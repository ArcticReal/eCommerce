package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.responsibility;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.AddEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.DeleteEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.UpdateEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.responsibility.EmplPositionResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility.EmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.responsibility.FindEmplPositionResponsibilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionResponsibilitys")
public class EmplPositionResponsibilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionResponsibilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionResponsibility
	 * @return a List with the EmplPositionResponsibilitys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionResponsibilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionResponsibilitysBy query = new FindEmplPositionResponsibilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionResponsibility> emplPositionResponsibilitys =((EmplPositionResponsibilityFound) Scheduler.execute(query).data()).getEmplPositionResponsibilitys();

		if (emplPositionResponsibilitys.size() == 1) {
			return ResponseEntity.ok().body(emplPositionResponsibilitys.get(0));
		}

		return ResponseEntity.ok().body(emplPositionResponsibilitys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createEmplPositionResponsibility(HttpServletRequest request) throws Exception {

		EmplPositionResponsibility emplPositionResponsibilityToBeAdded = new EmplPositionResponsibility();
		try {
			emplPositionResponsibilityToBeAdded = EmplPositionResponsibilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPositionResponsibility(emplPositionResponsibilityToBeAdded);

	}

	/**
	 * creates a new EmplPositionResponsibility entry in the ofbiz database
	 * 
	 * @param emplPositionResponsibilityToBeAdded
	 *            the EmplPositionResponsibility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPositionResponsibility(@RequestBody EmplPositionResponsibility emplPositionResponsibilityToBeAdded) throws Exception {

		AddEmplPositionResponsibility command = new AddEmplPositionResponsibility(emplPositionResponsibilityToBeAdded);
		EmplPositionResponsibility emplPositionResponsibility = ((EmplPositionResponsibilityAdded) Scheduler.execute(command).data()).getAddedEmplPositionResponsibility();
		
		if (emplPositionResponsibility != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPositionResponsibility);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPositionResponsibility could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateEmplPositionResponsibility(HttpServletRequest request) throws Exception {

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

		EmplPositionResponsibility emplPositionResponsibilityToBeUpdated = new EmplPositionResponsibility();

		try {
			emplPositionResponsibilityToBeUpdated = EmplPositionResponsibilityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPositionResponsibility(emplPositionResponsibilityToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplPositionResponsibility with the specific Id
	 * 
	 * @param emplPositionResponsibilityToBeUpdated
	 *            the EmplPositionResponsibility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplPositionResponsibility(@RequestBody EmplPositionResponsibility emplPositionResponsibilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionResponsibilityToBeUpdated.setnull(null);

		UpdateEmplPositionResponsibility command = new UpdateEmplPositionResponsibility(emplPositionResponsibilityToBeUpdated);

		try {
			if(((EmplPositionResponsibilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionResponsibilityId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionResponsibilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionResponsibilityId", emplPositionResponsibilityId);
		try {

			Object foundEmplPositionResponsibility = findEmplPositionResponsibilitysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPositionResponsibility);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionResponsibilityId}")
	public ResponseEntity<Object> deleteEmplPositionResponsibilityByIdUpdated(@PathVariable String emplPositionResponsibilityId) throws Exception {
		DeleteEmplPositionResponsibility command = new DeleteEmplPositionResponsibility(emplPositionResponsibilityId);

		try {
			if (((EmplPositionResponsibilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPositionResponsibility could not be deleted");

	}

}
