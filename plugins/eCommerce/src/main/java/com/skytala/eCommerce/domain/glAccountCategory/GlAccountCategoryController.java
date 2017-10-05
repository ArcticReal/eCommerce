package com.skytala.eCommerce.domain.glAccountCategory;

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
import com.skytala.eCommerce.domain.glAccountCategory.command.AddGlAccountCategory;
import com.skytala.eCommerce.domain.glAccountCategory.command.DeleteGlAccountCategory;
import com.skytala.eCommerce.domain.glAccountCategory.command.UpdateGlAccountCategory;
import com.skytala.eCommerce.domain.glAccountCategory.event.GlAccountCategoryAdded;
import com.skytala.eCommerce.domain.glAccountCategory.event.GlAccountCategoryDeleted;
import com.skytala.eCommerce.domain.glAccountCategory.event.GlAccountCategoryFound;
import com.skytala.eCommerce.domain.glAccountCategory.event.GlAccountCategoryUpdated;
import com.skytala.eCommerce.domain.glAccountCategory.mapper.GlAccountCategoryMapper;
import com.skytala.eCommerce.domain.glAccountCategory.model.GlAccountCategory;
import com.skytala.eCommerce.domain.glAccountCategory.query.FindGlAccountCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountCategorys")
public class GlAccountCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountCategory
	 * @return a List with the GlAccountCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategorysBy query = new FindGlAccountCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategory> glAccountCategorys =((GlAccountCategoryFound) Scheduler.execute(query).data()).getGlAccountCategorys();

		if (glAccountCategorys.size() == 1) {
			return ResponseEntity.ok().body(glAccountCategorys.get(0));
		}

		return ResponseEntity.ok().body(glAccountCategorys);

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
	public ResponseEntity<Object> createGlAccountCategory(HttpServletRequest request) throws Exception {

		GlAccountCategory glAccountCategoryToBeAdded = new GlAccountCategory();
		try {
			glAccountCategoryToBeAdded = GlAccountCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountCategory(glAccountCategoryToBeAdded);

	}

	/**
	 * creates a new GlAccountCategory entry in the ofbiz database
	 * 
	 * @param glAccountCategoryToBeAdded
	 *            the GlAccountCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountCategory(@RequestBody GlAccountCategory glAccountCategoryToBeAdded) throws Exception {

		AddGlAccountCategory command = new AddGlAccountCategory(glAccountCategoryToBeAdded);
		GlAccountCategory glAccountCategory = ((GlAccountCategoryAdded) Scheduler.execute(command).data()).getAddedGlAccountCategory();
		
		if (glAccountCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountCategory could not be created.");
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
	public boolean updateGlAccountCategory(HttpServletRequest request) throws Exception {

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

		GlAccountCategory glAccountCategoryToBeUpdated = new GlAccountCategory();

		try {
			glAccountCategoryToBeUpdated = GlAccountCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountCategory(glAccountCategoryToBeUpdated, glAccountCategoryToBeUpdated.getGlAccountCategoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountCategory with the specific Id
	 * 
	 * @param glAccountCategoryToBeUpdated
	 *            the GlAccountCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountCategory(@RequestBody GlAccountCategory glAccountCategoryToBeUpdated,
			@PathVariable String glAccountCategoryId) throws Exception {

		glAccountCategoryToBeUpdated.setGlAccountCategoryId(glAccountCategoryId);

		UpdateGlAccountCategory command = new UpdateGlAccountCategory(glAccountCategoryToBeUpdated);

		try {
			if(((GlAccountCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryId", glAccountCategoryId);
		try {

			Object foundGlAccountCategory = findGlAccountCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountCategoryId}")
	public ResponseEntity<Object> deleteGlAccountCategoryByIdUpdated(@PathVariable String glAccountCategoryId) throws Exception {
		DeleteGlAccountCategory command = new DeleteGlAccountCategory(glAccountCategoryId);

		try {
			if (((GlAccountCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountCategory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountCategory/\" plus one of the following: "
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
