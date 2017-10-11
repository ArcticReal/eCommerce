package com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo;

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
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.command.AddOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.command.DeleteOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.command.UpdateOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event.OldPartyTaxInfoAdded;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event.OldPartyTaxInfoDeleted;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event.OldPartyTaxInfoFound;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event.OldPartyTaxInfoUpdated;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.mapper.OldPartyTaxInfoMapper;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.model.OldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.query.FindOldPartyTaxInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/oldPartyTaxInfos")
public class OldPartyTaxInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OldPartyTaxInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OldPartyTaxInfo
	 * @return a List with the OldPartyTaxInfos
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOldPartyTaxInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOldPartyTaxInfosBy query = new FindOldPartyTaxInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OldPartyTaxInfo> oldPartyTaxInfos =((OldPartyTaxInfoFound) Scheduler.execute(query).data()).getOldPartyTaxInfos();

		if (oldPartyTaxInfos.size() == 1) {
			return ResponseEntity.ok().body(oldPartyTaxInfos.get(0));
		}

		return ResponseEntity.ok().body(oldPartyTaxInfos);

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
	public ResponseEntity<Object> createOldPartyTaxInfo(HttpServletRequest request) throws Exception {

		OldPartyTaxInfo oldPartyTaxInfoToBeAdded = new OldPartyTaxInfo();
		try {
			oldPartyTaxInfoToBeAdded = OldPartyTaxInfoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOldPartyTaxInfo(oldPartyTaxInfoToBeAdded);

	}

	/**
	 * creates a new OldPartyTaxInfo entry in the ofbiz database
	 * 
	 * @param oldPartyTaxInfoToBeAdded
	 *            the OldPartyTaxInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOldPartyTaxInfo(@RequestBody OldPartyTaxInfo oldPartyTaxInfoToBeAdded) throws Exception {

		AddOldPartyTaxInfo command = new AddOldPartyTaxInfo(oldPartyTaxInfoToBeAdded);
		OldPartyTaxInfo oldPartyTaxInfo = ((OldPartyTaxInfoAdded) Scheduler.execute(command).data()).getAddedOldPartyTaxInfo();
		
		if (oldPartyTaxInfo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(oldPartyTaxInfo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OldPartyTaxInfo could not be created.");
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
	public boolean updateOldPartyTaxInfo(HttpServletRequest request) throws Exception {

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

		OldPartyTaxInfo oldPartyTaxInfoToBeUpdated = new OldPartyTaxInfo();

		try {
			oldPartyTaxInfoToBeUpdated = OldPartyTaxInfoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOldPartyTaxInfo(oldPartyTaxInfoToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OldPartyTaxInfo with the specific Id
	 * 
	 * @param oldPartyTaxInfoToBeUpdated
	 *            the OldPartyTaxInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOldPartyTaxInfo(@RequestBody OldPartyTaxInfo oldPartyTaxInfoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		oldPartyTaxInfoToBeUpdated.setnull(null);

		UpdateOldPartyTaxInfo command = new UpdateOldPartyTaxInfo(oldPartyTaxInfoToBeUpdated);

		try {
			if(((OldPartyTaxInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{oldPartyTaxInfoId}")
	public ResponseEntity<Object> findById(@PathVariable String oldPartyTaxInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("oldPartyTaxInfoId", oldPartyTaxInfoId);
		try {

			Object foundOldPartyTaxInfo = findOldPartyTaxInfosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOldPartyTaxInfo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{oldPartyTaxInfoId}")
	public ResponseEntity<Object> deleteOldPartyTaxInfoByIdUpdated(@PathVariable String oldPartyTaxInfoId) throws Exception {
		DeleteOldPartyTaxInfo command = new DeleteOldPartyTaxInfo(oldPartyTaxInfoId);

		try {
			if (((OldPartyTaxInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OldPartyTaxInfo could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/oldPartyTaxInfo/\" plus one of the following: "
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
