package com.skytala.eCommerce.domain.content.relations.contentMetaData;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.command.AddContentMetaData;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.command.DeleteContentMetaData;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.command.UpdateContentMetaData;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.event.ContentMetaDataAdded;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.event.ContentMetaDataDeleted;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.event.ContentMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.event.ContentMetaDataUpdated;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.mapper.ContentMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.model.ContentMetaData;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.query.FindContentMetaDatasBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contentMetaDatas")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@RequestMapping(method = RequestMethod.GET, value = "/{contentMetaDataId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentMetaDataId}")
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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contentMetaData/\" plus one of the following: "
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
