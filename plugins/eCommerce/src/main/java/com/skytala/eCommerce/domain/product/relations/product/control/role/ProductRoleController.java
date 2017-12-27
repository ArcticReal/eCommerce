package com.skytala.eCommerce.domain.product.relations.product.control.role;

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
import com.skytala.eCommerce.domain.product.relations.product.command.role.AddProductRole;
import com.skytala.eCommerce.domain.product.relations.product.command.role.DeleteProductRole;
import com.skytala.eCommerce.domain.product.relations.product.command.role.UpdateProductRole;
import com.skytala.eCommerce.domain.product.relations.product.event.role.ProductRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.role.ProductRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.role.ProductRoleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.role.ProductRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.role.ProductRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;
import com.skytala.eCommerce.domain.product.relations.product.query.role.FindProductRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productRoles")
public class ProductRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductRole
	 * @return a List with the ProductRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductRole>> findProductRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductRolesBy query = new FindProductRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductRole> productRoles =((ProductRoleFound) Scheduler.execute(query).data()).getProductRoles();

		return ResponseEntity.ok().body(productRoles);

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
	public ResponseEntity<ProductRole> createProductRole(HttpServletRequest request) throws Exception {

		ProductRole productRoleToBeAdded = new ProductRole();
		try {
			productRoleToBeAdded = ProductRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductRole(productRoleToBeAdded);

	}

	/**
	 * creates a new ProductRole entry in the ofbiz database
	 * 
	 * @param productRoleToBeAdded
	 *            the ProductRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductRole> createProductRole(@RequestBody ProductRole productRoleToBeAdded) throws Exception {

		AddProductRole command = new AddProductRole(productRoleToBeAdded);
		ProductRole productRole = ((ProductRoleAdded) Scheduler.execute(command).data()).getAddedProductRole();
		
		if (productRole != null) 
			return successful(productRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductRole with the specific Id
	 * 
	 * @param productRoleToBeUpdated
	 *            the ProductRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductRole(@RequestBody ProductRole productRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductRole command = new UpdateProductRole(productRoleToBeUpdated);

		try {
			if(((ProductRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productRoleId}")
	public ResponseEntity<ProductRole> findById(@PathVariable String productRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productRoleId", productRoleId);
		try {

			List<ProductRole> foundProductRole = findProductRolesBy(requestParams).getBody();
			if(foundProductRole.size()==1){				return successful(foundProductRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productRoleId}")
	public ResponseEntity<String> deleteProductRoleByIdUpdated(@PathVariable String productRoleId) throws Exception {
		DeleteProductRole command = new DeleteProductRole(productRoleId);

		try {
			if (((ProductRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
