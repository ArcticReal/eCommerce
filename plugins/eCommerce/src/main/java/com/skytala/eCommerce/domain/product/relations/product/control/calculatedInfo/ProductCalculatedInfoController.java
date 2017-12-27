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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductCalculatedInfo>> findProductCalculatedInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCalculatedInfosBy query = new FindProductCalculatedInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCalculatedInfo> productCalculatedInfos =((ProductCalculatedInfoFound) Scheduler.execute(query).data()).getProductCalculatedInfos();

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
	public ResponseEntity<ProductCalculatedInfo> createProductCalculatedInfo(HttpServletRequest request) throws Exception {

		ProductCalculatedInfo productCalculatedInfoToBeAdded = new ProductCalculatedInfo();
		try {
			productCalculatedInfoToBeAdded = ProductCalculatedInfoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductCalculatedInfo> createProductCalculatedInfo(@RequestBody ProductCalculatedInfo productCalculatedInfoToBeAdded) throws Exception {

		AddProductCalculatedInfo command = new AddProductCalculatedInfo(productCalculatedInfoToBeAdded);
		ProductCalculatedInfo productCalculatedInfo = ((ProductCalculatedInfoAdded) Scheduler.execute(command).data()).getAddedProductCalculatedInfo();
		
		if (productCalculatedInfo != null) 
			return successful(productCalculatedInfo);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductCalculatedInfo(@RequestBody ProductCalculatedInfo productCalculatedInfoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCalculatedInfoToBeUpdated.setnull(null);

		UpdateProductCalculatedInfo command = new UpdateProductCalculatedInfo(productCalculatedInfoToBeUpdated);

		try {
			if(((ProductCalculatedInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCalculatedInfoId}")
	public ResponseEntity<ProductCalculatedInfo> findById(@PathVariable String productCalculatedInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCalculatedInfoId", productCalculatedInfoId);
		try {

			List<ProductCalculatedInfo> foundProductCalculatedInfo = findProductCalculatedInfosBy(requestParams).getBody();
			if(foundProductCalculatedInfo.size()==1){				return successful(foundProductCalculatedInfo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCalculatedInfoId}")
	public ResponseEntity<String> deleteProductCalculatedInfoByIdUpdated(@PathVariable String productCalculatedInfoId) throws Exception {
		DeleteProductCalculatedInfo command = new DeleteProductCalculatedInfo(productCalculatedInfoId);

		try {
			if (((ProductCalculatedInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
