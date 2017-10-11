package com.skytala.eCommerce.domain.product.relations.containerType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.containerType.command.AddContainerType;
import com.skytala.eCommerce.domain.product.relations.containerType.command.DeleteContainerType;
import com.skytala.eCommerce.domain.product.relations.containerType.command.UpdateContainerType;
import com.skytala.eCommerce.domain.product.relations.containerType.event.ContainerTypeAdded;
import com.skytala.eCommerce.domain.product.relations.containerType.event.ContainerTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.containerType.event.ContainerTypeFound;
import com.skytala.eCommerce.domain.product.relations.containerType.event.ContainerTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.containerType.mapper.ContainerTypeMapper;
import com.skytala.eCommerce.domain.product.relations.containerType.model.ContainerType;
import com.skytala.eCommerce.domain.product.relations.containerType.query.FindContainerTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/containerTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContainerTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainerTypesBy query = new FindContainerTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContainerType> containerTypes =((ContainerTypeFound) Scheduler.execute(query).data()).getContainerTypes();

		if (containerTypes.size() == 1) {
			return ResponseEntity.ok().body(containerTypes.get(0));
		}

		return ResponseEntity.ok().body(containerTypes);

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
	public ResponseEntity<Object> createContainerType(HttpServletRequest request) throws Exception {

		ContainerType containerTypeToBeAdded = new ContainerType();
		try {
			containerTypeToBeAdded = ContainerTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContainerType(containerTypeToBeAdded);

	}

	/**
	 * creates a new ContainerType entry in the ofbiz database
	 * 
	 * @param containerTypeToBeAdded
	 *            the ContainerType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContainerType(@RequestBody ContainerType containerTypeToBeAdded) throws Exception {

		AddContainerType command = new AddContainerType(containerTypeToBeAdded);
		ContainerType containerType = ((ContainerTypeAdded) Scheduler.execute(command).data()).getAddedContainerType();
		
		if (containerType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(containerType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContainerType could not be created.");
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
	public boolean updateContainerType(HttpServletRequest request) throws Exception {

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

		ContainerType containerTypeToBeUpdated = new ContainerType();

		try {
			containerTypeToBeUpdated = ContainerTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContainerType(containerTypeToBeUpdated, containerTypeToBeUpdated.getContainerTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContainerType(@RequestBody ContainerType containerTypeToBeUpdated,
			@PathVariable String containerTypeId) throws Exception {

		containerTypeToBeUpdated.setContainerTypeId(containerTypeId);

		UpdateContainerType command = new UpdateContainerType(containerTypeToBeUpdated);

		try {
			if(((ContainerTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{containerTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String containerTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerTypeId", containerTypeId);
		try {

			Object foundContainerType = findContainerTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContainerType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{containerTypeId}")
	public ResponseEntity<Object> deleteContainerTypeByIdUpdated(@PathVariable String containerTypeId) throws Exception {
		DeleteContainerType command = new DeleteContainerType(containerTypeId);

		try {
			if (((ContainerTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContainerType could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/containerType/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
