package com.skytala.eCommerce.domain.glAccountGroupType;

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
import com.skytala.eCommerce.domain.glAccountGroupType.command.AddGlAccountGroupType;
import com.skytala.eCommerce.domain.glAccountGroupType.command.DeleteGlAccountGroupType;
import com.skytala.eCommerce.domain.glAccountGroupType.command.UpdateGlAccountGroupType;
import com.skytala.eCommerce.domain.glAccountGroupType.event.GlAccountGroupTypeAdded;
import com.skytala.eCommerce.domain.glAccountGroupType.event.GlAccountGroupTypeDeleted;
import com.skytala.eCommerce.domain.glAccountGroupType.event.GlAccountGroupTypeFound;
import com.skytala.eCommerce.domain.glAccountGroupType.event.GlAccountGroupTypeUpdated;
import com.skytala.eCommerce.domain.glAccountGroupType.mapper.GlAccountGroupTypeMapper;
import com.skytala.eCommerce.domain.glAccountGroupType.model.GlAccountGroupType;
import com.skytala.eCommerce.domain.glAccountGroupType.query.FindGlAccountGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountGroupTypes")
public class GlAccountGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountGroupType
	 * @return a List with the GlAccountGroupTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupTypesBy query = new FindGlAccountGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroupType> glAccountGroupTypes =((GlAccountGroupTypeFound) Scheduler.execute(query).data()).getGlAccountGroupTypes();

		if (glAccountGroupTypes.size() == 1) {
			return ResponseEntity.ok().body(glAccountGroupTypes.get(0));
		}

		return ResponseEntity.ok().body(glAccountGroupTypes);

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
	public ResponseEntity<Object> createGlAccountGroupType(HttpServletRequest request) throws Exception {

		GlAccountGroupType glAccountGroupTypeToBeAdded = new GlAccountGroupType();
		try {
			glAccountGroupTypeToBeAdded = GlAccountGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountGroupType(glAccountGroupTypeToBeAdded);

	}

	/**
	 * creates a new GlAccountGroupType entry in the ofbiz database
	 * 
	 * @param glAccountGroupTypeToBeAdded
	 *            the GlAccountGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountGroupType(@RequestBody GlAccountGroupType glAccountGroupTypeToBeAdded) throws Exception {

		AddGlAccountGroupType command = new AddGlAccountGroupType(glAccountGroupTypeToBeAdded);
		GlAccountGroupType glAccountGroupType = ((GlAccountGroupTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountGroupType();
		
		if (glAccountGroupType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountGroupType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountGroupType could not be created.");
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
	public boolean updateGlAccountGroupType(HttpServletRequest request) throws Exception {

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

		GlAccountGroupType glAccountGroupTypeToBeUpdated = new GlAccountGroupType();

		try {
			glAccountGroupTypeToBeUpdated = GlAccountGroupTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountGroupType(glAccountGroupTypeToBeUpdated, glAccountGroupTypeToBeUpdated.getGlAccountGroupTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountGroupType with the specific Id
	 * 
	 * @param glAccountGroupTypeToBeUpdated
	 *            the GlAccountGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountGroupType(@RequestBody GlAccountGroupType glAccountGroupTypeToBeUpdated,
			@PathVariable String glAccountGroupTypeId) throws Exception {

		glAccountGroupTypeToBeUpdated.setGlAccountGroupTypeId(glAccountGroupTypeId);

		UpdateGlAccountGroupType command = new UpdateGlAccountGroupType(glAccountGroupTypeToBeUpdated);

		try {
			if(((GlAccountGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountGroupTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupTypeId", glAccountGroupTypeId);
		try {

			Object foundGlAccountGroupType = findGlAccountGroupTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountGroupType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountGroupTypeId}")
	public ResponseEntity<Object> deleteGlAccountGroupTypeByIdUpdated(@PathVariable String glAccountGroupTypeId) throws Exception {
		DeleteGlAccountGroupType command = new DeleteGlAccountGroupType(glAccountGroupTypeId);

		try {
			if (((GlAccountGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountGroupType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountGroupType/\" plus one of the following: "
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
