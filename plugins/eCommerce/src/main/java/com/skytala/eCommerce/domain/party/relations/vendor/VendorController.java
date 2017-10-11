package com.skytala.eCommerce.domain.party.relations.vendor;

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
import com.skytala.eCommerce.domain.party.relations.vendor.command.AddVendor;
import com.skytala.eCommerce.domain.party.relations.vendor.command.DeleteVendor;
import com.skytala.eCommerce.domain.party.relations.vendor.command.UpdateVendor;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorAdded;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorDeleted;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorFound;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorUpdated;
import com.skytala.eCommerce.domain.party.relations.vendor.mapper.VendorMapper;
import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
import com.skytala.eCommerce.domain.party.relations.vendor.query.FindVendorsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VendorController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Vendor
	 * @return a List with the Vendors
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findVendorsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVendorsBy query = new FindVendorsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Vendor> vendors =((VendorFound) Scheduler.execute(query).data()).getVendors();

		if (vendors.size() == 1) {
			return ResponseEntity.ok().body(vendors.get(0));
		}

		return ResponseEntity.ok().body(vendors);

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
	public ResponseEntity<Object> createVendor(HttpServletRequest request) throws Exception {

		Vendor vendorToBeAdded = new Vendor();
		try {
			vendorToBeAdded = VendorMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createVendor(vendorToBeAdded);

	}

	/**
	 * creates a new Vendor entry in the ofbiz database
	 * 
	 * @param vendorToBeAdded
	 *            the Vendor thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createVendor(@RequestBody Vendor vendorToBeAdded) throws Exception {

		AddVendor command = new AddVendor(vendorToBeAdded);
		Vendor vendor = ((VendorAdded) Scheduler.execute(command).data()).getAddedVendor();
		
		if (vendor != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(vendor);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Vendor could not be created.");
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
	public boolean updateVendor(HttpServletRequest request) throws Exception {

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

		Vendor vendorToBeUpdated = new Vendor();

		try {
			vendorToBeUpdated = VendorMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateVendor(vendorToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Vendor with the specific Id
	 * 
	 * @param vendorToBeUpdated
	 *            the Vendor thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateVendor(@RequestBody Vendor vendorToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		vendorToBeUpdated.setnull(null);

		UpdateVendor command = new UpdateVendor(vendorToBeUpdated);

		try {
			if(((VendorUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{vendorId}")
	public ResponseEntity<Object> findById(@PathVariable String vendorId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("vendorId", vendorId);
		try {

			Object foundVendor = findVendorsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundVendor);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{vendorId}")
	public ResponseEntity<Object> deleteVendorByIdUpdated(@PathVariable String vendorId) throws Exception {
		DeleteVendor command = new DeleteVendor(vendorId);

		try {
			if (((VendorDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Vendor could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/vendor/\" plus one of the following: "
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
