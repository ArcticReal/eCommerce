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
	public ResponseEntity<Object> findProductRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductRolesBy query = new FindProductRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductRole> productRoles =((ProductRoleFound) Scheduler.execute(query).data()).getProductRoles();

		if (productRoles.size() == 1) {
			return ResponseEntity.ok().body(productRoles.get(0));
		}

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
	public ResponseEntity<Object> createProductRole(HttpServletRequest request) throws Exception {

		ProductRole productRoleToBeAdded = new ProductRole();
		try {
			productRoleToBeAdded = ProductRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductRole(@RequestBody ProductRole productRoleToBeAdded) throws Exception {

		AddProductRole command = new AddProductRole(productRoleToBeAdded);
		ProductRole productRole = ((ProductRoleAdded) Scheduler.execute(command).data()).getAddedProductRole();
		
		if (productRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductRole could not be created.");
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
	public boolean updateProductRole(HttpServletRequest request) throws Exception {

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

		ProductRole productRoleToBeUpdated = new ProductRole();

		try {
			productRoleToBeUpdated = ProductRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductRole(productRoleToBeUpdated, productRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductRole(@RequestBody ProductRole productRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductRole command = new UpdateProductRole(productRoleToBeUpdated);

		try {
			if(((ProductRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String productRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productRoleId", productRoleId);
		try {

			Object foundProductRole = findProductRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productRoleId}")
	public ResponseEntity<Object> deleteProductRoleByIdUpdated(@PathVariable String productRoleId) throws Exception {
		DeleteProductRole command = new DeleteProductRole(productRoleId);

		try {
			if (((ProductRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductRole could not be deleted");

	}

}
