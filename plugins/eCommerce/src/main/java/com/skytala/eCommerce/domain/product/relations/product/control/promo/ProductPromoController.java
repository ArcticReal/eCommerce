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
import com.skytala.eCommerce.domain.product.relations.product.command.promo.AddProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.DeleteProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.UpdateProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.query.promo.FindProductPromosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromos")
public class ProductPromoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromo
	 * @return a List with the ProductPromos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromo>> findProductPromosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromosBy query = new FindProductPromosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromo> productPromos =((ProductPromoFound) Scheduler.execute(query).data()).getProductPromos();

		return ResponseEntity.ok().body(productPromos);

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
	public ResponseEntity<ProductPromo> createProductPromo(HttpServletRequest request) throws Exception {

		ProductPromo productPromoToBeAdded = new ProductPromo();
		try {
			productPromoToBeAdded = ProductPromoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductPromo(productPromoToBeAdded);

	}

	/**
	 * creates a new ProductPromo entry in the ofbiz database
	 * 
	 * @param productPromoToBeAdded
	 *            the ProductPromo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromo> createProductPromo(@RequestBody ProductPromo productPromoToBeAdded) throws Exception {

		AddProductPromo command = new AddProductPromo(productPromoToBeAdded);
		ProductPromo productPromo = ((ProductPromoAdded) Scheduler.execute(command).data()).getAddedProductPromo();
		
		if (productPromo != null) 
			return successful(productPromo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromo with the specific Id
	 * 
	 * @param productPromoToBeUpdated
	 *            the ProductPromo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPromoId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromo(@RequestBody ProductPromo productPromoToBeUpdated,
			@PathVariable String productPromoId) throws Exception {

		productPromoToBeUpdated.setProductPromoId(productPromoId);

		UpdateProductPromo command = new UpdateProductPromo(productPromoToBeUpdated);

		try {
			if(((ProductPromoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoId}")
	public ResponseEntity<ProductPromo> findById(@PathVariable String productPromoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoId", productPromoId);
		try {

			List<ProductPromo> foundProductPromo = findProductPromosBy(requestParams).getBody();
			if(foundProductPromo.size()==1){				return successful(foundProductPromo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoId}")
	public ResponseEntity<String> deleteProductPromoByIdUpdated(@PathVariable String productPromoId) throws Exception {
		DeleteProductPromo command = new DeleteProductPromo(productPromoId);

		try {
			if (((ProductPromoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
