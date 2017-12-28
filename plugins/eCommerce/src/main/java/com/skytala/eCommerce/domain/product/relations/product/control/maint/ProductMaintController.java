package com.skytala.eCommerce.domain.product.relations.product.control.maint;

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
import com.skytala.eCommerce.domain.product.relations.product.command.maint.AddProductMaint;
import com.skytala.eCommerce.domain.product.relations.product.command.maint.DeleteProductMaint;
import com.skytala.eCommerce.domain.product.relations.product.command.maint.UpdateProductMaint;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintFound;
import com.skytala.eCommerce.domain.product.relations.product.event.maint.ProductMaintUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maint.ProductMaintMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;
import com.skytala.eCommerce.domain.product.relations.product.query.maint.FindProductMaintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productMaints")
public class ProductMaintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMaintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMaint
	 * @return a List with the ProductMaints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductMaint>> findProductMaintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMaintsBy query = new FindProductMaintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMaint> productMaints =((ProductMaintFound) Scheduler.execute(query).data()).getProductMaints();

		return ResponseEntity.ok().body(productMaints);

	}

	/**
	 * creates a new ProductMaint entry in the ofbiz database
	 * 
	 * @param productMaintToBeAdded
	 *            the ProductMaint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductMaint> createProductMaint(@RequestBody ProductMaint productMaintToBeAdded) throws Exception {

		AddProductMaint command = new AddProductMaint(productMaintToBeAdded);
		ProductMaint productMaint = ((ProductMaintAdded) Scheduler.execute(command).data()).getAddedProductMaint();
		
		if (productMaint != null) 
			return successful(productMaint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductMaint with the specific Id
	 * 
	 * @param productMaintToBeUpdated
	 *            the ProductMaint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMaintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductMaint(@RequestBody ProductMaint productMaintToBeUpdated,
			@PathVariable String productMaintSeqId) throws Exception {

		productMaintToBeUpdated.setProductMaintSeqId(productMaintSeqId);

		UpdateProductMaint command = new UpdateProductMaint(productMaintToBeUpdated);

		try {
			if(((ProductMaintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productMaintId}")
	public ResponseEntity<ProductMaint> findById(@PathVariable String productMaintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMaintId", productMaintId);
		try {

			List<ProductMaint> foundProductMaint = findProductMaintsBy(requestParams).getBody();
			if(foundProductMaint.size()==1){				return successful(foundProductMaint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productMaintId}")
	public ResponseEntity<String> deleteProductMaintByIdUpdated(@PathVariable String productMaintId) throws Exception {
		DeleteProductMaint command = new DeleteProductMaint(productMaintId);

		try {
			if (((ProductMaintDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
