package com.skytala.eCommerce.domain.content.relations.fileExtension;

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
import com.skytala.eCommerce.domain.content.relations.fileExtension.command.AddFileExtension;
import com.skytala.eCommerce.domain.content.relations.fileExtension.command.DeleteFileExtension;
import com.skytala.eCommerce.domain.content.relations.fileExtension.command.UpdateFileExtension;
import com.skytala.eCommerce.domain.content.relations.fileExtension.event.FileExtensionAdded;
import com.skytala.eCommerce.domain.content.relations.fileExtension.event.FileExtensionDeleted;
import com.skytala.eCommerce.domain.content.relations.fileExtension.event.FileExtensionFound;
import com.skytala.eCommerce.domain.content.relations.fileExtension.event.FileExtensionUpdated;
import com.skytala.eCommerce.domain.content.relations.fileExtension.mapper.FileExtensionMapper;
import com.skytala.eCommerce.domain.content.relations.fileExtension.model.FileExtension;
import com.skytala.eCommerce.domain.content.relations.fileExtension.query.FindFileExtensionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fileExtensions")
public class FileExtensionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FileExtensionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FileExtension
	 * @return a List with the FileExtensions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFileExtensionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFileExtensionsBy query = new FindFileExtensionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FileExtension> fileExtensions =((FileExtensionFound) Scheduler.execute(query).data()).getFileExtensions();

		if (fileExtensions.size() == 1) {
			return ResponseEntity.ok().body(fileExtensions.get(0));
		}

		return ResponseEntity.ok().body(fileExtensions);

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
	public ResponseEntity<Object> createFileExtension(HttpServletRequest request) throws Exception {

		FileExtension fileExtensionToBeAdded = new FileExtension();
		try {
			fileExtensionToBeAdded = FileExtensionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFileExtension(fileExtensionToBeAdded);

	}

	/**
	 * creates a new FileExtension entry in the ofbiz database
	 * 
	 * @param fileExtensionToBeAdded
	 *            the FileExtension thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFileExtension(@RequestBody FileExtension fileExtensionToBeAdded) throws Exception {

		AddFileExtension command = new AddFileExtension(fileExtensionToBeAdded);
		FileExtension fileExtension = ((FileExtensionAdded) Scheduler.execute(command).data()).getAddedFileExtension();
		
		if (fileExtension != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fileExtension);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FileExtension could not be created.");
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
	public boolean updateFileExtension(HttpServletRequest request) throws Exception {

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

		FileExtension fileExtensionToBeUpdated = new FileExtension();

		try {
			fileExtensionToBeUpdated = FileExtensionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFileExtension(fileExtensionToBeUpdated, fileExtensionToBeUpdated.getFileExtensionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FileExtension with the specific Id
	 * 
	 * @param fileExtensionToBeUpdated
	 *            the FileExtension thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fileExtensionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFileExtension(@RequestBody FileExtension fileExtensionToBeUpdated,
			@PathVariable String fileExtensionId) throws Exception {

		fileExtensionToBeUpdated.setFileExtensionId(fileExtensionId);

		UpdateFileExtension command = new UpdateFileExtension(fileExtensionToBeUpdated);

		try {
			if(((FileExtensionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fileExtensionId}")
	public ResponseEntity<Object> findById(@PathVariable String fileExtensionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fileExtensionId", fileExtensionId);
		try {

			Object foundFileExtension = findFileExtensionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFileExtension);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fileExtensionId}")
	public ResponseEntity<Object> deleteFileExtensionByIdUpdated(@PathVariable String fileExtensionId) throws Exception {
		DeleteFileExtension command = new DeleteFileExtension(fileExtensionId);

		try {
			if (((FileExtensionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FileExtension could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/fileExtension/\" plus one of the following: "
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
