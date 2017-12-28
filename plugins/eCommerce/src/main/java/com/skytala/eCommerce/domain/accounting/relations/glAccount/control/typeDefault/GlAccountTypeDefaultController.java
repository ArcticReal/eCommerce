package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.typeDefault;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.AddGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.DeleteGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault.UpdateGlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.typeDefault.GlAccountTypeDefaultMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.typeDefault.FindGlAccountTypeDefaultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountTypeDefaults")
public class GlAccountTypeDefaultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountTypeDefaultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountTypeDefault
	 * @return a List with the GlAccountTypeDefaults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountTypeDefault>> findGlAccountTypeDefaultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountTypeDefaultsBy query = new FindGlAccountTypeDefaultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountTypeDefault> glAccountTypeDefaults =((GlAccountTypeDefaultFound) Scheduler.execute(query).data()).getGlAccountTypeDefaults();

		return ResponseEntity.ok().body(glAccountTypeDefaults);

	}

	/**
	 * creates a new GlAccountTypeDefault entry in the ofbiz database
	 * 
	 * @param glAccountTypeDefaultToBeAdded
	 *            the GlAccountTypeDefault thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountTypeDefault> createGlAccountTypeDefault(@RequestBody GlAccountTypeDefault glAccountTypeDefaultToBeAdded) throws Exception {

		AddGlAccountTypeDefault command = new AddGlAccountTypeDefault(glAccountTypeDefaultToBeAdded);
		GlAccountTypeDefault glAccountTypeDefault = ((GlAccountTypeDefaultAdded) Scheduler.execute(command).data()).getAddedGlAccountTypeDefault();
		
		if (glAccountTypeDefault != null) 
			return successful(glAccountTypeDefault);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountTypeDefault with the specific Id
	 * 
	 * @param glAccountTypeDefaultToBeUpdated
	 *            the GlAccountTypeDefault thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountTypeDefault(@RequestBody GlAccountTypeDefault glAccountTypeDefaultToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountTypeDefaultToBeUpdated.setnull(null);

		UpdateGlAccountTypeDefault command = new UpdateGlAccountTypeDefault(glAccountTypeDefaultToBeUpdated);

		try {
			if(((GlAccountTypeDefaultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountTypeDefaultId}")
	public ResponseEntity<GlAccountTypeDefault> findById(@PathVariable String glAccountTypeDefaultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountTypeDefaultId", glAccountTypeDefaultId);
		try {

			List<GlAccountTypeDefault> foundGlAccountTypeDefault = findGlAccountTypeDefaultsBy(requestParams).getBody();
			if(foundGlAccountTypeDefault.size()==1){				return successful(foundGlAccountTypeDefault.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountTypeDefaultId}")
	public ResponseEntity<String> deleteGlAccountTypeDefaultByIdUpdated(@PathVariable String glAccountTypeDefaultId) throws Exception {
		DeleteGlAccountTypeDefault command = new DeleteGlAccountTypeDefault(glAccountTypeDefaultId);

		try {
			if (((GlAccountTypeDefaultDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
