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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreGroups")
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
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreGroup>> findProductStoreGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupsBy query = new FindProductStoreGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroup> productStoreGroups =((ProductStoreGroupFound) Scheduler.execute(query).data()).getProductStoreGroups();

		return ResponseEntity.ok().body(productStoreGroups);

	}

	/**
	 * creates a new ProductStoreGroup entry in the ofbiz database
	 * 
	 * @param productStoreGroupToBeAdded
	 *            the ProductStoreGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreGroup> createProductStoreGroup(@RequestBody ProductStoreGroup productStoreGroupToBeAdded) throws Exception {

		AddProductStoreGroup command = new AddProductStoreGroup(productStoreGroupToBeAdded);
		ProductStoreGroup productStoreGroup = ((ProductStoreGroupAdded) Scheduler.execute(command).data()).getAddedProductStoreGroup();
		
		if (productStoreGroup != null) 
			return successful(productStoreGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductStoreGroup(@RequestBody ProductStoreGroup productStoreGroupToBeUpdated,
			@PathVariable String productStoreGroupId) throws Exception {

		productStoreGroupToBeUpdated.setProductStoreGroupId(productStoreGroupId);

		UpdateProductStoreGroup command = new UpdateProductStoreGroup(productStoreGroupToBeUpdated);

		try {
			if(((ProductStoreGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreGroupId}")
	public ResponseEntity<ProductStoreGroup> findById(@PathVariable String productStoreGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupId", productStoreGroupId);
		try {

			List<ProductStoreGroup> foundProductStoreGroup = findProductStoreGroupsBy(requestParams).getBody();
			if(foundProductStoreGroup.size()==1){				return successful(foundProductStoreGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreGroupId}")
	public ResponseEntity<String> deleteProductStoreGroupByIdUpdated(@PathVariable String productStoreGroupId) throws Exception {
		DeleteProductStoreGroup command = new DeleteProductStoreGroup(productStoreGroupId);

		try {
			if (((ProductStoreGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
