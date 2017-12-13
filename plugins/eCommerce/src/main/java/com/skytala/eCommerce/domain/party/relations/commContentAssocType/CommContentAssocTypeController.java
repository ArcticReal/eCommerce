package com.skytala.eCommerce.domain.party.relations.commContentAssocType;

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
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.command.AddCommContentAssocType;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.command.DeleteCommContentAssocType;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.command.UpdateCommContentAssocType;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeAdded;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeFound;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.mapper.CommContentAssocTypeMapper;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.query.FindCommContentAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/commContentAssocTypes")
public class CommContentAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommContentAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommContentAssocType
	 * @return a List with the CommContentAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCommContentAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommContentAssocTypesBy query = new FindCommContentAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommContentAssocType> commContentAssocTypes =((CommContentAssocTypeFound) Scheduler.execute(query).data()).getCommContentAssocTypes();

		if (commContentAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(commContentAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(commContentAssocTypes);

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
	public ResponseEntity<Object> createCommContentAssocType(HttpServletRequest request) throws Exception {

		CommContentAssocType commContentAssocTypeToBeAdded = new CommContentAssocType();
		try {
			commContentAssocTypeToBeAdded = CommContentAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommContentAssocType(commContentAssocTypeToBeAdded);

	}

	/**
	 * creates a new CommContentAssocType entry in the ofbiz database
	 * 
	 * @param commContentAssocTypeToBeAdded
	 *            the CommContentAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommContentAssocType(@RequestBody CommContentAssocType commContentAssocTypeToBeAdded) throws Exception {

		AddCommContentAssocType command = new AddCommContentAssocType(commContentAssocTypeToBeAdded);
		CommContentAssocType commContentAssocType = ((CommContentAssocTypeAdded) Scheduler.execute(command).data()).getAddedCommContentAssocType();
		
		if (commContentAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(commContentAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommContentAssocType could not be created.");
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
	public boolean updateCommContentAssocType(HttpServletRequest request) throws Exception {

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

		CommContentAssocType commContentAssocTypeToBeUpdated = new CommContentAssocType();

		try {
			commContentAssocTypeToBeUpdated = CommContentAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommContentAssocType(commContentAssocTypeToBeUpdated, commContentAssocTypeToBeUpdated.getCommContentAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommContentAssocType with the specific Id
	 * 
	 * @param commContentAssocTypeToBeUpdated
	 *            the CommContentAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{commContentAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommContentAssocType(@RequestBody CommContentAssocType commContentAssocTypeToBeUpdated,
			@PathVariable String commContentAssocTypeId) throws Exception {

		commContentAssocTypeToBeUpdated.setCommContentAssocTypeId(commContentAssocTypeId);

		UpdateCommContentAssocType command = new UpdateCommContentAssocType(commContentAssocTypeToBeUpdated);

		try {
			if(((CommContentAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{commContentAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String commContentAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("commContentAssocTypeId", commContentAssocTypeId);
		try {

			Object foundCommContentAssocType = findCommContentAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommContentAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{commContentAssocTypeId}")
	public ResponseEntity<Object> deleteCommContentAssocTypeByIdUpdated(@PathVariable String commContentAssocTypeId) throws Exception {
		DeleteCommContentAssocType command = new DeleteCommContentAssocType(commContentAssocTypeId);

		try {
			if (((CommContentAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommContentAssocType could not be deleted");

	}

}
