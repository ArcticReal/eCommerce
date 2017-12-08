package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.attribute.AddFixedAssetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.attribute.DeleteFixedAssetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.attribute.UpdateFixedAssetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute.FixedAssetAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute.FixedAssetAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute.FixedAssetAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute.FixedAssetAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.attribute.FixedAssetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.attribute.FixedAssetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.attribute.FindFixedAssetAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/fixedAssetAttributes")
public class FixedAssetAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetAttribute
	 * @return a List with the FixedAssetAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetAttributesBy query = new FindFixedAssetAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetAttribute> fixedAssetAttributes =((FixedAssetAttributeFound) Scheduler.execute(query).data()).getFixedAssetAttributes();

		if (fixedAssetAttributes.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetAttributes.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetAttributes);

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
	public ResponseEntity<Object> createFixedAssetAttribute(HttpServletRequest request) throws Exception {

		FixedAssetAttribute fixedAssetAttributeToBeAdded = new FixedAssetAttribute();
		try {
			fixedAssetAttributeToBeAdded = FixedAssetAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetAttribute(fixedAssetAttributeToBeAdded);

	}

	/**
	 * creates a new FixedAssetAttribute entry in the ofbiz database
	 * 
	 * @param fixedAssetAttributeToBeAdded
	 *            the FixedAssetAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetAttribute(@RequestBody FixedAssetAttribute fixedAssetAttributeToBeAdded) throws Exception {

		AddFixedAssetAttribute command = new AddFixedAssetAttribute(fixedAssetAttributeToBeAdded);
		FixedAssetAttribute fixedAssetAttribute = ((FixedAssetAttributeAdded) Scheduler.execute(command).data()).getAddedFixedAssetAttribute();
		
		if (fixedAssetAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetAttribute could not be created.");
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
	public boolean updateFixedAssetAttribute(HttpServletRequest request) throws Exception {

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

		FixedAssetAttribute fixedAssetAttributeToBeUpdated = new FixedAssetAttribute();

		try {
			fixedAssetAttributeToBeUpdated = FixedAssetAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetAttribute(fixedAssetAttributeToBeUpdated, fixedAssetAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetAttribute with the specific Id
	 * 
	 * @param fixedAssetAttributeToBeUpdated
	 *            the FixedAssetAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetAttribute(@RequestBody FixedAssetAttribute fixedAssetAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		fixedAssetAttributeToBeUpdated.setAttrName(attrName);

		UpdateFixedAssetAttribute command = new UpdateFixedAssetAttribute(fixedAssetAttributeToBeUpdated);

		try {
			if(((FixedAssetAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetAttributeId", fixedAssetAttributeId);
		try {

			Object foundFixedAssetAttribute = findFixedAssetAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetAttributeId}")
	public ResponseEntity<Object> deleteFixedAssetAttributeByIdUpdated(@PathVariable String fixedAssetAttributeId) throws Exception {
		DeleteFixedAssetAttribute command = new DeleteFixedAssetAttribute(fixedAssetAttributeId);

		try {
			if (((FixedAssetAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetAttribute could not be deleted");

	}

}
