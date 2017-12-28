package com.skytala.eCommerce.domain.product.relations.product.control.storeCatalog;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeCatalog.AddProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.product.command.storeCatalog.DeleteProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.product.command.storeCatalog.UpdateProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeCatalog.ProductStoreCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.product.query.storeCatalog.FindProductStoreCatalogsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreCatalogs")
public class ProductStoreCatalogController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreCatalogController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreCatalog
	 * @return a List with the ProductStoreCatalogs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreCatalog>> findProductStoreCatalogsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreCatalogsBy query = new FindProductStoreCatalogsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreCatalog> productStoreCatalogs =((ProductStoreCatalogFound) Scheduler.execute(query).data()).getProductStoreCatalogs();

		return ResponseEntity.ok().body(productStoreCatalogs);

	}

	/**
	 * creates a new ProductStoreCatalog entry in the ofbiz database
	 * 
	 * @param productStoreCatalogToBeAdded
	 *            the ProductStoreCatalog thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreCatalog> createProductStoreCatalog(@RequestBody ProductStoreCatalog productStoreCatalogToBeAdded) throws Exception {

		AddProductStoreCatalog command = new AddProductStoreCatalog(productStoreCatalogToBeAdded);
		ProductStoreCatalog productStoreCatalog = ((ProductStoreCatalogAdded) Scheduler.execute(command).data()).getAddedProductStoreCatalog();
		
		if (productStoreCatalog != null) 
			return successful(productStoreCatalog);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreCatalog with the specific Id
	 * 
	 * @param productStoreCatalogToBeUpdated
	 *            the ProductStoreCatalog thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreCatalog(@RequestBody ProductStoreCatalog productStoreCatalogToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreCatalogToBeUpdated.setnull(null);

		UpdateProductStoreCatalog command = new UpdateProductStoreCatalog(productStoreCatalogToBeUpdated);

		try {
			if(((ProductStoreCatalogUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreCatalogId}")
	public ResponseEntity<ProductStoreCatalog> findById(@PathVariable String productStoreCatalogId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreCatalogId", productStoreCatalogId);
		try {

			List<ProductStoreCatalog> foundProductStoreCatalog = findProductStoreCatalogsBy(requestParams).getBody();
			if(foundProductStoreCatalog.size()==1){				return successful(foundProductStoreCatalog.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreCatalogId}")
	public ResponseEntity<String> deleteProductStoreCatalogByIdUpdated(@PathVariable String productStoreCatalogId) throws Exception {
		DeleteProductStoreCatalog command = new DeleteProductStoreCatalog(productStoreCatalogId);

		try {
			if (((ProductStoreCatalogDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
