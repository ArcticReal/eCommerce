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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountCategoryType>> findGlAccountCategoryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategoryTypesBy query = new FindGlAccountCategoryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategoryType> glAccountCategoryTypes =((GlAccountCategoryTypeFound) Scheduler.execute(query).data()).getGlAccountCategoryTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<GlAccountCategoryType> createGlAccountCategoryType(HttpServletRequest request) throws Exception {

		GlAccountCategoryType glAccountCategoryTypeToBeAdded = new GlAccountCategoryType();
		try {
			glAccountCategoryTypeToBeAdded = GlAccountCategoryTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<GlAccountCategoryType> createGlAccountCategoryType(@RequestBody GlAccountCategoryType glAccountCategoryTypeToBeAdded) throws Exception {

		AddGlAccountCategoryType command = new AddGlAccountCategoryType(glAccountCategoryTypeToBeAdded);
		GlAccountCategoryType glAccountCategoryType = ((GlAccountCategoryTypeAdded) Scheduler.execute(command).data()).getAddedGlAccountCategoryType();
		
		if (glAccountCategoryType != null) 
			return successful(glAccountCategoryType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGlAccountCategoryType(@RequestBody GlAccountCategoryType glAccountCategoryTypeToBeUpdated,
			@PathVariable String glAccountCategoryTypeId) throws Exception {

		glAccountCategoryTypeToBeUpdated.setGlAccountCategoryTypeId(glAccountCategoryTypeId);

		UpdateGlAccountCategoryType command = new UpdateGlAccountCategoryType(glAccountCategoryTypeToBeUpdated);

		try {
			if(((GlAccountCategoryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountCategoryTypeId}")
	public ResponseEntity<GlAccountCategoryType> findById(@PathVariable String glAccountCategoryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryTypeId", glAccountCategoryTypeId);
		try {

			List<GlAccountCategoryType> foundGlAccountCategoryType = findGlAccountCategoryTypesBy(requestParams).getBody();
			if(foundGlAccountCategoryType.size()==1){				return successful(foundGlAccountCategoryType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountCategoryTypeId}")
	public ResponseEntity<String> deleteGlAccountCategoryTypeByIdUpdated(@PathVariable String glAccountCategoryTypeId) throws Exception {
		DeleteGlAccountCategoryType command = new DeleteGlAccountCategoryType(glAccountCategoryTypeId);

		try {
			if (((GlAccountCategoryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
