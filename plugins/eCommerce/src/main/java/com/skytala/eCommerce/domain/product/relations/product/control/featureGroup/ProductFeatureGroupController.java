package com.skytala.eCommerce.domain.product.relations.product.control.featureGroup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.AddProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.DeleteProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroup.UpdateProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroup.ProductFeatureGroupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;
import com.skytala.eCommerce.domain.product.relations.product.query.featureGroup.FindProductFeatureGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureGroups")
public class ProductFeatureGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureGroup
	 * @return a List with the ProductFeatureGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureGroup>> findProductFeatureGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureGroupsBy query = new FindProductFeatureGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureGroup> productFeatureGroups =((ProductFeatureGroupFound) Scheduler.execute(query).data()).getProductFeatureGroups();

		return ResponseEntity.ok().body(productFeatureGroups);

	}

	/**
	 * creates a new ProductFeatureGroup entry in the ofbiz database
	 * 
	 * @param productFeatureGroupToBeAdded
	 *            the ProductFeatureGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureGroup> createProductFeatureGroup(@RequestBody ProductFeatureGroup productFeatureGroupToBeAdded) throws Exception {

		AddProductFeatureGroup command = new AddProductFeatureGroup(productFeatureGroupToBeAdded);
		ProductFeatureGroup productFeatureGroup = ((ProductFeatureGroupAdded) Scheduler.execute(command).data()).getAddedProductFeatureGroup();
		
		if (productFeatureGroup != null) 
			return successful(productFeatureGroup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureGroup with the specific Id
	 * 
	 * @param productFeatureGroupToBeUpdated
	 *            the ProductFeatureGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureGroup(@RequestBody ProductFeatureGroup productFeatureGroupToBeUpdated,
			@PathVariable String productFeatureGroupId) throws Exception {

		productFeatureGroupToBeUpdated.setProductFeatureGroupId(productFeatureGroupId);

		UpdateProductFeatureGroup command = new UpdateProductFeatureGroup(productFeatureGroupToBeUpdated);

		try {
			if(((ProductFeatureGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureGroupId}")
	public ResponseEntity<ProductFeatureGroup> findById(@PathVariable String productFeatureGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureGroupId", productFeatureGroupId);
		try {

			List<ProductFeatureGroup> foundProductFeatureGroup = findProductFeatureGroupsBy(requestParams).getBody();
			if(foundProductFeatureGroup.size()==1){				return successful(foundProductFeatureGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureGroupId}")
	public ResponseEntity<String> deleteProductFeatureGroupByIdUpdated(@PathVariable String productFeatureGroupId) throws Exception {
		DeleteProductFeatureGroup command = new DeleteProductFeatureGroup(productFeatureGroupId);

		try {
			if (((ProductFeatureGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
