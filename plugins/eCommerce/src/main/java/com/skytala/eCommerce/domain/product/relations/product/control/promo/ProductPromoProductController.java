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
import com.skytala.eCommerce.domain.product.relations.product.command.promo.AddProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.DeleteProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.promo.UpdateProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromoProduct;
import com.skytala.eCommerce.domain.product.relations.product.query.promo.FindProductPromoProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromoProducts")
public class ProductPromoProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoProduct
	 * @return a List with the ProductPromoProducts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromoProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoProductsBy query = new FindProductPromoProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoProduct> productPromoProducts =((ProductPromoProductFound) Scheduler.execute(query).data()).getProductPromoProducts();

		if (productPromoProducts.size() == 1) {
			return ResponseEntity.ok().body(productPromoProducts.get(0));
		}

		return ResponseEntity.ok().body(productPromoProducts);

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
	public ResponseEntity<Object> createProductPromoProduct(HttpServletRequest request) throws Exception {

		ProductPromoProduct productPromoProductToBeAdded = new ProductPromoProduct();
		try {
			productPromoProductToBeAdded = ProductPromoProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoProduct(productPromoProductToBeAdded);

	}

	/**
	 * creates a new ProductPromoProduct entry in the ofbiz database
	 * 
	 * @param productPromoProductToBeAdded
	 *            the ProductPromoProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoProduct(@RequestBody ProductPromoProduct productPromoProductToBeAdded) throws Exception {

		AddProductPromoProduct command = new AddProductPromoProduct(productPromoProductToBeAdded);
		ProductPromoProduct productPromoProduct = ((ProductPromoProductAdded) Scheduler.execute(command).data()).getAddedProductPromoProduct();
		
		if (productPromoProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoProduct could not be created.");
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
	public boolean updateProductPromoProduct(HttpServletRequest request) throws Exception {

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

		ProductPromoProduct productPromoProductToBeUpdated = new ProductPromoProduct();

		try {
			productPromoProductToBeUpdated = ProductPromoProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoProduct(productPromoProductToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoProduct with the specific Id
	 * 
	 * @param productPromoProductToBeUpdated
	 *            the ProductPromoProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoProduct(@RequestBody ProductPromoProduct productPromoProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoProductToBeUpdated.setnull(null);

		UpdateProductPromoProduct command = new UpdateProductPromoProduct(productPromoProductToBeUpdated);

		try {
			if(((ProductPromoProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoProductId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoProductId", productPromoProductId);
		try {

			Object foundProductPromoProduct = findProductPromoProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoProductId}")
	public ResponseEntity<Object> deleteProductPromoProductByIdUpdated(@PathVariable String productPromoProductId) throws Exception {
		DeleteProductPromoProduct command = new DeleteProductPromoProduct(productPromoProductId);

		try {
			if (((ProductPromoProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoProduct could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPromoProduct/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
