package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.clazz;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.clazz.AddGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.clazz.DeleteGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.clazz.UpdateGlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz.GlAccountClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz.GlAccountClassDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz.GlAccountClassFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz.GlAccountClassUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.clazz.GlAccountClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.clazz.GlAccountClass;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.clazz.FindGlAccountClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountClasss")
public class GlAccountClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountClass
	 * @return a List with the GlAccountClasss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountClass>> findGlAccountClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountClasssBy query = new FindGlAccountClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountClass> glAccountClasss =((GlAccountClassFound) Scheduler.execute(query).data()).getGlAccountClasss();

		return ResponseEntity.ok().body(glAccountClasss);

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
	public ResponseEntity<GlAccountClass> createGlAccountClass(HttpServletRequest request) throws Exception {

		GlAccountClass glAccountClassToBeAdded = new GlAccountClass();
		try {
			glAccountClassToBeAdded = GlAccountClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createGlAccountClass(glAccountClassToBeAdded);

	}

	/**
	 * creates a new GlAccountClass entry in the ofbiz database
	 * 
	 * @param glAccountClassToBeAdded
	 *            the GlAccountClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountClass> createGlAccountClass(@RequestBody GlAccountClass glAccountClassToBeAdded) throws Exception {

		AddGlAccountClass command = new AddGlAccountClass(glAccountClassToBeAdded);
		GlAccountClass glAccountClass = ((GlAccountClassAdded) Scheduler.execute(command).data()).getAddedGlAccountClass();
		
		if (glAccountClass != null) 
			return successful(glAccountClass);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountClass with the specific Id
	 * 
	 * @param glAccountClassToBeUpdated
	 *            the GlAccountClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountClassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountClass(@RequestBody GlAccountClass glAccountClassToBeUpdated,
			@PathVariable String glAccountClassId) throws Exception {

		glAccountClassToBeUpdated.setGlAccountClassId(glAccountClassId);

		UpdateGlAccountClass command = new UpdateGlAccountClass(glAccountClassToBeUpdated);

		try {
			if(((GlAccountClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountClassId}")
	public ResponseEntity<GlAccountClass> findById(@PathVariable String glAccountClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountClassId", glAccountClassId);
		try {

			List<GlAccountClass> foundGlAccountClass = findGlAccountClasssBy(requestParams).getBody();
			if(foundGlAccountClass.size()==1){				return successful(foundGlAccountClass.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountClassId}")
	public ResponseEntity<String> deleteGlAccountClassByIdUpdated(@PathVariable String glAccountClassId) throws Exception {
		DeleteGlAccountClass command = new DeleteGlAccountClass(glAccountClassId);

		try {
			if (((GlAccountClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
