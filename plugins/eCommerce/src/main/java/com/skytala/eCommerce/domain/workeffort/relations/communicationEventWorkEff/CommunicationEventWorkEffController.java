package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff;

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
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.command.AddCommunicationEventWorkEff;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.command.DeleteCommunicationEventWorkEff;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.command.UpdateCommunicationEventWorkEff;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffAdded;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffFound;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.mapper.CommunicationEventWorkEffMapper;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.query.FindCommunicationEventWorkEffsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/communicationEventWorkEffs")
public class CommunicationEventWorkEffController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventWorkEffController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventWorkEff
	 * @return a List with the CommunicationEventWorkEffs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventWorkEff>> findCommunicationEventWorkEffsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventWorkEffsBy query = new FindCommunicationEventWorkEffsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventWorkEff> communicationEventWorkEffs =((CommunicationEventWorkEffFound) Scheduler.execute(query).data()).getCommunicationEventWorkEffs();

		return ResponseEntity.ok().body(communicationEventWorkEffs);

	}

	/**
	 * creates a new CommunicationEventWorkEff entry in the ofbiz database
	 * 
	 * @param communicationEventWorkEffToBeAdded
	 *            the CommunicationEventWorkEff thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommunicationEventWorkEff> createCommunicationEventWorkEff(@RequestBody CommunicationEventWorkEff communicationEventWorkEffToBeAdded) throws Exception {

		AddCommunicationEventWorkEff command = new AddCommunicationEventWorkEff(communicationEventWorkEffToBeAdded);
		CommunicationEventWorkEff communicationEventWorkEff = ((CommunicationEventWorkEffAdded) Scheduler.execute(command).data()).getAddedCommunicationEventWorkEff();
		
		if (communicationEventWorkEff != null) 
			return successful(communicationEventWorkEff);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEventWorkEff with the specific Id
	 * 
	 * @param communicationEventWorkEffToBeUpdated
	 *            the CommunicationEventWorkEff thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEventWorkEff(@RequestBody CommunicationEventWorkEff communicationEventWorkEffToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventWorkEffToBeUpdated.setnull(null);

		UpdateCommunicationEventWorkEff command = new UpdateCommunicationEventWorkEff(communicationEventWorkEffToBeUpdated);

		try {
			if(((CommunicationEventWorkEffUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventWorkEffId}")
	public ResponseEntity<CommunicationEventWorkEff> findById(@PathVariable String communicationEventWorkEffId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventWorkEffId", communicationEventWorkEffId);
		try {

			List<CommunicationEventWorkEff> foundCommunicationEventWorkEff = findCommunicationEventWorkEffsBy(requestParams).getBody();
			if(foundCommunicationEventWorkEff.size()==1){				return successful(foundCommunicationEventWorkEff.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventWorkEffId}")
	public ResponseEntity<String> deleteCommunicationEventWorkEffByIdUpdated(@PathVariable String communicationEventWorkEffId) throws Exception {
		DeleteCommunicationEventWorkEff command = new DeleteCommunicationEventWorkEff(communicationEventWorkEffId);

		try {
			if (((CommunicationEventWorkEffDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
