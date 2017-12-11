package com.skytala.eCommerce.domain.product.relations.product.control.storeKeywordOvrd;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.AddProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.DeleteProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.UpdateProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeKeywordOvrd.ProductStoreKeywordOvrdMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.query.storeKeywordOvrd.FindProductStoreKeywordOvrdsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productStoreKeywordOvrds")
public class ProductStoreKeywordOvrdController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreKeywordOvrdController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreKeywordOvrd
	 * @return a List with the ProductStoreKeywordOvrds
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreKeywordOvrdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreKeywordOvrdsBy query = new FindProductStoreKeywordOvrdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreKeywordOvrd> productStoreKeywordOvrds =((ProductStoreKeywordOvrdFound) Scheduler.execute(query).data()).getProductStoreKeywordOvrds();

		if (productStoreKeywordOvrds.size() == 1) {
			return ResponseEntity.ok().body(productStoreKeywordOvrds.get(0));
		}

		return ResponseEntity.ok().body(productStoreKeywordOvrds);

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
	public ResponseEntity<Object> createProductStoreKeywordOvrd(HttpServletRequest request) throws Exception {

		ProductStoreKeywordOvrd productStoreKeywordOvrdToBeAdded = new ProductStoreKeywordOvrd();
		try {
			productStoreKeywordOvrdToBeAdded = ProductStoreKeywordOvrdMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreKeywordOvrd(productStoreKeywordOvrdToBeAdded);

	}

	/**
	 * creates a new ProductStoreKeywordOvrd entry in the ofbiz database
	 * 
	 * @param productStoreKeywordOvrdToBeAdded
	 *            the ProductStoreKeywordOvrd thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreKeywordOvrd(@RequestBody ProductStoreKeywordOvrd productStoreKeywordOvrdToBeAdded) throws Exception {

		AddProductStoreKeywordOvrd command = new AddProductStoreKeywordOvrd(productStoreKeywordOvrdToBeAdded);
		ProductStoreKeywordOvrd productStoreKeywordOvrd = ((ProductStoreKeywordOvrdAdded) Scheduler.execute(command).data()).getAddedProductStoreKeywordOvrd();
		
		if (productStoreKeywordOvrd != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreKeywordOvrd);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreKeywordOvrd could not be created.");
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
	public boolean updateProductStoreKeywordOvrd(HttpServletRequest request) throws Exception {

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

		ProductStoreKeywordOvrd productStoreKeywordOvrdToBeUpdated = new ProductStoreKeywordOvrd();

		try {
			productStoreKeywordOvrdToBeUpdated = ProductStoreKeywordOvrdMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreKeywordOvrd(productStoreKeywordOvrdToBeUpdated, productStoreKeywordOvrdToBeUpdated.getKeyword()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreKeywordOvrd with the specific Id
	 * 
	 * @param productStoreKeywordOvrdToBeUpdated
	 *            the ProductStoreKeywordOvrd thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{keyword}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreKeywordOvrd(@RequestBody ProductStoreKeywordOvrd productStoreKeywordOvrdToBeUpdated,
			@PathVariable String keyword) throws Exception {

		productStoreKeywordOvrdToBeUpdated.setKeyword(keyword);

		UpdateProductStoreKeywordOvrd command = new UpdateProductStoreKeywordOvrd(productStoreKeywordOvrdToBeUpdated);

		try {
			if(((ProductStoreKeywordOvrdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreKeywordOvrdId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreKeywordOvrdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreKeywordOvrdId", productStoreKeywordOvrdId);
		try {

			Object foundProductStoreKeywordOvrd = findProductStoreKeywordOvrdsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreKeywordOvrd);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreKeywordOvrdId}")
	public ResponseEntity<Object> deleteProductStoreKeywordOvrdByIdUpdated(@PathVariable String productStoreKeywordOvrdId) throws Exception {
		DeleteProductStoreKeywordOvrd command = new DeleteProductStoreKeywordOvrd(productStoreKeywordOvrdId);

		try {
			if (((ProductStoreKeywordOvrdDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreKeywordOvrd could not be deleted");

	}

}
