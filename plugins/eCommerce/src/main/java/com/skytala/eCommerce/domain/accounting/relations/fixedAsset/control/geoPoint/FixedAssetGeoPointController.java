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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetGeoPointsBy query = new FindFixedAssetGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetGeoPoint> fixedAssetGeoPoints =((FixedAssetGeoPointFound) Scheduler.execute(query).data()).getFixedAssetGeoPoints();

		if (fixedAssetGeoPoints.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetGeoPoints.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createFixedAssetGeoPoint(HttpServletRequest request) throws Exception {

		FixedAssetGeoPoint fixedAssetGeoPointToBeAdded = new FixedAssetGeoPoint();
		try {
			fixedAssetGeoPointToBeAdded = FixedAssetGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createFixedAssetGeoPoint(@RequestBody FixedAssetGeoPoint fixedAssetGeoPointToBeAdded) throws Exception {

		AddFixedAssetGeoPoint command = new AddFixedAssetGeoPoint(fixedAssetGeoPointToBeAdded);
		FixedAssetGeoPoint fixedAssetGeoPoint = ((FixedAssetGeoPointAdded) Scheduler.execute(command).data()).getAddedFixedAssetGeoPoint();
		
		if (fixedAssetGeoPoint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetGeoPoint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetGeoPoint could not be created.");
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
	public boolean updateFixedAssetGeoPoint(HttpServletRequest request) throws Exception {

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

		FixedAssetGeoPoint fixedAssetGeoPointToBeUpdated = new FixedAssetGeoPoint();

		try {
			fixedAssetGeoPointToBeUpdated = FixedAssetGeoPointMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetGeoPoint(fixedAssetGeoPointToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFixedAssetGeoPoint(@RequestBody FixedAssetGeoPoint fixedAssetGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetGeoPointToBeUpdated.setnull(null);

		UpdateFixedAssetGeoPoint command = new UpdateFixedAssetGeoPoint(fixedAssetGeoPointToBeUpdated);

		try {
			if(((FixedAssetGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetGeoPointId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetGeoPointId", fixedAssetGeoPointId);
		try {

			Object foundFixedAssetGeoPoint = findFixedAssetGeoPointsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetGeoPoint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetGeoPointId}")
	public ResponseEntity<Object> deleteFixedAssetGeoPointByIdUpdated(@PathVariable String fixedAssetGeoPointId) throws Exception {
		DeleteFixedAssetGeoPoint command = new DeleteFixedAssetGeoPoint(fixedAssetGeoPointId);

		try {
			if (((FixedAssetGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetGeoPoint could not be deleted");

	}

}
