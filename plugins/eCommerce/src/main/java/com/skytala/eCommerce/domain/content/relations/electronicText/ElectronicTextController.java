package com.skytala.eCommerce.domain.content.relations.electronicText;

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
import com.skytala.eCommerce.domain.content.relations.electronicText.command.AddElectronicText;
import com.skytala.eCommerce.domain.content.relations.electronicText.command.DeleteElectronicText;
import com.skytala.eCommerce.domain.content.relations.electronicText.command.UpdateElectronicText;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextAdded;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextDeleted;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextFound;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextUpdated;
import com.skytala.eCommerce.domain.content.relations.electronicText.mapper.ElectronicTextMapper;
import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
import com.skytala.eCommerce.domain.content.relations.electronicText.query.FindElectronicTextsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/electronicTexts")
public class ElectronicTextController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ElectronicTextController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ElectronicText
	 * @return a List with the ElectronicTexts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findElectronicTextsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindElectronicTextsBy query = new FindElectronicTextsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ElectronicText> electronicTexts =((ElectronicTextFound) Scheduler.execute(query).data()).getElectronicTexts();

		if (electronicTexts.size() == 1) {
			return ResponseEntity.ok().body(electronicTexts.get(0));
		}

		return ResponseEntity.ok().body(electronicTexts);

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
	public ResponseEntity<Object> createElectronicText(HttpServletRequest request) throws Exception {

		ElectronicText electronicTextToBeAdded = new ElectronicText();
		try {
			electronicTextToBeAdded = ElectronicTextMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createElectronicText(electronicTextToBeAdded);

	}

	/**
	 * creates a new ElectronicText entry in the ofbiz database
	 * 
	 * @param electronicTextToBeAdded
	 *            the ElectronicText thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createElectronicText(@RequestBody ElectronicText electronicTextToBeAdded) throws Exception {

		AddElectronicText command = new AddElectronicText(electronicTextToBeAdded);
		ElectronicText electronicText = ((ElectronicTextAdded) Scheduler.execute(command).data()).getAddedElectronicText();
		
		if (electronicText != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(electronicText);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ElectronicText could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateElectronicText(HttpServletRequest request) throws Exception {

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

		ElectronicText electronicTextToBeUpdated = new ElectronicText();

		try {
			electronicTextToBeUpdated = ElectronicTextMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateElectronicText(electronicTextToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ElectronicText with the specific Id
	 * 
	 * @param electronicTextToBeUpdated
	 *            the ElectronicText thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateElectronicText(@RequestBody ElectronicText electronicTextToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		electronicTextToBeUpdated.setnull(null);

		UpdateElectronicText command = new UpdateElectronicText(electronicTextToBeUpdated);

		try {
			if(((ElectronicTextUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{electronicTextId}")
	public ResponseEntity<Object> findById(@PathVariable String electronicTextId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("electronicTextId", electronicTextId);
		try {

			Object foundElectronicText = findElectronicTextsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundElectronicText);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{electronicTextId}")
	public ResponseEntity<Object> deleteElectronicTextByIdUpdated(@PathVariable String electronicTextId) throws Exception {
		DeleteElectronicText command = new DeleteElectronicText(electronicTextId);

		try {
			if (((ElectronicTextDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ElectronicText could not be deleted");

	}

}
