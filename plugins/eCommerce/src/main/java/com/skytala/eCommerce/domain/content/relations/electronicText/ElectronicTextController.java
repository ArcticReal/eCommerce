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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ElectronicText>> findElectronicTextsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindElectronicTextsBy query = new FindElectronicTextsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ElectronicText> electronicTexts =((ElectronicTextFound) Scheduler.execute(query).data()).getElectronicTexts();

		return ResponseEntity.ok().body(electronicTexts);

	}

	/**
	 * creates a new ElectronicText entry in the ofbiz database
	 * 
	 * @param electronicTextToBeAdded
	 *            the ElectronicText thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ElectronicText> createElectronicText(@RequestBody ElectronicText electronicTextToBeAdded) throws Exception {

		AddElectronicText command = new AddElectronicText(electronicTextToBeAdded);
		ElectronicText electronicText = ((ElectronicTextAdded) Scheduler.execute(command).data()).getAddedElectronicText();
		
		if (electronicText != null) 
			return successful(electronicText);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateElectronicText(@RequestBody ElectronicText electronicTextToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		electronicTextToBeUpdated.setnull(null);

		UpdateElectronicText command = new UpdateElectronicText(electronicTextToBeUpdated);

		try {
			if(((ElectronicTextUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{electronicTextId}")
	public ResponseEntity<ElectronicText> findById(@PathVariable String electronicTextId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("electronicTextId", electronicTextId);
		try {

			List<ElectronicText> foundElectronicText = findElectronicTextsBy(requestParams).getBody();
			if(foundElectronicText.size()==1){				return successful(foundElectronicText.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{electronicTextId}")
	public ResponseEntity<String> deleteElectronicTextByIdUpdated(@PathVariable String electronicTextId) throws Exception {
		DeleteElectronicText command = new DeleteElectronicText(electronicTextId);

		try {
			if (((ElectronicTextDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
