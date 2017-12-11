package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.control.geo;

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
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.geo.AddSegmentGroupGeo;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.geo.DeleteSegmentGroupGeo;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.geo.UpdateSegmentGroupGeo;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo.SegmentGroupGeoUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.geo.SegmentGroupGeoMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.geo.FindSegmentGroupGeosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/segmentGroup/segmentGroupGeos")
public class SegmentGroupGeoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SegmentGroupGeoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SegmentGroupGeo
	 * @return a List with the SegmentGroupGeos
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSegmentGroupGeosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupGeosBy query = new FindSegmentGroupGeosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupGeo> segmentGroupGeos =((SegmentGroupGeoFound) Scheduler.execute(query).data()).getSegmentGroupGeos();

		if (segmentGroupGeos.size() == 1) {
			return ResponseEntity.ok().body(segmentGroupGeos.get(0));
		}

		return ResponseEntity.ok().body(segmentGroupGeos);

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
	public ResponseEntity<Object> createSegmentGroupGeo(HttpServletRequest request) throws Exception {

		SegmentGroupGeo segmentGroupGeoToBeAdded = new SegmentGroupGeo();
		try {
			segmentGroupGeoToBeAdded = SegmentGroupGeoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSegmentGroupGeo(segmentGroupGeoToBeAdded);

	}

	/**
	 * creates a new SegmentGroupGeo entry in the ofbiz database
	 * 
	 * @param segmentGroupGeoToBeAdded
	 *            the SegmentGroupGeo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSegmentGroupGeo(@RequestBody SegmentGroupGeo segmentGroupGeoToBeAdded) throws Exception {

		AddSegmentGroupGeo command = new AddSegmentGroupGeo(segmentGroupGeoToBeAdded);
		SegmentGroupGeo segmentGroupGeo = ((SegmentGroupGeoAdded) Scheduler.execute(command).data()).getAddedSegmentGroupGeo();
		
		if (segmentGroupGeo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(segmentGroupGeo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SegmentGroupGeo could not be created.");
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
	public boolean updateSegmentGroupGeo(HttpServletRequest request) throws Exception {

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

		SegmentGroupGeo segmentGroupGeoToBeUpdated = new SegmentGroupGeo();

		try {
			segmentGroupGeoToBeUpdated = SegmentGroupGeoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSegmentGroupGeo(segmentGroupGeoToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SegmentGroupGeo with the specific Id
	 * 
	 * @param segmentGroupGeoToBeUpdated
	 *            the SegmentGroupGeo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSegmentGroupGeo(@RequestBody SegmentGroupGeo segmentGroupGeoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		segmentGroupGeoToBeUpdated.setnull(null);

		UpdateSegmentGroupGeo command = new UpdateSegmentGroupGeo(segmentGroupGeoToBeUpdated);

		try {
			if(((SegmentGroupGeoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{segmentGroupGeoId}")
	public ResponseEntity<Object> findById(@PathVariable String segmentGroupGeoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupGeoId", segmentGroupGeoId);
		try {

			Object foundSegmentGroupGeo = findSegmentGroupGeosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSegmentGroupGeo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{segmentGroupGeoId}")
	public ResponseEntity<Object> deleteSegmentGroupGeoByIdUpdated(@PathVariable String segmentGroupGeoId) throws Exception {
		DeleteSegmentGroupGeo command = new DeleteSegmentGroupGeo(segmentGroupGeoId);

		try {
			if (((SegmentGroupGeoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SegmentGroupGeo could not be deleted");

	}

}
