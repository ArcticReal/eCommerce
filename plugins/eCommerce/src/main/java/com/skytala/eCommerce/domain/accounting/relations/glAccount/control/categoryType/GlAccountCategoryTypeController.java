package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.categoryType;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryType.AddGlAccountCategoryType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryType.DeleteGlAccountCategoryType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryType.UpdateGlAccountCategoryType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType.GlAccountCategoryTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType.GlAccountCategoryTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType.GlAccountCategoryTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType.GlAccountCategoryTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryType.GlAccountCategoryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType.GlAccountCategoryType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.categoryType.FindGlAccountCategoryTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/glAccount/glAccountCategoryTypes")
public class GlAccountCategoryTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountCategoryTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountCategoryType
	 * @return a List with the GlAccountCategoryTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountCategoryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategoryTypesBy query = new FindGlAccountCategoryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategoryType> glAccountCategoryTypes =((GlAccountCategoryTypeFound) Scheduler.execute(query).data()).getGlAccountCategoryTypes();

		if (glAccountCategoryTypes.size() == 1) {
			return ResponseEntity.ok().body(glAccountCategoryTypes.get(0));
		}

		return ResponseEntity.ok().body(glAccountCategoryTypes);

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
	public ResponseEntity<Object> createGlAccountCategoryType(HttpServletRequest request) throws Exception {

		GlAccountCategoryType glAccountCategoryTypeToBeAdded = new GlAccountCategoryType();
		try {
			glAccountCategoryTypeToBeAdded = GlAccountCategoryTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountCategoryType(glAccountCategoryTypeToBeAdded);

	}

	/**
	 * creates a new GlAccountCategoryType entry in the ofbiz database
	 * 
	 * @param glAccountCategoryTypeToBeAdded
	 *            the GlAccountCategoryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountCategoryType(@RequestBody GlAccountCategoryType glAccountCategoryTypeToBeAdded) throws Exception {

		AddGlAccountCategoryType command = new AddGlAccountCategoryType(glAccountCategoryTypeToBeAdded);
		GlAccountCategoryType glAccountCategoryType = ((GlAccountCategoryTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountCategoryType();
		
		if (glAccountCategoryType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountCategoryType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountCategoryType could not be created.");
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
	public boolean updateGlAccountCategoryType(HttpServletRequest request) throws Exception {

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

		GlAccountCategoryType glAccountCategoryTypeToBeUpdated = new GlAccountCategoryType();

		try {
			glAccountCategoryTypeToBeUpdated = GlAccountCategoryTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountCategoryType(glAccountCategoryTypeToBeUpdated, glAccountCategoryTypeToBeUpdated.getGlAccountCategoryTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountCategoryType with the specific Id
	 * 
	 * @param glAccountCategoryTypeToBeUpdated
	 *            the GlAccountCategoryType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountCategoryTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountCategoryType(@RequestBody GlAccountCategoryType glAccountCategoryTypeToBeUpdated,
			@PathVariable String glAccountCategoryTypeId) throws Exception {

		glAccountCategoryTypeToBeUpdated.setGlAccountCategoryTypeId(glAccountCategoryTypeId);

		UpdateGlAccountCategoryType command = new UpdateGlAccountCategoryType(glAccountCategoryTypeToBeUpdated);

		try {
			if(((GlAccountCategoryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountCategoryTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountCategoryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryTypeId", glAccountCategoryTypeId);
		try {

			Object foundGlAccountCategoryType = findGlAccountCategoryTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountCategoryType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountCategoryTypeId}")
	public ResponseEntity<Object> deleteGlAccountCategoryTypeByIdUpdated(@PathVariable String glAccountCategoryTypeId) throws Exception {
		DeleteGlAccountCategoryType command = new DeleteGlAccountCategoryType(glAccountCategoryTypeId);

		try {
			if (((GlAccountCategoryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountCategoryType could not be deleted");

	}

}
