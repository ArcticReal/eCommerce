package com.skytala.eCommerce.domain.accounting.relations.glFiscalType;

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
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.command.AddGlFiscalType;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.command.DeleteGlFiscalType;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.command.UpdateGlFiscalType;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.mapper.GlFiscalTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.model.GlFiscalType;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.query.FindGlFiscalTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glFiscalTypes")
public class GlFiscalTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlFiscalTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlFiscalType
	 * @return a List with the GlFiscalTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlFiscalType>> findGlFiscalTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlFiscalTypesBy query = new FindGlFiscalTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlFiscalType> glFiscalTypes =((GlFiscalTypeFound) Scheduler.execute(query).data()).getGlFiscalTypes();

		return ResponseEntity.ok().body(glFiscalTypes);

	}

	/**
	 * creates a new GlFiscalType entry in the ofbiz database
	 * 
	 * @param glFiscalTypeToBeAdded
	 *            the GlFiscalType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlFiscalType> createGlFiscalType(@RequestBody GlFiscalType glFiscalTypeToBeAdded) throws Exception {

		AddGlFiscalType command = new AddGlFiscalType(glFiscalTypeToBeAdded);
		GlFiscalType glFiscalType = ((GlFiscalTypeAdded) Scheduler.execute(command).data()).getAddedGlFiscalType();
		
		if (glFiscalType != null) 
			return successful(glFiscalType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlFiscalType with the specific Id
	 * 
	 * @param glFiscalTypeToBeUpdated
	 *            the GlFiscalType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glFiscalTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlFiscalType(@RequestBody GlFiscalType glFiscalTypeToBeUpdated,
			@PathVariable String glFiscalTypeId) throws Exception {

		glFiscalTypeToBeUpdated.setGlFiscalTypeId(glFiscalTypeId);

		UpdateGlFiscalType command = new UpdateGlFiscalType(glFiscalTypeToBeUpdated);

		try {
			if(((GlFiscalTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glFiscalTypeId}")
	public ResponseEntity<GlFiscalType> findById(@PathVariable String glFiscalTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glFiscalTypeId", glFiscalTypeId);
		try {

			List<GlFiscalType> foundGlFiscalType = findGlFiscalTypesBy(requestParams).getBody();
			if(foundGlFiscalType.size()==1){				return successful(foundGlFiscalType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glFiscalTypeId}")
	public ResponseEntity<String> deleteGlFiscalTypeByIdUpdated(@PathVariable String glFiscalTypeId) throws Exception {
		DeleteGlFiscalType command = new DeleteGlFiscalType(glFiscalTypeId);

		try {
			if (((GlFiscalTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
