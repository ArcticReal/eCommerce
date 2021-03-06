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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetAttribute>> findFixedAssetAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetAttributesBy query = new FindFixedAssetAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetAttribute> fixedAssetAttributes =((FixedAssetAttributeFound) Scheduler.execute(query).data()).getFixedAssetAttributes();

		return ResponseEntity.ok().body(fixedAssetAttributes);

	}

	/**
	 * creates a new FixedAssetAttribute entry in the ofbiz database
	 * 
	 * @param fixedAssetAttributeToBeAdded
	 *            the FixedAssetAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetAttribute> createFixedAssetAttribute(@RequestBody FixedAssetAttribute fixedAssetAttributeToBeAdded) throws Exception {

		AddFixedAssetAttribute command = new AddFixedAssetAttribute(fixedAssetAttributeToBeAdded);
		FixedAssetAttribute fixedAssetAttribute = ((FixedAssetAttributeAdded) Scheduler.execute(command).data()).getAddedFixedAssetAttribute();
		
		if (fixedAssetAttribute != null) 
			return successful(fixedAssetAttribute);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFixedAssetAttribute(@RequestBody FixedAssetAttribute fixedAssetAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		fixedAssetAttributeToBeUpdated.setAttrName(attrName);

		UpdateFixedAssetAttribute command = new UpdateFixedAssetAttribute(fixedAssetAttributeToBeUpdated);

		try {
			if(((FixedAssetAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetAttributeId}")
	public ResponseEntity<FixedAssetAttribute> findById(@PathVariable String fixedAssetAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetAttributeId", fixedAssetAttributeId);
		try {

			List<FixedAssetAttribute> foundFixedAssetAttribute = findFixedAssetAttributesBy(requestParams).getBody();
			if(foundFixedAssetAttribute.size()==1){				return successful(foundFixedAssetAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetAttributeId}")
	public ResponseEntity<String> deleteFixedAssetAttributeByIdUpdated(@PathVariable String fixedAssetAttributeId) throws Exception {
		DeleteFixedAssetAttribute command = new DeleteFixedAssetAttribute(fixedAssetAttributeId);

		try {
			if (((FixedAssetAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
