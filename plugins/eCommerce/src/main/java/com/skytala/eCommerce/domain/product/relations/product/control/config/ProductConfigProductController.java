package com.skytala.eCommerce.domain.product.relations.product.control.config;

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
import com.skytala.eCommerce.domain.product.relations.product.command.config.AddProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.config.DeleteProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.config.UpdateProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductFound;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.product.query.config.FindProductConfigProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productConfigProducts")
public class ProductConfigProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigProduct
	 * @return a List with the ProductConfigProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductConfigProduct>> findProductConfigProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigProductsBy query = new FindProductConfigProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigProduct> productConfigProducts =((ProductConfigProductFound) Scheduler.execute(query).data()).getProductConfigProducts();

		return ResponseEntity.ok().body(productConfigProducts);

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
	public ResponseEntity<ProductConfigProduct> createProductConfigProduct(HttpServletRequest request) throws Exception {

		ProductConfigProduct productConfigProductToBeAdded = new ProductConfigProduct();
		try {
			productConfigProductToBeAdded = ProductConfigProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductConfigProduct(productConfigProductToBeAdded);

	}

	/**
	 * creates a new ProductConfigProduct entry in the ofbiz database
	 * 
	 * @param productConfigProductToBeAdded
	 *            the ProductConfigProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductConfigProduct> createProductConfigProduct(@RequestBody ProductConfigProduct productConfigProductToBeAdded) throws Exception {

		AddProductConfigProduct command = new AddProductConfigProduct(productConfigProductToBeAdded);
		ProductConfigProduct productConfigProduct = ((ProductConfigProductAdded) Scheduler.execute(command).data()).getAddedProductConfigProduct();
		
		if (productConfigProduct != null) 
			return successful(productConfigProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductConfigProduct with the specific Id
	 * 
	 * @param productConfigProductToBeUpdated
	 *            the ProductConfigProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{configOptionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductConfigProduct(@RequestBody ProductConfigProduct productConfigProductToBeUpdated,
			@PathVariable String configOptionId) throws Exception {

		productConfigProductToBeUpdated.setConfigOptionId(configOptionId);

		UpdateProductConfigProduct command = new UpdateProductConfigProduct(productConfigProductToBeUpdated);

		try {
			if(((ProductConfigProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigProductId}")
	public ResponseEntity<ProductConfigProduct> findById(@PathVariable String productConfigProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigProductId", productConfigProductId);
		try {

			List<ProductConfigProduct> foundProductConfigProduct = findProductConfigProductsBy(requestParams).getBody();
			if(foundProductConfigProduct.size()==1){				return successful(foundProductConfigProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigProductId}")
	public ResponseEntity<String> deleteProductConfigProductByIdUpdated(@PathVariable String productConfigProductId) throws Exception {
		DeleteProductConfigProduct command = new DeleteProductConfigProduct(productConfigProductId);

		try {
			if (((ProductConfigProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
