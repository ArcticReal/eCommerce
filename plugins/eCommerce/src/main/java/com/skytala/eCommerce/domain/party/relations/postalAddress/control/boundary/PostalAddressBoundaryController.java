package com.skytala.eCommerce.domain.party.relations.postalAddress.control.boundary;

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
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.boundary.AddPostalAddressBoundary;
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.boundary.DeletePostalAddressBoundary;
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.boundary.UpdatePostalAddressBoundary;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary.PostalAddressBoundaryAdded;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary.PostalAddressBoundaryDeleted;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary.PostalAddressBoundaryFound;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary.PostalAddressBoundaryUpdated;
import com.skytala.eCommerce.domain.party.relations.postalAddress.mapper.boundary.PostalAddressBoundaryMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.boundary.PostalAddressBoundary;
import com.skytala.eCommerce.domain.party.relations.postalAddress.query.boundary.FindPostalAddressBoundarysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/postalAddress/postalAddressBoundarys")
public class PostalAddressBoundaryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PostalAddressBoundaryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PostalAddressBoundary
	 * @return a List with the PostalAddressBoundarys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPostalAddressBoundarysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPostalAddressBoundarysBy query = new FindPostalAddressBoundarysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PostalAddressBoundary> postalAddressBoundarys =((PostalAddressBoundaryFound) Scheduler.execute(query).data()).getPostalAddressBoundarys();

		if (postalAddressBoundarys.size() == 1) {
			return ResponseEntity.ok().body(postalAddressBoundarys.get(0));
		}

		return ResponseEntity.ok().body(postalAddressBoundarys);

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
	public ResponseEntity<Object> createPostalAddressBoundary(HttpServletRequest request) throws Exception {

		PostalAddressBoundary postalAddressBoundaryToBeAdded = new PostalAddressBoundary();
		try {
			postalAddressBoundaryToBeAdded = PostalAddressBoundaryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPostalAddressBoundary(postalAddressBoundaryToBeAdded);

	}

	/**
	 * creates a new PostalAddressBoundary entry in the ofbiz database
	 * 
	 * @param postalAddressBoundaryToBeAdded
	 *            the PostalAddressBoundary thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPostalAddressBoundary(@RequestBody PostalAddressBoundary postalAddressBoundaryToBeAdded) throws Exception {

		AddPostalAddressBoundary command = new AddPostalAddressBoundary(postalAddressBoundaryToBeAdded);
		PostalAddressBoundary postalAddressBoundary = ((PostalAddressBoundaryAdded) Scheduler.execute(command).data()).getAddedPostalAddressBoundary();
		
		if (postalAddressBoundary != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(postalAddressBoundary);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PostalAddressBoundary could not be created.");
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
	public boolean updatePostalAddressBoundary(HttpServletRequest request) throws Exception {

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

		PostalAddressBoundary postalAddressBoundaryToBeUpdated = new PostalAddressBoundary();

		try {
			postalAddressBoundaryToBeUpdated = PostalAddressBoundaryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePostalAddressBoundary(postalAddressBoundaryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PostalAddressBoundary with the specific Id
	 * 
	 * @param postalAddressBoundaryToBeUpdated
	 *            the PostalAddressBoundary thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePostalAddressBoundary(@RequestBody PostalAddressBoundary postalAddressBoundaryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		postalAddressBoundaryToBeUpdated.setnull(null);

		UpdatePostalAddressBoundary command = new UpdatePostalAddressBoundary(postalAddressBoundaryToBeUpdated);

		try {
			if(((PostalAddressBoundaryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{postalAddressBoundaryId}")
	public ResponseEntity<Object> findById(@PathVariable String postalAddressBoundaryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("postalAddressBoundaryId", postalAddressBoundaryId);
		try {

			Object foundPostalAddressBoundary = findPostalAddressBoundarysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPostalAddressBoundary);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{postalAddressBoundaryId}")
	public ResponseEntity<Object> deletePostalAddressBoundaryByIdUpdated(@PathVariable String postalAddressBoundaryId) throws Exception {
		DeletePostalAddressBoundary command = new DeletePostalAddressBoundary(postalAddressBoundaryId);

		try {
			if (((PostalAddressBoundaryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PostalAddressBoundary could not be deleted");

	}

}
