package com.skytala.eCommerce.domain.content.relations.contentKeyword;

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
import com.skytala.eCommerce.domain.content.relations.contentKeyword.command.AddContentKeyword;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.command.DeleteContentKeyword;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.command.UpdateContentKeyword;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.event.ContentKeywordAdded;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.event.ContentKeywordDeleted;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.event.ContentKeywordFound;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.event.ContentKeywordUpdated;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.mapper.ContentKeywordMapper;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.model.ContentKeyword;
import com.skytala.eCommerce.domain.content.relations.contentKeyword.query.FindContentKeywordsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contentKeywords")
public class ContentKeywordController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentKeywordController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentKeyword
	 * @return a List with the ContentKeywords
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentKeywordsBy query = new FindContentKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentKeyword> contentKeywords =((ContentKeywordFound) Scheduler.execute(query).data()).getContentKeywords();

		if (contentKeywords.size() == 1) {
			return ResponseEntity.ok().body(contentKeywords.get(0));
		}

		return ResponseEntity.ok().body(contentKeywords);

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
	public ResponseEntity<Object> createContentKeyword(HttpServletRequest request) throws Exception {

		ContentKeyword contentKeywordToBeAdded = new ContentKeyword();
		try {
			contentKeywordToBeAdded = ContentKeywordMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentKeyword(contentKeywordToBeAdded);

	}

	/**
	 * creates a new ContentKeyword entry in the ofbiz database
	 * 
	 * @param contentKeywordToBeAdded
	 *            the ContentKeyword thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentKeyword(@RequestBody ContentKeyword contentKeywordToBeAdded) throws Exception {

		AddContentKeyword command = new AddContentKeyword(contentKeywordToBeAdded);
		ContentKeyword contentKeyword = ((ContentKeywordAdded) Scheduler.execute(command).data()).getAddedContentKeyword();
		
		if (contentKeyword != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentKeyword);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentKeyword could not be created.");
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
	public boolean updateContentKeyword(HttpServletRequest request) throws Exception {

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

		ContentKeyword contentKeywordToBeUpdated = new ContentKeyword();

		try {
			contentKeywordToBeUpdated = ContentKeywordMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentKeyword(contentKeywordToBeUpdated, contentKeywordToBeUpdated.getKeyword()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentKeyword with the specific Id
	 * 
	 * @param contentKeywordToBeUpdated
	 *            the ContentKeyword thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{keyword}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentKeyword(@RequestBody ContentKeyword contentKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		contentKeywordToBeUpdated.setKeyword(keyword);

		UpdateContentKeyword command = new UpdateContentKeyword(contentKeywordToBeUpdated);

		try {
			if(((ContentKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentKeywordId}")
	public ResponseEntity<Object> findById(@PathVariable String contentKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentKeywordId", contentKeywordId);
		try {

			Object foundContentKeyword = findContentKeywordsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentKeyword);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentKeywordId}")
	public ResponseEntity<Object> deleteContentKeywordByIdUpdated(@PathVariable String contentKeywordId) throws Exception {
		DeleteContentKeyword command = new DeleteContentKeyword(contentKeywordId);

		try {
			if (((ContentKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentKeyword could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contentKeyword/\" plus one of the following: "
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