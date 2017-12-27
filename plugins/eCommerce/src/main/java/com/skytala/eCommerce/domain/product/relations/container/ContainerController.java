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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/containers")
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
	@GetMapping("/find")
	public ResponseEntity<List<Container>> findContainersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainersBy query = new FindContainersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Container> containers =((ContainerFound) Scheduler.execute(query).data()).getContainers();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Container> createContainer(HttpServletRequest request) throws Exception {

		Container containerToBeAdded = new Container();
		try {
			containerToBeAdded = ContainerMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Container> createContainer(@RequestBody Container containerToBeAdded) throws Exception {

		AddContainer command = new AddContainer(containerToBeAdded);
		Container container = ((ContainerAdded) Scheduler.execute(command).data()).getAddedContainer();
		
		if (container != null) 
			return successful(container);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContainer(@RequestBody Container containerToBeUpdated,
			@PathVariable String containerId) throws Exception {

		containerToBeUpdated.setContainerId(containerId);

		UpdateContainer command = new UpdateContainer(containerToBeUpdated);

		try {
			if(((ContainerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{containerId}")
	public ResponseEntity<Container> findById(@PathVariable String containerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerId", containerId);
		try {

			List<Container> foundContainer = findContainersBy(requestParams).getBody();
			if(foundContainer.size()==1){				return successful(foundContainer.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{containerId}")
	public ResponseEntity<String> deleteContainerByIdUpdated(@PathVariable String containerId) throws Exception {
		DeleteContainer command = new DeleteContainer(containerId);

		try {
			if (((ContainerDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
