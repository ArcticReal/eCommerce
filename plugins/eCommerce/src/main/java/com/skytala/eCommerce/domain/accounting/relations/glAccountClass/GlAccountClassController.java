package com.skytala.eCommerce.domain.accounting.relations.glAccountClass;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.command.AddGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.command.DeleteGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.command.UpdateGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event.GlAccountClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event.GlAccountClassDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event.GlAccountClassFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event.GlAccountClassUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.mapper.GlAccountClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.model.GlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.query.FindGlAccountClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountClasss")
public class GlAccountClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountClass
	 * @return a List with the GlAccountClasss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountClasssBy query = new FindGlAccountClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountClass> glAccountClasss =((GlAccountClassFound) Scheduler.execute(query).data()).getGlAccountClasss();

		if (glAccountClasss.size() == 1) {
			return ResponseEntity.ok().body(glAccountClasss.get(0));
		}

		return ResponseEntity.ok().body(glAccountClasss);

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
	public ResponseEntity<Object> createGlAccountClass(HttpServletRequest request) throws Exception {

		GlAccountClass glAccountClassToBeAdded = new GlAccountClass();
		try {
			glAccountClassToBeAdded = GlAccountClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountClass(glAccountClassToBeAdded);

	}

	/**
	 * creates a new GlAccountClass entry in the ofbiz database
	 * 
	 * @param glAccountClassToBeAdded
	 *            the GlAccountClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountClass(@RequestBody GlAccountClass glAccountClassToBeAdded) throws Exception {

		AddGlAccountClass command = new AddGlAccountClass(glAccountClassToBeAdded);
		GlAccountClass glAccountClass = ((GlAccountClassAdded) Scheduler.execute(command).data()).getAddedGlAccountClass();
		
		if (glAccountClass != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountClass);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountClass could not be created.");
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
	public boolean updateGlAccountClass(HttpServletRequest request) throws Exception {

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

		GlAccountClass glAccountClassToBeUpdated = new GlAccountClass();

		try {
			glAccountClassToBeUpdated = GlAccountClassMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountClass(glAccountClassToBeUpdated, glAccountClassToBeUpdated.getGlAccountClassId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountClass with the specific Id
	 * 
	 * @param glAccountClassToBeUpdated
	 *            the GlAccountClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountClass(@RequestBody GlAccountClass glAccountClassToBeUpdated,
			@PathVariable String glAccountClassId) throws Exception {

		glAccountClassToBeUpdated.setGlAccountClassId(glAccountClassId);

		UpdateGlAccountClass command = new UpdateGlAccountClass(glAccountClassToBeUpdated);

		try {
			if(((GlAccountClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountClassId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountClassId", glAccountClassId);
		try {

			Object foundGlAccountClass = findGlAccountClasssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountClass);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountClassId}")
	public ResponseEntity<Object> deleteGlAccountClassByIdUpdated(@PathVariable String glAccountClassId) throws Exception {
		DeleteGlAccountClass command = new DeleteGlAccountClass(glAccountClassId);

		try {
			if (((GlAccountClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountClass could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountClass/\" plus one of the following: "
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
