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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/deliverables")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDeliverablesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverablesBy query = new FindDeliverablesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Deliverable> deliverables =((DeliverableFound) Scheduler.execute(query).data()).getDeliverables();

		if (deliverables.size() == 1) {
			return ResponseEntity.ok().body(deliverables.get(0));
		}

		return ResponseEntity.ok().body(deliverables);

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
	public ResponseEntity<Object> createDeliverable(HttpServletRequest request) throws Exception {

		Deliverable deliverableToBeAdded = new Deliverable();
		try {
			deliverableToBeAdded = DeliverableMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDeliverable(deliverableToBeAdded);

	}

	/**
	 * creates a new Deliverable entry in the ofbiz database
	 * 
	 * @param deliverableToBeAdded
	 *            the Deliverable thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDeliverable(@RequestBody Deliverable deliverableToBeAdded) throws Exception {

		AddDeliverable command = new AddDeliverable(deliverableToBeAdded);
		Deliverable deliverable = ((DeliverableAdded) Scheduler.execute(command).data()).getAddedDeliverable();
		
		if (deliverable != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(deliverable);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Deliverable could not be created.");
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
	public boolean updateDeliverable(HttpServletRequest request) throws Exception {

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

		Deliverable deliverableToBeUpdated = new Deliverable();

		try {
			deliverableToBeUpdated = DeliverableMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDeliverable(deliverableToBeUpdated, deliverableToBeUpdated.getDeliverableId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDeliverable(@RequestBody Deliverable deliverableToBeUpdated,
			@PathVariable String deliverableId) throws Exception {

		deliverableToBeUpdated.setDeliverableId(deliverableId);

		UpdateDeliverable command = new UpdateDeliverable(deliverableToBeUpdated);

		try {
			if(((DeliverableUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{deliverableId}")
	public ResponseEntity<Object> findById(@PathVariable String deliverableId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliverableId", deliverableId);
		try {

			Object foundDeliverable = findDeliverablesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDeliverable);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{deliverableId}")
	public ResponseEntity<Object> deleteDeliverableByIdUpdated(@PathVariable String deliverableId) throws Exception {
		DeleteDeliverable command = new DeleteDeliverable(deliverableId);

		try {
			if (((DeliverableDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Deliverable could not be deleted");

	}

}
