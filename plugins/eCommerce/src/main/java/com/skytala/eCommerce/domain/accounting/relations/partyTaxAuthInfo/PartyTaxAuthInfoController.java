package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo;

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
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.AddPartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.DeletePartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.UpdatePartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoDeleted;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoFound;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper.PartyTaxAuthInfoMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.query.FindPartyTaxAuthInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyTaxAuthInfos")
public class PartyTaxAuthInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyTaxAuthInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyTaxAuthInfo
	 * @return a List with the PartyTaxAuthInfos
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyTaxAuthInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyTaxAuthInfosBy query = new FindPartyTaxAuthInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyTaxAuthInfo> partyTaxAuthInfos =((PartyTaxAuthInfoFound) Scheduler.execute(query).data()).getPartyTaxAuthInfos();

		if (partyTaxAuthInfos.size() == 1) {
			return ResponseEntity.ok().body(partyTaxAuthInfos.get(0));
		}

		return ResponseEntity.ok().body(partyTaxAuthInfos);

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
	public ResponseEntity<Object> createPartyTaxAuthInfo(HttpServletRequest request) throws Exception {

		PartyTaxAuthInfo partyTaxAuthInfoToBeAdded = new PartyTaxAuthInfo();
		try {
			partyTaxAuthInfoToBeAdded = PartyTaxAuthInfoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyTaxAuthInfo(partyTaxAuthInfoToBeAdded);

	}

	/**
	 * creates a new PartyTaxAuthInfo entry in the ofbiz database
	 * 
	 * @param partyTaxAuthInfoToBeAdded
	 *            the PartyTaxAuthInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyTaxAuthInfo(@RequestBody PartyTaxAuthInfo partyTaxAuthInfoToBeAdded) throws Exception {

		AddPartyTaxAuthInfo command = new AddPartyTaxAuthInfo(partyTaxAuthInfoToBeAdded);
		PartyTaxAuthInfo partyTaxAuthInfo = ((PartyTaxAuthInfoAdded) Scheduler.execute(command).data()).getAddedPartyTaxAuthInfo();
		
		if (partyTaxAuthInfo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyTaxAuthInfo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyTaxAuthInfo could not be created.");
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
	public boolean updatePartyTaxAuthInfo(HttpServletRequest request) throws Exception {

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

		PartyTaxAuthInfo partyTaxAuthInfoToBeUpdated = new PartyTaxAuthInfo();

		try {
			partyTaxAuthInfoToBeUpdated = PartyTaxAuthInfoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyTaxAuthInfo(partyTaxAuthInfoToBeUpdated, partyTaxAuthInfoToBeUpdated.getTaxAuthPartyId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyTaxAuthInfo with the specific Id
	 * 
	 * @param partyTaxAuthInfoToBeUpdated
	 *            the PartyTaxAuthInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthPartyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyTaxAuthInfo(@RequestBody PartyTaxAuthInfo partyTaxAuthInfoToBeUpdated,
			@PathVariable String taxAuthPartyId) throws Exception {

		partyTaxAuthInfoToBeUpdated.setTaxAuthPartyId(taxAuthPartyId);

		UpdatePartyTaxAuthInfo command = new UpdatePartyTaxAuthInfo(partyTaxAuthInfoToBeUpdated);

		try {
			if(((PartyTaxAuthInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyTaxAuthInfoId}")
	public ResponseEntity<Object> findById(@PathVariable String partyTaxAuthInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyTaxAuthInfoId", partyTaxAuthInfoId);
		try {

			Object foundPartyTaxAuthInfo = findPartyTaxAuthInfosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyTaxAuthInfo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyTaxAuthInfoId}")
	public ResponseEntity<Object> deletePartyTaxAuthInfoByIdUpdated(@PathVariable String partyTaxAuthInfoId) throws Exception {
		DeletePartyTaxAuthInfo command = new DeletePartyTaxAuthInfo(partyTaxAuthInfoId);

		try {
			if (((PartyTaxAuthInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyTaxAuthInfo could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyTaxAuthInfo/\" plus one of the following: "
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
