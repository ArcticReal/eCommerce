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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CommContentAssocType>> findCommContentAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommContentAssocTypesBy query = new FindCommContentAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommContentAssocType> commContentAssocTypes =((CommContentAssocTypeFound) Scheduler.execute(query).data()).getCommContentAssocTypes();

		return ResponseEntity.ok().body(commContentAssocTypes);

	}

	/**
	 * creates a new CommContentAssocType entry in the ofbiz database
	 * 
	 * @param commContentAssocTypeToBeAdded
	 *            the CommContentAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommContentAssocType> createCommContentAssocType(@RequestBody CommContentAssocType commContentAssocTypeToBeAdded) throws Exception {

		AddCommContentAssocType command = new AddCommContentAssocType(commContentAssocTypeToBeAdded);
		CommContentAssocType commContentAssocType = ((CommContentAssocTypeAdded) Scheduler.execute(command).data()).getAddedCommContentAssocType();
		
		if (commContentAssocType != null) 
			return successful(commContentAssocType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCommContentAssocType(@RequestBody CommContentAssocType commContentAssocTypeToBeUpdated,
			@PathVariable String commContentAssocTypeId) throws Exception {

		commContentAssocTypeToBeUpdated.setCommContentAssocTypeId(commContentAssocTypeId);

		UpdateCommContentAssocType command = new UpdateCommContentAssocType(commContentAssocTypeToBeUpdated);

		try {
			if(((CommContentAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{commContentAssocTypeId}")
	public ResponseEntity<CommContentAssocType> findById(@PathVariable String commContentAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("commContentAssocTypeId", commContentAssocTypeId);
		try {

			List<CommContentAssocType> foundCommContentAssocType = findCommContentAssocTypesBy(requestParams).getBody();
			if(foundCommContentAssocType.size()==1){				return successful(foundCommContentAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{commContentAssocTypeId}")
	public ResponseEntity<String> deleteCommContentAssocTypeByIdUpdated(@PathVariable String commContentAssocTypeId) throws Exception {
		DeleteCommContentAssocType command = new DeleteCommContentAssocType(commContentAssocTypeId);

		try {
			if (((CommContentAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
