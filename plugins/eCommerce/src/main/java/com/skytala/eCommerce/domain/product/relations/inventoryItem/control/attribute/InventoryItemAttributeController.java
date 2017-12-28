package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.attribute;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.attribute.AddInventoryItemAttribute;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.attribute.DeleteInventoryItemAttribute;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.attribute.UpdateInventoryItemAttribute;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.attribute.InventoryItemAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.attribute.InventoryItemAttribute;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.attribute.FindInventoryItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemAttributes")
public class InventoryItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemAttribute
	 * @return a List with the InventoryItemAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemAttribute>> findInventoryItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemAttributesBy query = new FindInventoryItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemAttribute> inventoryItemAttributes =((InventoryItemAttributeFound) Scheduler.execute(query).data()).getInventoryItemAttributes();

		return ResponseEntity.ok().body(inventoryItemAttributes);

	}

	/**
	 * creates a new InventoryItemAttribute entry in the ofbiz database
	 * 
	 * @param inventoryItemAttributeToBeAdded
	 *            the InventoryItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemAttribute> createInventoryItemAttribute(@RequestBody InventoryItemAttribute inventoryItemAttributeToBeAdded) throws Exception {

		AddInventoryItemAttribute command = new AddInventoryItemAttribute(inventoryItemAttributeToBeAdded);
		InventoryItemAttribute inventoryItemAttribute = ((InventoryItemAttributeAdded) Scheduler.execute(command).data()).getAddedInventoryItemAttribute();
		
		if (inventoryItemAttribute != null) 
			return successful(inventoryItemAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemAttribute with the specific Id
	 * 
	 * @param inventoryItemAttributeToBeUpdated
	 *            the InventoryItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemAttribute(@RequestBody InventoryItemAttribute inventoryItemAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		inventoryItemAttributeToBeUpdated.setAttrName(attrName);

		UpdateInventoryItemAttribute command = new UpdateInventoryItemAttribute(inventoryItemAttributeToBeUpdated);

		try {
			if(((InventoryItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemAttributeId}")
	public ResponseEntity<InventoryItemAttribute> findById(@PathVariable String inventoryItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemAttributeId", inventoryItemAttributeId);
		try {

			List<InventoryItemAttribute> foundInventoryItemAttribute = findInventoryItemAttributesBy(requestParams).getBody();
			if(foundInventoryItemAttribute.size()==1){				return successful(foundInventoryItemAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemAttributeId}")
	public ResponseEntity<String> deleteInventoryItemAttributeByIdUpdated(@PathVariable String inventoryItemAttributeId) throws Exception {
		DeleteInventoryItemAttribute command = new DeleteInventoryItemAttribute(inventoryItemAttributeId);

		try {
			if (((InventoryItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
