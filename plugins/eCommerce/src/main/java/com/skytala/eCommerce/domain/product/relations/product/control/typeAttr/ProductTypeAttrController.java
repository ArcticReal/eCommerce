package com.skytala.eCommerce.domain.product.relations.product.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.AddProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.DeleteProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.UpdateProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.typeAttr.ProductTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.query.typeAttr.FindProductTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productTypeAttrs")
public class ProductTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductTypeAttr
	 * @return a List with the ProductTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductTypeAttr>> findProductTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductTypeAttrsBy query = new FindProductTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductTypeAttr> productTypeAttrs =((ProductTypeAttrFound) Scheduler.execute(query).data()).getProductTypeAttrs();

		return ResponseEntity.ok().body(productTypeAttrs);

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
	public ResponseEntity<ProductTypeAttr> createProductTypeAttr(HttpServletRequest request) throws Exception {

		ProductTypeAttr productTypeAttrToBeAdded = new ProductTypeAttr();
		try {
			productTypeAttrToBeAdded = ProductTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductTypeAttr(productTypeAttrToBeAdded);

	}

	/**
	 * creates a new ProductTypeAttr entry in the ofbiz database
	 * 
	 * @param productTypeAttrToBeAdded
	 *            the ProductTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductTypeAttr> createProductTypeAttr(@RequestBody ProductTypeAttr productTypeAttrToBeAdded) throws Exception {

		AddProductTypeAttr command = new AddProductTypeAttr(productTypeAttrToBeAdded);
		ProductTypeAttr productTypeAttr = ((ProductTypeAttrAdded) Scheduler.execute(command).data()).getAddedProductTypeAttr();
		
		if (productTypeAttr != null) 
			return successful(productTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductTypeAttr with the specific Id
	 * 
	 * @param productTypeAttrToBeUpdated
	 *            the ProductTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductTypeAttr(@RequestBody ProductTypeAttr productTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		productTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateProductTypeAttr command = new UpdateProductTypeAttr(productTypeAttrToBeUpdated);

		try {
			if(((ProductTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productTypeAttrId}")
	public ResponseEntity<ProductTypeAttr> findById(@PathVariable String productTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productTypeAttrId", productTypeAttrId);
		try {

			List<ProductTypeAttr> foundProductTypeAttr = findProductTypeAttrsBy(requestParams).getBody();
			if(foundProductTypeAttr.size()==1){				return successful(foundProductTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productTypeAttrId}")
	public ResponseEntity<String> deleteProductTypeAttrByIdUpdated(@PathVariable String productTypeAttrId) throws Exception {
		DeleteProductTypeAttr command = new DeleteProductTypeAttr(productTypeAttrId);

		try {
			if (((ProductTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
