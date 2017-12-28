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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/document/documentTypeAttrs")
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
	@GetMapping("/find")
	public ResponseEntity<List<DocumentTypeAttr>> findDocumentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentTypeAttrsBy query = new FindDocumentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DocumentTypeAttr> documentTypeAttrs =((DocumentTypeAttrFound) Scheduler.execute(query).data()).getDocumentTypeAttrs();

		return ResponseEntity.ok().body(documentTypeAttrs);

	}

	/**
	 * creates a new DocumentTypeAttr entry in the ofbiz database
	 * 
	 * @param documentTypeAttrToBeAdded
	 *            the DocumentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DocumentTypeAttr> createDocumentTypeAttr(@RequestBody DocumentTypeAttr documentTypeAttrToBeAdded) throws Exception {

		AddDocumentTypeAttr command = new AddDocumentTypeAttr(documentTypeAttrToBeAdded);
		DocumentTypeAttr documentTypeAttr = ((DocumentTypeAttrAdded) Scheduler.execute(command).data()).getAddedDocumentTypeAttr();
		
		if (documentTypeAttr != null) 
			return successful(documentTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DocumentTypeAttr with the specific Id
	 * 
	 * @param documentTypeAttrToBeUpdated
	 *            the DocumentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDocumentTypeAttr(@RequestBody DocumentTypeAttr documentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		documentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateDocumentTypeAttr command = new UpdateDocumentTypeAttr(documentTypeAttrToBeUpdated);

		try {
			if(((DocumentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{documentTypeAttrId}")
	public ResponseEntity<DocumentTypeAttr> findById(@PathVariable String documentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentTypeAttrId", documentTypeAttrId);
		try {

			List<DocumentTypeAttr> foundDocumentTypeAttr = findDocumentTypeAttrsBy(requestParams).getBody();
			if(foundDocumentTypeAttr.size()==1){				return successful(foundDocumentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{documentTypeAttrId}")
	public ResponseEntity<String> deleteDocumentTypeAttrByIdUpdated(@PathVariable String documentTypeAttrId) throws Exception {
		DeleteDocumentTypeAttr command = new DeleteDocumentTypeAttr(documentTypeAttrId);

		try {
			if (((DocumentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
