package com.skytala.eCommerce.domain.product.relations.physicalInventory;

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
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.AddPhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.DeletePhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.UpdatePhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryAdded;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryDeleted;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryFound;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryUpdated;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper.PhysicalInventoryMapper;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.query.FindPhysicalInventorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/physicalInventorys")
public class PhysicalInventoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PhysicalInventoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PhysicalInventory
	 * @return a List with the PhysicalInventorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPhysicalInventorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPhysicalInventorysBy query = new FindPhysicalInventorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PhysicalInventory> physicalInventorys =((PhysicalInventoryFound) Scheduler.execute(query).data()).getPhysicalInventorys();

		if (physicalInventorys.size() == 1) {
			return ResponseEntity.ok().body(physicalInventorys.get(0));
		}

		return ResponseEntity.ok().body(physicalInventorys);

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
	public ResponseEntity<Object> createPhysicalInventory(HttpServletRequest request) throws Exception {

		PhysicalInventory physicalInventoryToBeAdded = new PhysicalInventory();
		try {
			physicalInventoryToBeAdded = PhysicalInventoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPhysicalInventory(physicalInventoryToBeAdded);

	}

	/**
	 * creates a new PhysicalInventory entry in the ofbiz database
	 * 
	 * @param physicalInventoryToBeAdded
	 *            the PhysicalInventory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPhysicalInventory(@RequestBody PhysicalInventory physicalInventoryToBeAdded) throws Exception {

		AddPhysicalInventory command = new AddPhysicalInventory(physicalInventoryToBeAdded);
		PhysicalInventory physicalInventory = ((PhysicalInventoryAdded) Scheduler.execute(command).data()).getAddedPhysicalInventory();
		
		if (physicalInventory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(physicalInventory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PhysicalInventory could not be created.");
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
	public boolean updatePhysicalInventory(HttpServletRequest request) throws Exception {

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

		PhysicalInventory physicalInventoryToBeUpdated = new PhysicalInventory();

		try {
			physicalInventoryToBeUpdated = PhysicalInventoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePhysicalInventory(physicalInventoryToBeUpdated, physicalInventoryToBeUpdated.getPhysicalInventoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PhysicalInventory with the specific Id
	 * 
	 * @param physicalInventoryToBeUpdated
	 *            the PhysicalInventory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{physicalInventoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePhysicalInventory(@RequestBody PhysicalInventory physicalInventoryToBeUpdated,
			@PathVariable String physicalInventoryId) throws Exception {

		physicalInventoryToBeUpdated.setPhysicalInventoryId(physicalInventoryId);

		UpdatePhysicalInventory command = new UpdatePhysicalInventory(physicalInventoryToBeUpdated);

		try {
			if(((PhysicalInventoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{physicalInventoryId}")
	public ResponseEntity<Object> findById(@PathVariable String physicalInventoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("physicalInventoryId", physicalInventoryId);
		try {

			Object foundPhysicalInventory = findPhysicalInventorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPhysicalInventory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{physicalInventoryId}")
	public ResponseEntity<Object> deletePhysicalInventoryByIdUpdated(@PathVariable String physicalInventoryId) throws Exception {
		DeletePhysicalInventory command = new DeletePhysicalInventory(physicalInventoryId);

		try {
			if (((PhysicalInventoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PhysicalInventory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/physicalInventory/\" plus one of the following: "
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
