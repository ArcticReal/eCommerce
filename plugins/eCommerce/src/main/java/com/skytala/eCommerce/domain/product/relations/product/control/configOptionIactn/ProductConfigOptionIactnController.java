package com.skytala.eCommerce.domain.product.relations.product.control.configOptionIactn;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.AddProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.DeleteProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.UpdateProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionIactn.ProductConfigOptionIactnMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn.ProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.query.configOptionIactn.FindProductConfigOptionIactnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productConfigOptionIactns")
public class ProductConfigOptionIactnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigOptionIactnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigOptionIactn
	 * @return a List with the ProductConfigOptionIactns
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductConfigOptionIactnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigOptionIactnsBy query = new FindProductConfigOptionIactnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigOptionIactn> productConfigOptionIactns =((ProductConfigOptionIactnFound) Scheduler.execute(query).data()).getProductConfigOptionIactns();

		if (productConfigOptionIactns.size() == 1) {
			return ResponseEntity.ok().body(productConfigOptionIactns.get(0));
		}

		return ResponseEntity.ok().body(productConfigOptionIactns);

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
	public ResponseEntity<Object> createProductConfigOptionIactn(HttpServletRequest request) throws Exception {

		ProductConfigOptionIactn productConfigOptionIactnToBeAdded = new ProductConfigOptionIactn();
		try {
			productConfigOptionIactnToBeAdded = ProductConfigOptionIactnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductConfigOptionIactn(productConfigOptionIactnToBeAdded);

	}

	/**
	 * creates a new ProductConfigOptionIactn entry in the ofbiz database
	 * 
	 * @param productConfigOptionIactnToBeAdded
	 *            the ProductConfigOptionIactn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductConfigOptionIactn(@RequestBody ProductConfigOptionIactn productConfigOptionIactnToBeAdded) throws Exception {

		AddProductConfigOptionIactn command = new AddProductConfigOptionIactn(productConfigOptionIactnToBeAdded);
		ProductConfigOptionIactn productConfigOptionIactn = ((ProductConfigOptionIactnAdded) Scheduler.execute(command).data()).getAddedProductConfigOptionIactn();
		
		if (productConfigOptionIactn != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfigOptionIactn);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfigOptionIactn could not be created.");
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
	public boolean updateProductConfigOptionIactn(HttpServletRequest request) throws Exception {

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

		ProductConfigOptionIactn productConfigOptionIactnToBeUpdated = new ProductConfigOptionIactn();

		try {
			productConfigOptionIactnToBeUpdated = ProductConfigOptionIactnMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfigOptionIactn(productConfigOptionIactnToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductConfigOptionIactn with the specific Id
	 * 
	 * @param productConfigOptionIactnToBeUpdated
	 *            the ProductConfigOptionIactn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductConfigOptionIactn(@RequestBody ProductConfigOptionIactn productConfigOptionIactnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigOptionIactnToBeUpdated.setnull(null);

		UpdateProductConfigOptionIactn command = new UpdateProductConfigOptionIactn(productConfigOptionIactnToBeUpdated);

		try {
			if(((ProductConfigOptionIactnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productConfigOptionIactnId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigOptionIactnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigOptionIactnId", productConfigOptionIactnId);
		try {

			Object foundProductConfigOptionIactn = findProductConfigOptionIactnsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfigOptionIactn);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productConfigOptionIactnId}")
	public ResponseEntity<Object> deleteProductConfigOptionIactnByIdUpdated(@PathVariable String productConfigOptionIactnId) throws Exception {
		DeleteProductConfigOptionIactn command = new DeleteProductConfigOptionIactn(productConfigOptionIactnId);

		try {
			if (((ProductConfigOptionIactnDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfigOptionIactn could not be deleted");

	}

}
