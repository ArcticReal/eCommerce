package com.skytala.eCommerce.domain.content.relations.document.control.type;

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
import com.skytala.eCommerce.domain.content.relations.document.command.type.AddDocumentType;
import com.skytala.eCommerce.domain.content.relations.document.command.type.DeleteDocumentType;
import com.skytala.eCommerce.domain.content.relations.document.command.type.UpdateDocumentType;
import com.skytala.eCommerce.domain.content.relations.document.event.type.DocumentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.document.event.type.DocumentTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.document.event.type.DocumentTypeFound;
import com.skytala.eCommerce.domain.content.relations.document.event.type.DocumentTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.document.mapper.type.DocumentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.type.DocumentType;
import com.skytala.eCommerce.domain.content.relations.document.query.type.FindDocumentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/document/documentTypes")
public class DocumentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DocumentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DocumentType
	 * @return a List with the DocumentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DocumentType>> findDocumentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentTypesBy query = new FindDocumentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DocumentType> documentTypes =((DocumentTypeFound) Scheduler.execute(query).data()).getDocumentTypes();

		return ResponseEntity.ok().body(documentTypes);

	}

	/**
	 * creates a new DocumentType entry in the ofbiz database
	 * 
	 * @param documentTypeToBeAdded
	 *            the DocumentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DocumentType> createDocumentType(@RequestBody DocumentType documentTypeToBeAdded) throws Exception {

		AddDocumentType command = new AddDocumentType(documentTypeToBeAdded);
		DocumentType documentType = ((DocumentTypeAdded) Scheduler.execute(command).data()).getAddedDocumentType();
		
		if (documentType != null) 
			return successful(documentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DocumentType with the specific Id
	 * 
	 * @param documentTypeToBeUpdated
	 *            the DocumentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{documentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDocumentType(@RequestBody DocumentType documentTypeToBeUpdated,
			@PathVariable String documentTypeId) throws Exception {

		documentTypeToBeUpdated.setDocumentTypeId(documentTypeId);

		UpdateDocumentType command = new UpdateDocumentType(documentTypeToBeUpdated);

		try {
			if(((DocumentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{documentTypeId}")
	public ResponseEntity<DocumentType> findById(@PathVariable String documentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentTypeId", documentTypeId);
		try {

			List<DocumentType> foundDocumentType = findDocumentTypesBy(requestParams).getBody();
			if(foundDocumentType.size()==1){				return successful(foundDocumentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{documentTypeId}")
	public ResponseEntity<String> deleteDocumentTypeByIdUpdated(@PathVariable String documentTypeId) throws Exception {
		DeleteDocumentType command = new DeleteDocumentType(documentTypeId);

		try {
			if (((DocumentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
