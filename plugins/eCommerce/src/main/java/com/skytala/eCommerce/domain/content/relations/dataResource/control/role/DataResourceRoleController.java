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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findDataResourceRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDataResourceRolesBy query = new FindDataResourceRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<DataResourceRole> dataResourceRoles =((DataResourceRoleFound) Scheduler.execute(query).data()).getDataResourceRoles();

		if (dataResourceRoles.size() == 1) {
			return ResponseEntity.ok().body(dataResourceRoles.get(0));
		}

		return ResponseEntity.ok().body(dataResourceRoles);

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
	public ResponseEntity<Object> createDataResourceRole(HttpServletRequest request) throws Exception {

		DataResourceRole dataResourceRoleToBeAdded = new DataResourceRole();
		try {
			dataResourceRoleToBeAdded = DataResourceRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDataResourceRole(dataResourceRoleToBeAdded);

	}

	/**
	 * creates a new DataResourceRole entry in the ofbiz database
	 * 
	 * @param dataResourceRoleToBeAdded
	 *            the DataResourceRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDataResourceRole(@RequestBody DataResourceRole dataResourceRoleToBeAdded) throws Exception {

		AddDataResourceRole command = new AddDataResourceRole(dataResourceRoleToBeAdded);
		DataResourceRole dataResourceRole = ((DataResourceRoleAdded) Scheduler.execute(command).data()).getAddedDataResourceRole();
		
		if (dataResourceRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(dataResourceRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("DataResourceRole could not be created.");
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
	public boolean updateDataResourceRole(HttpServletRequest request) throws Exception {

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

		DataResourceRole dataResourceRoleToBeUpdated = new DataResourceRole();

		try {
			dataResourceRoleToBeUpdated = DataResourceRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDataResourceRole(dataResourceRoleToBeUpdated, dataResourceRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDataResourceRole(@RequestBody DataResourceRole dataResourceRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		dataResourceRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateDataResourceRole command = new UpdateDataResourceRole(dataResourceRoleToBeUpdated);

		try {
			if(((DataResourceRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{dataResourceRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String dataResourceRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("dataResourceRoleId", dataResourceRoleId);
		try {

			Object foundDataResourceRole = findDataResourceRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDataResourceRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{dataResourceRoleId}")
	public ResponseEntity<Object> deleteDataResourceRoleByIdUpdated(@PathVariable String dataResourceRoleId) throws Exception {
		DeleteDataResourceRole command = new DeleteDataResourceRole(dataResourceRoleId);

		try {
			if (((DataResourceRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("DataResourceRole could not be deleted");

	}

}
