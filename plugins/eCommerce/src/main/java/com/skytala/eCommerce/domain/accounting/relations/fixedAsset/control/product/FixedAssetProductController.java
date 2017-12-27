package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.product;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.product.AddFixedAssetProduct;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.product.DeleteFixedAssetProduct;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.product.UpdateFixedAssetProduct;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product.FixedAssetProductUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.product.FixedAssetProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product.FixedAssetProduct;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.product.FindFixedAssetProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetProducts")
public class FixedAssetProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetProduct
	 * @return a List with the FixedAssetProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetProduct>> findFixedAssetProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetProductsBy query = new FindFixedAssetProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetProduct> fixedAssetProducts =((FixedAssetProductFound) Scheduler.execute(query).data()).getFixedAssetProducts();

		return ResponseEntity.ok().body(fixedAssetProducts);

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
	public ResponseEntity<FixedAssetProduct> createFixedAssetProduct(HttpServletRequest request) throws Exception {

		FixedAssetProduct fixedAssetProductToBeAdded = new FixedAssetProduct();
		try {
			fixedAssetProductToBeAdded = FixedAssetProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFixedAssetProduct(fixedAssetProductToBeAdded);

	}

	/**
	 * creates a new FixedAssetProduct entry in the ofbiz database
	 * 
	 * @param fixedAssetProductToBeAdded
	 *            the FixedAssetProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetProduct> createFixedAssetProduct(@RequestBody FixedAssetProduct fixedAssetProductToBeAdded) throws Exception {

		AddFixedAssetProduct command = new AddFixedAssetProduct(fixedAssetProductToBeAdded);
		FixedAssetProduct fixedAssetProduct = ((FixedAssetProductAdded) Scheduler.execute(command).data()).getAddedFixedAssetProduct();
		
		if (fixedAssetProduct != null) 
			return successful(fixedAssetProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetProduct with the specific Id
	 * 
	 * @param fixedAssetProductToBeUpdated
	 *            the FixedAssetProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetProduct(@RequestBody FixedAssetProduct fixedAssetProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetProductToBeUpdated.setnull(null);

		UpdateFixedAssetProduct command = new UpdateFixedAssetProduct(fixedAssetProductToBeUpdated);

		try {
			if(((FixedAssetProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetProductId}")
	public ResponseEntity<FixedAssetProduct> findById(@PathVariable String fixedAssetProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetProductId", fixedAssetProductId);
		try {

			List<FixedAssetProduct> foundFixedAssetProduct = findFixedAssetProductsBy(requestParams).getBody();
			if(foundFixedAssetProduct.size()==1){				return successful(foundFixedAssetProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetProductId}")
	public ResponseEntity<String> deleteFixedAssetProductByIdUpdated(@PathVariable String fixedAssetProductId) throws Exception {
		DeleteFixedAssetProduct command = new DeleteFixedAssetProduct(fixedAssetProductId);

		try {
			if (((FixedAssetProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
