package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.geoPoint;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.geoPoint.AddFixedAssetGeoPoint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.geoPoint.DeleteFixedAssetGeoPoint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.geoPoint.UpdateFixedAssetGeoPoint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint.FixedAssetGeoPointAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint.FixedAssetGeoPointDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint.FixedAssetGeoPointFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint.FixedAssetGeoPointUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.geoPoint.FixedAssetGeoPointMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint.FixedAssetGeoPoint;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.geoPoint.FindFixedAssetGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetGeoPoints")
public class FixedAssetGeoPointController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetGeoPointController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetGeoPoint
	 * @return a List with the FixedAssetGeoPoints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetGeoPoint>> findFixedAssetGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetGeoPointsBy query = new FindFixedAssetGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetGeoPoint> fixedAssetGeoPoints =((FixedAssetGeoPointFound) Scheduler.execute(query).data()).getFixedAssetGeoPoints();

		return ResponseEntity.ok().body(fixedAssetGeoPoints);

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
	public ResponseEntity<FixedAssetGeoPoint> createFixedAssetGeoPoint(HttpServletRequest request) throws Exception {

		FixedAssetGeoPoint fixedAssetGeoPointToBeAdded = new FixedAssetGeoPoint();
		try {
			fixedAssetGeoPointToBeAdded = FixedAssetGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFixedAssetGeoPoint(fixedAssetGeoPointToBeAdded);

	}

	/**
	 * creates a new FixedAssetGeoPoint entry in the ofbiz database
	 * 
	 * @param fixedAssetGeoPointToBeAdded
	 *            the FixedAssetGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetGeoPoint> createFixedAssetGeoPoint(@RequestBody FixedAssetGeoPoint fixedAssetGeoPointToBeAdded) throws Exception {

		AddFixedAssetGeoPoint command = new AddFixedAssetGeoPoint(fixedAssetGeoPointToBeAdded);
		FixedAssetGeoPoint fixedAssetGeoPoint = ((FixedAssetGeoPointAdded) Scheduler.execute(command).data()).getAddedFixedAssetGeoPoint();
		
		if (fixedAssetGeoPoint != null) 
			return successful(fixedAssetGeoPoint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetGeoPoint with the specific Id
	 * 
	 * @param fixedAssetGeoPointToBeUpdated
	 *            the FixedAssetGeoPoint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetGeoPoint(@RequestBody FixedAssetGeoPoint fixedAssetGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetGeoPointToBeUpdated.setnull(null);

		UpdateFixedAssetGeoPoint command = new UpdateFixedAssetGeoPoint(fixedAssetGeoPointToBeUpdated);

		try {
			if(((FixedAssetGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetGeoPointId}")
	public ResponseEntity<FixedAssetGeoPoint> findById(@PathVariable String fixedAssetGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetGeoPointId", fixedAssetGeoPointId);
		try {

			List<FixedAssetGeoPoint> foundFixedAssetGeoPoint = findFixedAssetGeoPointsBy(requestParams).getBody();
			if(foundFixedAssetGeoPoint.size()==1){				return successful(foundFixedAssetGeoPoint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetGeoPointId}")
	public ResponseEntity<String> deleteFixedAssetGeoPointByIdUpdated(@PathVariable String fixedAssetGeoPointId) throws Exception {
		DeleteFixedAssetGeoPoint command = new DeleteFixedAssetGeoPoint(fixedAssetGeoPointId);

		try {
			if (((FixedAssetGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
