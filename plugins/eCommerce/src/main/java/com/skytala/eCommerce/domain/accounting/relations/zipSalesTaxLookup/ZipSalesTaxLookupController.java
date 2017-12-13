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
	public ResponseEntity<Object> findZipSalesTaxLookupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindZipSalesTaxLookupsBy query = new FindZipSalesTaxLookupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ZipSalesTaxLookup> zipSalesTaxLookups =((ZipSalesTaxLookupFound) Scheduler.execute(query).data()).getZipSalesTaxLookups();

		if (zipSalesTaxLookups.size() == 1) {
			return ResponseEntity.ok().body(zipSalesTaxLookups.get(0));
		}

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
	public ResponseEntity<Object> createZipSalesTaxLookup(HttpServletRequest request) throws Exception {

		ZipSalesTaxLookup zipSalesTaxLookupToBeAdded = new ZipSalesTaxLookup();
		try {
			zipSalesTaxLookupToBeAdded = ZipSalesTaxLookupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createZipSalesTaxLookup(@RequestBody ZipSalesTaxLookup zipSalesTaxLookupToBeAdded) throws Exception {

		AddZipSalesTaxLookup command = new AddZipSalesTaxLookup(zipSalesTaxLookupToBeAdded);
		ZipSalesTaxLookup zipSalesTaxLookup = ((ZipSalesTaxLookupAdded) Scheduler.execute(command).data()).getAddedZipSalesTaxLookup();
		
		if (zipSalesTaxLookup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(zipSalesTaxLookup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ZipSalesTaxLookup could not be created.");
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
	public boolean updateZipSalesTaxLookup(HttpServletRequest request) throws Exception {

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

		ZipSalesTaxLookup zipSalesTaxLookupToBeUpdated = new ZipSalesTaxLookup();

		try {
			zipSalesTaxLookupToBeUpdated = ZipSalesTaxLookupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateZipSalesTaxLookup(zipSalesTaxLookupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateZipSalesTaxLookup(@RequestBody ZipSalesTaxLookup zipSalesTaxLookupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		zipSalesTaxLookupToBeUpdated.setnull(null);

		UpdateZipSalesTaxLookup command = new UpdateZipSalesTaxLookup(zipSalesTaxLookupToBeUpdated);

		try {
			if(((ZipSalesTaxLookupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{zipSalesTaxLookupId}")
	public ResponseEntity<Object> findById(@PathVariable String zipSalesTaxLookupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("zipSalesTaxLookupId", zipSalesTaxLookupId);
		try {

			Object foundZipSalesTaxLookup = findZipSalesTaxLookupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundZipSalesTaxLookup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{zipSalesTaxLookupId}")
	public ResponseEntity<Object> deleteZipSalesTaxLookupByIdUpdated(@PathVariable String zipSalesTaxLookupId) throws Exception {
		DeleteZipSalesTaxLookup command = new DeleteZipSalesTaxLookup(zipSalesTaxLookupId);

		try {
			if (((ZipSalesTaxLookupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ZipSalesTaxLookup could not be deleted");

	}

}
