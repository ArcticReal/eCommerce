package com.skytala.eCommerce.domain.content.relations.content.control.keyword;

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
import com.skytala.eCommerce.domain.content.relations.content.command.keyword.AddContentKeyword;
import com.skytala.eCommerce.domain.content.relations.content.command.keyword.DeleteContentKeyword;
import com.skytala.eCommerce.domain.content.relations.content.command.keyword.UpdateContentKeyword;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordFound;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.keyword.ContentKeywordMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
import com.skytala.eCommerce.domain.content.relations.content.query.keyword.FindContentKeywordsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentKeywords")
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
	@GetMapping("/find")
	public ResponseEntity<List<ContentKeyword>> findContentKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentKeywordsBy query = new FindContentKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentKeyword> contentKeywords =((ContentKeywordFound) Scheduler.execute(query).data()).getContentKeywords();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ContentKeyword> createContentKeyword(HttpServletRequest request) throws Exception {

		ContentKeyword contentKeywordToBeAdded = new ContentKeyword();
		try {
			contentKeywordToBeAdded = ContentKeywordMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ContentKeyword> createContentKeyword(@RequestBody ContentKeyword contentKeywordToBeAdded) throws Exception {

		AddContentKeyword command = new AddContentKeyword(contentKeywordToBeAdded);
		ContentKeyword contentKeyword = ((ContentKeywordAdded) Scheduler.execute(command).data()).getAddedContentKeyword();
		
		if (contentKeyword != null) 
			return successful(contentKeyword);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentKeyword(@RequestBody ContentKeyword contentKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		contentKeywordToBeUpdated.setKeyword(keyword);

		UpdateContentKeyword command = new UpdateContentKeyword(contentKeywordToBeUpdated);

		try {
			if(((ContentKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentKeywordId}")
	public ResponseEntity<ContentKeyword> findById(@PathVariable String contentKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentKeywordId", contentKeywordId);
		try {

			List<ContentKeyword> foundContentKeyword = findContentKeywordsBy(requestParams).getBody();
			if(foundContentKeyword.size()==1){				return successful(foundContentKeyword.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentKeywordId}")
	public ResponseEntity<String> deleteContentKeywordByIdUpdated(@PathVariable String contentKeywordId) throws Exception {
		DeleteContentKeyword command = new DeleteContentKeyword(contentKeywordId);

		try {
			if (((ContentKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
