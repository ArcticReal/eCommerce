package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.meter;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.meter.AddFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.meter.DeleteFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.meter.UpdateFixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter.FixedAssetMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter.FixedAssetMeterDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter.FixedAssetMeterFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter.FixedAssetMeterUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.meter.FixedAssetMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.meter.FixedAssetMeter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.meter.FindFixedAssetMetersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetMeters")
public class FixedAssetMeterController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMeterController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMeter
	 * @return a List with the FixedAssetMeters
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetMeter>> findFixedAssetMetersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMetersBy query = new FindFixedAssetMetersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMeter> fixedAssetMeters =((FixedAssetMeterFound) Scheduler.execute(query).data()).getFixedAssetMeters();

		return ResponseEntity.ok().body(fixedAssetMeters);

	}

	/**
	 * creates a new FixedAssetMeter entry in the ofbiz database
	 * 
	 * @param fixedAssetMeterToBeAdded
	 *            the FixedAssetMeter thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetMeter> createFixedAssetMeter(@RequestBody FixedAssetMeter fixedAssetMeterToBeAdded) throws Exception {

		AddFixedAssetMeter command = new AddFixedAssetMeter(fixedAssetMeterToBeAdded);
		FixedAssetMeter fixedAssetMeter = ((FixedAssetMeterAdded) Scheduler.execute(command).data()).getAddedFixedAssetMeter();
		
		if (fixedAssetMeter != null) 
			return successful(fixedAssetMeter);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetMeter with the specific Id
	 * 
	 * @param fixedAssetMeterToBeUpdated
	 *            the FixedAssetMeter thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetMeter(@RequestBody FixedAssetMeter fixedAssetMeterToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetMeterToBeUpdated.setnull(null);

		UpdateFixedAssetMeter command = new UpdateFixedAssetMeter(fixedAssetMeterToBeUpdated);

		try {
			if(((FixedAssetMeterUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetMeterId}")
	public ResponseEntity<FixedAssetMeter> findById(@PathVariable String fixedAssetMeterId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMeterId", fixedAssetMeterId);
		try {

			List<FixedAssetMeter> foundFixedAssetMeter = findFixedAssetMetersBy(requestParams).getBody();
			if(foundFixedAssetMeter.size()==1){				return successful(foundFixedAssetMeter.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetMeterId}")
	public ResponseEntity<String> deleteFixedAssetMeterByIdUpdated(@PathVariable String fixedAssetMeterId) throws Exception {
		DeleteFixedAssetMeter command = new DeleteFixedAssetMeter(fixedAssetMeterId);

		try {
			if (((FixedAssetMeterDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
