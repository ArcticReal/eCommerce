package com.skytala.eCommerce.domain.product.relations.product.control.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.product.command.price.AddProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.command.price.DeleteProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.command.price.UpdateProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceFound;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.price.ProductPriceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.query.price.FindProductPricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productPrices")
public class ProductPriceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPrice
	 * @return a List with the ProductPrices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<ProductPrice>> findProductPricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPricesBy query = new FindProductPricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPrice> productPrices =((ProductPriceFound) Scheduler.execute(query).data()).getProductPrices();


		return ResponseEntity.ok().body(getNewestPrices(productPrices));

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ProductPrice> createProductPrice(HttpServletRequest request) throws Exception {

		ProductPrice productPriceToBeAdded = new ProductPrice();
		try {
			productPriceToBeAdded = ProductPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createProductPrice(productPriceToBeAdded);

	}

	/**
	 * creates a new ProductPrice entry in the ofbiz database
	 * 
	 * @param productPriceToBeAdded
	 *            the ProductPrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPrice> createProductPrice(@RequestBody ProductPrice productPriceToBeAdded) throws Exception {

		AddProductPrice command = new AddProductPrice(productPriceToBeAdded);
		ProductPrice productPrice = ((ProductPriceAdded) Scheduler.execute(command).data()).getAddedProductPrice();
		
		if (productPrice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPrice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
	}

	/**
	 * Updates the ProductPrice with the specific Id
	 * 
	 * @param productPriceToBeUpdated
	 *            the ProductPrice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPrice(@RequestBody ProductPrice productPriceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPriceToBeUpdated.setnull(null);

		UpdateProductPrice command = new UpdateProductPrice(productPriceToBeUpdated);

		try {
			if(((ProductPriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceId}")
	public ResponseEntity<ProductPrice> findById(@PathVariable String productPriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceId", productPriceId);
		try {

			List<ProductPrice> foundProductPrices = findProductPricesBy(requestParams).getBody();

			getNewestPrice(foundProductPrices);

			return ResponseEntity.status(HttpStatus.OK).body(foundProductPrices.get(0));
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceId}")
	public ResponseEntity<Object> deleteProductPriceByIdUpdated(@PathVariable String productPriceId) throws Exception {
		DeleteProductPrice command = new DeleteProductPrice(productPriceId);

		try {
			if (((ProductPriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPrice could not be deleted");

	}


	public ProductPrice getNewestPrice(List<ProductPrice> prices) throws RecordNotFoundException{
		ProductPrice newestPrice = null;
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		currentDate.setNanos(0);

		for (ProductPrice currentPrice : prices){
			if(newestPrice==null||currentPrice.getFromDate().after(newestPrice.getFromDate()))
				if(currentPrice.getThruDate()==null||currentPrice.getThruDate().after(currentDate))
					newestPrice = currentPrice;

		}

		if (newestPrice == null)
			throw new RecordNotFoundException(ProductPrice.class);

		return newestPrice;
	}

    public List<ProductPrice> getNewestPrices(List<ProductPrice> productPrices) {

		List<ProductPrice> pricesSameProduct = new LinkedList<>();
		List<ProductPrice> newestPrices = new LinkedList<>();
		while (productPrices.size()!=0){

			Iterator<ProductPrice> it = productPrices.iterator();
			while (it.hasNext()){

				ProductPrice price = it.next();
				if(pricesSameProduct.size()==0||pricesSameProduct.get(0).getProductId().equals(price.getProductId()))
					pricesSameProduct.add(price);



			}
			newestPrices.add(getNewestPrice(pricesSameProduct));
			productPrices.removeAll(pricesSameProduct);
			pricesSameProduct.clear();
		}

		return newestPrices;
    }
}
