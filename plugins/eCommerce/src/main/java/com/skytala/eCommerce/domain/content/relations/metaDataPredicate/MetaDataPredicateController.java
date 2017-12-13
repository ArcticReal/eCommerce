package com.skytala.eCommerce.domain.content.relations.metaDataPredicate;

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
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.command.AddMetaDataPredicate;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.command.DeleteMetaDataPredicate;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.command.UpdateMetaDataPredicate;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateAdded;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateDeleted;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateFound;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateUpdated;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.mapper.MetaDataPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.query.FindMetaDataPredicatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/metaDataPredicates")
public class MetaDataPredicateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MetaDataPredicateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MetaDataPredicate
	 * @return a List with the MetaDataPredicates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findMetaDataPredicatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMetaDataPredicatesBy query = new FindMetaDataPredicatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MetaDataPredicate> metaDataPredicates =((MetaDataPredicateFound) Scheduler.execute(query).data()).getMetaDataPredicates();

		if (metaDataPredicates.size() == 1) {
			return ResponseEntity.ok().body(metaDataPredicates.get(0));
		}

		return ResponseEntity.ok().body(metaDataPredicates);

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
	public ResponseEntity<Object> createMetaDataPredicate(HttpServletRequest request) throws Exception {

		MetaDataPredicate metaDataPredicateToBeAdded = new MetaDataPredicate();
		try {
			metaDataPredicateToBeAdded = MetaDataPredicateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMetaDataPredicate(metaDataPredicateToBeAdded);

	}

	/**
	 * creates a new MetaDataPredicate entry in the ofbiz database
	 * 
	 * @param metaDataPredicateToBeAdded
	 *            the MetaDataPredicate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMetaDataPredicate(@RequestBody MetaDataPredicate metaDataPredicateToBeAdded) throws Exception {

		AddMetaDataPredicate command = new AddMetaDataPredicate(metaDataPredicateToBeAdded);
		MetaDataPredicate metaDataPredicate = ((MetaDataPredicateAdded) Scheduler.execute(command).data()).getAddedMetaDataPredicate();
		
		if (metaDataPredicate != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(metaDataPredicate);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MetaDataPredicate could not be created.");
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
	public boolean updateMetaDataPredicate(HttpServletRequest request) throws Exception {

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

		MetaDataPredicate metaDataPredicateToBeUpdated = new MetaDataPredicate();

		try {
			metaDataPredicateToBeUpdated = MetaDataPredicateMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMetaDataPredicate(metaDataPredicateToBeUpdated, metaDataPredicateToBeUpdated.getMetaDataPredicateId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MetaDataPredicate with the specific Id
	 * 
	 * @param metaDataPredicateToBeUpdated
	 *            the MetaDataPredicate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{metaDataPredicateId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMetaDataPredicate(@RequestBody MetaDataPredicate metaDataPredicateToBeUpdated,
			@PathVariable String metaDataPredicateId) throws Exception {

		metaDataPredicateToBeUpdated.setMetaDataPredicateId(metaDataPredicateId);

		UpdateMetaDataPredicate command = new UpdateMetaDataPredicate(metaDataPredicateToBeUpdated);

		try {
			if(((MetaDataPredicateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{metaDataPredicateId}")
	public ResponseEntity<Object> findById(@PathVariable String metaDataPredicateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("metaDataPredicateId", metaDataPredicateId);
		try {

			Object foundMetaDataPredicate = findMetaDataPredicatesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMetaDataPredicate);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{metaDataPredicateId}")
	public ResponseEntity<Object> deleteMetaDataPredicateByIdUpdated(@PathVariable String metaDataPredicateId) throws Exception {
		DeleteMetaDataPredicate command = new DeleteMetaDataPredicate(metaDataPredicateId);

		try {
			if (((MetaDataPredicateDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MetaDataPredicate could not be deleted");

	}

}
