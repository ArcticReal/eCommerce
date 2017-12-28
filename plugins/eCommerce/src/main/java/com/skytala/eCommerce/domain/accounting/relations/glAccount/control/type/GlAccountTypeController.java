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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountType>> findGlAccountTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountTypesBy query = new FindGlAccountTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountType> glAccountTypes =((GlAccountTypeFound) Scheduler.execute(query).data()).getGlAccountTypes();

		return ResponseEntity.ok().body(glAccountTypes);

	}

	/**
	 * creates a new GlAccountType entry in the ofbiz database
	 * 
	 * @param glAccountTypeToBeAdded
	 *            the GlAccountType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountType> createGlAccountType(@RequestBody GlAccountType glAccountTypeToBeAdded) throws Exception {

		AddGlAccountType command = new AddGlAccountType(glAccountTypeToBeAdded);
		GlAccountType glAccountType = ((GlAccountTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountType();
		
		if (glAccountType != null) 
			return successful(glAccountType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGlAccountType(@RequestBody GlAccountType glAccountTypeToBeUpdated,
			@PathVariable String glAccountTypeId) throws Exception {

		glAccountTypeToBeUpdated.setGlAccountTypeId(glAccountTypeId);

		UpdateGlAccountType command = new UpdateGlAccountType(glAccountTypeToBeUpdated);

		try {
			if(((GlAccountTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountTypeId}")
	public ResponseEntity<GlAccountType> findById(@PathVariable String glAccountTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountTypeId", glAccountTypeId);
		try {

			List<GlAccountType> foundGlAccountType = findGlAccountTypesBy(requestParams).getBody();
			if(foundGlAccountType.size()==1){				return successful(foundGlAccountType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountTypeId}")
	public ResponseEntity<String> deleteGlAccountTypeByIdUpdated(@PathVariable String glAccountTypeId) throws Exception {
		DeleteGlAccountType command = new DeleteGlAccountType(glAccountTypeId);

		try {
			if (((GlAccountTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
