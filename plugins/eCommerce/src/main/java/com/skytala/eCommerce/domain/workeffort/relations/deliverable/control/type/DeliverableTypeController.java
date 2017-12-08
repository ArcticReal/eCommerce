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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/deliverableTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDeliverableTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverableTypesBy query = new FindDeliverableTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DeliverableType> deliverableTypes =((DeliverableTypeFound) Scheduler.execute(query).data()).getDeliverableTypes();

		if (deliverableTypes.size() == 1) {
			return ResponseEntity.ok().body(deliverableTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createDeliverableType(HttpServletRequest request) throws Exception {

		DeliverableType deliverableTypeToBeAdded = new DeliverableType();
		try {
			deliverableTypeToBeAdded = DeliverableTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createDeliverableType(@RequestBody DeliverableType deliverableTypeToBeAdded) throws Exception {

		AddDeliverableType command = new AddDeliverableType(deliverableTypeToBeAdded);
		DeliverableType deliverableType = ((DeliverableTypeAdded) Scheduler.execute(command).data()).getAddedDeliverableType();
		
		if (deliverableType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(deliverableType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DeliverableType could not be created.");
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
	public boolean updateDeliverableType(HttpServletRequest request) throws Exception {

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

		DeliverableType deliverableTypeToBeUpdated = new DeliverableType();

		try {
			deliverableTypeToBeUpdated = DeliverableTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDeliverableType(deliverableTypeToBeUpdated, deliverableTypeToBeUpdated.getDeliverableTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDeliverableType(@RequestBody DeliverableType deliverableTypeToBeUpdated,
			@PathVariable String deliverableTypeId) throws Exception {

		deliverableTypeToBeUpdated.setDeliverableTypeId(deliverableTypeId);

		UpdateDeliverableType command = new UpdateDeliverableType(deliverableTypeToBeUpdated);

		try {
			if(((DeliverableTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{deliverableTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String deliverableTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliverableTypeId", deliverableTypeId);
		try {

			Object foundDeliverableType = findDeliverableTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDeliverableType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{deliverableTypeId}")
	public ResponseEntity<Object> deleteDeliverableTypeByIdUpdated(@PathVariable String deliverableTypeId) throws Exception {
		DeleteDeliverableType command = new DeleteDeliverableType(deliverableTypeId);

		try {
			if (((DeliverableTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DeliverableType could not be deleted");

	}

}
