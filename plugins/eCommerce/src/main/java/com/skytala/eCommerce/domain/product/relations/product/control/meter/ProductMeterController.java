package com.skytala.eCommerce.domain.product.relations.product.control.meter;

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
import com.skytala.eCommerce.domain.product.relations.product.command.meter.AddProductMeter;
import com.skytala.eCommerce.domain.product.relations.product.command.meter.DeleteProductMeter;
import com.skytala.eCommerce.domain.product.relations.product.command.meter.UpdateProductMeter;
import com.skytala.eCommerce.domain.product.relations.product.event.meter.ProductMeterAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.meter.ProductMeterDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.meter.ProductMeterFound;
import com.skytala.eCommerce.domain.product.relations.product.event.meter.ProductMeterUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.meter.ProductMeterMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.meter.ProductMeter;
import com.skytala.eCommerce.domain.product.relations.product.query.meter.FindProductMetersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productMeters")
public class ProductMeterController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMeterController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMeter
	 * @return a List with the ProductMeters
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductMeter>> findProductMetersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMetersBy query = new FindProductMetersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMeter> productMeters =((ProductMeterFound) Scheduler.execute(query).data()).getProductMeters();

		return ResponseEntity.ok().body(productMeters);

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
	public ResponseEntity<ProductMeter> createProductMeter(HttpServletRequest request) throws Exception {

		ProductMeter productMeterToBeAdded = new ProductMeter();
		try {
			productMeterToBeAdded = ProductMeterMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductMeter(productMeterToBeAdded);

	}

	/**
	 * creates a new ProductMeter entry in the ofbiz database
	 * 
	 * @param productMeterToBeAdded
	 *            the ProductMeter thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductMeter> createProductMeter(@RequestBody ProductMeter productMeterToBeAdded) throws Exception {

		AddProductMeter command = new AddProductMeter(productMeterToBeAdded);
		ProductMeter productMeter = ((ProductMeterAdded) Scheduler.execute(command).data()).getAddedProductMeter();
		
		if (productMeter != null) 
			return successful(productMeter);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductMeter with the specific Id
	 * 
	 * @param productMeterToBeUpdated
	 *            the ProductMeter thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductMeter(@RequestBody ProductMeter productMeterToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productMeterToBeUpdated.setnull(null);

		UpdateProductMeter command = new UpdateProductMeter(productMeterToBeUpdated);

		try {
			if(((ProductMeterUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productMeterId}")
	public ResponseEntity<ProductMeter> findById(@PathVariable String productMeterId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMeterId", productMeterId);
		try {

			List<ProductMeter> foundProductMeter = findProductMetersBy(requestParams).getBody();
			if(foundProductMeter.size()==1){				return successful(foundProductMeter.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productMeterId}")
	public ResponseEntity<String> deleteProductMeterByIdUpdated(@PathVariable String productMeterId) throws Exception {
		DeleteProductMeter command = new DeleteProductMeter(productMeterId);

		try {
			if (((ProductMeterDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
