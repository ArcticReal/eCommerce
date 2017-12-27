package com.skytala.eCommerce.domain.content.relations.content.control.assoc;

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
import com.skytala.eCommerce.domain.content.relations.content.command.assoc.AddContentAssoc;
import com.skytala.eCommerce.domain.content.relations.content.command.assoc.DeleteContentAssoc;
import com.skytala.eCommerce.domain.content.relations.content.command.assoc.UpdateContentAssoc;
import com.skytala.eCommerce.domain.content.relations.content.event.assoc.ContentAssocAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.assoc.ContentAssocDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.assoc.ContentAssocFound;
import com.skytala.eCommerce.domain.content.relations.content.event.assoc.ContentAssocUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assoc.ContentAssocMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assoc.ContentAssoc;
import com.skytala.eCommerce.domain.content.relations.content.query.assoc.FindContentAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/content/contentAssocs")
public class ContentAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentAssoc
	 * @return a List with the ContentAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContentAssoc>> findContentAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocsBy query = new FindContentAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssoc> contentAssocs =((ContentAssocFound) Scheduler.execute(query).data()).getContentAssocs();

		return ResponseEntity.ok().body(contentAssocs);

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
	public ResponseEntity<ContentAssoc> createContentAssoc(HttpServletRequest request) throws Exception {

		ContentAssoc contentAssocToBeAdded = new ContentAssoc();
		try {
			contentAssocToBeAdded = ContentAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContentAssoc(contentAssocToBeAdded);

	}

	/**
	 * creates a new ContentAssoc entry in the ofbiz database
	 * 
	 * @param contentAssocToBeAdded
	 *            the ContentAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentAssoc> createContentAssoc(@RequestBody ContentAssoc contentAssocToBeAdded) throws Exception {

		AddContentAssoc command = new AddContentAssoc(contentAssocToBeAdded);
		ContentAssoc contentAssoc = ((ContentAssocAdded) Scheduler.execute(command).data()).getAddedContentAssoc();
		
		if (contentAssoc != null) 
			return successful(contentAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContentAssoc with the specific Id
	 * 
	 * @param contentAssocToBeUpdated
	 *            the ContentAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContentAssoc(@RequestBody ContentAssoc contentAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentAssocToBeUpdated.setnull(null);

		UpdateContentAssoc command = new UpdateContentAssoc(contentAssocToBeUpdated);

		try {
			if(((ContentAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentAssocId}")
	public ResponseEntity<ContentAssoc> findById(@PathVariable String contentAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocId", contentAssocId);
		try {

			List<ContentAssoc> foundContentAssoc = findContentAssocsBy(requestParams).getBody();
			if(foundContentAssoc.size()==1){				return successful(foundContentAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentAssocId}")
	public ResponseEntity<String> deleteContentAssocByIdUpdated(@PathVariable String contentAssocId) throws Exception {
		DeleteContentAssoc command = new DeleteContentAssoc(contentAssocId);

		try {
			if (((ContentAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
