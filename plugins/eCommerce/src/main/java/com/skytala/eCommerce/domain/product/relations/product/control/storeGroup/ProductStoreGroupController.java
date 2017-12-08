package com.skytala.eCommerce.domain.product.relations.product.control.storeGroup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroup.AddProductStoreGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroup.DeleteProductStoreGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroup.UpdateProductStoreGroup;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroup.ProductStoreGroupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroup.ProductStoreGroupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroup.ProductStoreGroupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroup.ProductStoreGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroup.ProductStoreGroupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroup.ProductStoreGroup;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroup.FindProductStoreGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreGroups")
public class ProductStoreGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroup
	 * @return a List with the ProductStoreGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupsBy query = new FindProductStoreGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroup> productStoreGroups =((ProductStoreGroupFound) Scheduler.execute(query).data()).getProductStoreGroups();

		if (productStoreGroups.size() == 1) {
			return ResponseEntity.ok().body(productStoreGroups.get(0));
		}

		return ResponseEntity.ok().body(productStoreGroups);

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
	public ResponseEntity<Object> createProductStoreGroup(HttpServletRequest request) throws Exception {

		ProductStoreGroup productStoreGroupToBeAdded = new ProductStoreGroup();
		try {
			productStoreGroupToBeAdded = ProductStoreGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreGroup(productStoreGroupToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroup entry in the ofbiz database
	 * 
	 * @param productStoreGroupToBeAdded
	 *            the ProductStoreGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreGroup(@RequestBody ProductStoreGroup productStoreGroupToBeAdded) throws Exception {

		AddProductStoreGroup command = new AddProductStoreGroup(productStoreGroupToBeAdded);
		ProductStoreGroup productStoreGroup = ((ProductStoreGroupAdded) Scheduler.execute(command).data()).getAddedProductStoreGroup();
		
		if (productStoreGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreGroup could not be created.");
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
	public boolean updateProductStoreGroup(HttpServletRequest request) throws Exception {

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

		ProductStoreGroup productStoreGroupToBeUpdated = new ProductStoreGroup();

		try {
			productStoreGroupToBeUpdated = ProductStoreGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreGroup(productStoreGroupToBeUpdated, productStoreGroupToBeUpdated.getProductStoreGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreGroup with the specific Id
	 * 
	 * @param productStoreGroupToBeUpdated
	 *            the ProductStoreGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreGroup(@RequestBody ProductStoreGroup productStoreGroupToBeUpdated,
			@PathVariable String productStoreGroupId) throws Exception {

		productStoreGroupToBeUpdated.setProductStoreGroupId(productStoreGroupId);

		UpdateProductStoreGroup command = new UpdateProductStoreGroup(productStoreGroupToBeUpdated);

		try {
			if(((ProductStoreGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupId", productStoreGroupId);
		try {

			Object foundProductStoreGroup = findProductStoreGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreGroupId}")
	public ResponseEntity<Object> deleteProductStoreGroupByIdUpdated(@PathVariable String productStoreGroupId) throws Exception {
		DeleteProductStoreGroup command = new DeleteProductStoreGroup(productStoreGroupId);

		try {
			if (((ProductStoreGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreGroup could not be deleted");

	}

}
