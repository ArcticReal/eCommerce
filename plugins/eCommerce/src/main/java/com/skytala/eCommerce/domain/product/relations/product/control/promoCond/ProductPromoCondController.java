package com.skytala.eCommerce.domain.product.relations.product.control.promoCond;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.AddProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.DeleteProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCond.UpdateProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCond.ProductPromoCondMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCond.FindProductPromoCondsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromoConds")
public class ProductPromoCondController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCondController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCond
	 * @return a List with the ProductPromoConds
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromoCondsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCondsBy query = new FindProductPromoCondsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCond> productPromoConds =((ProductPromoCondFound) Scheduler.execute(query).data()).getProductPromoConds();

		if (productPromoConds.size() == 1) {
			return ResponseEntity.ok().body(productPromoConds.get(0));
		}

		return ResponseEntity.ok().body(productPromoConds);

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
	public ResponseEntity<Object> createProductPromoCond(HttpServletRequest request) throws Exception {

		ProductPromoCond productPromoCondToBeAdded = new ProductPromoCond();
		try {
			productPromoCondToBeAdded = ProductPromoCondMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoCond(productPromoCondToBeAdded);

	}

	/**
	 * creates a new ProductPromoCond entry in the ofbiz database
	 * 
	 * @param productPromoCondToBeAdded
	 *            the ProductPromoCond thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoCond(@RequestBody ProductPromoCond productPromoCondToBeAdded) throws Exception {

		AddProductPromoCond command = new AddProductPromoCond(productPromoCondToBeAdded);
		ProductPromoCond productPromoCond = ((ProductPromoCondAdded) Scheduler.execute(command).data()).getAddedProductPromoCond();
		
		if (productPromoCond != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoCond);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoCond could not be created.");
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
	public boolean updateProductPromoCond(HttpServletRequest request) throws Exception {

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

		ProductPromoCond productPromoCondToBeUpdated = new ProductPromoCond();

		try {
			productPromoCondToBeUpdated = ProductPromoCondMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoCond(productPromoCondToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoCond with the specific Id
	 * 
	 * @param productPromoCondToBeUpdated
	 *            the ProductPromoCond thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoCond(@RequestBody ProductPromoCond productPromoCondToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoCondToBeUpdated.setnull(null);

		UpdateProductPromoCond command = new UpdateProductPromoCond(productPromoCondToBeUpdated);

		try {
			if(((ProductPromoCondUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoCondId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoCondId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCondId", productPromoCondId);
		try {

			Object foundProductPromoCond = findProductPromoCondsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoCond);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoCondId}")
	public ResponseEntity<Object> deleteProductPromoCondByIdUpdated(@PathVariable String productPromoCondId) throws Exception {
		DeleteProductPromoCond command = new DeleteProductPromoCond(productPromoCondId);

		try {
			if (((ProductPromoCondDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoCond could not be deleted");

	}

}
