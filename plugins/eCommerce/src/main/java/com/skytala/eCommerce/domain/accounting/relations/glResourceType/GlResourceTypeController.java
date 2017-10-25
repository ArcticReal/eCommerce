package com.skytala.eCommerce.domain.accounting.relations.glResourceType;

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
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.AddGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.DeleteGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.UpdateGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.mapper.GlResourceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.query.FindGlResourceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glResourceTypes")
public class GlResourceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlResourceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlResourceType
	 * @return a List with the GlResourceTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlResourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlResourceTypesBy query = new FindGlResourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlResourceType> glResourceTypes =((GlResourceTypeFound) Scheduler.execute(query).data()).getGlResourceTypes();

		if (glResourceTypes.size() == 1) {
			return ResponseEntity.ok().body(glResourceTypes.get(0));
		}

		return ResponseEntity.ok().body(glResourceTypes);

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
	public ResponseEntity<Object> createGlResourceType(HttpServletRequest request) throws Exception {

		GlResourceType glResourceTypeToBeAdded = new GlResourceType();
		try {
			glResourceTypeToBeAdded = GlResourceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlResourceType(glResourceTypeToBeAdded);

	}

	/**
	 * creates a new GlResourceType entry in the ofbiz database
	 * 
	 * @param glResourceTypeToBeAdded
	 *            the GlResourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlResourceType(@RequestBody GlResourceType glResourceTypeToBeAdded) throws Exception {

		AddGlResourceType command = new AddGlResourceType(glResourceTypeToBeAdded);
		GlResourceType glResourceType = ((GlResourceTypeAdded) Scheduler.execute(command).data()).getAddedGlResourceType();
		
		if (glResourceType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glResourceType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlResourceType could not be created.");
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
	public boolean updateGlResourceType(HttpServletRequest request) throws Exception {

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

		GlResourceType glResourceTypeToBeUpdated = new GlResourceType();

		try {
			glResourceTypeToBeUpdated = GlResourceTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlResourceType(glResourceTypeToBeUpdated, glResourceTypeToBeUpdated.getGlResourceTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlResourceType with the specific Id
	 * 
	 * @param glResourceTypeToBeUpdated
	 *            the GlResourceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glResourceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlResourceType(@RequestBody GlResourceType glResourceTypeToBeUpdated,
			@PathVariable String glResourceTypeId) throws Exception {

		glResourceTypeToBeUpdated.setGlResourceTypeId(glResourceTypeId);

		UpdateGlResourceType command = new UpdateGlResourceType(glResourceTypeToBeUpdated);

		try {
			if(((GlResourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glResourceTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String glResourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glResourceTypeId", glResourceTypeId);
		try {

			Object foundGlResourceType = findGlResourceTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlResourceType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glResourceTypeId}")
	public ResponseEntity<Object> deleteGlResourceTypeByIdUpdated(@PathVariable String glResourceTypeId) throws Exception {
		DeleteGlResourceType command = new DeleteGlResourceType(glResourceTypeId);

		try {
			if (((GlResourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlResourceType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glResourceType/\" plus one of the following: "
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
