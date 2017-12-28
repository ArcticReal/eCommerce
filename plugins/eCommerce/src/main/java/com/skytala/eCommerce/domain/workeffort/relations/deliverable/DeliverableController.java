package com.skytala.eCommerce.domain.workeffort.relations.deliverable;

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
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.AddDeliverable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.DeleteDeliverable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.UpdateDeliverable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.DeliverableAdded;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.DeliverableDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.DeliverableFound;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.DeliverableUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.DeliverableMapper;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.Deliverable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.query.FindDeliverablesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/deliverables")
public class DeliverableController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DeliverableController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Deliverable
	 * @return a List with the Deliverables
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Deliverable>> findDeliverablesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverablesBy query = new FindDeliverablesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Deliverable> deliverables =((DeliverableFound) Scheduler.execute(query).data()).getDeliverables();

		return ResponseEntity.ok().body(deliverables);

	}

	/**
	 * creates a new Deliverable entry in the ofbiz database
	 * 
	 * @param deliverableToBeAdded
	 *            the Deliverable thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Deliverable> createDeliverable(@RequestBody Deliverable deliverableToBeAdded) throws Exception {

		AddDeliverable command = new AddDeliverable(deliverableToBeAdded);
		Deliverable deliverable = ((DeliverableAdded) Scheduler.execute(command).data()).getAddedDeliverable();
		
		if (deliverable != null) 
			return successful(deliverable);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Deliverable with the specific Id
	 * 
	 * @param deliverableToBeUpdated
	 *            the Deliverable thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{deliverableId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDeliverable(@RequestBody Deliverable deliverableToBeUpdated,
			@PathVariable String deliverableId) throws Exception {

		deliverableToBeUpdated.setDeliverableId(deliverableId);

		UpdateDeliverable command = new UpdateDeliverable(deliverableToBeUpdated);

		try {
			if(((DeliverableUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{deliverableId}")
	public ResponseEntity<Deliverable> findById(@PathVariable String deliverableId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliverableId", deliverableId);
		try {

			List<Deliverable> foundDeliverable = findDeliverablesBy(requestParams).getBody();
			if(foundDeliverable.size()==1){				return successful(foundDeliverable.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{deliverableId}")
	public ResponseEntity<String> deleteDeliverableByIdUpdated(@PathVariable String deliverableId) throws Exception {
		DeleteDeliverable command = new DeleteDeliverable(deliverableId);

		try {
			if (((DeliverableDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
