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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/fixedAssetProducts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFixedAssetProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetProductsBy query = new FindFixedAssetProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetProduct> fixedAssetProducts =((FixedAssetProductFound) Scheduler.execute(query).data()).getFixedAssetProducts();

		if (fixedAssetProducts.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetProducts.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createFixedAssetProduct(HttpServletRequest request) throws Exception {

		FixedAssetProduct fixedAssetProductToBeAdded = new FixedAssetProduct();
		try {
			fixedAssetProductToBeAdded = FixedAssetProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createFixedAssetProduct(@RequestBody FixedAssetProduct fixedAssetProductToBeAdded) throws Exception {

		AddFixedAssetProduct command = new AddFixedAssetProduct(fixedAssetProductToBeAdded);
		FixedAssetProduct fixedAssetProduct = ((FixedAssetProductAdded) Scheduler.execute(command).data()).getAddedFixedAssetProduct();
		
		if (fixedAssetProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetProduct could not be created.");
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
	public boolean updateFixedAssetProduct(HttpServletRequest request) throws Exception {

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

		FixedAssetProduct fixedAssetProductToBeUpdated = new FixedAssetProduct();

		try {
			fixedAssetProductToBeUpdated = FixedAssetProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetProduct(fixedAssetProductToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFixedAssetProduct(@RequestBody FixedAssetProduct fixedAssetProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetProductToBeUpdated.setnull(null);

		UpdateFixedAssetProduct command = new UpdateFixedAssetProduct(fixedAssetProductToBeUpdated);

		try {
			if(((FixedAssetProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{fixedAssetProductId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetProductId", fixedAssetProductId);
		try {

			Object foundFixedAssetProduct = findFixedAssetProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{fixedAssetProductId}")
	public ResponseEntity<Object> deleteFixedAssetProductByIdUpdated(@PathVariable String fixedAssetProductId) throws Exception {
		DeleteFixedAssetProduct command = new DeleteFixedAssetProduct(fixedAssetProductId);

		try {
			if (((FixedAssetProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetProduct could not be deleted");

	}

}
