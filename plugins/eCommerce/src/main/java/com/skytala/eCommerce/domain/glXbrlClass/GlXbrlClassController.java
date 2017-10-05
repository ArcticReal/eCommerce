package com.skytala.eCommerce.domain.glXbrlClass;

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
import com.skytala.eCommerce.domain.glXbrlClass.command.AddGlXbrlClass;
import com.skytala.eCommerce.domain.glXbrlClass.command.DeleteGlXbrlClass;
import com.skytala.eCommerce.domain.glXbrlClass.command.UpdateGlXbrlClass;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassAdded;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassDeleted;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassFound;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassUpdated;
import com.skytala.eCommerce.domain.glXbrlClass.mapper.GlXbrlClassMapper;
import com.skytala.eCommerce.domain.glXbrlClass.model.GlXbrlClass;
import com.skytala.eCommerce.domain.glXbrlClass.query.FindGlXbrlClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glXbrlClasss")
public class GlXbrlClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlXbrlClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlXbrlClass
	 * @return a List with the GlXbrlClasss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlXbrlClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlXbrlClasssBy query = new FindGlXbrlClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlXbrlClass> glXbrlClasss =((GlXbrlClassFound) Scheduler.execute(query).data()).getGlXbrlClasss();

		if (glXbrlClasss.size() == 1) {
			return ResponseEntity.ok().body(glXbrlClasss.get(0));
		}

		return ResponseEntity.ok().body(glXbrlClasss);

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
	public ResponseEntity<Object> createGlXbrlClass(HttpServletRequest request) throws Exception {

		GlXbrlClass glXbrlClassToBeAdded = new GlXbrlClass();
		try {
			glXbrlClassToBeAdded = GlXbrlClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlXbrlClass(glXbrlClassToBeAdded);

	}

	/**
	 * creates a new GlXbrlClass entry in the ofbiz database
	 * 
	 * @param glXbrlClassToBeAdded
	 *            the GlXbrlClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlXbrlClass(@RequestBody GlXbrlClass glXbrlClassToBeAdded) throws Exception {

		AddGlXbrlClass command = new AddGlXbrlClass(glXbrlClassToBeAdded);
		GlXbrlClass glXbrlClass = ((GlXbrlClassAdded) Scheduler.execute(command).data()).getAddedGlXbrlClass();
		
		if (glXbrlClass != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glXbrlClass);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlXbrlClass could not be created.");
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
	public boolean updateGlXbrlClass(HttpServletRequest request) throws Exception {

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

		GlXbrlClass glXbrlClassToBeUpdated = new GlXbrlClass();

		try {
			glXbrlClassToBeUpdated = GlXbrlClassMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlXbrlClass(glXbrlClassToBeUpdated, glXbrlClassToBeUpdated.getGlXbrlClassId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlXbrlClass with the specific Id
	 * 
	 * @param glXbrlClassToBeUpdated
	 *            the GlXbrlClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glXbrlClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlXbrlClass(@RequestBody GlXbrlClass glXbrlClassToBeUpdated,
			@PathVariable String glXbrlClassId) throws Exception {

		glXbrlClassToBeUpdated.setGlXbrlClassId(glXbrlClassId);

		UpdateGlXbrlClass command = new UpdateGlXbrlClass(glXbrlClassToBeUpdated);

		try {
			if(((GlXbrlClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glXbrlClassId}")
	public ResponseEntity<Object> findById(@PathVariable String glXbrlClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glXbrlClassId", glXbrlClassId);
		try {

			Object foundGlXbrlClass = findGlXbrlClasssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlXbrlClass);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glXbrlClassId}")
	public ResponseEntity<Object> deleteGlXbrlClassByIdUpdated(@PathVariable String glXbrlClassId) throws Exception {
		DeleteGlXbrlClass command = new DeleteGlXbrlClass(glXbrlClassId);

		try {
			if (((GlXbrlClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlXbrlClass could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glXbrlClass/\" plus one of the following: "
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
