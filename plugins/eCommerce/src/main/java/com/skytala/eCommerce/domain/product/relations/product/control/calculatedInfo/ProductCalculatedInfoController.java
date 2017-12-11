package com.skytala.eCommerce.domain.product.relations.product.control.calculatedInfo;

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
import com.skytala.eCommerce.domain.product.relations.product.command.calculatedInfo.AddProductCalculatedInfo;
import com.skytala.eCommerce.domain.product.relations.product.command.calculatedInfo.DeleteProductCalculatedInfo;
import com.skytala.eCommerce.domain.product.relations.product.command.calculatedInfo.UpdateProductCalculatedInfo;
import com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo.ProductCalculatedInfoAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo.ProductCalculatedInfoDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo.ProductCalculatedInfoFound;
import com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo.ProductCalculatedInfoUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.calculatedInfo.ProductCalculatedInfoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.calculatedInfo.ProductCalculatedInfo;
import com.skytala.eCommerce.domain.product.relations.product.query.calculatedInfo.FindProductCalculatedInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productCalculatedInfos")
public class ProductCalculatedInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCalculatedInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCalculatedInfo
	 * @return a List with the ProductCalculatedInfos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductCalculatedInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCalculatedInfosBy query = new FindProductCalculatedInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCalculatedInfo> productCalculatedInfos =((ProductCalculatedInfoFound) Scheduler.execute(query).data()).getProductCalculatedInfos();

		if (productCalculatedInfos.size() == 1) {
			return ResponseEntity.ok().body(productCalculatedInfos.get(0));
		}

		return ResponseEntity.ok().body(productCalculatedInfos);

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
	public ResponseEntity<Object> createProductCalculatedInfo(HttpServletRequest request) throws Exception {

		ProductCalculatedInfo productCalculatedInfoToBeAdded = new ProductCalculatedInfo();
		try {
			productCalculatedInfoToBeAdded = ProductCalculatedInfoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCalculatedInfo(productCalculatedInfoToBeAdded);

	}

	/**
	 * creates a new ProductCalculatedInfo entry in the ofbiz database
	 * 
	 * @param productCalculatedInfoToBeAdded
	 *            the ProductCalculatedInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCalculatedInfo(@RequestBody ProductCalculatedInfo productCalculatedInfoToBeAdded) throws Exception {

		AddProductCalculatedInfo command = new AddProductCalculatedInfo(productCalculatedInfoToBeAdded);
		ProductCalculatedInfo productCalculatedInfo = ((ProductCalculatedInfoAdded) Scheduler.execute(command).data()).getAddedProductCalculatedInfo();
		
		if (productCalculatedInfo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCalculatedInfo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCalculatedInfo could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductCalculatedInfo(HttpServletRequest request) throws Exception {

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

		ProductCalculatedInfo productCalculatedInfoToBeUpdated = new ProductCalculatedInfo();

		try {
			productCalculatedInfoToBeUpdated = ProductCalculatedInfoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCalculatedInfo(productCalculatedInfoToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCalculatedInfo with the specific Id
	 * 
	 * @param productCalculatedInfoToBeUpdated
	 *            the ProductCalculatedInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCalculatedInfo(@RequestBody ProductCalculatedInfo productCalculatedInfoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCalculatedInfoToBeUpdated.setnull(null);

		UpdateProductCalculatedInfo command = new UpdateProductCalculatedInfo(productCalculatedInfoToBeUpdated);

		try {
			if(((ProductCalculatedInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productCalculatedInfoId}")
	public ResponseEntity<Object> findById(@PathVariable String productCalculatedInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCalculatedInfoId", productCalculatedInfoId);
		try {

			Object foundProductCalculatedInfo = findProductCalculatedInfosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCalculatedInfo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productCalculatedInfoId}")
	public ResponseEntity<Object> deleteProductCalculatedInfoByIdUpdated(@PathVariable String productCalculatedInfoId) throws Exception {
		DeleteProductCalculatedInfo command = new DeleteProductCalculatedInfo(productCalculatedInfoId);

		try {
			if (((ProductCalculatedInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCalculatedInfo could not be deleted");

	}

}
