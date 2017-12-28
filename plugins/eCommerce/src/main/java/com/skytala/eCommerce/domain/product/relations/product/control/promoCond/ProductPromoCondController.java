package com.skytala.eCommerce.domain.product.relations.product.control.promoCond;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.AddProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.DeleteProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.UpdateProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCond.ProductPromoCondMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCond.FindProductPromoCondsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoConds")
public class ProductPromoCondController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCondController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCond
	 * @return a List with the ProductPromoConds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoCond>> findProductPromoCondsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCondsBy query = new FindProductPromoCondsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCond> productPromoConds =((ProductPromoCondFound) Scheduler.execute(query).data()).getProductPromoConds();

		return ResponseEntity.ok().body(productPromoConds);

	}

	/**
	 * creates a new ProductPromoCond entry in the ofbiz database
	 * 
	 * @param productPromoCondToBeAdded
	 *            the ProductPromoCond thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoCond> createProductPromoCond(@RequestBody ProductPromoCond productPromoCondToBeAdded) throws Exception {

		AddProductPromoCond command = new AddProductPromoCond(productPromoCondToBeAdded);
		ProductPromoCond productPromoCond = ((ProductPromoCondAdded) Scheduler.execute(command).data()).getAddedProductPromoCond();
		
		if (productPromoCond != null) 
			return successful(productPromoCond);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoCond with the specific Id
	 * 
	 * @param productPromoCondToBeUpdated
	 *            the ProductPromoCond thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoCond(@RequestBody ProductPromoCond productPromoCondToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoCondToBeUpdated.setnull(null);

		UpdateProductPromoCond command = new UpdateProductPromoCond(productPromoCondToBeUpdated);

		try {
			if(((ProductPromoCondUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoCondId}")
	public ResponseEntity<ProductPromoCond> findById(@PathVariable String productPromoCondId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCondId", productPromoCondId);
		try {

			List<ProductPromoCond> foundProductPromoCond = findProductPromoCondsBy(requestParams).getBody();
			if(foundProductPromoCond.size()==1){				return successful(foundProductPromoCond.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoCondId}")
	public ResponseEntity<String> deleteProductPromoCondByIdUpdated(@PathVariable String productPromoCondId) throws Exception {
		DeleteProductPromoCond command = new DeleteProductPromoCond(productPromoCondId);

		try {
			if (((ProductPromoCondDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
