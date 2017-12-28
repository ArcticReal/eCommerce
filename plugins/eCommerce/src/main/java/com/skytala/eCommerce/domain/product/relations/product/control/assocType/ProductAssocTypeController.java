package com.skytala.eCommerce.domain.product.relations.product.control.assocType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.AddProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.DeleteProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.UpdateProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.assocType.ProductAssocTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.query.assocType.FindProductAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productAssocTypes")
public class ProductAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAssocType
	 * @return a List with the ProductAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductAssocType>> findProductAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAssocTypesBy query = new FindProductAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAssocType> productAssocTypes =((ProductAssocTypeFound) Scheduler.execute(query).data()).getProductAssocTypes();

		return ResponseEntity.ok().body(productAssocTypes);

	}

	/**
	 * creates a new ProductAssocType entry in the ofbiz database
	 * 
	 * @param productAssocTypeToBeAdded
	 *            the ProductAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAssocType> createProductAssocType(@RequestBody ProductAssocType productAssocTypeToBeAdded) throws Exception {

		AddProductAssocType command = new AddProductAssocType(productAssocTypeToBeAdded);
		ProductAssocType productAssocType = ((ProductAssocTypeAdded) Scheduler.execute(command).data()).getAddedProductAssocType();
		
		if (productAssocType != null) 
			return successful(productAssocType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductAssocType with the specific Id
	 * 
	 * @param productAssocTypeToBeUpdated
	 *            the ProductAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductAssocType(@RequestBody ProductAssocType productAssocTypeToBeUpdated,
			@PathVariable String productAssocTypeId) throws Exception {

		productAssocTypeToBeUpdated.setProductAssocTypeId(productAssocTypeId);

		UpdateProductAssocType command = new UpdateProductAssocType(productAssocTypeToBeUpdated);

		try {
			if(((ProductAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productAssocTypeId}")
	public ResponseEntity<ProductAssocType> findById(@PathVariable String productAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAssocTypeId", productAssocTypeId);
		try {

			List<ProductAssocType> foundProductAssocType = findProductAssocTypesBy(requestParams).getBody();
			if(foundProductAssocType.size()==1){				return successful(foundProductAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productAssocTypeId}")
	public ResponseEntity<String> deleteProductAssocTypeByIdUpdated(@PathVariable String productAssocTypeId) throws Exception {
		DeleteProductAssocType command = new DeleteProductAssocType(productAssocTypeId);

		try {
			if (((ProductAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
