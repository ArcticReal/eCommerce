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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/document/documentAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<DocumentAttribute>> findDocumentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDocumentAttributesBy query = new FindDocumentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DocumentAttribute> documentAttributes =((DocumentAttributeFound) Scheduler.execute(query).data()).getDocumentAttributes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<DocumentAttribute> createDocumentAttribute(HttpServletRequest request) throws Exception {

		DocumentAttribute documentAttributeToBeAdded = new DocumentAttribute();
		try {
			documentAttributeToBeAdded = DocumentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<DocumentAttribute> createDocumentAttribute(@RequestBody DocumentAttribute documentAttributeToBeAdded) throws Exception {

		AddDocumentAttribute command = new AddDocumentAttribute(documentAttributeToBeAdded);
		DocumentAttribute documentAttribute = ((DocumentAttributeAdded) Scheduler.execute(command).data()).getAddedDocumentAttribute();
		
		if (documentAttribute != null) 
			return successful(documentAttribute);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDocumentAttribute(@RequestBody DocumentAttribute documentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		documentAttributeToBeUpdated.setAttrName(attrName);

		UpdateDocumentAttribute command = new UpdateDocumentAttribute(documentAttributeToBeUpdated);

		try {
			if(((DocumentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{documentAttributeId}")
	public ResponseEntity<DocumentAttribute> findById(@PathVariable String documentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("documentAttributeId", documentAttributeId);
		try {

			List<DocumentAttribute> foundDocumentAttribute = findDocumentAttributesBy(requestParams).getBody();
			if(foundDocumentAttribute.size()==1){				return successful(foundDocumentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{documentAttributeId}")
	public ResponseEntity<String> deleteDocumentAttributeByIdUpdated(@PathVariable String documentAttributeId) throws Exception {
		DeleteDocumentAttribute command = new DeleteDocumentAttribute(documentAttributeId);

		try {
			if (((DocumentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
