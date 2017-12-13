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
	public ResponseEntity<Object> findEmploymentAppsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmploymentAppsBy query = new FindEmploymentAppsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmploymentApp> employmentApps =((EmploymentAppFound) Scheduler.execute(query).data()).getEmploymentApps();

		if (employmentApps.size() == 1) {
			return ResponseEntity.ok().body(employmentApps.get(0));
		}

		return ResponseEntity.ok().body(employmentApps);

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
	public ResponseEntity<Object> createEmploymentApp(HttpServletRequest request) throws Exception {

		EmploymentApp employmentAppToBeAdded = new EmploymentApp();
		try {
			employmentAppToBeAdded = EmploymentAppMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmploymentApp(employmentAppToBeAdded);

	}

	/**
	 * creates a new EmploymentApp entry in the ofbiz database
	 * 
	 * @param employmentAppToBeAdded
	 *            the EmploymentApp thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmploymentApp(@RequestBody EmploymentApp employmentAppToBeAdded) throws Exception {

		AddEmploymentApp command = new AddEmploymentApp(employmentAppToBeAdded);
		EmploymentApp employmentApp = ((EmploymentAppAdded) Scheduler.execute(command).data()).getAddedEmploymentApp();
		
		if (employmentApp != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(employmentApp);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmploymentApp could not be created.");
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
	public boolean updateEmploymentApp(HttpServletRequest request) throws Exception {

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

		EmploymentApp employmentAppToBeUpdated = new EmploymentApp();

		try {
			employmentAppToBeUpdated = EmploymentAppMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmploymentApp(employmentAppToBeUpdated, employmentAppToBeUpdated.getApplicationId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateEmploymentApp(@RequestBody EmploymentApp employmentAppToBeUpdated,
			@PathVariable String applicationId) throws Exception {

		employmentAppToBeUpdated.setApplicationId(applicationId);

		UpdateEmploymentApp command = new UpdateEmploymentApp(employmentAppToBeUpdated);

		try {
			if(((EmploymentAppUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{employmentAppId}")
	public ResponseEntity<Object> findById(@PathVariable String employmentAppId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("employmentAppId", employmentAppId);
		try {

			Object foundEmploymentApp = findEmploymentAppsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmploymentApp);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{employmentAppId}")
	public ResponseEntity<Object> deleteEmploymentAppByIdUpdated(@PathVariable String employmentAppId) throws Exception {
		DeleteEmploymentApp command = new DeleteEmploymentApp(employmentAppId);

		try {
			if (((EmploymentAppDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmploymentApp could not be deleted");

	}

}
