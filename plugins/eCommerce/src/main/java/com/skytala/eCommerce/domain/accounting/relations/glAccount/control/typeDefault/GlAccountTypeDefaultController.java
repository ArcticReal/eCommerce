package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.typeDefault;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.AddGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.DeleteGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.UpdateGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.typeDefault.GlAccountTypeDefaultMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.typeDefault.FindGlAccountTypeDefaultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountTypeDefaults")
public class GlAccountTypeDefaultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountTypeDefaultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountTypeDefault
	 * @return a List with the GlAccountTypeDefaults
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountTypeDefaultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountTypeDefaultsBy query = new FindGlAccountTypeDefaultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountTypeDefault> glAccountTypeDefaults =((GlAccountTypeDefaultFound) Scheduler.execute(query).data()).getGlAccountTypeDefaults();

		if (glAccountTypeDefaults.size() == 1) {
			return ResponseEntity.ok().body(glAccountTypeDefaults.get(0));
		}

		return ResponseEntity.ok().body(glAccountTypeDefaults);

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
	public ResponseEntity<Object> createGlAccountTypeDefault(HttpServletRequest request) throws Exception {

		GlAccountTypeDefault glAccountTypeDefaultToBeAdded = new GlAccountTypeDefault();
		try {
			glAccountTypeDefaultToBeAdded = GlAccountTypeDefaultMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountTypeDefault(glAccountTypeDefaultToBeAdded);

	}

	/**
	 * creates a new GlAccountTypeDefault entry in the ofbiz database
	 * 
	 * @param glAccountTypeDefaultToBeAdded
	 *            the GlAccountTypeDefault thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountTypeDefault(@RequestBody GlAccountTypeDefault glAccountTypeDefaultToBeAdded) throws Exception {

		AddGlAccountTypeDefault command = new AddGlAccountTypeDefault(glAccountTypeDefaultToBeAdded);
		GlAccountTypeDefault glAccountTypeDefault = ((GlAccountTypeDefaultAdded) Scheduler.execute(command).data()).getAddedGlAccountTypeDefault();
		
		if (glAccountTypeDefault != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountTypeDefault);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountTypeDefault could not be created.");
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
	public boolean updateGlAccountTypeDefault(HttpServletRequest request) throws Exception {

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

		GlAccountTypeDefault glAccountTypeDefaultToBeUpdated = new GlAccountTypeDefault();

		try {
			glAccountTypeDefaultToBeUpdated = GlAccountTypeDefaultMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountTypeDefault(glAccountTypeDefaultToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountTypeDefault with the specific Id
	 * 
	 * @param glAccountTypeDefaultToBeUpdated
	 *            the GlAccountTypeDefault thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountTypeDefault(@RequestBody GlAccountTypeDefault glAccountTypeDefaultToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountTypeDefaultToBeUpdated.setnull(null);

		UpdateGlAccountTypeDefault command = new UpdateGlAccountTypeDefault(glAccountTypeDefaultToBeUpdated);

		try {
			if(((GlAccountTypeDefaultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountTypeDefaultId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountTypeDefaultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountTypeDefaultId", glAccountTypeDefaultId);
		try {

			Object foundGlAccountTypeDefault = findGlAccountTypeDefaultsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountTypeDefault);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountTypeDefaultId}")
	public ResponseEntity<Object> deleteGlAccountTypeDefaultByIdUpdated(@PathVariable String glAccountTypeDefaultId) throws Exception {
		DeleteGlAccountTypeDefault command = new DeleteGlAccountTypeDefault(glAccountTypeDefaultId);

		try {
			if (((GlAccountTypeDefaultDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountTypeDefault could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountTypeDefault/\" plus one of the following: "
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
