package com.skytala.eCommerce.domain.marketing.relations.segmentGroupType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.command.AddSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.command.DeleteSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.command.UpdateSegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event.SegmentGroupTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event.SegmentGroupTypeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event.SegmentGroupTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event.SegmentGroupTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.mapper.SegmentGroupTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.model.SegmentGroupType;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.query.FindSegmentGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/segmentGroupTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSegmentGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupTypesBy query = new FindSegmentGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupType> segmentGroupTypes =((SegmentGroupTypeFound) Scheduler.execute(query).data()).getSegmentGroupTypes();

		if (segmentGroupTypes.size() == 1) {
			return ResponseEntity.ok().body(segmentGroupTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSegmentGroupType(HttpServletRequest request) throws Exception {

		SegmentGroupType segmentGroupTypeToBeAdded = new SegmentGroupType();
		try {
			segmentGroupTypeToBeAdded = SegmentGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSegmentGroupType(@RequestBody SegmentGroupType segmentGroupTypeToBeAdded) throws Exception {

		AddSegmentGroupType command = new AddSegmentGroupType(segmentGroupTypeToBeAdded);
		SegmentGroupType segmentGroupType = ((SegmentGroupTypeAdded) Scheduler.execute(command).data()).getAddedSegmentGroupType();
		
		if (segmentGroupType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(segmentGroupType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SegmentGroupType could not be created.");
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
	public boolean updateSegmentGroupType(HttpServletRequest request) throws Exception {

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

		SegmentGroupType segmentGroupTypeToBeUpdated = new SegmentGroupType();

		try {
			segmentGroupTypeToBeUpdated = SegmentGroupTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSegmentGroupType(segmentGroupTypeToBeUpdated, segmentGroupTypeToBeUpdated.getSegmentGroupTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSegmentGroupType(@RequestBody SegmentGroupType segmentGroupTypeToBeUpdated,
			@PathVariable String segmentGroupTypeId) throws Exception {

		segmentGroupTypeToBeUpdated.setSegmentGroupTypeId(segmentGroupTypeId);

		UpdateSegmentGroupType command = new UpdateSegmentGroupType(segmentGroupTypeToBeUpdated);

		try {
			if(((SegmentGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{segmentGroupTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String segmentGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupTypeId", segmentGroupTypeId);
		try {

			Object foundSegmentGroupType = findSegmentGroupTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSegmentGroupType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{segmentGroupTypeId}")
	public ResponseEntity<Object> deleteSegmentGroupTypeByIdUpdated(@PathVariable String segmentGroupTypeId) throws Exception {
		DeleteSegmentGroupType command = new DeleteSegmentGroupType(segmentGroupTypeId);

		try {
			if (((SegmentGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SegmentGroupType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/segmentGroupType/\" plus one of the following: "
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
