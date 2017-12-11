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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreRolesBy query = new FindProductStoreRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreRole> productStoreRoles =((ProductStoreRoleFound) Scheduler.execute(query).data()).getProductStoreRoles();

		if (productStoreRoles.size() == 1) {
			return ResponseEntity.ok().body(productStoreRoles.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductStoreRole(HttpServletRequest request) throws Exception {

		ProductStoreRole productStoreRoleToBeAdded = new ProductStoreRole();
		try {
			productStoreRoleToBeAdded = ProductStoreRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductStoreRole(@RequestBody ProductStoreRole productStoreRoleToBeAdded) throws Exception {

		AddProductStoreRole command = new AddProductStoreRole(productStoreRoleToBeAdded);
		ProductStoreRole productStoreRole = ((ProductStoreRoleAdded) Scheduler.execute(command).data()).getAddedProductStoreRole();
		
		if (productStoreRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreRole could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductStoreRole(HttpServletRequest request) throws Exception {

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

		ProductStoreRole productStoreRoleToBeUpdated = new ProductStoreRole();

		try {
			productStoreRoleToBeUpdated = ProductStoreRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreRole(productStoreRoleToBeUpdated, productStoreRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductStoreRole(@RequestBody ProductStoreRole productStoreRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		productStoreRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateProductStoreRole command = new UpdateProductStoreRole(productStoreRoleToBeUpdated);

		try {
			if(((ProductStoreRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreRoleId", productStoreRoleId);
		try {

			Object foundProductStoreRole = findProductStoreRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreRoleId}")
	public ResponseEntity<Object> deleteProductStoreRoleByIdUpdated(@PathVariable String productStoreRoleId) throws Exception {
		DeleteProductStoreRole command = new DeleteProductStoreRole(productStoreRoleId);

		try {
			if (((ProductStoreRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreRole could not be deleted");

	}

}
