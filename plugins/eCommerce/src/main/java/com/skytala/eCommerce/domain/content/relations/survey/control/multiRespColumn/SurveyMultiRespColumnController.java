package com.skytala.eCommerce.domain.content.relations.survey.control.multiRespColumn;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.AddSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.DeleteSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.UpdateSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.multiRespColumn.SurveyMultiRespColumnMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.query.multiRespColumn.FindSurveyMultiRespColumnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyMultiRespColumns")
public class SurveyMultiRespColumnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyMultiRespColumnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyMultiRespColumn
	 * @return a List with the SurveyMultiRespColumns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyMultiRespColumn>> findSurveyMultiRespColumnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyMultiRespColumnsBy query = new FindSurveyMultiRespColumnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyMultiRespColumn> surveyMultiRespColumns =((SurveyMultiRespColumnFound) Scheduler.execute(query).data()).getSurveyMultiRespColumns();

		return ResponseEntity.ok().body(surveyMultiRespColumns);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SurveyMultiRespColumn> createSurveyMultiRespColumn(HttpServletRequest request) throws Exception {

		SurveyMultiRespColumn surveyMultiRespColumnToBeAdded = new SurveyMultiRespColumn();
		try {
			surveyMultiRespColumnToBeAdded = SurveyMultiRespColumnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSurveyMultiRespColumn(surveyMultiRespColumnToBeAdded);

	}

	/**
	 * creates a new SurveyMultiRespColumn entry in the ofbiz database
	 * 
	 * @param surveyMultiRespColumnToBeAdded
	 *            the SurveyMultiRespColumn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyMultiRespColumn> createSurveyMultiRespColumn(@RequestBody SurveyMultiRespColumn surveyMultiRespColumnToBeAdded) throws Exception {

		AddSurveyMultiRespColumn command = new AddSurveyMultiRespColumn(surveyMultiRespColumnToBeAdded);
		SurveyMultiRespColumn surveyMultiRespColumn = ((SurveyMultiRespColumnAdded) Scheduler.execute(command).data()).getAddedSurveyMultiRespColumn();
		
		if (surveyMultiRespColumn != null) 
			return successful(surveyMultiRespColumn);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyMultiRespColumn with the specific Id
	 * 
	 * @param surveyMultiRespColumnToBeUpdated
	 *            the SurveyMultiRespColumn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyMultiRespColumn(@RequestBody SurveyMultiRespColumn surveyMultiRespColumnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyMultiRespColumnToBeUpdated.setnull(null);

		UpdateSurveyMultiRespColumn command = new UpdateSurveyMultiRespColumn(surveyMultiRespColumnToBeUpdated);

		try {
			if(((SurveyMultiRespColumnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyMultiRespColumnId}")
	public ResponseEntity<SurveyMultiRespColumn> findById(@PathVariable String surveyMultiRespColumnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyMultiRespColumnId", surveyMultiRespColumnId);
		try {

			List<SurveyMultiRespColumn> foundSurveyMultiRespColumn = findSurveyMultiRespColumnsBy(requestParams).getBody();
			if(foundSurveyMultiRespColumn.size()==1){				return successful(foundSurveyMultiRespColumn.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyMultiRespColumnId}")
	public ResponseEntity<String> deleteSurveyMultiRespColumnByIdUpdated(@PathVariable String surveyMultiRespColumnId) throws Exception {
		DeleteSurveyMultiRespColumn command = new DeleteSurveyMultiRespColumn(surveyMultiRespColumnId);

		try {
			if (((SurveyMultiRespColumnDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
