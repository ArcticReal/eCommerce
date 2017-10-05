package com.skytala.eCommerce.domain.inventoryItemLabel;

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
import com.skytala.eCommerce.domain.inventoryItemLabel.command.AddInventoryItemLabel;
import com.skytala.eCommerce.domain.inventoryItemLabel.command.DeleteInventoryItemLabel;
import com.skytala.eCommerce.domain.inventoryItemLabel.command.UpdateInventoryItemLabel;
import com.skytala.eCommerce.domain.inventoryItemLabel.event.InventoryItemLabelAdded;
import com.skytala.eCommerce.domain.inventoryItemLabel.event.InventoryItemLabelDeleted;
import com.skytala.eCommerce.domain.inventoryItemLabel.event.InventoryItemLabelFound;
import com.skytala.eCommerce.domain.inventoryItemLabel.event.InventoryItemLabelUpdated;
import com.skytala.eCommerce.domain.inventoryItemLabel.mapper.InventoryItemLabelMapper;
import com.skytala.eCommerce.domain.inventoryItemLabel.model.InventoryItemLabel;
import com.skytala.eCommerce.domain.inventoryItemLabel.query.FindInventoryItemLabelsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemLabels")
public class InventoryItemLabelController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabel
	 * @return a List with the InventoryItemLabels
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemLabelsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelsBy query = new FindInventoryItemLabelsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabel> inventoryItemLabels =((InventoryItemLabelFound) Scheduler.execute(query).data()).getInventoryItemLabels();

		if (inventoryItemLabels.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemLabels.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemLabels);

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
	public ResponseEntity<Object> createInventoryItemLabel(HttpServletRequest request) throws Exception {

		InventoryItemLabel inventoryItemLabelToBeAdded = new InventoryItemLabel();
		try {
			inventoryItemLabelToBeAdded = InventoryItemLabelMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemLabel(inventoryItemLabelToBeAdded);

	}

	/**
	 * creates a new InventoryItemLabel entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelToBeAdded
	 *            the InventoryItemLabel thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemLabel(@RequestBody InventoryItemLabel inventoryItemLabelToBeAdded) throws Exception {

		AddInventoryItemLabel command = new AddInventoryItemLabel(inventoryItemLabelToBeAdded);
		InventoryItemLabel inventoryItemLabel = ((InventoryItemLabelAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabel();
		
		if (inventoryItemLabel != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemLabel);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemLabel could not be created.");
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
	public boolean updateInventoryItemLabel(HttpServletRequest request) throws Exception {

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

		InventoryItemLabel inventoryItemLabelToBeUpdated = new InventoryItemLabel();

		try {
			inventoryItemLabelToBeUpdated = InventoryItemLabelMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemLabel(inventoryItemLabelToBeUpdated, inventoryItemLabelToBeUpdated.getInventoryItemLabelId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemLabel with the specific Id
	 * 
	 * @param inventoryItemLabelToBeUpdated
	 *            the InventoryItemLabel thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemLabelId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemLabel(@RequestBody InventoryItemLabel inventoryItemLabelToBeUpdated,
			@PathVariable String inventoryItemLabelId) throws Exception {

		inventoryItemLabelToBeUpdated.setInventoryItemLabelId(inventoryItemLabelId);

		UpdateInventoryItemLabel command = new UpdateInventoryItemLabel(inventoryItemLabelToBeUpdated);

		try {
			if(((InventoryItemLabelUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemLabelId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemLabelId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelId", inventoryItemLabelId);
		try {

			Object foundInventoryItemLabel = findInventoryItemLabelsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemLabel);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemLabelId}")
	public ResponseEntity<Object> deleteInventoryItemLabelByIdUpdated(@PathVariable String inventoryItemLabelId) throws Exception {
		DeleteInventoryItemLabel command = new DeleteInventoryItemLabel(inventoryItemLabelId);

		try {
			if (((InventoryItemLabelDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemLabel could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemLabel/\" plus one of the following: "
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
