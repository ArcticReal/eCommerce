package com.skytala.eCommerce.domain.product.relations.product.control.storeRole;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeRole.AddProductStoreRole;
import com.skytala.eCommerce.domain.product.relations.product.command.storeRole.DeleteProductStoreRole;
import com.skytala.eCommerce.domain.product.relations.product.command.storeRole.UpdateProductStoreRole;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeRole.ProductStoreRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;
import com.skytala.eCommerce.domain.product.relations.product.query.storeRole.FindProductStoreRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreRoles")
public class ProductStoreRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreRole
	 * @return a List with the ProductStoreRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreRole>> findProductStoreRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreRolesBy query = new FindProductStoreRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreRole> productStoreRoles =((ProductStoreRoleFound) Scheduler.execute(query).data()).getProductStoreRoles();

		return ResponseEntity.ok().body(productStoreRoles);

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
	public ResponseEntity<ProductStoreRole> createProductStoreRole(HttpServletRequest request) throws Exception {

		ProductStoreRole productStoreRoleToBeAdded = new ProductStoreRole();
		try {
			productStoreRoleToBeAdded = ProductStoreRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreRole(productStoreRoleToBeAdded);

	}

	/**
	 * creates a new ProductStoreRole entry in the ofbiz database
	 * 
	 * @param productStoreRoleToBeAdded
	 *            the ProductStoreRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreRole> createProductStoreRole(@RequestBody ProductStoreRole productStoreRoleToBeAdded) throws Exception {

		AddProductStoreRole command = new AddProductStoreRole(productStoreRoleToBeAdded);
		ProductStoreRole productStoreRole = ((ProductStoreRoleAdded) Scheduler.execute(command).data()).getAddedProductStoreRole();
		
		if (productStoreRole != null) 
			return successful(productStoreRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreRole with the specific Id
	 * 
	 * @param productStoreRoleToBeUpdated
	 *            the ProductStoreRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreRole(@RequestBody ProductStoreRole productStoreRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productStoreRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductStoreRole command = new UpdateProductStoreRole(productStoreRoleToBeUpdated);

		try {
			if(((ProductStoreRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreRoleId}")
	public ResponseEntity<ProductStoreRole> findById(@PathVariable String productStoreRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreRoleId", productStoreRoleId);
		try {

			List<ProductStoreRole> foundProductStoreRole = findProductStoreRolesBy(requestParams).getBody();
			if(foundProductStoreRole.size()==1){				return successful(foundProductStoreRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreRoleId}")
	public ResponseEntity<String> deleteProductStoreRoleByIdUpdated(@PathVariable String productStoreRoleId) throws Exception {
		DeleteProductStoreRole command = new DeleteProductStoreRole(productStoreRoleId);

		try {
			if (((ProductStoreRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
