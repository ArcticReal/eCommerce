package com.skytala.eCommerce.domain.login.relations.protectedView;

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
import com.skytala.eCommerce.domain.login.relations.protectedView.command.AddProtectedView;
import com.skytala.eCommerce.domain.login.relations.protectedView.command.DeleteProtectedView;
import com.skytala.eCommerce.domain.login.relations.protectedView.command.UpdateProtectedView;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewAdded;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewDeleted;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewFound;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewUpdated;
import com.skytala.eCommerce.domain.login.relations.protectedView.mapper.ProtectedViewMapper;
import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
import com.skytala.eCommerce.domain.login.relations.protectedView.query.FindProtectedViewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/protectedViews")
public class ProtectedViewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProtectedViewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProtectedView
	 * @return a List with the ProtectedViews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProtectedViewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProtectedViewsBy query = new FindProtectedViewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProtectedView> protectedViews =((ProtectedViewFound) Scheduler.execute(query).data()).getProtectedViews();

		if (protectedViews.size() == 1) {
			return ResponseEntity.ok().body(protectedViews.get(0));
		}

		return ResponseEntity.ok().body(protectedViews);

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
	public ResponseEntity<Object> createProtectedView(HttpServletRequest request) throws Exception {

		ProtectedView protectedViewToBeAdded = new ProtectedView();
		try {
			protectedViewToBeAdded = ProtectedViewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProtectedView(protectedViewToBeAdded);

	}

	/**
	 * creates a new ProtectedView entry in the ofbiz database
	 * 
	 * @param protectedViewToBeAdded
	 *            the ProtectedView thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProtectedView(@RequestBody ProtectedView protectedViewToBeAdded) throws Exception {

		AddProtectedView command = new AddProtectedView(protectedViewToBeAdded);
		ProtectedView protectedView = ((ProtectedViewAdded) Scheduler.execute(command).data()).getAddedProtectedView();
		
		if (protectedView != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(protectedView);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProtectedView could not be created.");
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
	public boolean updateProtectedView(HttpServletRequest request) throws Exception {

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

		ProtectedView protectedViewToBeUpdated = new ProtectedView();

		try {
			protectedViewToBeUpdated = ProtectedViewMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProtectedView(protectedViewToBeUpdated, protectedViewToBeUpdated.getViewNameId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProtectedView with the specific Id
	 * 
	 * @param protectedViewToBeUpdated
	 *            the ProtectedView thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{viewNameId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProtectedView(@RequestBody ProtectedView protectedViewToBeUpdated,
			@PathVariable String viewNameId) throws Exception {

		protectedViewToBeUpdated.setViewNameId(viewNameId);

		UpdateProtectedView command = new UpdateProtectedView(protectedViewToBeUpdated);

		try {
			if(((ProtectedViewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{protectedViewId}")
	public ResponseEntity<Object> findById(@PathVariable String protectedViewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("protectedViewId", protectedViewId);
		try {

			Object foundProtectedView = findProtectedViewsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProtectedView);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{protectedViewId}")
	public ResponseEntity<Object> deleteProtectedViewByIdUpdated(@PathVariable String protectedViewId) throws Exception {
		DeleteProtectedView command = new DeleteProtectedView(protectedViewId);

		try {
			if (((ProtectedViewDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProtectedView could not be deleted");

	}

}
