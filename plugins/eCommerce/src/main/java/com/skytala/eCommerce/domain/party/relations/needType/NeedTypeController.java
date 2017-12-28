package com.skytala.eCommerce.domain.party.relations.needType;

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
import com.skytala.eCommerce.domain.party.relations.needType.command.AddNeedType;
import com.skytala.eCommerce.domain.party.relations.needType.command.DeleteNeedType;
import com.skytala.eCommerce.domain.party.relations.needType.command.UpdateNeedType;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeAdded;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeFound;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.needType.mapper.NeedTypeMapper;
import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;
import com.skytala.eCommerce.domain.party.relations.needType.query.FindNeedTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/needTypes")
public class NeedTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public NeedTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a NeedType
	 * @return a List with the NeedTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<NeedType>> findNeedTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindNeedTypesBy query = new FindNeedTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<NeedType> needTypes =((NeedTypeFound) Scheduler.execute(query).data()).getNeedTypes();

		return ResponseEntity.ok().body(needTypes);

	}

	/**
	 * creates a new NeedType entry in the ofbiz database
	 * 
	 * @param needTypeToBeAdded
	 *            the NeedType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<NeedType> createNeedType(@RequestBody NeedType needTypeToBeAdded) throws Exception {

		AddNeedType command = new AddNeedType(needTypeToBeAdded);
		NeedType needType = ((NeedTypeAdded) Scheduler.execute(command).data()).getAddedNeedType();
		
		if (needType != null) 
			return successful(needType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the NeedType with the specific Id
	 * 
	 * @param needTypeToBeUpdated
	 *            the NeedType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{needTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateNeedType(@RequestBody NeedType needTypeToBeUpdated,
			@PathVariable String needTypeId) throws Exception {

		needTypeToBeUpdated.setNeedTypeId(needTypeId);

		UpdateNeedType command = new UpdateNeedType(needTypeToBeUpdated);

		try {
			if(((NeedTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{needTypeId}")
	public ResponseEntity<NeedType> findById(@PathVariable String needTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("needTypeId", needTypeId);
		try {

			List<NeedType> foundNeedType = findNeedTypesBy(requestParams).getBody();
			if(foundNeedType.size()==1){				return successful(foundNeedType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{needTypeId}")
	public ResponseEntity<String> deleteNeedTypeByIdUpdated(@PathVariable String needTypeId) throws Exception {
		DeleteNeedType command = new DeleteNeedType(needTypeId);

		try {
			if (((NeedTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
