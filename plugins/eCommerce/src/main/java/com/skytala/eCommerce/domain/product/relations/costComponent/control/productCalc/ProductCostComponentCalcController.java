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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/productCostComponentCalcs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCostComponentCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCostComponentCalcsBy query = new FindProductCostComponentCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCostComponentCalc> productCostComponentCalcs =((ProductCostComponentCalcFound) Scheduler.execute(query).data()).getProductCostComponentCalcs();

		if (productCostComponentCalcs.size() == 1) {
			return ResponseEntity.ok().body(productCostComponentCalcs.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductCostComponentCalc(HttpServletRequest request) throws Exception {

		ProductCostComponentCalc productCostComponentCalcToBeAdded = new ProductCostComponentCalc();
		try {
			productCostComponentCalcToBeAdded = ProductCostComponentCalcMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductCostComponentCalc(@RequestBody ProductCostComponentCalc productCostComponentCalcToBeAdded) throws Exception {

		AddProductCostComponentCalc command = new AddProductCostComponentCalc(productCostComponentCalcToBeAdded);
		ProductCostComponentCalc productCostComponentCalc = ((ProductCostComponentCalcAdded) Scheduler.execute(command).data()).getAddedProductCostComponentCalc();
		
		if (productCostComponentCalc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCostComponentCalc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCostComponentCalc could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductCostComponentCalc(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductCostComponentCalc productCostComponentCalcToBeUpdated = new ProductCostComponentCalc();

		try {
			productCostComponentCalcToBeUpdated = ProductCostComponentCalcMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCostComponentCalc(productCostComponentCalcToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductCostComponentCalc(@RequestBody ProductCostComponentCalc productCostComponentCalcToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCostComponentCalcToBeUpdated.setnull(null);

		UpdateProductCostComponentCalc command = new UpdateProductCostComponentCalc(productCostComponentCalcToBeUpdated);

		try {
			if(((ProductCostComponentCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCostComponentCalcId}")
	public ResponseEntity<Object> findById(@PathVariable String productCostComponentCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCostComponentCalcId", productCostComponentCalcId);
		try {

			Object foundProductCostComponentCalc = findProductCostComponentCalcsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCostComponentCalc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCostComponentCalcId}")
	public ResponseEntity<Object> deleteProductCostComponentCalcByIdUpdated(@PathVariable String productCostComponentCalcId) throws Exception {
		DeleteProductCostComponentCalc command = new DeleteProductCostComponentCalc(productCostComponentCalcId);

		try {
			if (((ProductCostComponentCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCostComponentCalc could not be deleted");

	}

}
