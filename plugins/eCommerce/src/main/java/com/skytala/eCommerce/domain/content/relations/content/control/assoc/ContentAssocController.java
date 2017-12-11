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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContentAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentAssocsBy query = new FindContentAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentAssoc> contentAssocs =((ContentAssocFound) Scheduler.execute(query).data()).getContentAssocs();

		if (contentAssocs.size() == 1) {
			return ResponseEntity.ok().body(contentAssocs.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContentAssoc(HttpServletRequest request) throws Exception {

		ContentAssoc contentAssocToBeAdded = new ContentAssoc();
		try {
			contentAssocToBeAdded = ContentAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContentAssoc(@RequestBody ContentAssoc contentAssocToBeAdded) throws Exception {

		AddContentAssoc command = new AddContentAssoc(contentAssocToBeAdded);
		ContentAssoc contentAssoc = ((ContentAssocAdded) Scheduler.execute(command).data()).getAddedContentAssoc();
		
		if (contentAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentAssoc could not be created.");
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
	public boolean updateContentAssoc(HttpServletRequest request) throws Exception {

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

		ContentAssoc contentAssocToBeUpdated = new ContentAssoc();

		try {
			contentAssocToBeUpdated = ContentAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentAssoc(contentAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContentAssoc(@RequestBody ContentAssoc contentAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentAssocToBeUpdated.setnull(null);

		UpdateContentAssoc command = new UpdateContentAssoc(contentAssocToBeUpdated);

		try {
			if(((ContentAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contentAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String contentAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentAssocId", contentAssocId);
		try {

			Object foundContentAssoc = findContentAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentAssocId}")
	public ResponseEntity<Object> deleteContentAssocByIdUpdated(@PathVariable String contentAssocId) throws Exception {
		DeleteContentAssoc command = new DeleteContentAssoc(contentAssocId);

		try {
			if (((ContentAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentAssoc could not be deleted");

	}

}
