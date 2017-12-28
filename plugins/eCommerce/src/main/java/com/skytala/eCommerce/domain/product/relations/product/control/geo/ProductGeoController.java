package com.skytala.eCommerce.domain.product.relations.product.control.geo;

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
import com.skytala.eCommerce.domain.product.relations.product.command.geo.AddProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.command.geo.DeleteProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.command.geo.UpdateProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoFound;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.geo.ProductGeoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.query.geo.FindProductGeosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productGeos")
public class ProductGeoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductGeoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductGeo
	 * @return a List with the ProductGeos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductGeo>> findProductGeosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGeosBy query = new FindProductGeosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGeo> productGeos =((ProductGeoFound) Scheduler.execute(query).data()).getProductGeos();

		return ResponseEntity.ok().body(productGeos);

	}

	/**
	 * creates a new ProductGeo entry in the ofbiz database
	 * 
	 * @param productGeoToBeAdded
	 *            the ProductGeo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductGeo> createProductGeo(@RequestBody ProductGeo productGeoToBeAdded) throws Exception {

		AddProductGeo command = new AddProductGeo(productGeoToBeAdded);
		ProductGeo productGeo = ((ProductGeoAdded) Scheduler.execute(command).data()).getAddedProductGeo();
		
		if (productGeo != null) 
			return successful(productGeo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductGeo with the specific Id
	 * 
	 * @param productGeoToBeUpdated
	 *            the ProductGeo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductGeo(@RequestBody ProductGeo productGeoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productGeoToBeUpdated.setnull(null);

		UpdateProductGeo command = new UpdateProductGeo(productGeoToBeUpdated);

		try {
			if(((ProductGeoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productGeoId}")
	public ResponseEntity<ProductGeo> findById(@PathVariable String productGeoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGeoId", productGeoId);
		try {

			List<ProductGeo> foundProductGeo = findProductGeosBy(requestParams).getBody();
			if(foundProductGeo.size()==1){				return successful(foundProductGeo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productGeoId}")
	public ResponseEntity<String> deleteProductGeoByIdUpdated(@PathVariable String productGeoId) throws Exception {
		DeleteProductGeo command = new DeleteProductGeo(productGeoId);

		try {
			if (((ProductGeoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
