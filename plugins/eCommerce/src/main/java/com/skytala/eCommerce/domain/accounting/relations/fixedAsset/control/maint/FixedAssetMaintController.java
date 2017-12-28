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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<FixedAssetMaint>> findFixedAssetMaintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMaintsBy query = new FindFixedAssetMaintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMaint> fixedAssetMaints =((FixedAssetMaintFound) Scheduler.execute(query).data()).getFixedAssetMaints();

		return ResponseEntity.ok().body(fixedAssetMaints);

	}

	/**
	 * creates a new FixedAssetMaint entry in the ofbiz database
	 * 
	 * @param fixedAssetMaintToBeAdded
	 *            the FixedAssetMaint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetMaint> createFixedAssetMaint(@RequestBody FixedAssetMaint fixedAssetMaintToBeAdded) throws Exception {

		AddFixedAssetMaint command = new AddFixedAssetMaint(fixedAssetMaintToBeAdded);
		FixedAssetMaint fixedAssetMaint = ((FixedAssetMaintAdded) Scheduler.execute(command).data()).getAddedFixedAssetMaint();
		
		if (fixedAssetMaint != null) 
			return successful(fixedAssetMaint);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFixedAssetMaint(@RequestBody FixedAssetMaint fixedAssetMaintToBeUpdated,
			@PathVariable String maintHistSeqId) throws Exception {

		fixedAssetMaintToBeUpdated.setMaintHistSeqId(maintHistSeqId);

		UpdateFixedAssetMaint command = new UpdateFixedAssetMaint(fixedAssetMaintToBeUpdated);

		try {
			if(((FixedAssetMaintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetMaintId}")
	public ResponseEntity<FixedAssetMaint> findById(@PathVariable String fixedAssetMaintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMaintId", fixedAssetMaintId);
		try {

			List<FixedAssetMaint> foundFixedAssetMaint = findFixedAssetMaintsBy(requestParams).getBody();
			if(foundFixedAssetMaint.size()==1){				return successful(foundFixedAssetMaint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetMaintId}")
	public ResponseEntity<String> deleteFixedAssetMaintByIdUpdated(@PathVariable String fixedAssetMaintId) throws Exception {
		DeleteFixedAssetMaint command = new DeleteFixedAssetMaint(fixedAssetMaintId);

		try {
			if (((FixedAssetMaintDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
