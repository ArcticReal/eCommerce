package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.control.role;

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
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.role.AddItemIssuanceRole;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.role.DeleteItemIssuanceRole;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.role.UpdateItemIssuanceRole;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleDeleted;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleUpdated;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.role.ItemIssuanceRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.query.role.FindItemIssuanceRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/itemIssuance/itemIssuanceRoles")
public class ItemIssuanceRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ItemIssuanceRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ItemIssuanceRole
	 * @return a List with the ItemIssuanceRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ItemIssuanceRole>> findItemIssuanceRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindItemIssuanceRolesBy query = new FindItemIssuanceRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ItemIssuanceRole> itemIssuanceRoles =((ItemIssuanceRoleFound) Scheduler.execute(query).data()).getItemIssuanceRoles();

		return ResponseEntity.ok().body(itemIssuanceRoles);

	}

	/**
	 * creates a new ItemIssuanceRole entry in the ofbiz database
	 * 
	 * @param itemIssuanceRoleToBeAdded
	 *            the ItemIssuanceRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ItemIssuanceRole> createItemIssuanceRole(@RequestBody ItemIssuanceRole itemIssuanceRoleToBeAdded) throws Exception {

		AddItemIssuanceRole command = new AddItemIssuanceRole(itemIssuanceRoleToBeAdded);
		ItemIssuanceRole itemIssuanceRole = ((ItemIssuanceRoleAdded) Scheduler.execute(command).data()).getAddedItemIssuanceRole();
		
		if (itemIssuanceRole != null) 
			return successful(itemIssuanceRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ItemIssuanceRole with the specific Id
	 * 
	 * @param itemIssuanceRoleToBeUpdated
	 *            the ItemIssuanceRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateItemIssuanceRole(@RequestBody ItemIssuanceRole itemIssuanceRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		itemIssuanceRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateItemIssuanceRole command = new UpdateItemIssuanceRole(itemIssuanceRoleToBeUpdated);

		try {
			if(((ItemIssuanceRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{itemIssuanceRoleId}")
	public ResponseEntity<ItemIssuanceRole> findById(@PathVariable String itemIssuanceRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("itemIssuanceRoleId", itemIssuanceRoleId);
		try {

			List<ItemIssuanceRole> foundItemIssuanceRole = findItemIssuanceRolesBy(requestParams).getBody();
			if(foundItemIssuanceRole.size()==1){				return successful(foundItemIssuanceRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{itemIssuanceRoleId}")
	public ResponseEntity<String> deleteItemIssuanceRoleByIdUpdated(@PathVariable String itemIssuanceRoleId) throws Exception {
		DeleteItemIssuanceRole command = new DeleteItemIssuanceRole(itemIssuanceRoleId);

		try {
			if (((ItemIssuanceRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
