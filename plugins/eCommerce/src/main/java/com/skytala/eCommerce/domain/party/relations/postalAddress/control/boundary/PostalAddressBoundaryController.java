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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<PostalAddressBoundary>> findPostalAddressBoundarysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPostalAddressBoundarysBy query = new FindPostalAddressBoundarysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PostalAddressBoundary> postalAddressBoundarys =((PostalAddressBoundaryFound) Scheduler.execute(query).data()).getPostalAddressBoundarys();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PostalAddressBoundary> createPostalAddressBoundary(HttpServletRequest request) throws Exception {

		PostalAddressBoundary postalAddressBoundaryToBeAdded = new PostalAddressBoundary();
		try {
			postalAddressBoundaryToBeAdded = PostalAddressBoundaryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PostalAddressBoundary> createPostalAddressBoundary(@RequestBody PostalAddressBoundary postalAddressBoundaryToBeAdded) throws Exception {

		AddPostalAddressBoundary command = new AddPostalAddressBoundary(postalAddressBoundaryToBeAdded);
		PostalAddressBoundary postalAddressBoundary = ((PostalAddressBoundaryAdded) Scheduler.execute(command).data()).getAddedPostalAddressBoundary();
		
		if (postalAddressBoundary != null) 
			return successful(postalAddressBoundary);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePostalAddressBoundary(@RequestBody PostalAddressBoundary postalAddressBoundaryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		postalAddressBoundaryToBeUpdated.setnull(null);

		UpdatePostalAddressBoundary command = new UpdatePostalAddressBoundary(postalAddressBoundaryToBeUpdated);

		try {
			if(((PostalAddressBoundaryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{postalAddressBoundaryId}")
	public ResponseEntity<PostalAddressBoundary> findById(@PathVariable String postalAddressBoundaryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("postalAddressBoundaryId", postalAddressBoundaryId);
		try {

			List<PostalAddressBoundary> foundPostalAddressBoundary = findPostalAddressBoundarysBy(requestParams).getBody();
			if(foundPostalAddressBoundary.size()==1){				return successful(foundPostalAddressBoundary.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{postalAddressBoundaryId}")
	public ResponseEntity<String> deletePostalAddressBoundaryByIdUpdated(@PathVariable String postalAddressBoundaryId) throws Exception {
		DeletePostalAddressBoundary command = new DeletePostalAddressBoundary(postalAddressBoundaryId);

		try {
			if (((PostalAddressBoundaryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
