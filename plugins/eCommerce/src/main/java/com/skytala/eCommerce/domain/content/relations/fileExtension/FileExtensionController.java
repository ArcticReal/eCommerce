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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/fileExtensions")
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
	@GetMapping("/find")
	public ResponseEntity<List<FileExtension>> findFileExtensionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFileExtensionsBy query = new FindFileExtensionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FileExtension> fileExtensions =((FileExtensionFound) Scheduler.execute(query).data()).getFileExtensions();

		return ResponseEntity.ok().body(fileExtensions);

	}

	/**
	 * creates a new FileExtension entry in the ofbiz database
	 * 
	 * @param fileExtensionToBeAdded
	 *            the FileExtension thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FileExtension> createFileExtension(@RequestBody FileExtension fileExtensionToBeAdded) throws Exception {

		AddFileExtension command = new AddFileExtension(fileExtensionToBeAdded);
		FileExtension fileExtension = ((FileExtensionAdded) Scheduler.execute(command).data()).getAddedFileExtension();
		
		if (fileExtension != null) 
			return successful(fileExtension);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFileExtension(@RequestBody FileExtension fileExtensionToBeUpdated,
			@PathVariable String fileExtensionId) throws Exception {

		fileExtensionToBeUpdated.setFileExtensionId(fileExtensionId);

		UpdateFileExtension command = new UpdateFileExtension(fileExtensionToBeUpdated);

		try {
			if(((FileExtensionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fileExtensionId}")
	public ResponseEntity<FileExtension> findById(@PathVariable String fileExtensionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fileExtensionId", fileExtensionId);
		try {

			List<FileExtension> foundFileExtension = findFileExtensionsBy(requestParams).getBody();
			if(foundFileExtension.size()==1){				return successful(foundFileExtension.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fileExtensionId}")
	public ResponseEntity<String> deleteFileExtensionByIdUpdated(@PathVariable String fileExtensionId) throws Exception {
		DeleteFileExtension command = new DeleteFileExtension(fileExtensionId);

		try {
			if (((FileExtensionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
