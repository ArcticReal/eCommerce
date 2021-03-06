package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox;

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
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.AddApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.DeleteApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command.UpdateApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxFound;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper.ApplicationSandboxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.query.FindApplicationSandboxsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/applicationSandboxs")
public class ApplicationSandboxController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ApplicationSandboxController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ApplicationSandbox
	 * @return a List with the ApplicationSandboxs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ApplicationSandbox>> findApplicationSandboxsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindApplicationSandboxsBy query = new FindApplicationSandboxsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ApplicationSandbox> applicationSandboxs =((ApplicationSandboxFound) Scheduler.execute(query).data()).getApplicationSandboxs();

		return ResponseEntity.ok().body(applicationSandboxs);

	}

	/**
	 * creates a new ApplicationSandbox entry in the ofbiz database
	 * 
	 * @param applicationSandboxToBeAdded
	 *            the ApplicationSandbox thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ApplicationSandbox> createApplicationSandbox(@RequestBody ApplicationSandbox applicationSandboxToBeAdded) throws Exception {

		AddApplicationSandbox command = new AddApplicationSandbox(applicationSandboxToBeAdded);
		ApplicationSandbox applicationSandbox = ((ApplicationSandboxAdded) Scheduler.execute(command).data()).getAddedApplicationSandbox();
		
		if (applicationSandbox != null) 
			return successful(applicationSandbox);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ApplicationSandbox with the specific Id
	 * 
	 * @param applicationSandboxToBeUpdated
	 *            the ApplicationSandbox thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{applicationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateApplicationSandbox(@RequestBody ApplicationSandbox applicationSandboxToBeUpdated,
			@PathVariable String applicationId) throws Exception {

		applicationSandboxToBeUpdated.setApplicationId(applicationId);

		UpdateApplicationSandbox command = new UpdateApplicationSandbox(applicationSandboxToBeUpdated);

		try {
			if(((ApplicationSandboxUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{applicationSandboxId}")
	public ResponseEntity<ApplicationSandbox> findById(@PathVariable String applicationSandboxId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("applicationSandboxId", applicationSandboxId);
		try {

			List<ApplicationSandbox> foundApplicationSandbox = findApplicationSandboxsBy(requestParams).getBody();
			if(foundApplicationSandbox.size()==1){				return successful(foundApplicationSandbox.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{applicationSandboxId}")
	public ResponseEntity<String> deleteApplicationSandboxByIdUpdated(@PathVariable String applicationSandboxId) throws Exception {
		DeleteApplicationSandbox command = new DeleteApplicationSandbox(applicationSandboxId);

		try {
			if (((ApplicationSandboxDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
