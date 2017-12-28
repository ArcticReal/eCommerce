package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.maintMeter;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.AddFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.DeleteFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintMeter.UpdateFixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter.FixedAssetMaintMeterUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintMeter.FixedAssetMaintMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.maintMeter.FindFixedAssetMaintMetersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetMaintMeters")
public class FixedAssetMaintMeterController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMaintMeterController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMaintMeter
	 * @return a List with the FixedAssetMaintMeters
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetMaintMeter>> findFixedAssetMaintMetersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMaintMetersBy query = new FindFixedAssetMaintMetersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMaintMeter> fixedAssetMaintMeters =((FixedAssetMaintMeterFound) Scheduler.execute(query).data()).getFixedAssetMaintMeters();

		return ResponseEntity.ok().body(fixedAssetMaintMeters);

	}

	/**
	 * creates a new FixedAssetMaintMeter entry in the ofbiz database
	 * 
	 * @param fixedAssetMaintMeterToBeAdded
	 *            the FixedAssetMaintMeter thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetMaintMeter> createFixedAssetMaintMeter(@RequestBody FixedAssetMaintMeter fixedAssetMaintMeterToBeAdded) throws Exception {

		AddFixedAssetMaintMeter command = new AddFixedAssetMaintMeter(fixedAssetMaintMeterToBeAdded);
		FixedAssetMaintMeter fixedAssetMaintMeter = ((FixedAssetMaintMeterAdded) Scheduler.execute(command).data()).getAddedFixedAssetMaintMeter();
		
		if (fixedAssetMaintMeter != null) 
			return successful(fixedAssetMaintMeter);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetMaintMeter with the specific Id
	 * 
	 * @param fixedAssetMaintMeterToBeUpdated
	 *            the FixedAssetMaintMeter thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{maintHistSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetMaintMeter(@RequestBody FixedAssetMaintMeter fixedAssetMaintMeterToBeUpdated,
			@PathVariable String maintHistSeqId) throws Exception {

		fixedAssetMaintMeterToBeUpdated.setMaintHistSeqId(maintHistSeqId);

		UpdateFixedAssetMaintMeter command = new UpdateFixedAssetMaintMeter(fixedAssetMaintMeterToBeUpdated);

		try {
			if(((FixedAssetMaintMeterUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetMaintMeterId}")
	public ResponseEntity<FixedAssetMaintMeter> findById(@PathVariable String fixedAssetMaintMeterId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMaintMeterId", fixedAssetMaintMeterId);
		try {

			List<FixedAssetMaintMeter> foundFixedAssetMaintMeter = findFixedAssetMaintMetersBy(requestParams).getBody();
			if(foundFixedAssetMaintMeter.size()==1){				return successful(foundFixedAssetMaintMeter.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetMaintMeterId}")
	public ResponseEntity<String> deleteFixedAssetMaintMeterByIdUpdated(@PathVariable String fixedAssetMaintMeterId) throws Exception {
		DeleteFixedAssetMaintMeter command = new DeleteFixedAssetMaintMeter(fixedAssetMaintMeterId);

		try {
			if (((FixedAssetMaintMeterDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
