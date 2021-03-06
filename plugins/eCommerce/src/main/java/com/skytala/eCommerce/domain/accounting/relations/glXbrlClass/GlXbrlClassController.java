package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass;

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
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.command.AddGlXbrlClass;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.command.DeleteGlXbrlClass;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.command.UpdateGlXbrlClass;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event.GlXbrlClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event.GlXbrlClassDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event.GlXbrlClassFound;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event.GlXbrlClassUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.mapper.GlXbrlClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.query.FindGlXbrlClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glXbrlClasss")
public class GlXbrlClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlXbrlClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlXbrlClass
	 * @return a List with the GlXbrlClasss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlXbrlClass>> findGlXbrlClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlXbrlClasssBy query = new FindGlXbrlClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlXbrlClass> glXbrlClasss =((GlXbrlClassFound) Scheduler.execute(query).data()).getGlXbrlClasss();

		return ResponseEntity.ok().body(glXbrlClasss);

	}

	/**
	 * creates a new GlXbrlClass entry in the ofbiz database
	 * 
	 * @param glXbrlClassToBeAdded
	 *            the GlXbrlClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlXbrlClass> createGlXbrlClass(@RequestBody GlXbrlClass glXbrlClassToBeAdded) throws Exception {

		AddGlXbrlClass command = new AddGlXbrlClass(glXbrlClassToBeAdded);
		GlXbrlClass glXbrlClass = ((GlXbrlClassAdded) Scheduler.execute(command).data()).getAddedGlXbrlClass();
		
		if (glXbrlClass != null) 
			return successful(glXbrlClass);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlXbrlClass with the specific Id
	 * 
	 * @param glXbrlClassToBeUpdated
	 *            the GlXbrlClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glXbrlClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlXbrlClass(@RequestBody GlXbrlClass glXbrlClassToBeUpdated,
			@PathVariable String glXbrlClassId) throws Exception {

		glXbrlClassToBeUpdated.setGlXbrlClassId(glXbrlClassId);

		UpdateGlXbrlClass command = new UpdateGlXbrlClass(glXbrlClassToBeUpdated);

		try {
			if(((GlXbrlClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glXbrlClassId}")
	public ResponseEntity<GlXbrlClass> findById(@PathVariable String glXbrlClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glXbrlClassId", glXbrlClassId);
		try {

			List<GlXbrlClass> foundGlXbrlClass = findGlXbrlClasssBy(requestParams).getBody();
			if(foundGlXbrlClass.size()==1){				return successful(foundGlXbrlClass.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glXbrlClassId}")
	public ResponseEntity<String> deleteGlXbrlClassByIdUpdated(@PathVariable String glXbrlClassId) throws Exception {
		DeleteGlXbrlClass command = new DeleteGlXbrlClass(glXbrlClassId);

		try {
			if (((GlXbrlClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
