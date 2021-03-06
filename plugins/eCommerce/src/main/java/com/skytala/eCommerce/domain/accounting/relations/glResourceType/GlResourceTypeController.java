package com.skytala.eCommerce.domain.accounting.relations.glResourceType;

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
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.AddGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.DeleteGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.command.UpdateGlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.mapper.GlResourceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.query.FindGlResourceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glResourceTypes")
public class GlResourceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlResourceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlResourceType
	 * @return a List with the GlResourceTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlResourceType>> findGlResourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlResourceTypesBy query = new FindGlResourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlResourceType> glResourceTypes =((GlResourceTypeFound) Scheduler.execute(query).data()).getGlResourceTypes();

		return ResponseEntity.ok().body(glResourceTypes);

	}

	/**
	 * creates a new GlResourceType entry in the ofbiz database
	 * 
	 * @param glResourceTypeToBeAdded
	 *            the GlResourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlResourceType> createGlResourceType(@RequestBody GlResourceType glResourceTypeToBeAdded) throws Exception {

		AddGlResourceType command = new AddGlResourceType(glResourceTypeToBeAdded);
		GlResourceType glResourceType = ((GlResourceTypeAdded) Scheduler.execute(command).data()).getAddedGlResourceType();
		
		if (glResourceType != null) 
			return successful(glResourceType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlResourceType with the specific Id
	 * 
	 * @param glResourceTypeToBeUpdated
	 *            the GlResourceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glResourceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlResourceType(@RequestBody GlResourceType glResourceTypeToBeUpdated,
			@PathVariable String glResourceTypeId) throws Exception {

		glResourceTypeToBeUpdated.setGlResourceTypeId(glResourceTypeId);

		UpdateGlResourceType command = new UpdateGlResourceType(glResourceTypeToBeUpdated);

		try {
			if(((GlResourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glResourceTypeId}")
	public ResponseEntity<GlResourceType> findById(@PathVariable String glResourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glResourceTypeId", glResourceTypeId);
		try {

			List<GlResourceType> foundGlResourceType = findGlResourceTypesBy(requestParams).getBody();
			if(foundGlResourceType.size()==1){				return successful(foundGlResourceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glResourceTypeId}")
	public ResponseEntity<String> deleteGlResourceTypeByIdUpdated(@PathVariable String glResourceTypeId) throws Exception {
		DeleteGlResourceType command = new DeleteGlResourceType(glResourceTypeId);

		try {
			if (((GlResourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
