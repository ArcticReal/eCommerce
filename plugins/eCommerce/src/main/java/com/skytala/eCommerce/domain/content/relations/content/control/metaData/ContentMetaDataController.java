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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ContentMetaData>> findContentMetaDatasBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentMetaDatasBy query = new FindContentMetaDatasBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentMetaData> contentMetaDatas =((ContentMetaDataFound) Scheduler.execute(query).data()).getContentMetaDatas();

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
	public ResponseEntity<ContentMetaData> createContentMetaData(HttpServletRequest request) throws Exception {

		ContentMetaData contentMetaDataToBeAdded = new ContentMetaData();
		try {
			contentMetaDataToBeAdded = ContentMetaDataMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ContentMetaData> createContentMetaData(@RequestBody ContentMetaData contentMetaDataToBeAdded) throws Exception {

		AddContentMetaData command = new AddContentMetaData(contentMetaDataToBeAdded);
		ContentMetaData contentMetaData = ((ContentMetaDataAdded) Scheduler.execute(command).data()).getAddedContentMetaData();
		
		if (contentMetaData != null) 
			return successful(contentMetaData);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentMetaData(@RequestBody ContentMetaData contentMetaDataToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contentMetaDataToBeUpdated.setnull(null);

		UpdateContentMetaData command = new UpdateContentMetaData(contentMetaDataToBeUpdated);

		try {
			if(((ContentMetaDataUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentMetaDataId}")
	public ResponseEntity<ContentMetaData> findById(@PathVariable String contentMetaDataId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentMetaDataId", contentMetaDataId);
		try {

			List<ContentMetaData> foundContentMetaData = findContentMetaDatasBy(requestParams).getBody();
			if(foundContentMetaData.size()==1){				return successful(foundContentMetaData.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentMetaDataId}")
	public ResponseEntity<String> deleteContentMetaDataByIdUpdated(@PathVariable String contentMetaDataId) throws Exception {
		DeleteContentMetaData command = new DeleteContentMetaData(contentMetaDataId);

		try {
			if (((ContentMetaDataDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
