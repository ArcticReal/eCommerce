package com.skytala.eCommerce.domain.product.relations.product.control.storeShipmentMeth;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.AddProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.DeleteProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.UpdateProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeShipmentMeth.ProductStoreShipmentMethMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.query.storeShipmentMeth.FindProductStoreShipmentMethsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreShipmentMeths")
public class ProductStoreShipmentMethController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreShipmentMethController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreShipmentMeth
	 * @return a List with the ProductStoreShipmentMeths
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreShipmentMeth>> findProductStoreShipmentMethsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreShipmentMethsBy query = new FindProductStoreShipmentMethsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreShipmentMeth> productStoreShipmentMeths =((ProductStoreShipmentMethFound) Scheduler.execute(query).data()).getProductStoreShipmentMeths();

		return ResponseEntity.ok().body(productStoreShipmentMeths);

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
	public ResponseEntity<ProductStoreShipmentMeth> createProductStoreShipmentMeth(HttpServletRequest request) throws Exception {

		ProductStoreShipmentMeth productStoreShipmentMethToBeAdded = new ProductStoreShipmentMeth();
		try {
			productStoreShipmentMethToBeAdded = ProductStoreShipmentMethMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreShipmentMeth(productStoreShipmentMethToBeAdded);

	}

	/**
	 * creates a new ProductStoreShipmentMeth entry in the ofbiz database
	 * 
	 * @param productStoreShipmentMethToBeAdded
	 *            the ProductStoreShipmentMeth thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreShipmentMeth> createProductStoreShipmentMeth(@RequestBody ProductStoreShipmentMeth productStoreShipmentMethToBeAdded) throws Exception {

		AddProductStoreShipmentMeth command = new AddProductStoreShipmentMeth(productStoreShipmentMethToBeAdded);
		ProductStoreShipmentMeth productStoreShipmentMeth = ((ProductStoreShipmentMethAdded) Scheduler.execute(command).data()).getAddedProductStoreShipmentMeth();
		
		if (productStoreShipmentMeth != null) 
			return successful(productStoreShipmentMeth);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreShipmentMeth with the specific Id
	 * 
	 * @param productStoreShipmentMethToBeUpdated
	 *            the ProductStoreShipmentMeth thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreShipMethId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreShipmentMeth(@RequestBody ProductStoreShipmentMeth productStoreShipmentMethToBeUpdated,
			@PathVariable String productStoreShipMethId) throws Exception {

		productStoreShipmentMethToBeUpdated.setProductStoreShipMethId(productStoreShipMethId);

		UpdateProductStoreShipmentMeth command = new UpdateProductStoreShipmentMeth(productStoreShipmentMethToBeUpdated);

		try {
			if(((ProductStoreShipmentMethUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreShipmentMethId}")
	public ResponseEntity<ProductStoreShipmentMeth> findById(@PathVariable String productStoreShipmentMethId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreShipmentMethId", productStoreShipmentMethId);
		try {

			List<ProductStoreShipmentMeth> foundProductStoreShipmentMeth = findProductStoreShipmentMethsBy(requestParams).getBody();
			if(foundProductStoreShipmentMeth.size()==1){				return successful(foundProductStoreShipmentMeth.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreShipmentMethId}")
	public ResponseEntity<String> deleteProductStoreShipmentMethByIdUpdated(@PathVariable String productStoreShipmentMethId) throws Exception {
		DeleteProductStoreShipmentMeth command = new DeleteProductStoreShipmentMeth(productStoreShipmentMethId);

		try {
			if (((ProductStoreShipmentMethDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
