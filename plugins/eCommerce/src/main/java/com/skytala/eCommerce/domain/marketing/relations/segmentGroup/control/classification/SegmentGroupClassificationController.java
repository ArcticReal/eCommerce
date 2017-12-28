package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.control.classification;

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
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.classification.AddSegmentGroupClassification;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.classification.DeleteSegmentGroupClassification;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.classification.UpdateSegmentGroupClassification;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification.SegmentGroupClassificationAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification.SegmentGroupClassificationDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification.SegmentGroupClassificationFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification.SegmentGroupClassificationUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.classification.SegmentGroupClassificationMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.classification.FindSegmentGroupClassificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/segmentGroup/segmentGroupClassifications")
public class SegmentGroupClassificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SegmentGroupClassificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SegmentGroupClassification
	 * @return a List with the SegmentGroupClassifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SegmentGroupClassification>> findSegmentGroupClassificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupClassificationsBy query = new FindSegmentGroupClassificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupClassification> segmentGroupClassifications =((SegmentGroupClassificationFound) Scheduler.execute(query).data()).getSegmentGroupClassifications();

		return ResponseEntity.ok().body(segmentGroupClassifications);

	}

	/**
	 * creates a new SegmentGroupClassification entry in the ofbiz database
	 * 
	 * @param segmentGroupClassificationToBeAdded
	 *            the SegmentGroupClassification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SegmentGroupClassification> createSegmentGroupClassification(@RequestBody SegmentGroupClassification segmentGroupClassificationToBeAdded) throws Exception {

		AddSegmentGroupClassification command = new AddSegmentGroupClassification(segmentGroupClassificationToBeAdded);
		SegmentGroupClassification segmentGroupClassification = ((SegmentGroupClassificationAdded) Scheduler.execute(command).data()).getAddedSegmentGroupClassification();
		
		if (segmentGroupClassification != null) 
			return successful(segmentGroupClassification);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SegmentGroupClassification with the specific Id
	 * 
	 * @param segmentGroupClassificationToBeUpdated
	 *            the SegmentGroupClassification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSegmentGroupClassification(@RequestBody SegmentGroupClassification segmentGroupClassificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		segmentGroupClassificationToBeUpdated.setnull(null);

		UpdateSegmentGroupClassification command = new UpdateSegmentGroupClassification(segmentGroupClassificationToBeUpdated);

		try {
			if(((SegmentGroupClassificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{segmentGroupClassificationId}")
	public ResponseEntity<SegmentGroupClassification> findById(@PathVariable String segmentGroupClassificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupClassificationId", segmentGroupClassificationId);
		try {

			List<SegmentGroupClassification> foundSegmentGroupClassification = findSegmentGroupClassificationsBy(requestParams).getBody();
			if(foundSegmentGroupClassification.size()==1){				return successful(foundSegmentGroupClassification.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{segmentGroupClassificationId}")
	public ResponseEntity<String> deleteSegmentGroupClassificationByIdUpdated(@PathVariable String segmentGroupClassificationId) throws Exception {
		DeleteSegmentGroupClassification command = new DeleteSegmentGroupClassification(segmentGroupClassificationId);

		try {
			if (((SegmentGroupClassificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
