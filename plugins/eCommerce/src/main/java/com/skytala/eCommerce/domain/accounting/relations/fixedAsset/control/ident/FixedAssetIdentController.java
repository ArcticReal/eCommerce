package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.ident;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.AddFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.DeleteFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.UpdateFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.ident.FixedAssetIdentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.ident.FindFixedAssetIdentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetIdents")
public class FixedAssetIdentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetIdentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetIdent
	 * @return a List with the FixedAssetIdents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFixedAssetIdentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetIdentsBy query = new FindFixedAssetIdentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetIdent> fixedAssetIdents =((FixedAssetIdentFound) Scheduler.execute(query).data()).getFixedAssetIdents();

		if (fixedAssetIdents.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetIdents.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetIdents);

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
	public ResponseEntity<Object> createFixedAssetIdent(HttpServletRequest request) throws Exception {

		FixedAssetIdent fixedAssetIdentToBeAdded = new FixedAssetIdent();
		try {
			fixedAssetIdentToBeAdded = FixedAssetIdentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetIdent(fixedAssetIdentToBeAdded);

	}

	/**
	 * creates a new FixedAssetIdent entry in the ofbiz database
	 * 
	 * @param fixedAssetIdentToBeAdded
	 *            the FixedAssetIdent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetIdent(@RequestBody FixedAssetIdent fixedAssetIdentToBeAdded) throws Exception {

		AddFixedAssetIdent command = new AddFixedAssetIdent(fixedAssetIdentToBeAdded);
		FixedAssetIdent fixedAssetIdent = ((FixedAssetIdentAdded) Scheduler.execute(command).data()).getAddedFixedAssetIdent();
		
		if (fixedAssetIdent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetIdent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetIdent could not be created.");
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
	public boolean updateFixedAssetIdent(HttpServletRequest request) throws Exception {

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

		FixedAssetIdent fixedAssetIdentToBeUpdated = new FixedAssetIdent();

		try {
			fixedAssetIdentToBeUpdated = FixedAssetIdentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetIdent(fixedAssetIdentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetIdent with the specific Id
	 * 
	 * @param fixedAssetIdentToBeUpdated
	 *            the FixedAssetIdent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetIdent(@RequestBody FixedAssetIdent fixedAssetIdentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetIdentToBeUpdated.setnull(null);

		UpdateFixedAssetIdent command = new UpdateFixedAssetIdent(fixedAssetIdentToBeUpdated);

		try {
			if(((FixedAssetIdentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetIdentId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetIdentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetIdentId", fixedAssetIdentId);
		try {

			Object foundFixedAssetIdent = findFixedAssetIdentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetIdent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetIdentId}")
	public ResponseEntity<Object> deleteFixedAssetIdentByIdUpdated(@PathVariable String fixedAssetIdentId) throws Exception {
		DeleteFixedAssetIdent command = new DeleteFixedAssetIdent(fixedAssetIdentId);

		try {
			if (((FixedAssetIdentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetIdent could not be deleted");

	}

}
