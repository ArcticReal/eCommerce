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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/mimeTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMimeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMimeTypesBy query = new FindMimeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MimeType> mimeTypes =((MimeTypeFound) Scheduler.execute(query).data()).getMimeTypes();

		if (mimeTypes.size() == 1) {
			return ResponseEntity.ok().body(mimeTypes.get(0));
		}

		return ResponseEntity.ok().body(mimeTypes);

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
	public ResponseEntity<Object> createMimeType(HttpServletRequest request) throws Exception {

		MimeType mimeTypeToBeAdded = new MimeType();
		try {
			mimeTypeToBeAdded = MimeTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMimeType(mimeTypeToBeAdded);

	}

	/**
	 * creates a new MimeType entry in the ofbiz database
	 * 
	 * @param mimeTypeToBeAdded
	 *            the MimeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMimeType(@RequestBody MimeType mimeTypeToBeAdded) throws Exception {

		AddMimeType command = new AddMimeType(mimeTypeToBeAdded);
		MimeType mimeType = ((MimeTypeAdded) Scheduler.execute(command).data()).getAddedMimeType();
		
		if (mimeType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(mimeType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MimeType could not be created.");
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
	public boolean updateMimeType(HttpServletRequest request) throws Exception {

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

		MimeType mimeTypeToBeUpdated = new MimeType();

		try {
			mimeTypeToBeUpdated = MimeTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMimeType(mimeTypeToBeUpdated, mimeTypeToBeUpdated.getMimeTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateMimeType(@RequestBody MimeType mimeTypeToBeUpdated,
			@PathVariable String mimeTypeId) throws Exception {

		mimeTypeToBeUpdated.setMimeTypeId(mimeTypeId);

		UpdateMimeType command = new UpdateMimeType(mimeTypeToBeUpdated);

		try {
			if(((MimeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{mimeTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String mimeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mimeTypeId", mimeTypeId);
		try {

			Object foundMimeType = findMimeTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMimeType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{mimeTypeId}")
	public ResponseEntity<Object> deleteMimeTypeByIdUpdated(@PathVariable String mimeTypeId) throws Exception {
		DeleteMimeType command = new DeleteMimeType(mimeTypeId);

		try {
			if (((MimeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MimeType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/mimeType/\" plus one of the following: "
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
