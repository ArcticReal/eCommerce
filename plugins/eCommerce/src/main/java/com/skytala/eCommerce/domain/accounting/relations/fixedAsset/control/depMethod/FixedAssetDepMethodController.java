package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.depMethod;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.depMethod.AddFixedAssetDepMethod;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.depMethod.DeleteFixedAssetDepMethod;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.depMethod.UpdateFixedAssetDepMethod;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod.FixedAssetDepMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.depMethod.FixedAssetDepMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.depMethod.FindFixedAssetDepMethodsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetDepMethods")
public class FixedAssetDepMethodController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetDepMethodController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetDepMethod
	 * @return a List with the FixedAssetDepMethods
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetDepMethod>> findFixedAssetDepMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetDepMethodsBy query = new FindFixedAssetDepMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetDepMethod> fixedAssetDepMethods =((FixedAssetDepMethodFound) Scheduler.execute(query).data()).getFixedAssetDepMethods();

		return ResponseEntity.ok().body(fixedAssetDepMethods);

	}

	/**
	 * creates a new FixedAssetDepMethod entry in the ofbiz database
	 * 
	 * @param fixedAssetDepMethodToBeAdded
	 *            the FixedAssetDepMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetDepMethod> createFixedAssetDepMethod(@RequestBody FixedAssetDepMethod fixedAssetDepMethodToBeAdded) throws Exception {

		AddFixedAssetDepMethod command = new AddFixedAssetDepMethod(fixedAssetDepMethodToBeAdded);
		FixedAssetDepMethod fixedAssetDepMethod = ((FixedAssetDepMethodAdded) Scheduler.execute(command).data()).getAddedFixedAssetDepMethod();
		
		if (fixedAssetDepMethod != null) 
			return successful(fixedAssetDepMethod);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetDepMethod with the specific Id
	 * 
	 * @param fixedAssetDepMethodToBeUpdated
	 *            the FixedAssetDepMethod thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetDepMethod(@RequestBody FixedAssetDepMethod fixedAssetDepMethodToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetDepMethodToBeUpdated.setnull(null);

		UpdateFixedAssetDepMethod command = new UpdateFixedAssetDepMethod(fixedAssetDepMethodToBeUpdated);

		try {
			if(((FixedAssetDepMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetDepMethodId}")
	public ResponseEntity<FixedAssetDepMethod> findById(@PathVariable String fixedAssetDepMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetDepMethodId", fixedAssetDepMethodId);
		try {

			List<FixedAssetDepMethod> foundFixedAssetDepMethod = findFixedAssetDepMethodsBy(requestParams).getBody();
			if(foundFixedAssetDepMethod.size()==1){				return successful(foundFixedAssetDepMethod.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetDepMethodId}")
	public ResponseEntity<String> deleteFixedAssetDepMethodByIdUpdated(@PathVariable String fixedAssetDepMethodId) throws Exception {
		DeleteFixedAssetDepMethod command = new DeleteFixedAssetDepMethod(fixedAssetDepMethodId);

		try {
			if (((FixedAssetDepMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
