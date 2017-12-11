package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.AddFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.DeleteFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.UpdateFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeAttr.FixedAssetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.typeAttr.FindFixedAssetTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetTypeAttrs")
public class FixedAssetTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetTypeAttr
	 * @return a List with the FixedAssetTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFixedAssetTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypeAttrsBy query = new FindFixedAssetTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetTypeAttr> fixedAssetTypeAttrs =((FixedAssetTypeAttrFound) Scheduler.execute(query).data()).getFixedAssetTypeAttrs();

		if (fixedAssetTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetTypeAttrs);

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
	public ResponseEntity<Object> createFixedAssetTypeAttr(HttpServletRequest request) throws Exception {

		FixedAssetTypeAttr fixedAssetTypeAttrToBeAdded = new FixedAssetTypeAttr();
		try {
			fixedAssetTypeAttrToBeAdded = FixedAssetTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetTypeAttr(fixedAssetTypeAttrToBeAdded);

	}

	/**
	 * creates a new FixedAssetTypeAttr entry in the ofbiz database
	 * 
	 * @param fixedAssetTypeAttrToBeAdded
	 *            the FixedAssetTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetTypeAttr(@RequestBody FixedAssetTypeAttr fixedAssetTypeAttrToBeAdded) throws Exception {

		AddFixedAssetTypeAttr command = new AddFixedAssetTypeAttr(fixedAssetTypeAttrToBeAdded);
		FixedAssetTypeAttr fixedAssetTypeAttr = ((FixedAssetTypeAttrAdded) Scheduler.execute(command).data()).getAddedFixedAssetTypeAttr();
		
		if (fixedAssetTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetTypeAttr could not be created.");
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
	public boolean updateFixedAssetTypeAttr(HttpServletRequest request) throws Exception {

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

		FixedAssetTypeAttr fixedAssetTypeAttrToBeUpdated = new FixedAssetTypeAttr();

		try {
			fixedAssetTypeAttrToBeUpdated = FixedAssetTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetTypeAttr(fixedAssetTypeAttrToBeUpdated, fixedAssetTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetTypeAttr with the specific Id
	 * 
	 * @param fixedAssetTypeAttrToBeUpdated
	 *            the FixedAssetTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetTypeAttr(@RequestBody FixedAssetTypeAttr fixedAssetTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		fixedAssetTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFixedAssetTypeAttr command = new UpdateFixedAssetTypeAttr(fixedAssetTypeAttrToBeUpdated);

		try {
			if(((FixedAssetTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeAttrId", fixedAssetTypeAttrId);
		try {

			Object foundFixedAssetTypeAttr = findFixedAssetTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetTypeAttrId}")
	public ResponseEntity<Object> deleteFixedAssetTypeAttrByIdUpdated(@PathVariable String fixedAssetTypeAttrId) throws Exception {
		DeleteFixedAssetTypeAttr command = new DeleteFixedAssetTypeAttr(fixedAssetTypeAttrId);

		try {
			if (((FixedAssetTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetTypeAttr could not be deleted");

	}

}
