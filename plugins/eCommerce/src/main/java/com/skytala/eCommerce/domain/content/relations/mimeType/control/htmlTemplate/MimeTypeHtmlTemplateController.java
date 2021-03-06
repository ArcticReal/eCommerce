package com.skytala.eCommerce.domain.content.relations.mimeType.control.htmlTemplate;

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
import com.skytala.eCommerce.domain.content.relations.mimeType.command.htmlTemplate.AddMimeTypeHtmlTemplate;
import com.skytala.eCommerce.domain.content.relations.mimeType.command.htmlTemplate.DeleteMimeTypeHtmlTemplate;
import com.skytala.eCommerce.domain.content.relations.mimeType.command.htmlTemplate.UpdateMimeTypeHtmlTemplate;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateAdded;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateDeleted;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateFound;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateUpdated;
import com.skytala.eCommerce.domain.content.relations.mimeType.mapper.htmlTemplate.MimeTypeHtmlTemplateMapper;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;
import com.skytala.eCommerce.domain.content.relations.mimeType.query.htmlTemplate.FindMimeTypeHtmlTemplatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/mimeType/mimeTypeHtmlTemplates")
public class MimeTypeHtmlTemplateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MimeTypeHtmlTemplateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MimeTypeHtmlTemplate
	 * @return a List with the MimeTypeHtmlTemplates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MimeTypeHtmlTemplate>> findMimeTypeHtmlTemplatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMimeTypeHtmlTemplatesBy query = new FindMimeTypeHtmlTemplatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates =((MimeTypeHtmlTemplateFound) Scheduler.execute(query).data()).getMimeTypeHtmlTemplates();

		return ResponseEntity.ok().body(mimeTypeHtmlTemplates);

	}

	/**
	 * creates a new MimeTypeHtmlTemplate entry in the ofbiz database
	 * 
	 * @param mimeTypeHtmlTemplateToBeAdded
	 *            the MimeTypeHtmlTemplate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MimeTypeHtmlTemplate> createMimeTypeHtmlTemplate(@RequestBody MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeAdded) throws Exception {

		AddMimeTypeHtmlTemplate command = new AddMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeAdded);
		MimeTypeHtmlTemplate mimeTypeHtmlTemplate = ((MimeTypeHtmlTemplateAdded) Scheduler.execute(command).data()).getAddedMimeTypeHtmlTemplate();
		
		if (mimeTypeHtmlTemplate != null) 
			return successful(mimeTypeHtmlTemplate);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MimeTypeHtmlTemplate with the specific Id
	 * 
	 * @param mimeTypeHtmlTemplateToBeUpdated
	 *            the MimeTypeHtmlTemplate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMimeTypeHtmlTemplate(@RequestBody MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		mimeTypeHtmlTemplateToBeUpdated.setnull(null);

		UpdateMimeTypeHtmlTemplate command = new UpdateMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeUpdated);

		try {
			if(((MimeTypeHtmlTemplateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{mimeTypeHtmlTemplateId}")
	public ResponseEntity<MimeTypeHtmlTemplate> findById(@PathVariable String mimeTypeHtmlTemplateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mimeTypeHtmlTemplateId", mimeTypeHtmlTemplateId);
		try {

			List<MimeTypeHtmlTemplate> foundMimeTypeHtmlTemplate = findMimeTypeHtmlTemplatesBy(requestParams).getBody();
			if(foundMimeTypeHtmlTemplate.size()==1){				return successful(foundMimeTypeHtmlTemplate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{mimeTypeHtmlTemplateId}")
	public ResponseEntity<String> deleteMimeTypeHtmlTemplateByIdUpdated(@PathVariable String mimeTypeHtmlTemplateId) throws Exception {
		DeleteMimeTypeHtmlTemplate command = new DeleteMimeTypeHtmlTemplate(mimeTypeHtmlTemplateId);

		try {
			if (((MimeTypeHtmlTemplateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
