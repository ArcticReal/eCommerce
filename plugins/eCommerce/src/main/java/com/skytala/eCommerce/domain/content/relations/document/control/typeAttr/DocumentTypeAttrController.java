package com.skytala.eCommerce.domain.content.relations.document.control.typeAttr;

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
import com.skytala.eCommerce.domain.content.relations.document.command.typeAttr.AddDocumentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.document.command.typeAttr.DeleteDocumentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.document.command.typeAttr.UpdateDocumentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrDeleted;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrUpdated;
import com.skytala.eCommerce.domain.content.relations.document.mapper.typeAttr.DocumentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.typeAttr.DocumentTypeAttr;
import com.skytala.eCommerce.domain.content.relations.document.query.typeAttr.FindDocumentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/documentTypeAttrs")
public class DocumentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DocumentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DocumentTypeAttr
	 * @return a List with the DocumentTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDocumentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentTypeAttrsBy query = new FindDocumentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DocumentTypeAttr> documentTypeAttrs =((DocumentTypeAttrFound) Scheduler.execute(query).data()).getDocumentTypeAttrs();

		if (documentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(documentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(documentTypeAttrs);

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
	public ResponseEntity<Object> createDocumentTypeAttr(HttpServletRequest request) throws Exception {

		DocumentTypeAttr documentTypeAttrToBeAdded = new DocumentTypeAttr();
		try {
			documentTypeAttrToBeAdded = DocumentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDocumentTypeAttr(documentTypeAttrToBeAdded);

	}

	/**
	 * creates a new DocumentTypeAttr entry in the ofbiz database
	 * 
	 * @param documentTypeAttrToBeAdded
	 *            the DocumentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDocumentTypeAttr(@RequestBody DocumentTypeAttr documentTypeAttrToBeAdded) throws Exception {

		AddDocumentTypeAttr command = new AddDocumentTypeAttr(documentTypeAttrToBeAdded);
		DocumentTypeAttr documentTypeAttr = ((DocumentTypeAttrAdded) Scheduler.execute(command).data()).getAddedDocumentTypeAttr();
		
		if (documentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(documentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DocumentTypeAttr could not be created.");
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
	public boolean updateDocumentTypeAttr(HttpServletRequest request) throws Exception {

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

		DocumentTypeAttr documentTypeAttrToBeUpdated = new DocumentTypeAttr();

		try {
			documentTypeAttrToBeUpdated = DocumentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDocumentTypeAttr(documentTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the DocumentTypeAttr with the specific Id
	 * 
	 * @param documentTypeAttrToBeUpdated
	 *            the DocumentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDocumentTypeAttr(@RequestBody DocumentTypeAttr documentTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		documentTypeAttrToBeUpdated.setnull(null);

		UpdateDocumentTypeAttr command = new UpdateDocumentTypeAttr(documentTypeAttrToBeUpdated);

		try {
			if(((DocumentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{documentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String documentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentTypeAttrId", documentTypeAttrId);
		try {

			Object foundDocumentTypeAttr = findDocumentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDocumentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{documentTypeAttrId}")
	public ResponseEntity<Object> deleteDocumentTypeAttrByIdUpdated(@PathVariable String documentTypeAttrId) throws Exception {
		DeleteDocumentTypeAttr command = new DeleteDocumentTypeAttr(documentTypeAttrId);

		try {
			if (((DocumentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DocumentTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/documentTypeAttr/\" plus one of the following: "
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
