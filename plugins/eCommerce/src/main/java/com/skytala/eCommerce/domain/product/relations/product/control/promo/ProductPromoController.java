package com.skytala.eCommerce.domain.product.relations.product.control.promo;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promo.AddProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.DeleteProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.UpdateProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
import com.skytala.eCommerce.domain.product.relations.product.query.promo.FindProductPromosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromos")
public class ProductPromoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromo
	 * @return a List with the ProductPromos
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromosBy query = new FindProductPromosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromo> productPromos =((ProductPromoFound) Scheduler.execute(query).data()).getProductPromos();

		if (productPromos.size() == 1) {
			return ResponseEntity.ok().body(productPromos.get(0));
		}

		return ResponseEntity.ok().body(productPromos);

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
	public ResponseEntity<Object> createProductPromo(HttpServletRequest request) throws Exception {

		ProductPromo productPromoToBeAdded = new ProductPromo();
		try {
			productPromoToBeAdded = ProductPromoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromo(productPromoToBeAdded);

	}

	/**
	 * creates a new ProductPromo entry in the ofbiz database
	 * 
	 * @param productPromoToBeAdded
	 *            the ProductPromo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromo(@RequestBody ProductPromo productPromoToBeAdded) throws Exception {

		AddProductPromo command = new AddProductPromo(productPromoToBeAdded);
		ProductPromo productPromo = ((ProductPromoAdded) Scheduler.execute(command).data()).getAddedProductPromo();
		
		if (productPromo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromo could not be created.");
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
	public boolean updateProductPromo(HttpServletRequest request) throws Exception {

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

		ProductPromo productPromoToBeUpdated = new ProductPromo();

		try {
			productPromoToBeUpdated = ProductPromoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromo(productPromoToBeUpdated, productPromoToBeUpdated.getProductPromoId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromo with the specific Id
	 * 
	 * @param productPromoToBeUpdated
	 *            the ProductPromo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPromoId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromo(@RequestBody ProductPromo productPromoToBeUpdated,
			@PathVariable String productPromoId) throws Exception {

		productPromoToBeUpdated.setProductPromoId(productPromoId);

		UpdateProductPromo command = new UpdateProductPromo(productPromoToBeUpdated);

		try {
			if(((ProductPromoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoId", productPromoId);
		try {

			Object foundProductPromo = findProductPromosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoId}")
	public ResponseEntity<Object> deleteProductPromoByIdUpdated(@PathVariable String productPromoId) throws Exception {
		DeleteProductPromo command = new DeleteProductPromo(productPromoId);

		try {
			if (((ProductPromoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromo could not be deleted");

	}

}
