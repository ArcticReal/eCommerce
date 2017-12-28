package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupRole;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRole.AddProductStoreGroupRole;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRole.DeleteProductStoreGroupRole;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRole.UpdateProductStoreGroupRole;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRole.ProductStoreGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupRole.FindProductStoreGroupRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreGroupRoles")
public class ProductStoreGroupRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupRole
	 * @return a List with the ProductStoreGroupRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreGroupRole>> findProductStoreGroupRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupRolesBy query = new FindProductStoreGroupRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupRole> productStoreGroupRoles =((ProductStoreGroupRoleFound) Scheduler.execute(query).data()).getProductStoreGroupRoles();

		return ResponseEntity.ok().body(productStoreGroupRoles);

	}

	/**
	 * creates a new ProductStoreGroupRole entry in the ofbiz database
	 * 
	 * @param productStoreGroupRoleToBeAdded
	 *            the ProductStoreGroupRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreGroupRole> createProductStoreGroupRole(@RequestBody ProductStoreGroupRole productStoreGroupRoleToBeAdded) throws Exception {

		AddProductStoreGroupRole command = new AddProductStoreGroupRole(productStoreGroupRoleToBeAdded);
		ProductStoreGroupRole productStoreGroupRole = ((ProductStoreGroupRoleAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupRole();
		
		if (productStoreGroupRole != null) 
			return successful(productStoreGroupRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreGroupRole with the specific Id
	 * 
	 * @param productStoreGroupRoleToBeUpdated
	 *            the ProductStoreGroupRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreGroupRole(@RequestBody ProductStoreGroupRole productStoreGroupRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productStoreGroupRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductStoreGroupRole command = new UpdateProductStoreGroupRole(productStoreGroupRoleToBeUpdated);

		try {
			if(((ProductStoreGroupRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreGroupRoleId}")
	public ResponseEntity<ProductStoreGroupRole> findById(@PathVariable String productStoreGroupRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupRoleId", productStoreGroupRoleId);
		try {

			List<ProductStoreGroupRole> foundProductStoreGroupRole = findProductStoreGroupRolesBy(requestParams).getBody();
			if(foundProductStoreGroupRole.size()==1){				return successful(foundProductStoreGroupRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreGroupRoleId}")
	public ResponseEntity<String> deleteProductStoreGroupRoleByIdUpdated(@PathVariable String productStoreGroupRoleId) throws Exception {
		DeleteProductStoreGroupRole command = new DeleteProductStoreGroupRole(productStoreGroupRoleId);

		try {
			if (((ProductStoreGroupRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
