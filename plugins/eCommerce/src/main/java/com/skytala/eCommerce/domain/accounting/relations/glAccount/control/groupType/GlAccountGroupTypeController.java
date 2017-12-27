package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.groupType;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupType.AddGlAccountGroupType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupType.DeleteGlAccountGroupType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupType.UpdateGlAccountGroupType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupType.GlAccountGroupTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupType.GlAccountGroupType;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.groupType.FindGlAccountGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountGroupTypes")
public class GlAccountGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountGroupType
	 * @return a List with the GlAccountGroupTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountGroupType>> findGlAccountGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupTypesBy query = new FindGlAccountGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroupType> glAccountGroupTypes =((GlAccountGroupTypeFound) Scheduler.execute(query).data()).getGlAccountGroupTypes();

		return ResponseEntity.ok().body(glAccountGroupTypes);

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
	public ResponseEntity<GlAccountGroupType> createGlAccountGroupType(HttpServletRequest request) throws Exception {

		GlAccountGroupType glAccountGroupTypeToBeAdded = new GlAccountGroupType();
		try {
			glAccountGroupTypeToBeAdded = GlAccountGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createGlAccountGroupType(glAccountGroupTypeToBeAdded);

	}

	/**
	 * creates a new GlAccountGroupType entry in the ofbiz database
	 * 
	 * @param glAccountGroupTypeToBeAdded
	 *            the GlAccountGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountGroupType> createGlAccountGroupType(@RequestBody GlAccountGroupType glAccountGroupTypeToBeAdded) throws Exception {

		AddGlAccountGroupType command = new AddGlAccountGroupType(glAccountGroupTypeToBeAdded);
		GlAccountGroupType glAccountGroupType = ((GlAccountGroupTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountGroupType();
		
		if (glAccountGroupType != null) 
			return successful(glAccountGroupType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountGroupType with the specific Id
	 * 
	 * @param glAccountGroupTypeToBeUpdated
	 *            the GlAccountGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountGroupType(@RequestBody GlAccountGroupType glAccountGroupTypeToBeUpdated,
			@PathVariable String glAccountGroupTypeId) throws Exception {

		glAccountGroupTypeToBeUpdated.setGlAccountGroupTypeId(glAccountGroupTypeId);

		UpdateGlAccountGroupType command = new UpdateGlAccountGroupType(glAccountGroupTypeToBeUpdated);

		try {
			if(((GlAccountGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountGroupTypeId}")
	public ResponseEntity<GlAccountGroupType> findById(@PathVariable String glAccountGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupTypeId", glAccountGroupTypeId);
		try {

			List<GlAccountGroupType> foundGlAccountGroupType = findGlAccountGroupTypesBy(requestParams).getBody();
			if(foundGlAccountGroupType.size()==1){				return successful(foundGlAccountGroupType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountGroupTypeId}")
	public ResponseEntity<String> deleteGlAccountGroupTypeByIdUpdated(@PathVariable String glAccountGroupTypeId) throws Exception {
		DeleteGlAccountGroupType command = new DeleteGlAccountGroupType(glAccountGroupTypeId);

		try {
			if (((GlAccountGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
