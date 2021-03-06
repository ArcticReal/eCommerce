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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProtectedView>> findProtectedViewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProtectedViewsBy query = new FindProtectedViewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProtectedView> protectedViews =((ProtectedViewFound) Scheduler.execute(query).data()).getProtectedViews();

		return ResponseEntity.ok().body(protectedViews);

	}

	/**
	 * creates a new ProtectedView entry in the ofbiz database
	 * 
	 * @param protectedViewToBeAdded
	 *            the ProtectedView thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProtectedView> createProtectedView(@RequestBody ProtectedView protectedViewToBeAdded) throws Exception {

		AddProtectedView command = new AddProtectedView(protectedViewToBeAdded);
		ProtectedView protectedView = ((ProtectedViewAdded) Scheduler.execute(command).data()).getAddedProtectedView();
		
		if (protectedView != null) 
			return successful(protectedView);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProtectedView(@RequestBody ProtectedView protectedViewToBeUpdated,
			@PathVariable String viewNameId) throws Exception {

		protectedViewToBeUpdated.setViewNameId(viewNameId);

		UpdateProtectedView command = new UpdateProtectedView(protectedViewToBeUpdated);

		try {
			if(((ProtectedViewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{protectedViewId}")
	public ResponseEntity<ProtectedView> findById(@PathVariable String protectedViewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("protectedViewId", protectedViewId);
		try {

			List<ProtectedView> foundProtectedView = findProtectedViewsBy(requestParams).getBody();
			if(foundProtectedView.size()==1){				return successful(foundProtectedView.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{protectedViewId}")
	public ResponseEntity<String> deleteProtectedViewByIdUpdated(@PathVariable String protectedViewId) throws Exception {
		DeleteProtectedView command = new DeleteProtectedView(protectedViewId);

		try {
			if (((ProtectedViewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
