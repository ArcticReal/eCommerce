package com.skytala.eCommerce.domain.product.relations.prodCatalog.control.role;

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
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.role.AddProdCatalogRole;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.role.DeleteProdCatalogRole;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.role.UpdateProdCatalogRole;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.role.ProdCatalogRoleMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.query.role.FindProdCatalogRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/prodCatalog/prodCatalogRoles")
public class ProdCatalogRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalogRole
	 * @return a List with the ProdCatalogRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProdCatalogRole>> findProdCatalogRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogRolesBy query = new FindProdCatalogRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogRole> prodCatalogRoles =((ProdCatalogRoleFound) Scheduler.execute(query).data()).getProdCatalogRoles();

		return ResponseEntity.ok().body(prodCatalogRoles);

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
	public ResponseEntity<ProdCatalogRole> createProdCatalogRole(HttpServletRequest request) throws Exception {

		ProdCatalogRole prodCatalogRoleToBeAdded = new ProdCatalogRole();
		try {
			prodCatalogRoleToBeAdded = ProdCatalogRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProdCatalogRole(prodCatalogRoleToBeAdded);

	}

	/**
	 * creates a new ProdCatalogRole entry in the ofbiz database
	 * 
	 * @param prodCatalogRoleToBeAdded
	 *            the ProdCatalogRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdCatalogRole> createProdCatalogRole(@RequestBody ProdCatalogRole prodCatalogRoleToBeAdded) throws Exception {

		AddProdCatalogRole command = new AddProdCatalogRole(prodCatalogRoleToBeAdded);
		ProdCatalogRole prodCatalogRole = ((ProdCatalogRoleAdded) Scheduler.execute(command).data()).getAddedProdCatalogRole();
		
		if (prodCatalogRole != null) 
			return successful(prodCatalogRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProdCatalogRole with the specific Id
	 * 
	 * @param prodCatalogRoleToBeUpdated
	 *            the ProdCatalogRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProdCatalogRole(@RequestBody ProdCatalogRole prodCatalogRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		prodCatalogRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProdCatalogRole command = new UpdateProdCatalogRole(prodCatalogRoleToBeUpdated);

		try {
			if(((ProdCatalogRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodCatalogRoleId}")
	public ResponseEntity<ProdCatalogRole> findById(@PathVariable String prodCatalogRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogRoleId", prodCatalogRoleId);
		try {

			List<ProdCatalogRole> foundProdCatalogRole = findProdCatalogRolesBy(requestParams).getBody();
			if(foundProdCatalogRole.size()==1){				return successful(foundProdCatalogRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodCatalogRoleId}")
	public ResponseEntity<String> deleteProdCatalogRoleByIdUpdated(@PathVariable String prodCatalogRoleId) throws Exception {
		DeleteProdCatalogRole command = new DeleteProdCatalogRole(prodCatalogRoleId);

		try {
			if (((ProdCatalogRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
