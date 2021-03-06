package com.skytala.eCommerce.domain.content.relations.dataResource.control.role;

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
import com.skytala.eCommerce.domain.content.relations.dataResource.command.role.AddDataResourceRole;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.role.DeleteDataResourceRole;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.role.UpdateDataResourceRole;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.role.DataResourceRoleAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.role.DataResourceRoleDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.role.DataResourceRoleFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.role.DataResourceRoleUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.role.DataResourceRoleMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.role.DataResourceRole;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.role.FindDataResourceRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/dataResourceRoles")
public class DataResourceRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DataResourceRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a DataResourceRole
	 * @return a List with the DataResourceRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<DataResourceRole>> findDataResourceRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceRolesBy query = new FindDataResourceRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceRole> dataResourceRoles =((DataResourceRoleFound) Scheduler.execute(query).data()).getDataResourceRoles();

		return ResponseEntity.ok().body(dataResourceRoles);

	}

	/**
	 * creates a new DataResourceRole entry in the ofbiz database
	 * 
	 * @param dataResourceRoleToBeAdded
	 *            the DataResourceRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DataResourceRole> createDataResourceRole(@RequestBody DataResourceRole dataResourceRoleToBeAdded) throws Exception {

		AddDataResourceRole command = new AddDataResourceRole(dataResourceRoleToBeAdded);
		DataResourceRole dataResourceRole = ((DataResourceRoleAdded) Scheduler.execute(command).data()).getAddedDataResourceRole();
		
		if (dataResourceRole != null) 
			return successful(dataResourceRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the DataResourceRole with the specific Id
	 * 
	 * @param dataResourceRoleToBeUpdated
	 *            the DataResourceRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDataResourceRole(@RequestBody DataResourceRole dataResourceRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		dataResourceRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateDataResourceRole command = new UpdateDataResourceRole(dataResourceRoleToBeUpdated);

		try {
			if(((DataResourceRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{dataResourceRoleId}")
	public ResponseEntity<DataResourceRole> findById(@PathVariable String dataResourceRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceRoleId", dataResourceRoleId);
		try {

			List<DataResourceRole> foundDataResourceRole = findDataResourceRolesBy(requestParams).getBody();
			if(foundDataResourceRole.size()==1){				return successful(foundDataResourceRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{dataResourceRoleId}")
	public ResponseEntity<String> deleteDataResourceRoleByIdUpdated(@PathVariable String dataResourceRoleId) throws Exception {
		DeleteDataResourceRole command = new DeleteDataResourceRole(dataResourceRoleId);

		try {
			if (((DataResourceRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
