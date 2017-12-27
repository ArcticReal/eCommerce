package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.control.type;

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
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.type.AddSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.type.DeleteSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.type.UpdateSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.type.SegmentGroupTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type.SegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.type.FindSegmentGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/segmentGroup/segmentGroupTypes")
public class SegmentGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SegmentGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SegmentGroupType
	 * @return a List with the SegmentGroupTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SegmentGroupType>> findSegmentGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupTypesBy query = new FindSegmentGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupType> segmentGroupTypes =((SegmentGroupTypeFound) Scheduler.execute(query).data()).getSegmentGroupTypes();

		return ResponseEntity.ok().body(segmentGroupTypes);

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
	public ResponseEntity<SegmentGroupType> createSegmentGroupType(HttpServletRequest request) throws Exception {

		SegmentGroupType segmentGroupTypeToBeAdded = new SegmentGroupType();
		try {
			segmentGroupTypeToBeAdded = SegmentGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSegmentGroupType(segmentGroupTypeToBeAdded);

	}

	/**
	 * creates a new SegmentGroupType entry in the ofbiz database
	 * 
	 * @param segmentGroupTypeToBeAdded
	 *            the SegmentGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SegmentGroupType> createSegmentGroupType(@RequestBody SegmentGroupType segmentGroupTypeToBeAdded) throws Exception {

		AddSegmentGroupType command = new AddSegmentGroupType(segmentGroupTypeToBeAdded);
		SegmentGroupType segmentGroupType = ((SegmentGroupTypeAdded) Scheduler.execute(command).data()).getAddedSegmentGroupType();
		
		if (segmentGroupType != null) 
			return successful(segmentGroupType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SegmentGroupType with the specific Id
	 * 
	 * @param segmentGroupTypeToBeUpdated
	 *            the SegmentGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{segmentGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSegmentGroupType(@RequestBody SegmentGroupType segmentGroupTypeToBeUpdated,
			@PathVariable String segmentGroupTypeId) throws Exception {

		segmentGroupTypeToBeUpdated.setSegmentGroupTypeId(segmentGroupTypeId);

		UpdateSegmentGroupType command = new UpdateSegmentGroupType(segmentGroupTypeToBeUpdated);

		try {
			if(((SegmentGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{segmentGroupTypeId}")
	public ResponseEntity<SegmentGroupType> findById(@PathVariable String segmentGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupTypeId", segmentGroupTypeId);
		try {

			List<SegmentGroupType> foundSegmentGroupType = findSegmentGroupTypesBy(requestParams).getBody();
			if(foundSegmentGroupType.size()==1){				return successful(foundSegmentGroupType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{segmentGroupTypeId}")
	public ResponseEntity<String> deleteSegmentGroupTypeByIdUpdated(@PathVariable String segmentGroupTypeId) throws Exception {
		DeleteSegmentGroupType command = new DeleteSegmentGroupType(segmentGroupTypeId);

		try {
			if (((SegmentGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
