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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/segmentGroups")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSegmentGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupsBy query = new FindSegmentGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroup> segmentGroups =((SegmentGroupFound) Scheduler.execute(query).data()).getSegmentGroups();

		if (segmentGroups.size() == 1) {
			return ResponseEntity.ok().body(segmentGroups.get(0));
		}

		return ResponseEntity.ok().body(segmentGroups);

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
	public ResponseEntity<Object> createSegmentGroup(HttpServletRequest request) throws Exception {

		SegmentGroup segmentGroupToBeAdded = new SegmentGroup();
		try {
			segmentGroupToBeAdded = SegmentGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSegmentGroup(segmentGroupToBeAdded);

	}

	/**
	 * creates a new SegmentGroup entry in the ofbiz database
	 * 
	 * @param segmentGroupToBeAdded
	 *            the SegmentGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSegmentGroup(@RequestBody SegmentGroup segmentGroupToBeAdded) throws Exception {

		AddSegmentGroup command = new AddSegmentGroup(segmentGroupToBeAdded);
		SegmentGroup segmentGroup = ((SegmentGroupAdded) Scheduler.execute(command).data()).getAddedSegmentGroup();
		
		if (segmentGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(segmentGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SegmentGroup could not be created.");
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
	public boolean updateSegmentGroup(HttpServletRequest request) throws Exception {

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

		SegmentGroup segmentGroupToBeUpdated = new SegmentGroup();

		try {
			segmentGroupToBeUpdated = SegmentGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSegmentGroup(segmentGroupToBeUpdated, segmentGroupToBeUpdated.getSegmentGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSegmentGroup(@RequestBody SegmentGroup segmentGroupToBeUpdated,
			@PathVariable String segmentGroupId) throws Exception {

		segmentGroupToBeUpdated.setSegmentGroupId(segmentGroupId);

		UpdateSegmentGroup command = new UpdateSegmentGroup(segmentGroupToBeUpdated);

		try {
			if(((SegmentGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{segmentGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String segmentGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupId", segmentGroupId);
		try {

			Object foundSegmentGroup = findSegmentGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSegmentGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{segmentGroupId}")
	public ResponseEntity<Object> deleteSegmentGroupByIdUpdated(@PathVariable String segmentGroupId) throws Exception {
		DeleteSegmentGroup command = new DeleteSegmentGroup(segmentGroupId);

		try {
			if (((SegmentGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SegmentGroup could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/segmentGroup/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
