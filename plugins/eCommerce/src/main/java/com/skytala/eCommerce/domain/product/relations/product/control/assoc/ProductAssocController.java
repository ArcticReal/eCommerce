package com.skytala.eCommerce.domain.product.relations.product.control.assoc;

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
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.AddProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.DeleteProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.UpdateProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocFound;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.assoc.ProductAssocMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.assoc.ProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.query.assoc.FindProductAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productAssocs")
public class ProductAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAssoc
	 * @return a List with the ProductAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductAssoc>> findProductAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAssocsBy query = new FindProductAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAssoc> productAssocs =((ProductAssocFound) Scheduler.execute(query).data()).getProductAssocs();

		return ResponseEntity.ok().body(productAssocs);

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
	public ResponseEntity<ProductAssoc> createProductAssoc(HttpServletRequest request) throws Exception {

		ProductAssoc productAssocToBeAdded = new ProductAssoc();
		try {
			productAssocToBeAdded = ProductAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductAssoc(productAssocToBeAdded);

	}

	/**
	 * creates a new ProductAssoc entry in the ofbiz database
	 * 
	 * @param productAssocToBeAdded
	 *            the ProductAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAssoc> createProductAssoc(@RequestBody ProductAssoc productAssocToBeAdded) throws Exception {

		AddProductAssoc command = new AddProductAssoc(productAssocToBeAdded);
		ProductAssoc productAssoc = ((ProductAssocAdded) Scheduler.execute(command).data()).getAddedProductAssoc();
		
		if (productAssoc != null) 
			return successful(productAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductAssoc with the specific Id
	 * 
	 * @param productAssocToBeUpdated
	 *            the ProductAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductAssoc(@RequestBody ProductAssoc productAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productAssocToBeUpdated.setnull(null);

		UpdateProductAssoc command = new UpdateProductAssoc(productAssocToBeUpdated);

		try {
			if(((ProductAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productAssocId}")
	public ResponseEntity<ProductAssoc> findById(@PathVariable String productAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAssocId", productAssocId);
		try {

			List<ProductAssoc> foundProductAssoc = findProductAssocsBy(requestParams).getBody();
			if(foundProductAssoc.size()==1){				return successful(foundProductAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productAssocId}")
	public ResponseEntity<String> deleteProductAssocByIdUpdated(@PathVariable String productAssocId) throws Exception {
		DeleteProductAssoc command = new DeleteProductAssoc(productAssocId);

		try {
			if (((ProductAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
