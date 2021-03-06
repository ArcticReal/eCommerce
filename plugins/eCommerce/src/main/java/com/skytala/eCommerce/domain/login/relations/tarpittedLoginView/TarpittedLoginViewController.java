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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<TarpittedLoginView>> findTarpittedLoginViewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTarpittedLoginViewsBy query = new FindTarpittedLoginViewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TarpittedLoginView> tarpittedLoginViews =((TarpittedLoginViewFound) Scheduler.execute(query).data()).getTarpittedLoginViews();

		return ResponseEntity.ok().body(tarpittedLoginViews);

	}

	/**
	 * creates a new TarpittedLoginView entry in the ofbiz database
	 * 
	 * @param tarpittedLoginViewToBeAdded
	 *            the TarpittedLoginView thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TarpittedLoginView> createTarpittedLoginView(@RequestBody TarpittedLoginView tarpittedLoginViewToBeAdded) throws Exception {

		AddTarpittedLoginView command = new AddTarpittedLoginView(tarpittedLoginViewToBeAdded);
		TarpittedLoginView tarpittedLoginView = ((TarpittedLoginViewAdded) Scheduler.execute(command).data()).getAddedTarpittedLoginView();
		
		if (tarpittedLoginView != null) 
			return successful(tarpittedLoginView);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTarpittedLoginView(@RequestBody TarpittedLoginView tarpittedLoginViewToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		tarpittedLoginViewToBeUpdated.setnull(null);

		UpdateTarpittedLoginView command = new UpdateTarpittedLoginView(tarpittedLoginViewToBeUpdated);

		try {
			if(((TarpittedLoginViewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{tarpittedLoginViewId}")
	public ResponseEntity<TarpittedLoginView> findById(@PathVariable String tarpittedLoginViewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("tarpittedLoginViewId", tarpittedLoginViewId);
		try {

			List<TarpittedLoginView> foundTarpittedLoginView = findTarpittedLoginViewsBy(requestParams).getBody();
			if(foundTarpittedLoginView.size()==1){				return successful(foundTarpittedLoginView.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{tarpittedLoginViewId}")
	public ResponseEntity<String> deleteTarpittedLoginViewByIdUpdated(@PathVariable String tarpittedLoginViewId) throws Exception {
		DeleteTarpittedLoginView command = new DeleteTarpittedLoginView(tarpittedLoginViewId);

		try {
			if (((TarpittedLoginViewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
