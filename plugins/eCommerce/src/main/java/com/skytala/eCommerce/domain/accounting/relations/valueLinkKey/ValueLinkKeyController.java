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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/valueLinkKeys")
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
	@GetMapping("/find")
	public ResponseEntity<List<ValueLinkKey>> findValueLinkKeysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindValueLinkKeysBy query = new FindValueLinkKeysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ValueLinkKey> valueLinkKeys =((ValueLinkKeyFound) Scheduler.execute(query).data()).getValueLinkKeys();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ValueLinkKey> createValueLinkKey(HttpServletRequest request) throws Exception {

		ValueLinkKey valueLinkKeyToBeAdded = new ValueLinkKey();
		try {
			valueLinkKeyToBeAdded = ValueLinkKeyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ValueLinkKey> createValueLinkKey(@RequestBody ValueLinkKey valueLinkKeyToBeAdded) throws Exception {

		AddValueLinkKey command = new AddValueLinkKey(valueLinkKeyToBeAdded);
		ValueLinkKey valueLinkKey = ((ValueLinkKeyAdded) Scheduler.execute(command).data()).getAddedValueLinkKey();
		
		if (valueLinkKey != null) 
			return successful(valueLinkKey);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateValueLinkKey(@RequestBody ValueLinkKey valueLinkKeyToBeUpdated,
			@PathVariable String merchantId) throws Exception {

		valueLinkKeyToBeUpdated.setMerchantId(merchantId);

		UpdateValueLinkKey command = new UpdateValueLinkKey(valueLinkKeyToBeUpdated);

		try {
			if(((ValueLinkKeyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{valueLinkKeyId}")
	public ResponseEntity<ValueLinkKey> findById(@PathVariable String valueLinkKeyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("valueLinkKeyId", valueLinkKeyId);
		try {

			List<ValueLinkKey> foundValueLinkKey = findValueLinkKeysBy(requestParams).getBody();
			if(foundValueLinkKey.size()==1){				return successful(foundValueLinkKey.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{valueLinkKeyId}")
	public ResponseEntity<String> deleteValueLinkKeyByIdUpdated(@PathVariable String valueLinkKeyId) throws Exception {
		DeleteValueLinkKey command = new DeleteValueLinkKey(valueLinkKeyId);

		try {
			if (((ValueLinkKeyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
