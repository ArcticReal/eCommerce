package com.skytala.eCommerce.domain.product.relations.product.control.priceCond;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceCond.AddProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.product.command.priceCond.DeleteProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.product.command.priceCond.UpdateProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.product.event.priceCond.ProductPriceCondAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceCond.ProductPriceCondDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceCond.ProductPriceCondFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceCond.ProductPriceCondUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceCond.ProductPriceCondMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceCond.ProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.product.query.priceCond.FindProductPriceCondsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceConds")
public class ProductPriceCondController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceCondController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceCond
	 * @return a List with the ProductPriceConds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceCond>> findProductPriceCondsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceCondsBy query = new FindProductPriceCondsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceCond> productPriceConds =((ProductPriceCondFound) Scheduler.execute(query).data()).getProductPriceConds();

		return ResponseEntity.ok().body(productPriceConds);

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
	public ResponseEntity<ProductPriceCond> createProductPriceCond(HttpServletRequest request) throws Exception {

		ProductPriceCond productPriceCondToBeAdded = new ProductPriceCond();
		try {
			productPriceCondToBeAdded = ProductPriceCondMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductPriceCond(productPriceCondToBeAdded);

	}

	/**
	 * creates a new ProductPriceCond entry in the ofbiz database
	 * 
	 * @param productPriceCondToBeAdded
	 *            the ProductPriceCond thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceCond> createProductPriceCond(@RequestBody ProductPriceCond productPriceCondToBeAdded) throws Exception {

		AddProductPriceCond command = new AddProductPriceCond(productPriceCondToBeAdded);
		ProductPriceCond productPriceCond = ((ProductPriceCondAdded) Scheduler.execute(command).data()).getAddedProductPriceCond();
		
		if (productPriceCond != null) 
			return successful(productPriceCond);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPriceCond with the specific Id
	 * 
	 * @param productPriceCondToBeUpdated
	 *            the ProductPriceCond thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceCondSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPriceCond(@RequestBody ProductPriceCond productPriceCondToBeUpdated,
			@PathVariable String productPriceCondSeqId) throws Exception {

		productPriceCondToBeUpdated.setProductPriceCondSeqId(productPriceCondSeqId);

		UpdateProductPriceCond command = new UpdateProductPriceCond(productPriceCondToBeUpdated);

		try {
			if(((ProductPriceCondUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceCondId}")
	public ResponseEntity<ProductPriceCond> findById(@PathVariable String productPriceCondId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceCondId", productPriceCondId);
		try {

			List<ProductPriceCond> foundProductPriceCond = findProductPriceCondsBy(requestParams).getBody();
			if(foundProductPriceCond.size()==1){				return successful(foundProductPriceCond.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceCondId}")
	public ResponseEntity<String> deleteProductPriceCondByIdUpdated(@PathVariable String productPriceCondId) throws Exception {
		DeleteProductPriceCond command = new DeleteProductPriceCond(productPriceCondId);

		try {
			if (((ProductPriceCondDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
