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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/needTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findNeedTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindNeedTypesBy query = new FindNeedTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<NeedType> needTypes =((NeedTypeFound) Scheduler.execute(query).data()).getNeedTypes();

		if (needTypes.size() == 1) {
			return ResponseEntity.ok().body(needTypes.get(0));
		}

		return ResponseEntity.ok().body(needTypes);

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
	public ResponseEntity<Object> createNeedType(HttpServletRequest request) throws Exception {

		NeedType needTypeToBeAdded = new NeedType();
		try {
			needTypeToBeAdded = NeedTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createNeedType(needTypeToBeAdded);

	}

	/**
	 * creates a new NeedType entry in the ofbiz database
	 * 
	 * @param needTypeToBeAdded
	 *            the NeedType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createNeedType(@RequestBody NeedType needTypeToBeAdded) throws Exception {

		AddNeedType command = new AddNeedType(needTypeToBeAdded);
		NeedType needType = ((NeedTypeAdded) Scheduler.execute(command).data()).getAddedNeedType();
		
		if (needType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(needType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("NeedType could not be created.");
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
	public boolean updateNeedType(HttpServletRequest request) throws Exception {

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

		NeedType needTypeToBeUpdated = new NeedType();

		try {
			needTypeToBeUpdated = NeedTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateNeedType(needTypeToBeUpdated, needTypeToBeUpdated.getNeedTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateNeedType(@RequestBody NeedType needTypeToBeUpdated,
			@PathVariable String needTypeId) throws Exception {

		needTypeToBeUpdated.setNeedTypeId(needTypeId);

		UpdateNeedType command = new UpdateNeedType(needTypeToBeUpdated);

		try {
			if(((NeedTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{needTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String needTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("needTypeId", needTypeId);
		try {

			Object foundNeedType = findNeedTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundNeedType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{needTypeId}")
	public ResponseEntity<Object> deleteNeedTypeByIdUpdated(@PathVariable String needTypeId) throws Exception {
		DeleteNeedType command = new DeleteNeedType(needTypeId);

		try {
			if (((NeedTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("NeedType could not be deleted");

	}

}
