package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddDocument;
import com.skytala.eCommerce.command.DeleteDocument;
import com.skytala.eCommerce.command.UpdateDocument;
import com.skytala.eCommerce.entity.Document;
import com.skytala.eCommerce.entity.DocumentMapper;
import com.skytala.eCommerce.event.DocumentAdded;
import com.skytala.eCommerce.event.DocumentDeleted;
import com.skytala.eCommerce.event.DocumentFound;
import com.skytala.eCommerce.event.DocumentUpdated;
import com.skytala.eCommerce.query.FindDocumentsBy;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<Document>> queryReturnVal = new HashMap<>();

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Document
	 * @return a List with the Documents
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/findBy" })
	public List<Document> findDocumentsBy(@RequestParam Map<String, String> allRequestParams){

		FindDocumentsBy query = new FindDocumentsBy(allRequestParams);

		int usedTicketId;

		synchronized (DocumentController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(DocumentFound.class,
				event -> sendDocumentsFoundMessage(((DocumentFound) event).getDocuments(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	public void sendDocumentsFoundMessage(List<Document> documents, int usedTicketId) {
		queryReturnVal.put(usedTicketId, documents);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/create",
			"/insert", "/add" }, consumes = "application/x-www-form-urlencoded")
	public boolean createDocument(HttpServletRequest request) {

		Document documentToBeAdded = new Document();
		try {
			documentToBeAdded = DocumentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
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
	public boolean createDocument(Document documentToBeAdded) {

		AddDocument com = new AddDocument(documentToBeAdded);
		int usedTicketId;

		synchronized (DocumentController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(DocumentAdded.class,
				event -> sendDocumentChangedMessage(((DocumentAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = { "/update",
			"/change" }, consumes = "application/x-www-form-urlencoded")
	public boolean updateDocument(HttpServletRequest request) {

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

		return updateDocument(documentToBeUpdated);

	}

	/**
	 * Updates the Document with the specific Id
	 * 
	 * @param documentToBeUpdated the Document thats to be updated
	 * @return true on success, false on fail
	 */
	public boolean updateDocument(Document documentToBeUpdated) {

		UpdateDocument com = new UpdateDocument(documentToBeUpdated);

		int usedTicketId;

		synchronized (DocumentController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(DocumentUpdated.class,
				event -> sendDocumentChangedMessage(((DocumentUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	/**
	 * removes a Document from the database
	 * 
	 * @param documentId:
	 *            the id of the Document thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = { "/removeById", "/removeBy" })
	public boolean deletedocumentById(@RequestParam(value = "documentId") String documentId) {

		DeleteDocument com = new DeleteDocument(documentId);

		int usedTicketId;

		synchronized (DocumentController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(DocumentDeleted.class,
				event -> sendDocumentChangedMessage(((DocumentDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	public void sendDocumentChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(value = (" * "))
	public String returnErrorPage(HttpServletRequest request) {

		return "Error 404: Page not found! Check your spelling and/or your request method.";

}}
