package com.skytala.eCommerce.domain.product.relations.container.control.type;

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
import com.skytala.eCommerce.domain.product.relations.container.command.type.AddContainerType;
import com.skytala.eCommerce.domain.product.relations.container.command.type.DeleteContainerType;
import com.skytala.eCommerce.domain.product.relations.container.command.type.UpdateContainerType;
import com.skytala.eCommerce.domain.product.relations.container.event.type.ContainerTypeAdded;
import com.skytala.eCommerce.domain.product.relations.container.event.type.ContainerTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.container.event.type.ContainerTypeFound;
import com.skytala.eCommerce.domain.product.relations.container.event.type.ContainerTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.container.mapper.type.ContainerTypeMapper;
import com.skytala.eCommerce.domain.product.relations.container.model.type.ContainerType;
import com.skytala.eCommerce.domain.product.relations.container.query.type.FindContainerTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/container/containerTypes")
public class ContainerTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContainerTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContainerType
	 * @return a List with the ContainerTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContainerType>> findContainerTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainerTypesBy query = new FindContainerTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContainerType> containerTypes =((ContainerTypeFound) Scheduler.execute(query).data()).getContainerTypes();

		return ResponseEntity.ok().body(containerTypes);

	}

	/**
	 * creates a new ContainerType entry in the ofbiz database
	 * 
	 * @param containerTypeToBeAdded
	 *            the ContainerType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContainerType> createContainerType(@RequestBody ContainerType containerTypeToBeAdded) throws Exception {

		AddContainerType command = new AddContainerType(containerTypeToBeAdded);
		ContainerType containerType = ((ContainerTypeAdded) Scheduler.execute(command).data()).getAddedContainerType();
		
		if (containerType != null) 
			return successful(containerType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContainerType with the specific Id
	 * 
	 * @param containerTypeToBeUpdated
	 *            the ContainerType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{containerTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContainerType(@RequestBody ContainerType containerTypeToBeUpdated,
			@PathVariable String containerTypeId) throws Exception {

		containerTypeToBeUpdated.setContainerTypeId(containerTypeId);

		UpdateContainerType command = new UpdateContainerType(containerTypeToBeUpdated);

		try {
			if(((ContainerTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{containerTypeId}")
	public ResponseEntity<ContainerType> findById(@PathVariable String containerTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerTypeId", containerTypeId);
		try {

			List<ContainerType> foundContainerType = findContainerTypesBy(requestParams).getBody();
			if(foundContainerType.size()==1){				return successful(foundContainerType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{containerTypeId}")
	public ResponseEntity<String> deleteContainerTypeByIdUpdated(@PathVariable String containerTypeId) throws Exception {
		DeleteContainerType command = new DeleteContainerType(containerTypeId);

		try {
			if (((ContainerTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
