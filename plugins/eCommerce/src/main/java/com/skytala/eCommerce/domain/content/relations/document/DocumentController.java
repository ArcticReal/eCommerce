package com.skytala.eCommerce.domain.content.relations.document;

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
import com.skytala.eCommerce.domain.content.relations.document.command.AddDocument;
import com.skytala.eCommerce.domain.content.relations.document.command.DeleteDocument;
import com.skytala.eCommerce.domain.content.relations.document.command.UpdateDocument;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentAdded;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentDeleted;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentFound;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentUpdated;
import com.skytala.eCommerce.domain.content.relations.document.mapper.DocumentMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.Document;
import com.skytala.eCommerce.domain.content.relations.document.query.FindDocumentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/documents")
public class DocumentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DocumentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Document
	 * @return a List with the Documents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDocumentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentsBy query = new FindDocumentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Document> documents =((DocumentFound) Scheduler.execute(query).data()).getDocuments();

		if (documents.size() == 1) {
			return ResponseEntity.ok().body(documents.get(0));
		}

		return ResponseEntity.ok().body(documents);

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
	public ResponseEntity<Object> createDocument(HttpServletRequest request) throws Exception {

		Document documentToBeAdded = new Document();
		try {
			documentToBeAdded = DocumentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDocument(documentToBeAdded);

	}

	/**
	 * creates a new Document entry in the ofbiz database
	 * 
	 * @param documentToBeAdded
	 *            the Document thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDocument(@RequestBody Document documentToBeAdded) throws Exception {

		AddDocument command = new AddDocument(documentToBeAdded);
		Document document = ((DocumentAdded) Scheduler.execute(command).data()).getAddedDocument();
		
		if (document != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(document);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Document could not be created.");
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
	public boolean updateDocument(HttpServletRequest request) throws Exception {

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

		Document documentToBeUpdated = new Document();

		try {
			documentToBeUpdated = DocumentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDocument(documentToBeUpdated, documentToBeUpdated.getDocumentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Document with the specific Id
	 * 
	 * @param documentToBeUpdated
	 *            the Document thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{documentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDocument(@RequestBody Document documentToBeUpdated,
			@PathVariable String documentId) throws Exception {

		documentToBeUpdated.setDocumentId(documentId);

		UpdateDocument command = new UpdateDocument(documentToBeUpdated);

		try {
			if(((DocumentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{documentId}")
	public ResponseEntity<Object> findById(@PathVariable String documentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentId", documentId);
		try {

			Object foundDocument = findDocumentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDocument);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{documentId}")
	public ResponseEntity<Object> deleteDocumentByIdUpdated(@PathVariable String documentId) throws Exception {
		DeleteDocument command = new DeleteDocument(documentId);

		try {
			if (((DocumentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Document could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/document/\" plus one of the following: "
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
