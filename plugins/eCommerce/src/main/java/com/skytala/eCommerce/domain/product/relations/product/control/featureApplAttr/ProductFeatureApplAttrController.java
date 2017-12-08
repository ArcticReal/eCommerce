package com.skytala.eCommerce.domain.product.relations.product.control.featureApplAttr;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.AddProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.DeleteProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.UpdateProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplAttr.ProductFeatureApplAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplAttr.ProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.query.featureApplAttr.FindProductFeatureApplAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureApplAttrs")
public class ProductFeatureApplAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureApplAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureApplAttr
	 * @return a List with the ProductFeatureApplAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureApplAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureApplAttrsBy query = new FindProductFeatureApplAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureApplAttr> productFeatureApplAttrs =((ProductFeatureApplAttrFound) Scheduler.execute(query).data()).getProductFeatureApplAttrs();

		if (productFeatureApplAttrs.size() == 1) {
			return ResponseEntity.ok().body(productFeatureApplAttrs.get(0));
		}

		return ResponseEntity.ok().body(productFeatureApplAttrs);

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
	public ResponseEntity<Object> createProductFeatureApplAttr(HttpServletRequest request) throws Exception {

		ProductFeatureApplAttr productFeatureApplAttrToBeAdded = new ProductFeatureApplAttr();
		try {
			productFeatureApplAttrToBeAdded = ProductFeatureApplAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureApplAttr(productFeatureApplAttrToBeAdded);

	}

	/**
	 * creates a new ProductFeatureApplAttr entry in the ofbiz database
	 * 
	 * @param productFeatureApplAttrToBeAdded
	 *            the ProductFeatureApplAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureApplAttr(@RequestBody ProductFeatureApplAttr productFeatureApplAttrToBeAdded) throws Exception {

		AddProductFeatureApplAttr command = new AddProductFeatureApplAttr(productFeatureApplAttrToBeAdded);
		ProductFeatureApplAttr productFeatureApplAttr = ((ProductFeatureApplAttrAdded) Scheduler.execute(command).data()).getAddedProductFeatureApplAttr();
		
		if (productFeatureApplAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureApplAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureApplAttr could not be created.");
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
	public boolean updateProductFeatureApplAttr(HttpServletRequest request) throws Exception {

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

		ProductFeatureApplAttr productFeatureApplAttrToBeUpdated = new ProductFeatureApplAttr();

		try {
			productFeatureApplAttrToBeUpdated = ProductFeatureApplAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureApplAttr(productFeatureApplAttrToBeUpdated, productFeatureApplAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureApplAttr with the specific Id
	 * 
	 * @param productFeatureApplAttrToBeUpdated
	 *            the ProductFeatureApplAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureApplAttr(@RequestBody ProductFeatureApplAttr productFeatureApplAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		productFeatureApplAttrToBeUpdated.setAttrName(attrName);

		UpdateProductFeatureApplAttr command = new UpdateProductFeatureApplAttr(productFeatureApplAttrToBeUpdated);

		try {
			if(((ProductFeatureApplAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureApplAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureApplAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureApplAttrId", productFeatureApplAttrId);
		try {

			Object foundProductFeatureApplAttr = findProductFeatureApplAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureApplAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureApplAttrId}")
	public ResponseEntity<Object> deleteProductFeatureApplAttrByIdUpdated(@PathVariable String productFeatureApplAttrId) throws Exception {
		DeleteProductFeatureApplAttr command = new DeleteProductFeatureApplAttr(productFeatureApplAttrId);

		try {
			if (((ProductFeatureApplAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureApplAttr could not be deleted");

	}

}
