package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.maint;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maint.AddFixedAssetMaint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maint.DeleteFixedAssetMaint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maint.UpdateFixedAssetMaint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint.FixedAssetMaintAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint.FixedAssetMaintDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint.FixedAssetMaintFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint.FixedAssetMaintUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maint.FixedAssetMaintMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maint.FixedAssetMaint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.maint.FindFixedAssetMaintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetMaints")
public class FixedAssetMaintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMaintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMaint
	 * @return a List with the FixedAssetMaints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFixedAssetMaintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMaintsBy query = new FindFixedAssetMaintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMaint> fixedAssetMaints =((FixedAssetMaintFound) Scheduler.execute(query).data()).getFixedAssetMaints();

		if (fixedAssetMaints.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetMaints.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetMaints);

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
	public ResponseEntity<Object> createFixedAssetMaint(HttpServletRequest request) throws Exception {

		FixedAssetMaint fixedAssetMaintToBeAdded = new FixedAssetMaint();
		try {
			fixedAssetMaintToBeAdded = FixedAssetMaintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetMaint(fixedAssetMaintToBeAdded);

	}

	/**
	 * creates a new FixedAssetMaint entry in the ofbiz database
	 * 
	 * @param fixedAssetMaintToBeAdded
	 *            the FixedAssetMaint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetMaint(@RequestBody FixedAssetMaint fixedAssetMaintToBeAdded) throws Exception {

		AddFixedAssetMaint command = new AddFixedAssetMaint(fixedAssetMaintToBeAdded);
		FixedAssetMaint fixedAssetMaint = ((FixedAssetMaintAdded) Scheduler.execute(command).data()).getAddedFixedAssetMaint();
		
		if (fixedAssetMaint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetMaint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetMaint could not be created.");
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
	public boolean updateFixedAssetMaint(HttpServletRequest request) throws Exception {

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

		FixedAssetMaint fixedAssetMaintToBeUpdated = new FixedAssetMaint();

		try {
			fixedAssetMaintToBeUpdated = FixedAssetMaintMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetMaint(fixedAssetMaintToBeUpdated, fixedAssetMaintToBeUpdated.getMaintHistSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetMaint with the specific Id
	 * 
	 * @param fixedAssetMaintToBeUpdated
	 *            the FixedAssetMaint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{maintHistSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetMaint(@RequestBody FixedAssetMaint fixedAssetMaintToBeUpdated,
			@PathVariable String maintHistSeqId) throws Exception {

		fixedAssetMaintToBeUpdated.setMaintHistSeqId(maintHistSeqId);

		UpdateFixedAssetMaint command = new UpdateFixedAssetMaint(fixedAssetMaintToBeUpdated);

		try {
			if(((FixedAssetMaintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetMaintId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetMaintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMaintId", fixedAssetMaintId);
		try {

			Object foundFixedAssetMaint = findFixedAssetMaintsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetMaint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetMaintId}")
	public ResponseEntity<Object> deleteFixedAssetMaintByIdUpdated(@PathVariable String fixedAssetMaintId) throws Exception {
		DeleteFixedAssetMaint command = new DeleteFixedAssetMaint(fixedAssetMaintId);

		try {
			if (((FixedAssetMaintDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetMaint could not be deleted");

	}

}
