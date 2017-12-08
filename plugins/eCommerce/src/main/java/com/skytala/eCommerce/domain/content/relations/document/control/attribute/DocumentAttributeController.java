package com.skytala.eCommerce.domain.content.relations.document.control.attribute;

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
import com.skytala.eCommerce.domain.content.relations.document.command.attribute.AddDocumentAttribute;
import com.skytala.eCommerce.domain.content.relations.document.command.attribute.DeleteDocumentAttribute;
import com.skytala.eCommerce.domain.content.relations.document.command.attribute.UpdateDocumentAttribute;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeDeleted;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeFound;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.document.mapper.attribute.DocumentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.attribute.DocumentAttribute;
import com.skytala.eCommerce.domain.content.relations.document.query.attribute.FindDocumentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/documentAttributes")
public class DocumentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DocumentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DocumentAttribute
	 * @return a List with the DocumentAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDocumentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentAttributesBy query = new FindDocumentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DocumentAttribute> documentAttributes =((DocumentAttributeFound) Scheduler.execute(query).data()).getDocumentAttributes();

		if (documentAttributes.size() == 1) {
			return ResponseEntity.ok().body(documentAttributes.get(0));
		}

		return ResponseEntity.ok().body(documentAttributes);

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
	public ResponseEntity<Object> createDocumentAttribute(HttpServletRequest request) throws Exception {

		DocumentAttribute documentAttributeToBeAdded = new DocumentAttribute();
		try {
			documentAttributeToBeAdded = DocumentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDocumentAttribute(documentAttributeToBeAdded);

	}

	/**
	 * creates a new DocumentAttribute entry in the ofbiz database
	 * 
	 * @param documentAttributeToBeAdded
	 *            the DocumentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDocumentAttribute(@RequestBody DocumentAttribute documentAttributeToBeAdded) throws Exception {

		AddDocumentAttribute command = new AddDocumentAttribute(documentAttributeToBeAdded);
		DocumentAttribute documentAttribute = ((DocumentAttributeAdded) Scheduler.execute(command).data()).getAddedDocumentAttribute();
		
		if (documentAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(documentAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DocumentAttribute could not be created.");
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
	public boolean updateDocumentAttribute(HttpServletRequest request) throws Exception {

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

		DocumentAttribute documentAttributeToBeUpdated = new DocumentAttribute();

		try {
			documentAttributeToBeUpdated = DocumentAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDocumentAttribute(documentAttributeToBeUpdated, documentAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DocumentAttribute with the specific Id
	 * 
	 * @param documentAttributeToBeUpdated
	 *            the DocumentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDocumentAttribute(@RequestBody DocumentAttribute documentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		documentAttributeToBeUpdated.setAttrName(attrName);

		UpdateDocumentAttribute command = new UpdateDocumentAttribute(documentAttributeToBeUpdated);

		try {
			if(((DocumentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{documentAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String documentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentAttributeId", documentAttributeId);
		try {

			Object foundDocumentAttribute = findDocumentAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDocumentAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{documentAttributeId}")
	public ResponseEntity<Object> deleteDocumentAttributeByIdUpdated(@PathVariable String documentAttributeId) throws Exception {
		DeleteDocumentAttribute command = new DeleteDocumentAttribute(documentAttributeId);

		try {
			if (((DocumentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DocumentAttribute could not be deleted");

	}

}
