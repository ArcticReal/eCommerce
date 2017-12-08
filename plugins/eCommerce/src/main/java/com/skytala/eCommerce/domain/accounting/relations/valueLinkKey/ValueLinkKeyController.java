package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey;

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
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.command.AddValueLinkKey;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.command.DeleteValueLinkKey;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.command.UpdateValueLinkKey;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyAdded;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyDeleted;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyFound;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyUpdated;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.mapper.ValueLinkKeyMapper;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.query.FindValueLinkKeysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/valueLinkKeys")
public class ValueLinkKeyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ValueLinkKeyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ValueLinkKey
	 * @return a List with the ValueLinkKeys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findValueLinkKeysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindValueLinkKeysBy query = new FindValueLinkKeysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ValueLinkKey> valueLinkKeys =((ValueLinkKeyFound) Scheduler.execute(query).data()).getValueLinkKeys();

		if (valueLinkKeys.size() == 1) {
			return ResponseEntity.ok().body(valueLinkKeys.get(0));
		}

		return ResponseEntity.ok().body(valueLinkKeys);

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
	public ResponseEntity<Object> createValueLinkKey(HttpServletRequest request) throws Exception {

		ValueLinkKey valueLinkKeyToBeAdded = new ValueLinkKey();
		try {
			valueLinkKeyToBeAdded = ValueLinkKeyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createValueLinkKey(valueLinkKeyToBeAdded);

	}

	/**
	 * creates a new ValueLinkKey entry in the ofbiz database
	 * 
	 * @param valueLinkKeyToBeAdded
	 *            the ValueLinkKey thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createValueLinkKey(@RequestBody ValueLinkKey valueLinkKeyToBeAdded) throws Exception {

		AddValueLinkKey command = new AddValueLinkKey(valueLinkKeyToBeAdded);
		ValueLinkKey valueLinkKey = ((ValueLinkKeyAdded) Scheduler.execute(command).data()).getAddedValueLinkKey();
		
		if (valueLinkKey != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(valueLinkKey);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ValueLinkKey could not be created.");
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
	public boolean updateValueLinkKey(HttpServletRequest request) throws Exception {

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

		ValueLinkKey valueLinkKeyToBeUpdated = new ValueLinkKey();

		try {
			valueLinkKeyToBeUpdated = ValueLinkKeyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateValueLinkKey(valueLinkKeyToBeUpdated, valueLinkKeyToBeUpdated.getMerchantId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ValueLinkKey with the specific Id
	 * 
	 * @param valueLinkKeyToBeUpdated
	 *            the ValueLinkKey thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{merchantId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateValueLinkKey(@RequestBody ValueLinkKey valueLinkKeyToBeUpdated,
			@PathVariable String merchantId) throws Exception {

		valueLinkKeyToBeUpdated.setMerchantId(merchantId);

		UpdateValueLinkKey command = new UpdateValueLinkKey(valueLinkKeyToBeUpdated);

		try {
			if(((ValueLinkKeyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{valueLinkKeyId}")
	public ResponseEntity<Object> findById(@PathVariable String valueLinkKeyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("valueLinkKeyId", valueLinkKeyId);
		try {

			Object foundValueLinkKey = findValueLinkKeysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundValueLinkKey);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{valueLinkKeyId}")
	public ResponseEntity<Object> deleteValueLinkKeyByIdUpdated(@PathVariable String valueLinkKeyId) throws Exception {
		DeleteValueLinkKey command = new DeleteValueLinkKey(valueLinkKeyId);

		try {
			if (((ValueLinkKeyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ValueLinkKey could not be deleted");

	}

}
