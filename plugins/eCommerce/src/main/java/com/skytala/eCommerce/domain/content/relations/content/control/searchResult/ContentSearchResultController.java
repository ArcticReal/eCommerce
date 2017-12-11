package com.skytala.eCommerce.domain.content.relations.content.control.searchResult;

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
import com.skytala.eCommerce.domain.content.relations.content.command.searchResult.AddContentSearchResult;
import com.skytala.eCommerce.domain.content.relations.content.command.searchResult.DeleteContentSearchResult;
import com.skytala.eCommerce.domain.content.relations.content.command.searchResult.UpdateContentSearchResult;
import com.skytala.eCommerce.domain.content.relations.content.event.searchResult.ContentSearchResultAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.searchResult.ContentSearchResultDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.searchResult.ContentSearchResultFound;
import com.skytala.eCommerce.domain.content.relations.content.event.searchResult.ContentSearchResultUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.searchResult.ContentSearchResultMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.searchResult.ContentSearchResult;
import com.skytala.eCommerce.domain.content.relations.content.query.searchResult.FindContentSearchResultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/content/contentSearchResults")
public class ContentSearchResultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentSearchResultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentSearchResult
	 * @return a List with the ContentSearchResults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findContentSearchResultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentSearchResultsBy query = new FindContentSearchResultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentSearchResult> contentSearchResults =((ContentSearchResultFound) Scheduler.execute(query).data()).getContentSearchResults();

		if (contentSearchResults.size() == 1) {
			return ResponseEntity.ok().body(contentSearchResults.get(0));
		}

		return ResponseEntity.ok().body(contentSearchResults);

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
	public ResponseEntity<Object> createContentSearchResult(HttpServletRequest request) throws Exception {

		ContentSearchResult contentSearchResultToBeAdded = new ContentSearchResult();
		try {
			contentSearchResultToBeAdded = ContentSearchResultMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentSearchResult(contentSearchResultToBeAdded);

	}

	/**
	 * creates a new ContentSearchResult entry in the ofbiz database
	 * 
	 * @param contentSearchResultToBeAdded
	 *            the ContentSearchResult thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentSearchResult(@RequestBody ContentSearchResult contentSearchResultToBeAdded) throws Exception {

		AddContentSearchResult command = new AddContentSearchResult(contentSearchResultToBeAdded);
		ContentSearchResult contentSearchResult = ((ContentSearchResultAdded) Scheduler.execute(command).data()).getAddedContentSearchResult();
		
		if (contentSearchResult != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentSearchResult);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentSearchResult could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateContentSearchResult(HttpServletRequest request) throws Exception {

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

		ContentSearchResult contentSearchResultToBeUpdated = new ContentSearchResult();

		try {
			contentSearchResultToBeUpdated = ContentSearchResultMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentSearchResult(contentSearchResultToBeUpdated, contentSearchResultToBeUpdated.getContentSearchResultId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentSearchResult with the specific Id
	 * 
	 * @param contentSearchResultToBeUpdated
	 *            the ContentSearchResult thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contentSearchResultId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentSearchResult(@RequestBody ContentSearchResult contentSearchResultToBeUpdated,
			@PathVariable String contentSearchResultId) throws Exception {

		contentSearchResultToBeUpdated.setContentSearchResultId(contentSearchResultId);

		UpdateContentSearchResult command = new UpdateContentSearchResult(contentSearchResultToBeUpdated);

		try {
			if(((ContentSearchResultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentSearchResultId}")
	public ResponseEntity<Object> findById(@PathVariable String contentSearchResultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentSearchResultId", contentSearchResultId);
		try {

			Object foundContentSearchResult = findContentSearchResultsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentSearchResult);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentSearchResultId}")
	public ResponseEntity<Object> deleteContentSearchResultByIdUpdated(@PathVariable String contentSearchResultId) throws Exception {
		DeleteContentSearchResult command = new DeleteContentSearchResult(contentSearchResultId);

		try {
			if (((ContentSearchResultDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentSearchResult could not be deleted");

	}

}
