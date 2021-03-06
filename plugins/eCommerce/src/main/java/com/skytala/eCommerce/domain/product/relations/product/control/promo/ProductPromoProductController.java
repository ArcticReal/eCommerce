package com.skytala.eCommerce.domain.product.relations.product.control.promo;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promo.AddProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.DeleteProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.UpdateProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.query.promo.FindProductPromoProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoProducts")
public class ProductPromoProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoProduct
	 * @return a List with the ProductPromoProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoProduct>> findProductPromoProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoProductsBy query = new FindProductPromoProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoProduct> productPromoProducts =((ProductPromoProductFound) Scheduler.execute(query).data()).getProductPromoProducts();

		return ResponseEntity.ok().body(productPromoProducts);

	}

	/**
	 * creates a new ProductPromoProduct entry in the ofbiz database
	 * 
	 * @param productPromoProductToBeAdded
	 *            the ProductPromoProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoProduct> createProductPromoProduct(@RequestBody ProductPromoProduct productPromoProductToBeAdded) throws Exception {

		AddProductPromoProduct command = new AddProductPromoProduct(productPromoProductToBeAdded);
		ProductPromoProduct productPromoProduct = ((ProductPromoProductAdded) Scheduler.execute(command).data()).getAddedProductPromoProduct();
		
		if (productPromoProduct != null) 
			return successful(productPromoProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoProduct with the specific Id
	 * 
	 * @param productPromoProductToBeUpdated
	 *            the ProductPromoProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoProduct(@RequestBody ProductPromoProduct productPromoProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoProductToBeUpdated.setnull(null);

		UpdateProductPromoProduct command = new UpdateProductPromoProduct(productPromoProductToBeUpdated);

		try {
			if(((ProductPromoProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoProductId}")
	public ResponseEntity<ProductPromoProduct> findById(@PathVariable String productPromoProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoProductId", productPromoProductId);
		try {

			List<ProductPromoProduct> foundProductPromoProduct = findProductPromoProductsBy(requestParams).getBody();
			if(foundProductPromoProduct.size()==1){				return successful(foundProductPromoProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoProductId}")
	public ResponseEntity<String> deleteProductPromoProductByIdUpdated(@PathVariable String productPromoProductId) throws Exception {
		DeleteProductPromoProduct command = new DeleteProductPromoProduct(productPromoProductId);

		try {
			if (((ProductPromoProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
