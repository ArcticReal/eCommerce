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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/containerGeoPoints")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContainerGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContainerGeoPointsBy query = new FindContainerGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContainerGeoPoint> containerGeoPoints =((ContainerGeoPointFound) Scheduler.execute(query).data()).getContainerGeoPoints();

		if (containerGeoPoints.size() == 1) {
			return ResponseEntity.ok().body(containerGeoPoints.get(0));
		}

		return ResponseEntity.ok().body(containerGeoPoints);

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
	public ResponseEntity<Object> createContainerGeoPoint(HttpServletRequest request) throws Exception {

		ContainerGeoPoint containerGeoPointToBeAdded = new ContainerGeoPoint();
		try {
			containerGeoPointToBeAdded = ContainerGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContainerGeoPoint(containerGeoPointToBeAdded);

	}

	/**
	 * creates a new ContainerGeoPoint entry in the ofbiz database
	 * 
	 * @param containerGeoPointToBeAdded
	 *            the ContainerGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContainerGeoPoint(@RequestBody ContainerGeoPoint containerGeoPointToBeAdded) throws Exception {

		AddContainerGeoPoint command = new AddContainerGeoPoint(containerGeoPointToBeAdded);
		ContainerGeoPoint containerGeoPoint = ((ContainerGeoPointAdded) Scheduler.execute(command).data()).getAddedContainerGeoPoint();
		
		if (containerGeoPoint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(containerGeoPoint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContainerGeoPoint could not be created.");
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
	public boolean updateContainerGeoPoint(HttpServletRequest request) throws Exception {

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

		ContainerGeoPoint containerGeoPointToBeUpdated = new ContainerGeoPoint();

		try {
			containerGeoPointToBeUpdated = ContainerGeoPointMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContainerGeoPoint(containerGeoPointToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContainerGeoPoint(@RequestBody ContainerGeoPoint containerGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		containerGeoPointToBeUpdated.setnull(null);

		UpdateContainerGeoPoint command = new UpdateContainerGeoPoint(containerGeoPointToBeUpdated);

		try {
			if(((ContainerGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{containerGeoPointId}")
	public ResponseEntity<Object> findById(@PathVariable String containerGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("containerGeoPointId", containerGeoPointId);
		try {

			Object foundContainerGeoPoint = findContainerGeoPointsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContainerGeoPoint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{containerGeoPointId}")
	public ResponseEntity<Object> deleteContainerGeoPointByIdUpdated(@PathVariable String containerGeoPointId) throws Exception {
		DeleteContainerGeoPoint command = new DeleteContainerGeoPoint(containerGeoPointId);

		try {
			if (((ContainerGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContainerGeoPoint could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/containerGeoPoint/\" plus one of the following: "
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
