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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<SegmentGroupGeo>> findSegmentGroupGeosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupGeosBy query = new FindSegmentGroupGeosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupGeo> segmentGroupGeos =((SegmentGroupGeoFound) Scheduler.execute(query).data()).getSegmentGroupGeos();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SegmentGroupGeo> createSegmentGroupGeo(HttpServletRequest request) throws Exception {

		SegmentGroupGeo segmentGroupGeoToBeAdded = new SegmentGroupGeo();
		try {
			segmentGroupGeoToBeAdded = SegmentGroupGeoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SegmentGroupGeo> createSegmentGroupGeo(@RequestBody SegmentGroupGeo segmentGroupGeoToBeAdded) throws Exception {

		AddSegmentGroupGeo command = new AddSegmentGroupGeo(segmentGroupGeoToBeAdded);
		SegmentGroupGeo segmentGroupGeo = ((SegmentGroupGeoAdded) Scheduler.execute(command).data()).getAddedSegmentGroupGeo();
		
		if (segmentGroupGeo != null) 
			return successful(segmentGroupGeo);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSegmentGroupGeo(@RequestBody SegmentGroupGeo segmentGroupGeoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		segmentGroupGeoToBeUpdated.setnull(null);

		UpdateSegmentGroupGeo command = new UpdateSegmentGroupGeo(segmentGroupGeoToBeUpdated);

		try {
			if(((SegmentGroupGeoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{segmentGroupGeoId}")
	public ResponseEntity<SegmentGroupGeo> findById(@PathVariable String segmentGroupGeoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupGeoId", segmentGroupGeoId);
		try {

			List<SegmentGroupGeo> foundSegmentGroupGeo = findSegmentGroupGeosBy(requestParams).getBody();
			if(foundSegmentGroupGeo.size()==1){				return successful(foundSegmentGroupGeo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{segmentGroupGeoId}")
	public ResponseEntity<String> deleteSegmentGroupGeoByIdUpdated(@PathVariable String segmentGroupGeoId) throws Exception {
		DeleteSegmentGroupGeo command = new DeleteSegmentGroupGeo(segmentGroupGeoId);

		try {
			if (((SegmentGroupGeoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
