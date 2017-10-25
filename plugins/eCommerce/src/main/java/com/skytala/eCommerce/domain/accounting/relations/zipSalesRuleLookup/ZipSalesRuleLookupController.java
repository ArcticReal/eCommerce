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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/zipSalesRuleLookups")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findZipSalesRuleLookupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindZipSalesRuleLookupsBy query = new FindZipSalesRuleLookupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ZipSalesRuleLookup> zipSalesRuleLookups =((ZipSalesRuleLookupFound) Scheduler.execute(query).data()).getZipSalesRuleLookups();

		if (zipSalesRuleLookups.size() == 1) {
			return ResponseEntity.ok().body(zipSalesRuleLookups.get(0));
		}

		return ResponseEntity.ok().body(zipSalesRuleLookups);

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
	public ResponseEntity<Object> createZipSalesRuleLookup(HttpServletRequest request) throws Exception {

		ZipSalesRuleLookup zipSalesRuleLookupToBeAdded = new ZipSalesRuleLookup();
		try {
			zipSalesRuleLookupToBeAdded = ZipSalesRuleLookupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createZipSalesRuleLookup(zipSalesRuleLookupToBeAdded);

	}

	/**
	 * creates a new ZipSalesRuleLookup entry in the ofbiz database
	 * 
	 * @param zipSalesRuleLookupToBeAdded
	 *            the ZipSalesRuleLookup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createZipSalesRuleLookup(@RequestBody ZipSalesRuleLookup zipSalesRuleLookupToBeAdded) throws Exception {

		AddZipSalesRuleLookup command = new AddZipSalesRuleLookup(zipSalesRuleLookupToBeAdded);
		ZipSalesRuleLookup zipSalesRuleLookup = ((ZipSalesRuleLookupAdded) Scheduler.execute(command).data()).getAddedZipSalesRuleLookup();
		
		if (zipSalesRuleLookup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(zipSalesRuleLookup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ZipSalesRuleLookup could not be created.");
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
	public boolean updateZipSalesRuleLookup(HttpServletRequest request) throws Exception {

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

		ZipSalesRuleLookup zipSalesRuleLookupToBeUpdated = new ZipSalesRuleLookup();

		try {
			zipSalesRuleLookupToBeUpdated = ZipSalesRuleLookupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateZipSalesRuleLookup(zipSalesRuleLookupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateZipSalesRuleLookup(@RequestBody ZipSalesRuleLookup zipSalesRuleLookupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		zipSalesRuleLookupToBeUpdated.setnull(null);

		UpdateZipSalesRuleLookup command = new UpdateZipSalesRuleLookup(zipSalesRuleLookupToBeUpdated);

		try {
			if(((ZipSalesRuleLookupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{zipSalesRuleLookupId}")
	public ResponseEntity<Object> findById(@PathVariable String zipSalesRuleLookupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("zipSalesRuleLookupId", zipSalesRuleLookupId);
		try {

			Object foundZipSalesRuleLookup = findZipSalesRuleLookupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundZipSalesRuleLookup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{zipSalesRuleLookupId}")
	public ResponseEntity<Object> deleteZipSalesRuleLookupByIdUpdated(@PathVariable String zipSalesRuleLookupId) throws Exception {
		DeleteZipSalesRuleLookup command = new DeleteZipSalesRuleLookup(zipSalesRuleLookupId);

		try {
			if (((ZipSalesRuleLookupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ZipSalesRuleLookup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/zipSalesRuleLookup/\" plus one of the following: "
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
