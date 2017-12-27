package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup;

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
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.command.AddZipSalesTaxLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.command.DeleteZipSalesTaxLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.command.UpdateZipSalesTaxLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupAdded;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupDeleted;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupFound;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.mapper.ZipSalesTaxLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.query.FindZipSalesTaxLookupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/zipSalesTaxLookups")
public class ZipSalesTaxLookupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ZipSalesTaxLookupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ZipSalesTaxLookup
	 * @return a List with the ZipSalesTaxLookups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ZipSalesTaxLookup>> findZipSalesTaxLookupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindZipSalesTaxLookupsBy query = new FindZipSalesTaxLookupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ZipSalesTaxLookup> zipSalesTaxLookups =((ZipSalesTaxLookupFound) Scheduler.execute(query).data()).getZipSalesTaxLookups();

		return ResponseEntity.ok().body(zipSalesTaxLookups);

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
	public ResponseEntity<ZipSalesTaxLookup> createZipSalesTaxLookup(HttpServletRequest request) throws Exception {

		ZipSalesTaxLookup zipSalesTaxLookupToBeAdded = new ZipSalesTaxLookup();
		try {
			zipSalesTaxLookupToBeAdded = ZipSalesTaxLookupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createZipSalesTaxLookup(zipSalesTaxLookupToBeAdded);

	}

	/**
	 * creates a new ZipSalesTaxLookup entry in the ofbiz database
	 * 
	 * @param zipSalesTaxLookupToBeAdded
	 *            the ZipSalesTaxLookup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ZipSalesTaxLookup> createZipSalesTaxLookup(@RequestBody ZipSalesTaxLookup zipSalesTaxLookupToBeAdded) throws Exception {

		AddZipSalesTaxLookup command = new AddZipSalesTaxLookup(zipSalesTaxLookupToBeAdded);
		ZipSalesTaxLookup zipSalesTaxLookup = ((ZipSalesTaxLookupAdded) Scheduler.execute(command).data()).getAddedZipSalesTaxLookup();
		
		if (zipSalesTaxLookup != null) 
			return successful(zipSalesTaxLookup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ZipSalesTaxLookup with the specific Id
	 * 
	 * @param zipSalesTaxLookupToBeUpdated
	 *            the ZipSalesTaxLookup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateZipSalesTaxLookup(@RequestBody ZipSalesTaxLookup zipSalesTaxLookupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		zipSalesTaxLookupToBeUpdated.setnull(null);

		UpdateZipSalesTaxLookup command = new UpdateZipSalesTaxLookup(zipSalesTaxLookupToBeUpdated);

		try {
			if(((ZipSalesTaxLookupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{zipSalesTaxLookupId}")
	public ResponseEntity<ZipSalesTaxLookup> findById(@PathVariable String zipSalesTaxLookupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("zipSalesTaxLookupId", zipSalesTaxLookupId);
		try {

			List<ZipSalesTaxLookup> foundZipSalesTaxLookup = findZipSalesTaxLookupsBy(requestParams).getBody();
			if(foundZipSalesTaxLookup.size()==1){				return successful(foundZipSalesTaxLookup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{zipSalesTaxLookupId}")
	public ResponseEntity<String> deleteZipSalesTaxLookupByIdUpdated(@PathVariable String zipSalesTaxLookupId) throws Exception {
		DeleteZipSalesTaxLookup command = new DeleteZipSalesTaxLookup(zipSalesTaxLookupId);

		try {
			if (((ZipSalesTaxLookupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
