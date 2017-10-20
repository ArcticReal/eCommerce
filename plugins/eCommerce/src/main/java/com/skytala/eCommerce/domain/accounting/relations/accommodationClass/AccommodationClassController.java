package com.skytala.eCommerce.domain.accounting.relations.accommodationClass;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.AddAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.DeleteAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command.UpdateAccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.mapper.AccommodationClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.query.FindAccommodationClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/accommodationClasss")
public class AccommodationClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationClass
	 * @return a List with the AccommodationClasss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAccommodationClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationClasssBy query = new FindAccommodationClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationClass> accommodationClasss =((AccommodationClassFound) Scheduler.execute(query).data()).getAccommodationClasss();

		if (accommodationClasss.size() == 1) {
			return ResponseEntity.ok().body(accommodationClasss.get(0));
		}

		return ResponseEntity.ok().body(accommodationClasss);

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
	public ResponseEntity<Object> createAccommodationClass(HttpServletRequest request) throws Exception {

		AccommodationClass accommodationClassToBeAdded = new AccommodationClass();
		try {
			accommodationClassToBeAdded = AccommodationClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAccommodationClass(accommodationClassToBeAdded);

	}

	/**
	 * creates a new AccommodationClass entry in the ofbiz database
	 * 
	 * @param accommodationClassToBeAdded
	 *            the AccommodationClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAccommodationClass(@RequestBody AccommodationClass accommodationClassToBeAdded) throws Exception {

		AddAccommodationClass command = new AddAccommodationClass(accommodationClassToBeAdded);
		AccommodationClass accommodationClass = ((AccommodationClassAdded) Scheduler.execute(command).data()).getAddedAccommodationClass();
		
		if (accommodationClass != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(accommodationClass);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AccommodationClass could not be created.");
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
	public boolean updateAccommodationClass(HttpServletRequest request) throws Exception {

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

		AccommodationClass accommodationClassToBeUpdated = new AccommodationClass();

		try {
			accommodationClassToBeUpdated = AccommodationClassMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAccommodationClass(accommodationClassToBeUpdated, accommodationClassToBeUpdated.getAccommodationClassId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AccommodationClass with the specific Id
	 * 
	 * @param accommodationClassToBeUpdated
	 *            the AccommodationClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAccommodationClass(@RequestBody AccommodationClass accommodationClassToBeUpdated,
			@PathVariable String accommodationClassId) throws Exception {

		accommodationClassToBeUpdated.setAccommodationClassId(accommodationClassId);

		UpdateAccommodationClass command = new UpdateAccommodationClass(accommodationClassToBeUpdated);

		try {
			if(((AccommodationClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{accommodationClassId}")
	public ResponseEntity<Object> findById(@PathVariable String accommodationClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationClassId", accommodationClassId);
		try {

			Object foundAccommodationClass = findAccommodationClasssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAccommodationClass);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{accommodationClassId}")
	public ResponseEntity<Object> deleteAccommodationClassByIdUpdated(@PathVariable String accommodationClassId) throws Exception {
		DeleteAccommodationClass command = new DeleteAccommodationClass(accommodationClassId);

		try {
			if (((AccommodationClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AccommodationClass could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/accommodationClass/\" plus one of the following: "
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
