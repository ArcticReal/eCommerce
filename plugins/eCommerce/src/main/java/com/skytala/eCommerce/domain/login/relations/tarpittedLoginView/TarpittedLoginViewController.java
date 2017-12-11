package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView;

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
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.command.AddTarpittedLoginView;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.command.DeleteTarpittedLoginView;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.command.UpdateTarpittedLoginView;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewAdded;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewDeleted;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewFound;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewUpdated;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.mapper.TarpittedLoginViewMapper;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.query.FindTarpittedLoginViewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/tarpittedLoginViews")
public class TarpittedLoginViewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TarpittedLoginViewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TarpittedLoginView
	 * @return a List with the TarpittedLoginViews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTarpittedLoginViewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTarpittedLoginViewsBy query = new FindTarpittedLoginViewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TarpittedLoginView> tarpittedLoginViews =((TarpittedLoginViewFound) Scheduler.execute(query).data()).getTarpittedLoginViews();

		if (tarpittedLoginViews.size() == 1) {
			return ResponseEntity.ok().body(tarpittedLoginViews.get(0));
		}

		return ResponseEntity.ok().body(tarpittedLoginViews);

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
	public ResponseEntity<Object> createTarpittedLoginView(HttpServletRequest request) throws Exception {

		TarpittedLoginView tarpittedLoginViewToBeAdded = new TarpittedLoginView();
		try {
			tarpittedLoginViewToBeAdded = TarpittedLoginViewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTarpittedLoginView(tarpittedLoginViewToBeAdded);

	}

	/**
	 * creates a new TarpittedLoginView entry in the ofbiz database
	 * 
	 * @param tarpittedLoginViewToBeAdded
	 *            the TarpittedLoginView thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTarpittedLoginView(@RequestBody TarpittedLoginView tarpittedLoginViewToBeAdded) throws Exception {

		AddTarpittedLoginView command = new AddTarpittedLoginView(tarpittedLoginViewToBeAdded);
		TarpittedLoginView tarpittedLoginView = ((TarpittedLoginViewAdded) Scheduler.execute(command).data()).getAddedTarpittedLoginView();
		
		if (tarpittedLoginView != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(tarpittedLoginView);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TarpittedLoginView could not be created.");
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
	public boolean updateTarpittedLoginView(HttpServletRequest request) throws Exception {

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

		TarpittedLoginView tarpittedLoginViewToBeUpdated = new TarpittedLoginView();

		try {
			tarpittedLoginViewToBeUpdated = TarpittedLoginViewMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTarpittedLoginView(tarpittedLoginViewToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TarpittedLoginView with the specific Id
	 * 
	 * @param tarpittedLoginViewToBeUpdated
	 *            the TarpittedLoginView thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTarpittedLoginView(@RequestBody TarpittedLoginView tarpittedLoginViewToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		tarpittedLoginViewToBeUpdated.setnull(null);

		UpdateTarpittedLoginView command = new UpdateTarpittedLoginView(tarpittedLoginViewToBeUpdated);

		try {
			if(((TarpittedLoginViewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{tarpittedLoginViewId}")
	public ResponseEntity<Object> findById(@PathVariable String tarpittedLoginViewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("tarpittedLoginViewId", tarpittedLoginViewId);
		try {

			Object foundTarpittedLoginView = findTarpittedLoginViewsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTarpittedLoginView);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{tarpittedLoginViewId}")
	public ResponseEntity<Object> deleteTarpittedLoginViewByIdUpdated(@PathVariable String tarpittedLoginViewId) throws Exception {
		DeleteTarpittedLoginView command = new DeleteTarpittedLoginView(tarpittedLoginViewId);

		try {
			if (((TarpittedLoginViewDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TarpittedLoginView could not be deleted");

	}

}
