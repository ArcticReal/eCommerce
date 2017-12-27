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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<MetaDataPredicate>> findMetaDataPredicatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMetaDataPredicatesBy query = new FindMetaDataPredicatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MetaDataPredicate> metaDataPredicates =((MetaDataPredicateFound) Scheduler.execute(query).data()).getMetaDataPredicates();

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
	public ResponseEntity<MetaDataPredicate> createMetaDataPredicate(HttpServletRequest request) throws Exception {

		MetaDataPredicate metaDataPredicateToBeAdded = new MetaDataPredicate();
		try {
			metaDataPredicateToBeAdded = MetaDataPredicateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<MetaDataPredicate> createMetaDataPredicate(@RequestBody MetaDataPredicate metaDataPredicateToBeAdded) throws Exception {

		AddMetaDataPredicate command = new AddMetaDataPredicate(metaDataPredicateToBeAdded);
		MetaDataPredicate metaDataPredicate = ((MetaDataPredicateAdded) Scheduler.execute(command).data()).getAddedMetaDataPredicate();
		
		if (metaDataPredicate != null) 
			return successful(metaDataPredicate);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateMetaDataPredicate(@RequestBody MetaDataPredicate metaDataPredicateToBeUpdated,
			@PathVariable String metaDataPredicateId) throws Exception {

		metaDataPredicateToBeUpdated.setMetaDataPredicateId(metaDataPredicateId);

		UpdateMetaDataPredicate command = new UpdateMetaDataPredicate(metaDataPredicateToBeUpdated);

		try {
			if(((MetaDataPredicateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{metaDataPredicateId}")
	public ResponseEntity<MetaDataPredicate> findById(@PathVariable String metaDataPredicateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("metaDataPredicateId", metaDataPredicateId);
		try {

			List<MetaDataPredicate> foundMetaDataPredicate = findMetaDataPredicatesBy(requestParams).getBody();
			if(foundMetaDataPredicate.size()==1){				return successful(foundMetaDataPredicate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{metaDataPredicateId}")
	public ResponseEntity<String> deleteMetaDataPredicateByIdUpdated(@PathVariable String metaDataPredicateId) throws Exception {
		DeleteMetaDataPredicate command = new DeleteMetaDataPredicate(metaDataPredicateId);

		try {
			if (((MetaDataPredicateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
