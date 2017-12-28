package com.skytala.eCommerce.domain.product.relations.container.control.geoPoint;

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
import com.skytala.eCommerce.domain.product.relations.container.command.geoPoint.AddContainerGeoPoint;
import com.skytala.eCommerce.domain.product.relations.container.command.geoPoint.DeleteContainerGeoPoint;
import com.skytala.eCommerce.domain.product.relations.container.command.geoPoint.UpdateContainerGeoPoint;
import com.skytala.eCommerce.domain.product.relations.container.event.geoPoint.ContainerGeoPointAdded;
import com.skytala.eCommerce.domain.product.relations.container.event.geoPoint.ContainerGeoPointDeleted;
import com.skytala.eCommerce.domain.product.relations.container.event.geoPoint.ContainerGeoPointFound;
import com.skytala.eCommerce.domain.product.relations.container.event.geoPoint.ContainerGeoPointUpdated;
import com.skytala.eCommerce.domain.product.relations.container.mapper.geoPoint.ContainerGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
import com.skytala.eCommerce.domain.product.relations.container.query.geoPoint.FindContainerGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/container/containerGeoPoints")
public class ContainerGeoPointController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContainerGeoPointController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContainerGeoPoint
	 * @return a List with the ContainerGeoPoints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContainerGeoPoint>> findContainerGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainerGeoPointsBy query = new FindContainerGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContainerGeoPoint> containerGeoPoints =((ContainerGeoPointFound) Scheduler.execute(query).data()).getContainerGeoPoints();

		return ResponseEntity.ok().body(containerGeoPoints);

	}

	/**
	 * creates a new ContainerGeoPoint entry in the ofbiz database
	 * 
	 * @param containerGeoPointToBeAdded
	 *            the ContainerGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContainerGeoPoint> createContainerGeoPoint(@RequestBody ContainerGeoPoint containerGeoPointToBeAdded) throws Exception {

		AddContainerGeoPoint command = new AddContainerGeoPoint(containerGeoPointToBeAdded);
		ContainerGeoPoint containerGeoPoint = ((ContainerGeoPointAdded) Scheduler.execute(command).data()).getAddedContainerGeoPoint();
		
		if (containerGeoPoint != null) 
			return successful(containerGeoPoint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContainerGeoPoint with the specific Id
	 * 
	 * @param containerGeoPointToBeUpdated
	 *            the ContainerGeoPoint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContainerGeoPoint(@RequestBody ContainerGeoPoint containerGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		containerGeoPointToBeUpdated.setnull(null);

		UpdateContainerGeoPoint command = new UpdateContainerGeoPoint(containerGeoPointToBeUpdated);

		try {
			if(((ContainerGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{containerGeoPointId}")
	public ResponseEntity<ContainerGeoPoint> findById(@PathVariable String containerGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerGeoPointId", containerGeoPointId);
		try {

			List<ContainerGeoPoint> foundContainerGeoPoint = findContainerGeoPointsBy(requestParams).getBody();
			if(foundContainerGeoPoint.size()==1){				return successful(foundContainerGeoPoint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{containerGeoPointId}")
	public ResponseEntity<String> deleteContainerGeoPointByIdUpdated(@PathVariable String containerGeoPointId) throws Exception {
		DeleteContainerGeoPoint command = new DeleteContainerGeoPoint(containerGeoPointId);

		try {
			if (((ContainerGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
