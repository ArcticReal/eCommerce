package com.skytala.eCommerce.domain.content.relations.mimeType;

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
import com.skytala.eCommerce.domain.content.relations.mimeType.command.AddMimeType;
import com.skytala.eCommerce.domain.content.relations.mimeType.command.DeleteMimeType;
import com.skytala.eCommerce.domain.content.relations.mimeType.command.UpdateMimeType;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.MimeTypeAdded;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.MimeTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.MimeTypeFound;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.MimeTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.mimeType.mapper.MimeTypeMapper;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;
import com.skytala.eCommerce.domain.content.relations.mimeType.query.FindMimeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/mimeTypes")
public class MimeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MimeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MimeType
	 * @return a List with the MimeTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MimeType>> findMimeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMimeTypesBy query = new FindMimeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MimeType> mimeTypes =((MimeTypeFound) Scheduler.execute(query).data()).getMimeTypes();

		return ResponseEntity.ok().body(mimeTypes);

	}

	/**
	 * creates a new MimeType entry in the ofbiz database
	 * 
	 * @param mimeTypeToBeAdded
	 *            the MimeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MimeType> createMimeType(@RequestBody MimeType mimeTypeToBeAdded) throws Exception {

		AddMimeType command = new AddMimeType(mimeTypeToBeAdded);
		MimeType mimeType = ((MimeTypeAdded) Scheduler.execute(command).data()).getAddedMimeType();
		
		if (mimeType != null) 
			return successful(mimeType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MimeType with the specific Id
	 * 
	 * @param mimeTypeToBeUpdated
	 *            the MimeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{mimeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMimeType(@RequestBody MimeType mimeTypeToBeUpdated,
			@PathVariable String mimeTypeId) throws Exception {

		mimeTypeToBeUpdated.setMimeTypeId(mimeTypeId);

		UpdateMimeType command = new UpdateMimeType(mimeTypeToBeUpdated);

		try {
			if(((MimeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{mimeTypeId}")
	public ResponseEntity<MimeType> findById(@PathVariable String mimeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mimeTypeId", mimeTypeId);
		try {

			List<MimeType> foundMimeType = findMimeTypesBy(requestParams).getBody();
			if(foundMimeType.size()==1){				return successful(foundMimeType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{mimeTypeId}")
	public ResponseEntity<String> deleteMimeTypeByIdUpdated(@PathVariable String mimeTypeId) throws Exception {
		DeleteMimeType command = new DeleteMimeType(mimeTypeId);

		try {
			if (((MimeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
