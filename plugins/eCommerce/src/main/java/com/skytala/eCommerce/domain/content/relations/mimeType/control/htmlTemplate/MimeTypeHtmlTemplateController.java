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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/mimeTypeHtmlTemplates")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMimeTypeHtmlTemplatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMimeTypeHtmlTemplatesBy query = new FindMimeTypeHtmlTemplatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates =((MimeTypeHtmlTemplateFound) Scheduler.execute(query).data()).getMimeTypeHtmlTemplates();

		if (mimeTypeHtmlTemplates.size() == 1) {
			return ResponseEntity.ok().body(mimeTypeHtmlTemplates.get(0));
		}

		return ResponseEntity.ok().body(mimeTypeHtmlTemplates);

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
	public ResponseEntity<Object> createMimeTypeHtmlTemplate(HttpServletRequest request) throws Exception {

		MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeAdded = new MimeTypeHtmlTemplate();
		try {
			mimeTypeHtmlTemplateToBeAdded = MimeTypeHtmlTemplateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeAdded);

	}

	/**
	 * creates a new MimeTypeHtmlTemplate entry in the ofbiz database
	 * 
	 * @param mimeTypeHtmlTemplateToBeAdded
	 *            the MimeTypeHtmlTemplate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMimeTypeHtmlTemplate(@RequestBody MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeAdded) throws Exception {

		AddMimeTypeHtmlTemplate command = new AddMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeAdded);
		MimeTypeHtmlTemplate mimeTypeHtmlTemplate = ((MimeTypeHtmlTemplateAdded) Scheduler.execute(command).data()).getAddedMimeTypeHtmlTemplate();
		
		if (mimeTypeHtmlTemplate != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(mimeTypeHtmlTemplate);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MimeTypeHtmlTemplate could not be created.");
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
	public boolean updateMimeTypeHtmlTemplate(HttpServletRequest request) throws Exception {

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

		MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeUpdated = new MimeTypeHtmlTemplate();

		try {
			mimeTypeHtmlTemplateToBeUpdated = MimeTypeHtmlTemplateMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateMimeTypeHtmlTemplate(@RequestBody MimeTypeHtmlTemplate mimeTypeHtmlTemplateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		mimeTypeHtmlTemplateToBeUpdated.setnull(null);

		UpdateMimeTypeHtmlTemplate command = new UpdateMimeTypeHtmlTemplate(mimeTypeHtmlTemplateToBeUpdated);

		try {
			if(((MimeTypeHtmlTemplateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{mimeTypeHtmlTemplateId}")
	public ResponseEntity<Object> findById(@PathVariable String mimeTypeHtmlTemplateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mimeTypeHtmlTemplateId", mimeTypeHtmlTemplateId);
		try {

			Object foundMimeTypeHtmlTemplate = findMimeTypeHtmlTemplatesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMimeTypeHtmlTemplate);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{mimeTypeHtmlTemplateId}")
	public ResponseEntity<Object> deleteMimeTypeHtmlTemplateByIdUpdated(@PathVariable String mimeTypeHtmlTemplateId) throws Exception {
		DeleteMimeTypeHtmlTemplate command = new DeleteMimeTypeHtmlTemplate(mimeTypeHtmlTemplateId);

		try {
			if (((MimeTypeHtmlTemplateDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MimeTypeHtmlTemplate could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/mimeTypeHtmlTemplate/\" plus one of the following: "
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
