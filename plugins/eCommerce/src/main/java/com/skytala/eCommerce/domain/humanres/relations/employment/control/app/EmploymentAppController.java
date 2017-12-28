package com.skytala.eCommerce.domain.humanres.relations.employment.control.app;

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
import com.skytala.eCommerce.domain.humanres.relations.employment.command.app.AddEmploymentApp;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.app.DeleteEmploymentApp;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.app.UpdateEmploymentApp;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.app.EmploymentAppAdded;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.app.EmploymentAppDeleted;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.app.EmploymentAppFound;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.app.EmploymentAppUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.app.EmploymentAppMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.app.EmploymentApp;
import com.skytala.eCommerce.domain.humanres.relations.employment.query.app.FindEmploymentAppsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/employment/employmentApps")
public class EmploymentAppController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmploymentAppController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmploymentApp
	 * @return a List with the EmploymentApps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmploymentApp>> findEmploymentAppsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmploymentAppsBy query = new FindEmploymentAppsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmploymentApp> employmentApps =((EmploymentAppFound) Scheduler.execute(query).data()).getEmploymentApps();

		return ResponseEntity.ok().body(employmentApps);

	}

	/**
	 * creates a new EmploymentApp entry in the ofbiz database
	 * 
	 * @param employmentAppToBeAdded
	 *            the EmploymentApp thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmploymentApp> createEmploymentApp(@RequestBody EmploymentApp employmentAppToBeAdded) throws Exception {

		AddEmploymentApp command = new AddEmploymentApp(employmentAppToBeAdded);
		EmploymentApp employmentApp = ((EmploymentAppAdded) Scheduler.execute(command).data()).getAddedEmploymentApp();
		
		if (employmentApp != null) 
			return successful(employmentApp);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmploymentApp with the specific Id
	 * 
	 * @param employmentAppToBeUpdated
	 *            the EmploymentApp thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{applicationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmploymentApp(@RequestBody EmploymentApp employmentAppToBeUpdated,
			@PathVariable String applicationId) throws Exception {

		employmentAppToBeUpdated.setApplicationId(applicationId);

		UpdateEmploymentApp command = new UpdateEmploymentApp(employmentAppToBeUpdated);

		try {
			if(((EmploymentAppUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{employmentAppId}")
	public ResponseEntity<EmploymentApp> findById(@PathVariable String employmentAppId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("employmentAppId", employmentAppId);
		try {

			List<EmploymentApp> foundEmploymentApp = findEmploymentAppsBy(requestParams).getBody();
			if(foundEmploymentApp.size()==1){				return successful(foundEmploymentApp.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{employmentAppId}")
	public ResponseEntity<String> deleteEmploymentAppByIdUpdated(@PathVariable String employmentAppId) throws Exception {
		DeleteEmploymentApp command = new DeleteEmploymentApp(employmentAppId);

		try {
			if (((EmploymentAppDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
