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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/documents")
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
	@GetMapping("/find")
	public ResponseEntity<List<Document>> findDocumentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentsBy query = new FindDocumentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Document> documents =((DocumentFound) Scheduler.execute(query).data()).getDocuments();

		return ResponseEntity.ok().body(documents);

	}

	/**
	 * creates a new Document entry in the ofbiz database
	 * 
	 * @param documentToBeAdded
	 *            the Document thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Document> createDocument(@RequestBody Document documentToBeAdded) throws Exception {

		AddDocument command = new AddDocument(documentToBeAdded);
		Document document = ((DocumentAdded) Scheduler.execute(command).data()).getAddedDocument();
		
		if (document != null) 
			return successful(document);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDocument(@RequestBody Document documentToBeUpdated,
			@PathVariable String documentId) throws Exception {

		documentToBeUpdated.setDocumentId(documentId);

		UpdateDocument command = new UpdateDocument(documentToBeUpdated);

		try {
			if(((DocumentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{documentId}")
	public ResponseEntity<Document> findById(@PathVariable String documentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentId", documentId);
		try {

			List<Document> foundDocument = findDocumentsBy(requestParams).getBody();
			if(foundDocument.size()==1){				return successful(foundDocument.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{documentId}")
	public ResponseEntity<String> deleteDocumentByIdUpdated(@PathVariable String documentId) throws Exception {
		DeleteDocument command = new DeleteDocument(documentId);

		try {
			if (((DocumentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
