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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/vendors")
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
	@GetMapping("/find")
	public ResponseEntity<List<Vendor>> findVendorsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVendorsBy query = new FindVendorsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Vendor> vendors =((VendorFound) Scheduler.execute(query).data()).getVendors();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Vendor> createVendor(HttpServletRequest request) throws Exception {

		Vendor vendorToBeAdded = new Vendor();
		try {
			vendorToBeAdded = VendorMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendorToBeAdded) throws Exception {

		AddVendor command = new AddVendor(vendorToBeAdded);
		Vendor vendor = ((VendorAdded) Scheduler.execute(command).data()).getAddedVendor();
		
		if (vendor != null) 
			return successful(vendor);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateVendor(@RequestBody Vendor vendorToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		vendorToBeUpdated.setnull(null);

		UpdateVendor command = new UpdateVendor(vendorToBeUpdated);

		try {
			if(((VendorUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{vendorId}")
	public ResponseEntity<Vendor> findById(@PathVariable String vendorId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("vendorId", vendorId);
		try {

			List<Vendor> foundVendor = findVendorsBy(requestParams).getBody();
			if(foundVendor.size()==1){				return successful(foundVendor.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{vendorId}")
	public ResponseEntity<String> deleteVendorByIdUpdated(@PathVariable String vendorId) throws Exception {
		DeleteVendor command = new DeleteVendor(vendorId);

		try {
			if (((VendorDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
