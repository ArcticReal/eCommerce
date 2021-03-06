package com.skytala.eCommerce.domain.party.relations.postalAddress;

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
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.AddPostalAddress;
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.DeletePostalAddress;
import com.skytala.eCommerce.domain.party.relations.postalAddress.command.UpdatePostalAddress;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressAdded;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressDeleted;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressFound;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressUpdated;
import com.skytala.eCommerce.domain.party.relations.postalAddress.mapper.PostalAddressMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.domain.party.relations.postalAddress.query.FindPostalAddresssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/postalAddresss")
public class PostalAddressController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PostalAddressController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PostalAddress
	 * @return a List with the PostalAddresss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<PostalAddress>> findPostalAddresssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPostalAddresssBy query = new FindPostalAddresssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PostalAddress> postalAddresss =((PostalAddressFound) Scheduler.execute(query).data()).getPostalAddresss();

		return ResponseEntity.ok().body(postalAddresss);

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
	public ResponseEntity<PostalAddress> createPostalAddress(HttpServletRequest request) throws Exception {

		PostalAddress postalAddressToBeAdded = new PostalAddress();
		try {
			postalAddressToBeAdded = PostalAddressMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createPostalAddress(postalAddressToBeAdded);

	}

	/**
	 * creates a new PostalAddress entry in the ofbiz database
	 * 
	 * @param postalAddressToBeAdded
	 *            the PostalAddress thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PostalAddress> createPostalAddress(@RequestBody PostalAddress postalAddressToBeAdded) throws Exception {

		AddPostalAddress command = new AddPostalAddress(postalAddressToBeAdded);
		PostalAddress postalAddress = ((PostalAddressAdded) Scheduler.execute(command).data()).getAddedPostalAddress();
		
		if (postalAddress != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(postalAddress);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
	}

	/**
	 * Updates the PostalAddress with the specific Id
	 * 
	 * @param postalAddressToBeUpdated
	 *            the PostalAddress thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePostalAddress(@RequestBody PostalAddress postalAddressToBeUpdated,
			@PathVariable String nullVal) throws Exception {

		postalAddressToBeUpdated.setContactMechId(nullVal);

		UpdatePostalAddress command = new UpdatePostalAddress(postalAddressToBeUpdated);

		try {
			if(((PostalAddressUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{postalAddressId}")
	public ResponseEntity<PostalAddress> findById(@PathVariable String postalAddressId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechId", postalAddressId);
		try {

			List<PostalAddress> foundPostalAddress = findPostalAddresssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPostalAddress.get(0));
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{postalAddressId}")
	public ResponseEntity<Object> deletePostalAddressByIdUpdated(@PathVariable String postalAddressId) throws Exception {
		DeletePostalAddress command = new DeletePostalAddress(postalAddressId);

		try {
			if (((PostalAddressDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PostalAddress could not be deleted");

	}

}
