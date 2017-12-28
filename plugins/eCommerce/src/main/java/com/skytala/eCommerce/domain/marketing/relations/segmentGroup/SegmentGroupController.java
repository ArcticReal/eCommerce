package com.skytala.eCommerce.domain.marketing.relations.segmentGroup;

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
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.AddSegmentGroup;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.DeleteSegmentGroup;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.UpdateSegmentGroup;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.SegmentGroupMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.FindSegmentGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/segmentGroups")
public class SegmentGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SegmentGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SegmentGroup
	 * @return a List with the SegmentGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SegmentGroup>> findSegmentGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupsBy query = new FindSegmentGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroup> segmentGroups =((SegmentGroupFound) Scheduler.execute(query).data()).getSegmentGroups();

		return ResponseEntity.ok().body(segmentGroups);

	}

	/**
	 * creates a new SegmentGroup entry in the ofbiz database
	 * 
	 * @param segmentGroupToBeAdded
	 *            the SegmentGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SegmentGroup> createSegmentGroup(@RequestBody SegmentGroup segmentGroupToBeAdded) throws Exception {

		AddSegmentGroup command = new AddSegmentGroup(segmentGroupToBeAdded);
		SegmentGroup segmentGroup = ((SegmentGroupAdded) Scheduler.execute(command).data()).getAddedSegmentGroup();
		
		if (segmentGroup != null) 
			return successful(segmentGroup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SegmentGroup with the specific Id
	 * 
	 * @param segmentGroupToBeUpdated
	 *            the SegmentGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{segmentGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSegmentGroup(@RequestBody SegmentGroup segmentGroupToBeUpdated,
			@PathVariable String segmentGroupId) throws Exception {

		segmentGroupToBeUpdated.setSegmentGroupId(segmentGroupId);

		UpdateSegmentGroup command = new UpdateSegmentGroup(segmentGroupToBeUpdated);

		try {
			if(((SegmentGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{segmentGroupId}")
	public ResponseEntity<SegmentGroup> findById(@PathVariable String segmentGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupId", segmentGroupId);
		try {

			List<SegmentGroup> foundSegmentGroup = findSegmentGroupsBy(requestParams).getBody();
			if(foundSegmentGroup.size()==1){				return successful(foundSegmentGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{segmentGroupId}")
	public ResponseEntity<String> deleteSegmentGroupByIdUpdated(@PathVariable String segmentGroupId) throws Exception {
		DeleteSegmentGroup command = new DeleteSegmentGroup(segmentGroupId);

		try {
			if (((SegmentGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
