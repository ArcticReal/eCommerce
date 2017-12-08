package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.AddFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.DeleteFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.UpdateFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.type.FixedAssetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.type.FixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.type.FindFixedAssetTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetTypes")
public class FixedAssetTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetType
	 * @return a List with the FixedAssetTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypesBy query = new FindFixedAssetTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetType> fixedAssetTypes =((FixedAssetTypeFound) Scheduler.execute(query).data()).getFixedAssetTypes();

		if (fixedAssetTypes.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetTypes.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetTypes);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createFixedAssetType(HttpServletRequest request) throws Exception {

		FixedAssetType fixedAssetTypeToBeAdded = new FixedAssetType();
		try {
			fixedAssetTypeToBeAdded = FixedAssetTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetType(fixedAssetTypeToBeAdded);

	}

	/**
	 * creates a new FixedAssetType entry in the ofbiz database
	 * 
	 * @param fixedAssetTypeToBeAdded
	 *            the FixedAssetType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetType(@RequestBody FixedAssetType fixedAssetTypeToBeAdded) throws Exception {

		AddFixedAssetType command = new AddFixedAssetType(fixedAssetTypeToBeAdded);
		FixedAssetType fixedAssetType = ((FixedAssetTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetType();
		
		if (fixedAssetType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetType could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateFixedAssetType(HttpServletRequest request) throws Exception {

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

		FixedAssetType fixedAssetTypeToBeUpdated = new FixedAssetType();

		try {
			fixedAssetTypeToBeUpdated = FixedAssetTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetType(fixedAssetTypeToBeUpdated, fixedAssetTypeToBeUpdated.getFixedAssetTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetType with the specific Id
	 * 
	 * @param fixedAssetTypeToBeUpdated
	 *            the FixedAssetType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetType(@RequestBody FixedAssetType fixedAssetTypeToBeUpdated,
			@PathVariable String fixedAssetTypeId) throws Exception {

		fixedAssetTypeToBeUpdated.setFixedAssetTypeId(fixedAssetTypeId);

		UpdateFixedAssetType command = new UpdateFixedAssetType(fixedAssetTypeToBeUpdated);

		try {
			if(((FixedAssetTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeId", fixedAssetTypeId);
		try {

			Object foundFixedAssetType = findFixedAssetTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetTypeId}")
	public ResponseEntity<Object> deleteFixedAssetTypeByIdUpdated(@PathVariable String fixedAssetTypeId) throws Exception {
		DeleteFixedAssetType command = new DeleteFixedAssetType(fixedAssetTypeId);

		try {
			if (((FixedAssetTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetType could not be deleted");

	}

}
