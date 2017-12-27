package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.history;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.AddGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.DeleteGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.UpdateGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.history.GlAccountHistoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.history.FindGlAccountHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountHistorys")
public class GlAccountHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountHistory
	 * @return a List with the GlAccountHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountHistory>> findGlAccountHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountHistorysBy query = new FindGlAccountHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountHistory> glAccountHistorys =((GlAccountHistoryFound) Scheduler.execute(query).data()).getGlAccountHistorys();

		return ResponseEntity.ok().body(glAccountHistorys);

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
	public ResponseEntity<GlAccountHistory> createGlAccountHistory(HttpServletRequest request) throws Exception {

		GlAccountHistory glAccountHistoryToBeAdded = new GlAccountHistory();
		try {
			glAccountHistoryToBeAdded = GlAccountHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createGlAccountHistory(glAccountHistoryToBeAdded);

	}

	/**
	 * creates a new GlAccountHistory entry in the ofbiz database
	 * 
	 * @param glAccountHistoryToBeAdded
	 *            the GlAccountHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountHistory> createGlAccountHistory(@RequestBody GlAccountHistory glAccountHistoryToBeAdded) throws Exception {

		AddGlAccountHistory command = new AddGlAccountHistory(glAccountHistoryToBeAdded);
		GlAccountHistory glAccountHistory = ((GlAccountHistoryAdded) Scheduler.execute(command).data()).getAddedGlAccountHistory();
		
		if (glAccountHistory != null) 
			return successful(glAccountHistory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountHistory with the specific Id
	 * 
	 * @param glAccountHistoryToBeUpdated
	 *            the GlAccountHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountHistory(@RequestBody GlAccountHistory glAccountHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountHistoryToBeUpdated.setnull(null);

		UpdateGlAccountHistory command = new UpdateGlAccountHistory(glAccountHistoryToBeUpdated);

		try {
			if(((GlAccountHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountHistoryId}")
	public ResponseEntity<GlAccountHistory> findById(@PathVariable String glAccountHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountHistoryId", glAccountHistoryId);
		try {

			List<GlAccountHistory> foundGlAccountHistory = findGlAccountHistorysBy(requestParams).getBody();
			if(foundGlAccountHistory.size()==1){				return successful(foundGlAccountHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountHistoryId}")
	public ResponseEntity<String> deleteGlAccountHistoryByIdUpdated(@PathVariable String glAccountHistoryId) throws Exception {
		DeleteGlAccountHistory command = new DeleteGlAccountHistory(glAccountHistoryId);

		try {
			if (((GlAccountHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
