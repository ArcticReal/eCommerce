package com.skytala.eCommerce.domain.product.relations.product.control.categoryRole;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRole.AddProductCategoryRole;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRole.DeleteProductCategoryRole;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRole.UpdateProductCategoryRole;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRole.ProductCategoryRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRole.ProductCategoryRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRole.ProductCategoryRoleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRole.ProductCategoryRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRole.ProductCategoryRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryRole.FindProductCategoryRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryRoles")
public class ProductCategoryRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryRole
	 * @return a List with the ProductCategoryRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryRole>> findProductCategoryRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryRolesBy query = new FindProductCategoryRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryRole> productCategoryRoles =((ProductCategoryRoleFound) Scheduler.execute(query).data()).getProductCategoryRoles();

		return ResponseEntity.ok().body(productCategoryRoles);

	}

	/**
	 * creates a new ProductCategoryRole entry in the ofbiz database
	 * 
	 * @param productCategoryRoleToBeAdded
	 *            the ProductCategoryRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryRole> createProductCategoryRole(@RequestBody ProductCategoryRole productCategoryRoleToBeAdded) throws Exception {

		AddProductCategoryRole command = new AddProductCategoryRole(productCategoryRoleToBeAdded);
		ProductCategoryRole productCategoryRole = ((ProductCategoryRoleAdded) Scheduler.execute(command).data()).getAddedProductCategoryRole();
		
		if (productCategoryRole != null) 
			return successful(productCategoryRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryRole with the specific Id
	 * 
	 * @param productCategoryRoleToBeUpdated
	 *            the ProductCategoryRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryRole(@RequestBody ProductCategoryRole productCategoryRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productCategoryRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductCategoryRole command = new UpdateProductCategoryRole(productCategoryRoleToBeUpdated);

		try {
			if(((ProductCategoryRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryRoleId}")
	public ResponseEntity<ProductCategoryRole> findById(@PathVariable String productCategoryRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryRoleId", productCategoryRoleId);
		try {

			List<ProductCategoryRole> foundProductCategoryRole = findProductCategoryRolesBy(requestParams).getBody();
			if(foundProductCategoryRole.size()==1){				return successful(foundProductCategoryRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryRoleId}")
	public ResponseEntity<String> deleteProductCategoryRoleByIdUpdated(@PathVariable String productCategoryRoleId) throws Exception {
		DeleteProductCategoryRole command = new DeleteProductCategoryRole(productCategoryRoleId);

		try {
			if (((ProductCategoryRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
