package com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory;

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
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.command.AddSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.command.DeleteSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.command.UpdateSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event.SurveyQuestionCategoryAdded;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event.SurveyQuestionCategoryDeleted;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event.SurveyQuestionCategoryFound;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event.SurveyQuestionCategoryUpdated;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.mapper.SurveyQuestionCategoryMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.model.SurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.query.FindSurveyQuestionCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyQuestionCategorys")
public class SurveyQuestionCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestionCategory
	 * @return a List with the SurveyQuestionCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyQuestionCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionCategorysBy query = new FindSurveyQuestionCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionCategory> surveyQuestionCategorys =((SurveyQuestionCategoryFound) Scheduler.execute(query).data()).getSurveyQuestionCategorys();

		if (surveyQuestionCategorys.size() == 1) {
			return ResponseEntity.ok().body(surveyQuestionCategorys.get(0));
		}

		return ResponseEntity.ok().body(surveyQuestionCategorys);

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
	public ResponseEntity<Object> createSurveyQuestionCategory(HttpServletRequest request) throws Exception {

		SurveyQuestionCategory surveyQuestionCategoryToBeAdded = new SurveyQuestionCategory();
		try {
			surveyQuestionCategoryToBeAdded = SurveyQuestionCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyQuestionCategory(surveyQuestionCategoryToBeAdded);

	}

	/**
	 * creates a new SurveyQuestionCategory entry in the ofbiz database
	 * 
	 * @param surveyQuestionCategoryToBeAdded
	 *            the SurveyQuestionCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyQuestionCategory(@RequestBody SurveyQuestionCategory surveyQuestionCategoryToBeAdded) throws Exception {

		AddSurveyQuestionCategory command = new AddSurveyQuestionCategory(surveyQuestionCategoryToBeAdded);
		SurveyQuestionCategory surveyQuestionCategory = ((SurveyQuestionCategoryAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionCategory();
		
		if (surveyQuestionCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyQuestionCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyQuestionCategory could not be created.");
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
	public boolean updateSurveyQuestionCategory(HttpServletRequest request) throws Exception {

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

		SurveyQuestionCategory surveyQuestionCategoryToBeUpdated = new SurveyQuestionCategory();

		try {
			surveyQuestionCategoryToBeUpdated = SurveyQuestionCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyQuestionCategory(surveyQuestionCategoryToBeUpdated, surveyQuestionCategoryToBeUpdated.getSurveyQuestionCategoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyQuestionCategory with the specific Id
	 * 
	 * @param surveyQuestionCategoryToBeUpdated
	 *            the SurveyQuestionCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyQuestionCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyQuestionCategory(@RequestBody SurveyQuestionCategory surveyQuestionCategoryToBeUpdated,
			@PathVariable String surveyQuestionCategoryId) throws Exception {

		surveyQuestionCategoryToBeUpdated.setSurveyQuestionCategoryId(surveyQuestionCategoryId);

		UpdateSurveyQuestionCategory command = new UpdateSurveyQuestionCategory(surveyQuestionCategoryToBeUpdated);

		try {
			if(((SurveyQuestionCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyQuestionCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyQuestionCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionCategoryId", surveyQuestionCategoryId);
		try {

			Object foundSurveyQuestionCategory = findSurveyQuestionCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyQuestionCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyQuestionCategoryId}")
	public ResponseEntity<Object> deleteSurveyQuestionCategoryByIdUpdated(@PathVariable String surveyQuestionCategoryId) throws Exception {
		DeleteSurveyQuestionCategory command = new DeleteSurveyQuestionCategory(surveyQuestionCategoryId);

		try {
			if (((SurveyQuestionCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyQuestionCategory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyQuestionCategory/\" plus one of the following: "
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