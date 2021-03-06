package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup;

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
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.command.AddZipSalesRuleLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.command.DeleteZipSalesRuleLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.command.UpdateZipSalesRuleLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupAdded;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupDeleted;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupFound;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.mapper.ZipSalesRuleLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.query.FindZipSalesRuleLookupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/zipSalesRuleLookups")
public class ZipSalesRuleLookupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ZipSalesRuleLookupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ZipSalesRuleLookup
	 * @return a List with the ZipSalesRuleLookups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ZipSalesRuleLookup>> findZipSalesRuleLookupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindZipSalesRuleLookupsBy query = new FindZipSalesRuleLookupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ZipSalesRuleLookup> zipSalesRuleLookups =((ZipSalesRuleLookupFound) Scheduler.execute(query).data()).getZipSalesRuleLookups();

		return ResponseEntity.ok().body(zipSalesRuleLookups);

	}

	/**
	 * creates a new ZipSalesRuleLookup entry in the ofbiz database
	 * 
	 * @param zipSalesRuleLookupToBeAdded
	 *            the ZipSalesRuleLookup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ZipSalesRuleLookup> createZipSalesRuleLookup(@RequestBody ZipSalesRuleLookup zipSalesRuleLookupToBeAdded) throws Exception {

		AddZipSalesRuleLookup command = new AddZipSalesRuleLookup(zipSalesRuleLookupToBeAdded);
		ZipSalesRuleLookup zipSalesRuleLookup = ((ZipSalesRuleLookupAdded) Scheduler.execute(command).data()).getAddedZipSalesRuleLookup();
		
		if (zipSalesRuleLookup != null) 
			return successful(zipSalesRuleLookup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ZipSalesRuleLookup with the specific Id
	 * 
	 * @param zipSalesRuleLookupToBeUpdated
	 *            the ZipSalesRuleLookup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateZipSalesRuleLookup(@RequestBody ZipSalesRuleLookup zipSalesRuleLookupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		zipSalesRuleLookupToBeUpdated.setnull(null);

		UpdateZipSalesRuleLookup command = new UpdateZipSalesRuleLookup(zipSalesRuleLookupToBeUpdated);

		try {
			if(((ZipSalesRuleLookupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{zipSalesRuleLookupId}")
	public ResponseEntity<ZipSalesRuleLookup> findById(@PathVariable String zipSalesRuleLookupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("zipSalesRuleLookupId", zipSalesRuleLookupId);
		try {

			List<ZipSalesRuleLookup> foundZipSalesRuleLookup = findZipSalesRuleLookupsBy(requestParams).getBody();
			if(foundZipSalesRuleLookup.size()==1){				return successful(foundZipSalesRuleLookup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{zipSalesRuleLookupId}")
	public ResponseEntity<String> deleteZipSalesRuleLookupByIdUpdated(@PathVariable String zipSalesRuleLookupId) throws Exception {
		DeleteZipSalesRuleLookup command = new DeleteZipSalesRuleLookup(zipSalesRuleLookupId);

		try {
			if (((ZipSalesRuleLookupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
