package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.category;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.category.AddGlAccountCategory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.category.DeleteGlAccountCategory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.category.UpdateGlAccountCategory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category.GlAccountCategoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category.GlAccountCategoryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category.GlAccountCategoryFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category.GlAccountCategoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.category.GlAccountCategoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.category.FindGlAccountCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountCategorys")
public class GlAccountCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountCategory
	 * @return a List with the GlAccountCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountCategory>> findGlAccountCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategorysBy query = new FindGlAccountCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategory> glAccountCategorys =((GlAccountCategoryFound) Scheduler.execute(query).data()).getGlAccountCategorys();

		return ResponseEntity.ok().body(glAccountCategorys);

	}

	/**
	 * creates a new GlAccountCategory entry in the ofbiz database
	 * 
	 * @param glAccountCategoryToBeAdded
	 *            the GlAccountCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountCategory> createGlAccountCategory(@RequestBody GlAccountCategory glAccountCategoryToBeAdded) throws Exception {

		AddGlAccountCategory command = new AddGlAccountCategory(glAccountCategoryToBeAdded);
		GlAccountCategory glAccountCategory = ((GlAccountCategoryAdded) Scheduler.execute(command).data()).getAddedGlAccountCategory();
		
		if (glAccountCategory != null) 
			return successful(glAccountCategory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountCategory with the specific Id
	 * 
	 * @param glAccountCategoryToBeUpdated
	 *            the GlAccountCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountCategory(@RequestBody GlAccountCategory glAccountCategoryToBeUpdated,
			@PathVariable String glAccountCategoryId) throws Exception {

		glAccountCategoryToBeUpdated.setGlAccountCategoryId(glAccountCategoryId);

		UpdateGlAccountCategory command = new UpdateGlAccountCategory(glAccountCategoryToBeUpdated);

		try {
			if(((GlAccountCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountCategoryId}")
	public ResponseEntity<GlAccountCategory> findById(@PathVariable String glAccountCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryId", glAccountCategoryId);
		try {

			List<GlAccountCategory> foundGlAccountCategory = findGlAccountCategorysBy(requestParams).getBody();
			if(foundGlAccountCategory.size()==1){				return successful(foundGlAccountCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountCategoryId}")
	public ResponseEntity<String> deleteGlAccountCategoryByIdUpdated(@PathVariable String glAccountCategoryId) throws Exception {
		DeleteGlAccountCategory command = new DeleteGlAccountCategory(glAccountCategoryId);

		try {
			if (((GlAccountCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
