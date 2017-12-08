package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.type.AddGlAccountType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.type.DeleteGlAccountType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.type.UpdateGlAccountType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.type.GlAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.type.FindGlAccountTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountTypes")
public class GlAccountTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountType
	 * @return a List with the GlAccountTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountTypesBy query = new FindGlAccountTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountType> glAccountTypes =((GlAccountTypeFound) Scheduler.execute(query).data()).getGlAccountTypes();

		if (glAccountTypes.size() == 1) {
			return ResponseEntity.ok().body(glAccountTypes.get(0));
		}

		return ResponseEntity.ok().body(glAccountTypes);

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
	public ResponseEntity<Object> createGlAccountType(HttpServletRequest request) throws Exception {

		GlAccountType glAccountTypeToBeAdded = new GlAccountType();
		try {
			glAccountTypeToBeAdded = GlAccountTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountType(glAccountTypeToBeAdded);

	}

	/**
	 * creates a new GlAccountType entry in the ofbiz database
	 * 
	 * @param glAccountTypeToBeAdded
	 *            the GlAccountType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountType(@RequestBody GlAccountType glAccountTypeToBeAdded) throws Exception {

		AddGlAccountType command = new AddGlAccountType(glAccountTypeToBeAdded);
		GlAccountType glAccountType = ((GlAccountTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountType();
		
		if (glAccountType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountType could not be created.");
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
	public boolean updateGlAccountType(HttpServletRequest request) throws Exception {

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

		GlAccountType glAccountTypeToBeUpdated = new GlAccountType();

		try {
			glAccountTypeToBeUpdated = GlAccountTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountType(glAccountTypeToBeUpdated, glAccountTypeToBeUpdated.getGlAccountTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountType with the specific Id
	 * 
	 * @param glAccountTypeToBeUpdated
	 *            the GlAccountType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountType(@RequestBody GlAccountType glAccountTypeToBeUpdated,
			@PathVariable String glAccountTypeId) throws Exception {

		glAccountTypeToBeUpdated.setGlAccountTypeId(glAccountTypeId);

		UpdateGlAccountType command = new UpdateGlAccountType(glAccountTypeToBeUpdated);

		try {
			if(((GlAccountTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountTypeId", glAccountTypeId);
		try {

			Object foundGlAccountType = findGlAccountTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountTypeId}")
	public ResponseEntity<Object> deleteGlAccountTypeByIdUpdated(@PathVariable String glAccountTypeId) throws Exception {
		DeleteGlAccountType command = new DeleteGlAccountType(glAccountTypeId);

		try {
			if (((GlAccountTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountType could not be deleted");

	}

}
