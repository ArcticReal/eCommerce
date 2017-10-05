package com.skytala.eCommerce.domain.glFiscalType;

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
import com.skytala.eCommerce.domain.glFiscalType.command.AddGlFiscalType;
import com.skytala.eCommerce.domain.glFiscalType.command.DeleteGlFiscalType;
import com.skytala.eCommerce.domain.glFiscalType.command.UpdateGlFiscalType;
import com.skytala.eCommerce.domain.glFiscalType.event.GlFiscalTypeAdded;
import com.skytala.eCommerce.domain.glFiscalType.event.GlFiscalTypeDeleted;
import com.skytala.eCommerce.domain.glFiscalType.event.GlFiscalTypeFound;
import com.skytala.eCommerce.domain.glFiscalType.event.GlFiscalTypeUpdated;
import com.skytala.eCommerce.domain.glFiscalType.mapper.GlFiscalTypeMapper;
import com.skytala.eCommerce.domain.glFiscalType.model.GlFiscalType;
import com.skytala.eCommerce.domain.glFiscalType.query.FindGlFiscalTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glFiscalTypes")
public class GlFiscalTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlFiscalTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlFiscalType
	 * @return a List with the GlFiscalTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlFiscalTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlFiscalTypesBy query = new FindGlFiscalTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlFiscalType> glFiscalTypes =((GlFiscalTypeFound) Scheduler.execute(query).data()).getGlFiscalTypes();

		if (glFiscalTypes.size() == 1) {
			return ResponseEntity.ok().body(glFiscalTypes.get(0));
		}

		return ResponseEntity.ok().body(glFiscalTypes);

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
	public ResponseEntity<Object> createGlFiscalType(HttpServletRequest request) throws Exception {

		GlFiscalType glFiscalTypeToBeAdded = new GlFiscalType();
		try {
			glFiscalTypeToBeAdded = GlFiscalTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlFiscalType(glFiscalTypeToBeAdded);

	}

	/**
	 * creates a new GlFiscalType entry in the ofbiz database
	 * 
	 * @param glFiscalTypeToBeAdded
	 *            the GlFiscalType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlFiscalType(@RequestBody GlFiscalType glFiscalTypeToBeAdded) throws Exception {

		AddGlFiscalType command = new AddGlFiscalType(glFiscalTypeToBeAdded);
		GlFiscalType glFiscalType = ((GlFiscalTypeAdded) Scheduler.execute(command).data()).getAddedGlFiscalType();
		
		if (glFiscalType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glFiscalType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlFiscalType could not be created.");
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
	public boolean updateGlFiscalType(HttpServletRequest request) throws Exception {

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

		GlFiscalType glFiscalTypeToBeUpdated = new GlFiscalType();

		try {
			glFiscalTypeToBeUpdated = GlFiscalTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlFiscalType(glFiscalTypeToBeUpdated, glFiscalTypeToBeUpdated.getGlFiscalTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlFiscalType with the specific Id
	 * 
	 * @param glFiscalTypeToBeUpdated
	 *            the GlFiscalType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glFiscalTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlFiscalType(@RequestBody GlFiscalType glFiscalTypeToBeUpdated,
			@PathVariable String glFiscalTypeId) throws Exception {

		glFiscalTypeToBeUpdated.setGlFiscalTypeId(glFiscalTypeId);

		UpdateGlFiscalType command = new UpdateGlFiscalType(glFiscalTypeToBeUpdated);

		try {
			if(((GlFiscalTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glFiscalTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String glFiscalTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glFiscalTypeId", glFiscalTypeId);
		try {

			Object foundGlFiscalType = findGlFiscalTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlFiscalType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glFiscalTypeId}")
	public ResponseEntity<Object> deleteGlFiscalTypeByIdUpdated(@PathVariable String glFiscalTypeId) throws Exception {
		DeleteGlFiscalType command = new DeleteGlFiscalType(glFiscalTypeId);

		try {
			if (((GlFiscalTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlFiscalType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glFiscalType/\" plus one of the following: "
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
