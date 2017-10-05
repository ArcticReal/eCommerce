package com.skytala.eCommerce.domain.skillType;

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
import com.skytala.eCommerce.domain.skillType.command.AddSkillType;
import com.skytala.eCommerce.domain.skillType.command.DeleteSkillType;
import com.skytala.eCommerce.domain.skillType.command.UpdateSkillType;
import com.skytala.eCommerce.domain.skillType.event.SkillTypeAdded;
import com.skytala.eCommerce.domain.skillType.event.SkillTypeDeleted;
import com.skytala.eCommerce.domain.skillType.event.SkillTypeFound;
import com.skytala.eCommerce.domain.skillType.event.SkillTypeUpdated;
import com.skytala.eCommerce.domain.skillType.mapper.SkillTypeMapper;
import com.skytala.eCommerce.domain.skillType.model.SkillType;
import com.skytala.eCommerce.domain.skillType.query.FindSkillTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/skillTypes")
public class SkillTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SkillTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SkillType
	 * @return a List with the SkillTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSkillTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSkillTypesBy query = new FindSkillTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SkillType> skillTypes =((SkillTypeFound) Scheduler.execute(query).data()).getSkillTypes();

		if (skillTypes.size() == 1) {
			return ResponseEntity.ok().body(skillTypes.get(0));
		}

		return ResponseEntity.ok().body(skillTypes);

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
	public ResponseEntity<Object> createSkillType(HttpServletRequest request) throws Exception {

		SkillType skillTypeToBeAdded = new SkillType();
		try {
			skillTypeToBeAdded = SkillTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSkillType(skillTypeToBeAdded);

	}

	/**
	 * creates a new SkillType entry in the ofbiz database
	 * 
	 * @param skillTypeToBeAdded
	 *            the SkillType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSkillType(@RequestBody SkillType skillTypeToBeAdded) throws Exception {

		AddSkillType command = new AddSkillType(skillTypeToBeAdded);
		SkillType skillType = ((SkillTypeAdded) Scheduler.execute(command).data()).getAddedSkillType();
		
		if (skillType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(skillType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SkillType could not be created.");
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
	public boolean updateSkillType(HttpServletRequest request) throws Exception {

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

		SkillType skillTypeToBeUpdated = new SkillType();

		try {
			skillTypeToBeUpdated = SkillTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSkillType(skillTypeToBeUpdated, skillTypeToBeUpdated.getSkillTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SkillType with the specific Id
	 * 
	 * @param skillTypeToBeUpdated
	 *            the SkillType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{skillTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSkillType(@RequestBody SkillType skillTypeToBeUpdated,
			@PathVariable String skillTypeId) throws Exception {

		skillTypeToBeUpdated.setSkillTypeId(skillTypeId);

		UpdateSkillType command = new UpdateSkillType(skillTypeToBeUpdated);

		try {
			if(((SkillTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{skillTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String skillTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("skillTypeId", skillTypeId);
		try {

			Object foundSkillType = findSkillTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSkillType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{skillTypeId}")
	public ResponseEntity<Object> deleteSkillTypeByIdUpdated(@PathVariable String skillTypeId) throws Exception {
		DeleteSkillType command = new DeleteSkillType(skillTypeId);

		try {
			if (((SkillTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SkillType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/skillType/\" plus one of the following: "
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
