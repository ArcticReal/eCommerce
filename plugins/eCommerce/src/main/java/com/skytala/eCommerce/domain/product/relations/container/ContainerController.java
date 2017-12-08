package com.skytala.eCommerce.domain.product.relations.container;

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
import com.skytala.eCommerce.domain.product.relations.container.command.AddContainer;
import com.skytala.eCommerce.domain.product.relations.container.command.DeleteContainer;
import com.skytala.eCommerce.domain.product.relations.container.command.UpdateContainer;
import com.skytala.eCommerce.domain.product.relations.container.event.ContainerAdded;
import com.skytala.eCommerce.domain.product.relations.container.event.ContainerDeleted;
import com.skytala.eCommerce.domain.product.relations.container.event.ContainerFound;
import com.skytala.eCommerce.domain.product.relations.container.event.ContainerUpdated;
import com.skytala.eCommerce.domain.product.relations.container.mapper.ContainerMapper;
import com.skytala.eCommerce.domain.product.relations.container.model.Container;
import com.skytala.eCommerce.domain.product.relations.container.query.FindContainersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/containers")
public class ContainerController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContainerController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Container
	 * @return a List with the Containers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContainersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainersBy query = new FindContainersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Container> containers =((ContainerFound) Scheduler.execute(query).data()).getContainers();

		if (containers.size() == 1) {
			return ResponseEntity.ok().body(containers.get(0));
		}

		return ResponseEntity.ok().body(containers);

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
	public ResponseEntity<Object> createContainer(HttpServletRequest request) throws Exception {

		Container containerToBeAdded = new Container();
		try {
			containerToBeAdded = ContainerMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContainer(containerToBeAdded);

	}

	/**
	 * creates a new Container entry in the ofbiz database
	 * 
	 * @param containerToBeAdded
	 *            the Container thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContainer(@RequestBody Container containerToBeAdded) throws Exception {

		AddContainer command = new AddContainer(containerToBeAdded);
		Container container = ((ContainerAdded) Scheduler.execute(command).data()).getAddedContainer();
		
		if (container != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(container);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Container could not be created.");
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
	public boolean updateContainer(HttpServletRequest request) throws Exception {

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

		Container containerToBeUpdated = new Container();

		try {
			containerToBeUpdated = ContainerMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContainer(containerToBeUpdated, containerToBeUpdated.getContainerId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Container with the specific Id
	 * 
	 * @param containerToBeUpdated
	 *            the Container thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{containerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContainer(@RequestBody Container containerToBeUpdated,
			@PathVariable String containerId) throws Exception {

		containerToBeUpdated.setContainerId(containerId);

		UpdateContainer command = new UpdateContainer(containerToBeUpdated);

		try {
			if(((ContainerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{containerId}")
	public ResponseEntity<Object> findById(@PathVariable String containerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerId", containerId);
		try {

			Object foundContainer = findContainersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContainer);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{containerId}")
	public ResponseEntity<Object> deleteContainerByIdUpdated(@PathVariable String containerId) throws Exception {
		DeleteContainer command = new DeleteContainer(containerId);

		try {
			if (((ContainerDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Container could not be deleted");

	}

}
