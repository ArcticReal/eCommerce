package com.skytala.eCommerce.domain.workeffort.relations.deliverable.control.type;

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
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.type.AddDeliverableType;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.type.DeleteDeliverableType;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.type.UpdateDeliverableType;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type.DeliverableTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type.DeliverableTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type.DeliverableTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type.DeliverableTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.type.DeliverableTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.query.type.FindDeliverableTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/deliverable/deliverableTypes")
public class DeliverableTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DeliverableTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DeliverableType
	 * @return a List with the DeliverableTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DeliverableType>> findDeliverableTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverableTypesBy query = new FindDeliverableTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DeliverableType> deliverableTypes =((DeliverableTypeFound) Scheduler.execute(query).data()).getDeliverableTypes();

		return ResponseEntity.ok().body(deliverableTypes);

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
	public ResponseEntity<DeliverableType> createDeliverableType(HttpServletRequest request) throws Exception {

		DeliverableType deliverableTypeToBeAdded = new DeliverableType();
		try {
			deliverableTypeToBeAdded = DeliverableTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createDeliverableType(deliverableTypeToBeAdded);

	}

	/**
	 * creates a new DeliverableType entry in the ofbiz database
	 * 
	 * @param deliverableTypeToBeAdded
	 *            the DeliverableType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DeliverableType> createDeliverableType(@RequestBody DeliverableType deliverableTypeToBeAdded) throws Exception {

		AddDeliverableType command = new AddDeliverableType(deliverableTypeToBeAdded);
		DeliverableType deliverableType = ((DeliverableTypeAdded) Scheduler.execute(command).data()).getAddedDeliverableType();
		
		if (deliverableType != null) 
			return successful(deliverableType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DeliverableType with the specific Id
	 * 
	 * @param deliverableTypeToBeUpdated
	 *            the DeliverableType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{deliverableTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDeliverableType(@RequestBody DeliverableType deliverableTypeToBeUpdated,
			@PathVariable String deliverableTypeId) throws Exception {

		deliverableTypeToBeUpdated.setDeliverableTypeId(deliverableTypeId);

		UpdateDeliverableType command = new UpdateDeliverableType(deliverableTypeToBeUpdated);

		try {
			if(((DeliverableTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{deliverableTypeId}")
	public ResponseEntity<DeliverableType> findById(@PathVariable String deliverableTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliverableTypeId", deliverableTypeId);
		try {

			List<DeliverableType> foundDeliverableType = findDeliverableTypesBy(requestParams).getBody();
			if(foundDeliverableType.size()==1){				return successful(foundDeliverableType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{deliverableTypeId}")
	public ResponseEntity<String> deleteDeliverableTypeByIdUpdated(@PathVariable String deliverableTypeId) throws Exception {
		DeleteDeliverableType command = new DeleteDeliverableType(deliverableTypeId);

		try {
			if (((DeliverableTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
