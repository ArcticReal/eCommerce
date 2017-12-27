package com.skytala.eCommerce.domain.product.relations.costComponent.control.productCalc;

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
import com.skytala.eCommerce.domain.product.relations.costComponent.command.productCalc.AddProductCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.productCalc.DeleteProductCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.command.productCalc.UpdateProductCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcDeleted;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcUpdated;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.productCalc.ProductCostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;
import com.skytala.eCommerce.domain.product.relations.costComponent.query.productCalc.FindProductCostComponentCalcsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/costComponent/productCostComponentCalcs")
public class ProductCostComponentCalcController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCostComponentCalcController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCostComponentCalc
	 * @return a List with the ProductCostComponentCalcs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCostComponentCalc>> findProductCostComponentCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCostComponentCalcsBy query = new FindProductCostComponentCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCostComponentCalc> productCostComponentCalcs =((ProductCostComponentCalcFound) Scheduler.execute(query).data()).getProductCostComponentCalcs();

		return ResponseEntity.ok().body(productCostComponentCalcs);

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
	public ResponseEntity<ProductCostComponentCalc> createProductCostComponentCalc(HttpServletRequest request) throws Exception {

		ProductCostComponentCalc productCostComponentCalcToBeAdded = new ProductCostComponentCalc();
		try {
			productCostComponentCalcToBeAdded = ProductCostComponentCalcMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductCostComponentCalc(productCostComponentCalcToBeAdded);

	}

	/**
	 * creates a new ProductCostComponentCalc entry in the ofbiz database
	 * 
	 * @param productCostComponentCalcToBeAdded
	 *            the ProductCostComponentCalc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCostComponentCalc> createProductCostComponentCalc(@RequestBody ProductCostComponentCalc productCostComponentCalcToBeAdded) throws Exception {

		AddProductCostComponentCalc command = new AddProductCostComponentCalc(productCostComponentCalcToBeAdded);
		ProductCostComponentCalc productCostComponentCalc = ((ProductCostComponentCalcAdded) Scheduler.execute(command).data()).getAddedProductCostComponentCalc();
		
		if (productCostComponentCalc != null) 
			return successful(productCostComponentCalc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCostComponentCalc with the specific Id
	 * 
	 * @param productCostComponentCalcToBeUpdated
	 *            the ProductCostComponentCalc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCostComponentCalc(@RequestBody ProductCostComponentCalc productCostComponentCalcToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCostComponentCalcToBeUpdated.setnull(null);

		UpdateProductCostComponentCalc command = new UpdateProductCostComponentCalc(productCostComponentCalcToBeUpdated);

		try {
			if(((ProductCostComponentCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCostComponentCalcId}")
	public ResponseEntity<ProductCostComponentCalc> findById(@PathVariable String productCostComponentCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCostComponentCalcId", productCostComponentCalcId);
		try {

			List<ProductCostComponentCalc> foundProductCostComponentCalc = findProductCostComponentCalcsBy(requestParams).getBody();
			if(foundProductCostComponentCalc.size()==1){				return successful(foundProductCostComponentCalc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCostComponentCalcId}")
	public ResponseEntity<String> deleteProductCostComponentCalcByIdUpdated(@PathVariable String productCostComponentCalcId) throws Exception {
		DeleteProductCostComponentCalc command = new DeleteProductCostComponentCalc(productCostComponentCalcId);

		try {
			if (((ProductCostComponentCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
