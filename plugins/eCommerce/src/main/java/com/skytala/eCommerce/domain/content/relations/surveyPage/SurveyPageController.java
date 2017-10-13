package com.skytala.eCommerce.domain.content.relations.surveyPage;

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
import com.skytala.eCommerce.domain.content.relations.surveyPage.command.AddSurveyPage;
import com.skytala.eCommerce.domain.content.relations.surveyPage.command.DeleteSurveyPage;
import com.skytala.eCommerce.domain.content.relations.surveyPage.command.UpdateSurveyPage;
import com.skytala.eCommerce.domain.content.relations.surveyPage.event.SurveyPageAdded;
import com.skytala.eCommerce.domain.content.relations.surveyPage.event.SurveyPageDeleted;
import com.skytala.eCommerce.domain.content.relations.surveyPage.event.SurveyPageFound;
import com.skytala.eCommerce.domain.content.relations.surveyPage.event.SurveyPageUpdated;
import com.skytala.eCommerce.domain.content.relations.surveyPage.mapper.SurveyPageMapper;
import com.skytala.eCommerce.domain.content.relations.surveyPage.model.SurveyPage;
import com.skytala.eCommerce.domain.content.relations.surveyPage.query.FindSurveyPagesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyPages")
public class SurveyPageController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyPageController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyPage
	 * @return a List with the SurveyPages
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyPagesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyPagesBy query = new FindSurveyPagesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyPage> surveyPages =((SurveyPageFound) Scheduler.execute(query).data()).getSurveyPages();

		if (surveyPages.size() == 1) {
			return ResponseEntity.ok().body(surveyPages.get(0));
		}

		return ResponseEntity.ok().body(surveyPages);

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
	public ResponseEntity<Object> createSurveyPage(HttpServletRequest request) throws Exception {

		SurveyPage surveyPageToBeAdded = new SurveyPage();
		try {
			surveyPageToBeAdded = SurveyPageMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyPage(surveyPageToBeAdded);

	}

	/**
	 * creates a new SurveyPage entry in the ofbiz database
	 * 
	 * @param surveyPageToBeAdded
	 *            the SurveyPage thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyPage(@RequestBody SurveyPage surveyPageToBeAdded) throws Exception {

		AddSurveyPage command = new AddSurveyPage(surveyPageToBeAdded);
		SurveyPage surveyPage = ((SurveyPageAdded) Scheduler.execute(command).data()).getAddedSurveyPage();
		
		if (surveyPage != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyPage);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyPage could not be created.");
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
	public boolean updateSurveyPage(HttpServletRequest request) throws Exception {

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

		SurveyPage surveyPageToBeUpdated = new SurveyPage();

		try {
			surveyPageToBeUpdated = SurveyPageMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyPage(surveyPageToBeUpdated, surveyPageToBeUpdated.getSurveyPageSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyPage with the specific Id
	 * 
	 * @param surveyPageToBeUpdated
	 *            the SurveyPage thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyPageSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyPage(@RequestBody SurveyPage surveyPageToBeUpdated,
			@PathVariable String surveyPageSeqId) throws Exception {

		surveyPageToBeUpdated.setSurveyPageSeqId(surveyPageSeqId);

		UpdateSurveyPage command = new UpdateSurveyPage(surveyPageToBeUpdated);

		try {
			if(((SurveyPageUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyPageId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyPageId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyPageId", surveyPageId);
		try {

			Object foundSurveyPage = findSurveyPagesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyPage);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyPageId}")
	public ResponseEntity<Object> deleteSurveyPageByIdUpdated(@PathVariable String surveyPageId) throws Exception {
		DeleteSurveyPage command = new DeleteSurveyPage(surveyPageId);

		try {
			if (((SurveyPageDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyPage could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyPage/\" plus one of the following: "
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