package com.skytala.eCommerce.domain.product.relations.product.control.promoCode;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.AddProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.DeleteProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.UpdateProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCode.ProductPromoCodeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCode.ProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCode.FindProductPromoCodesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoCodes")
public class ProductPromoCodeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCodeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCode
	 * @return a List with the ProductPromoCodes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoCode>> findProductPromoCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCodesBy query = new FindProductPromoCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCode> productPromoCodes =((ProductPromoCodeFound) Scheduler.execute(query).data()).getProductPromoCodes();

		return ResponseEntity.ok().body(productPromoCodes);

	}

	/**
	 * creates a new ProductPromoCode entry in the ofbiz database
	 * 
	 * @param productPromoCodeToBeAdded
	 *            the ProductPromoCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoCode> createProductPromoCode(@RequestBody ProductPromoCode productPromoCodeToBeAdded) throws Exception {

		AddProductPromoCode command = new AddProductPromoCode(productPromoCodeToBeAdded);
		ProductPromoCode productPromoCode = ((ProductPromoCodeAdded) Scheduler.execute(command).data()).getAddedProductPromoCode();
		
		if (productPromoCode != null) 
			return successful(productPromoCode);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoCode with the specific Id
	 * 
	 * @param productPromoCodeToBeUpdated
	 *            the ProductPromoCode thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPromoCodeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoCode(@RequestBody ProductPromoCode productPromoCodeToBeUpdated,
			@PathVariable String productPromoCodeId) throws Exception {

		productPromoCodeToBeUpdated.setProductPromoCodeId(productPromoCodeId);

		UpdateProductPromoCode command = new UpdateProductPromoCode(productPromoCodeToBeUpdated);

		try {
			if(((ProductPromoCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoCodeId}")
	public ResponseEntity<ProductPromoCode> findById(@PathVariable String productPromoCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCodeId", productPromoCodeId);
		try {

			List<ProductPromoCode> foundProductPromoCode = findProductPromoCodesBy(requestParams).getBody();
			if(foundProductPromoCode.size()==1){				return successful(foundProductPromoCode.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoCodeId}")
	public ResponseEntity<String> deleteProductPromoCodeByIdUpdated(@PathVariable String productPromoCodeId) throws Exception {
		DeleteProductPromoCode command = new DeleteProductPromoCode(productPromoCodeId);

		try {
			if (((ProductPromoCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
