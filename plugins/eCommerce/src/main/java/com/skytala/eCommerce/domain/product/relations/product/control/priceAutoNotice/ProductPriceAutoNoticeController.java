package com.skytala.eCommerce.domain.product.relations.product.control.priceAutoNotice;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.AddProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.DeleteProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice.UpdateProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAutoNotice.ProductPriceAutoNoticeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;
import com.skytala.eCommerce.domain.product.relations.product.query.priceAutoNotice.FindProductPriceAutoNoticesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceAutoNotices")
public class ProductPriceAutoNoticeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceAutoNoticeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceAutoNotice
	 * @return a List with the ProductPriceAutoNotices
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceAutoNotice>> findProductPriceAutoNoticesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceAutoNoticesBy query = new FindProductPriceAutoNoticesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceAutoNotice> productPriceAutoNotices =((ProductPriceAutoNoticeFound) Scheduler.execute(query).data()).getProductPriceAutoNotices();

		return ResponseEntity.ok().body(productPriceAutoNotices);

	}

	/**
	 * creates a new ProductPriceAutoNotice entry in the ofbiz database
	 * 
	 * @param productPriceAutoNoticeToBeAdded
	 *            the ProductPriceAutoNotice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceAutoNotice> createProductPriceAutoNotice(@RequestBody ProductPriceAutoNotice productPriceAutoNoticeToBeAdded) throws Exception {

		AddProductPriceAutoNotice command = new AddProductPriceAutoNotice(productPriceAutoNoticeToBeAdded);
		ProductPriceAutoNotice productPriceAutoNotice = ((ProductPriceAutoNoticeAdded) Scheduler.execute(command).data()).getAddedProductPriceAutoNotice();
		
		if (productPriceAutoNotice != null) 
			return successful(productPriceAutoNotice);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPriceAutoNotice with the specific Id
	 * 
	 * @param productPriceAutoNoticeToBeUpdated
	 *            the ProductPriceAutoNotice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceNoticeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPriceAutoNotice(@RequestBody ProductPriceAutoNotice productPriceAutoNoticeToBeUpdated,
			@PathVariable String productPriceNoticeId) throws Exception {

		productPriceAutoNoticeToBeUpdated.setProductPriceNoticeId(productPriceNoticeId);

		UpdateProductPriceAutoNotice command = new UpdateProductPriceAutoNotice(productPriceAutoNoticeToBeUpdated);

		try {
			if(((ProductPriceAutoNoticeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceAutoNoticeId}")
	public ResponseEntity<ProductPriceAutoNotice> findById(@PathVariable String productPriceAutoNoticeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceAutoNoticeId", productPriceAutoNoticeId);
		try {

			List<ProductPriceAutoNotice> foundProductPriceAutoNotice = findProductPriceAutoNoticesBy(requestParams).getBody();
			if(foundProductPriceAutoNotice.size()==1){				return successful(foundProductPriceAutoNotice.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceAutoNoticeId}")
	public ResponseEntity<String> deleteProductPriceAutoNoticeByIdUpdated(@PathVariable String productPriceAutoNoticeId) throws Exception {
		DeleteProductPriceAutoNotice command = new DeleteProductPriceAutoNotice(productPriceAutoNoticeId);

		try {
			if (((ProductPriceAutoNoticeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
