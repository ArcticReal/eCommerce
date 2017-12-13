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
	public ResponseEntity<Object> findFixedAssetDepMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetDepMethodsBy query = new FindFixedAssetDepMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetDepMethod> fixedAssetDepMethods =((FixedAssetDepMethodFound) Scheduler.execute(query).data()).getFixedAssetDepMethods();

		if (fixedAssetDepMethods.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetDepMethods.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetDepMethods);

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
	public ResponseEntity<Object> createFixedAssetDepMethod(HttpServletRequest request) throws Exception {

		FixedAssetDepMethod fixedAssetDepMethodToBeAdded = new FixedAssetDepMethod();
		try {
			fixedAssetDepMethodToBeAdded = FixedAssetDepMethodMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetDepMethod(fixedAssetDepMethodToBeAdded);

	}

	/**
	 * creates a new FixedAssetDepMethod entry in the ofbiz database
	 * 
	 * @param fixedAssetDepMethodToBeAdded
	 *            the FixedAssetDepMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetDepMethod(@RequestBody FixedAssetDepMethod fixedAssetDepMethodToBeAdded) throws Exception {

		AddFixedAssetDepMethod command = new AddFixedAssetDepMethod(fixedAssetDepMethodToBeAdded);
		FixedAssetDepMethod fixedAssetDepMethod = ((FixedAssetDepMethodAdded) Scheduler.execute(command).data()).getAddedFixedAssetDepMethod();
		
		if (fixedAssetDepMethod != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetDepMethod);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetDepMethod could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateFixedAssetDepMethod(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		FixedAssetDepMethod fixedAssetDepMethodToBeUpdated = new FixedAssetDepMethod();

		try {
			fixedAssetDepMethodToBeUpdated = FixedAssetDepMethodMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetDepMethod(fixedAssetDepMethodToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFixedAssetDepMethod(@RequestBody FixedAssetDepMethod fixedAssetDepMethodToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetDepMethodToBeUpdated.setnull(null);

		UpdateFixedAssetDepMethod command = new UpdateFixedAssetDepMethod(fixedAssetDepMethodToBeUpdated);

		try {
			if(((FixedAssetDepMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetDepMethodId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetDepMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetDepMethodId", fixedAssetDepMethodId);
		try {

			Object foundFixedAssetDepMethod = findFixedAssetDepMethodsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetDepMethod);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetDepMethodId}")
	public ResponseEntity<Object> deleteFixedAssetDepMethodByIdUpdated(@PathVariable String fixedAssetDepMethodId) throws Exception {
		DeleteFixedAssetDepMethod command = new DeleteFixedAssetDepMethod(fixedAssetDepMethodId);

		try {
			if (((FixedAssetDepMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetDepMethod could not be deleted");

	}

}
