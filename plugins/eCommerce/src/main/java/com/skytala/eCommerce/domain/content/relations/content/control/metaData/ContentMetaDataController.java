package com.skytala.eCommerce.domain.content.relations.content.control.metaData;

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
import com.skytala.eCommerce.domain.content.relations.content.command.metaData.AddContentMetaData;
import com.skytala.eCommerce.domain.content.relations.content.command.metaData.DeleteContentMetaData;
import com.skytala.eCommerce.domain.content.relations.content.command.metaData.UpdateContentMetaData;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.metaData.ContentMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;
import com.skytala.eCommerce.domain.content.relations.content.query.metaData.FindContentMetaDatasBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/content/contentMetaDatas")
public class ContentMetaDataController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentMetaDataController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentMetaData
	 * @return a List with the ContentMetaDatas
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findContentMetaDatasBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentMetaDatasBy query = new FindContentMetaDatasBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentMetaData> contentMetaDatas =((ContentMetaDataFound) Scheduler.execute(query).data()).getContentMetaDatas();

		if (contentMetaDatas.size() == 1) {
			return ResponseEntity.ok().body(contentMetaDatas.get(0));
		}

		return ResponseEntity.ok().body(contentMetaDatas);

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
	public ResponseEntity<Object> createContentMetaData(HttpServletRequest request) throws Exception {

		ContentMetaData contentMetaDataToBeAdded = new ContentMetaData();
		try {
			contentMetaDataToBeAdded = ContentMetaDataMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentMetaData(contentMetaDataToBeAdded);

	}

	/**
	 * creates a new ContentMetaData entry in the ofbiz database
	 * 
	 * @param contentMetaDataToBeAdded
	 *            the ContentMetaData thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentMetaData(@RequestBody ContentMetaData contentMetaDataToBeAdded) throws Exception {

		AddContentMetaData command = new AddContentMetaData(contentMetaDataToBeAdded);
		ContentMetaData contentMetaData = ((ContentMetaDataAdded) Scheduler.execute(command).data()).getAddedContentMetaData();
		
		if (contentMetaData != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentMetaData);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentMetaData could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateContentMetaData(HttpServletRequest request) throws Exception {

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

		ContentMetaData contentMetaDataToBeUpdated = new ContentMetaData();

		try {
			contentMetaDataToBeUpdated = ContentMetaDataMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentMetaData(contentMetaDataToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentMetaData with the specific Id
	 * 
	 * @param contentMetaDataToBeUpdated
	 *            the ContentMetaData thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentMetaData(@RequestBody ContentMetaData contentMetaDataToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentMetaDataToBeUpdated.setnull(null);

		UpdateContentMetaData command = new UpdateContentMetaData(contentMetaDataToBeUpdated);

		try {
			if(((ContentMetaDataUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentMetaDataId}")
	public ResponseEntity<Object> findById(@PathVariable String contentMetaDataId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentMetaDataId", contentMetaDataId);
		try {

			Object foundContentMetaData = findContentMetaDatasBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentMetaData);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentMetaDataId}")
	public ResponseEntity<Object> deleteContentMetaDataByIdUpdated(@PathVariable String contentMetaDataId) throws Exception {
		DeleteContentMetaData command = new DeleteContentMetaData(contentMetaDataId);

		try {
			if (((ContentMetaDataDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentMetaData could not be deleted");

	}

}
