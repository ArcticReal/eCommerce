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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventWorkEffsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventWorkEffsBy query = new FindCommunicationEventWorkEffsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventWorkEff> communicationEventWorkEffs =((CommunicationEventWorkEffFound) Scheduler.execute(query).data()).getCommunicationEventWorkEffs();

		if (communicationEventWorkEffs.size() == 1) {
			return ResponseEntity.ok().body(communicationEventWorkEffs.get(0));
		}

		return ResponseEntity.ok().body(communicationEventWorkEffs);

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
	public ResponseEntity<Object> createCommunicationEventWorkEff(HttpServletRequest request) throws Exception {

		CommunicationEventWorkEff communicationEventWorkEffToBeAdded = new CommunicationEventWorkEff();
		try {
			communicationEventWorkEffToBeAdded = CommunicationEventWorkEffMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventWorkEff(communicationEventWorkEffToBeAdded);

	}

	/**
	 * creates a new CommunicationEventWorkEff entry in the ofbiz database
	 * 
	 * @param communicationEventWorkEffToBeAdded
	 *            the CommunicationEventWorkEff thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventWorkEff(@RequestBody CommunicationEventWorkEff communicationEventWorkEffToBeAdded) throws Exception {

		AddCommunicationEventWorkEff command = new AddCommunicationEventWorkEff(communicationEventWorkEffToBeAdded);
		CommunicationEventWorkEff communicationEventWorkEff = ((CommunicationEventWorkEffAdded) Scheduler.execute(command).data()).getAddedCommunicationEventWorkEff();
		
		if (communicationEventWorkEff != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventWorkEff);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventWorkEff could not be created.");
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
	public boolean updateCommunicationEventWorkEff(HttpServletRequest request) throws Exception {

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

		CommunicationEventWorkEff communicationEventWorkEffToBeUpdated = new CommunicationEventWorkEff();

		try {
			communicationEventWorkEffToBeUpdated = CommunicationEventWorkEffMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventWorkEff(communicationEventWorkEffToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCommunicationEventWorkEff(@RequestBody CommunicationEventWorkEff communicationEventWorkEffToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventWorkEffToBeUpdated.setnull(null);

		UpdateCommunicationEventWorkEff command = new UpdateCommunicationEventWorkEff(communicationEventWorkEffToBeUpdated);

		try {
			if(((CommunicationEventWorkEffUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventWorkEffId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventWorkEffId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventWorkEffId", communicationEventWorkEffId);
		try {

			Object foundCommunicationEventWorkEff = findCommunicationEventWorkEffsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventWorkEff);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventWorkEffId}")
	public ResponseEntity<Object> deleteCommunicationEventWorkEffByIdUpdated(@PathVariable String communicationEventWorkEffId) throws Exception {
		DeleteCommunicationEventWorkEff command = new DeleteCommunicationEventWorkEff(communicationEventWorkEffId);

		try {
			if (((CommunicationEventWorkEffDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventWorkEff could not be deleted");

	}

}
