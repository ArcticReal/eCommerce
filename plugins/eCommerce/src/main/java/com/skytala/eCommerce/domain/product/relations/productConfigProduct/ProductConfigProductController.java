package com.skytala.eCommerce.domain.product.relations.productConfigProduct;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.command.AddProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.command.DeleteProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.command.UpdateProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.event.ProductConfigProductAdded;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.event.ProductConfigProductDeleted;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.event.ProductConfigProductFound;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.event.ProductConfigProductUpdated;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.mapper.ProductConfigProductMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.model.ProductConfigProduct;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.query.FindProductConfigProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productConfigProducts")
public class ProductConfigProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigProduct
	 * @return a List with the ProductConfigProducts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductConfigProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigProductsBy query = new FindProductConfigProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigProduct> productConfigProducts =((ProductConfigProductFound) Scheduler.execute(query).data()).getProductConfigProducts();

		if (productConfigProducts.size() == 1) {
			return ResponseEntity.ok().body(productConfigProducts.get(0));
		}

		return ResponseEntity.ok().body(productConfigProducts);

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
	public ResponseEntity<Object> createProductConfigProduct(HttpServletRequest request) throws Exception {

		ProductConfigProduct productConfigProductToBeAdded = new ProductConfigProduct();
		try {
			productConfigProductToBeAdded = ProductConfigProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductConfigProduct(productConfigProductToBeAdded);

	}

	/**
	 * creates a new ProductConfigProduct entry in the ofbiz database
	 * 
	 * @param productConfigProductToBeAdded
	 *            the ProductConfigProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductConfigProduct(@RequestBody ProductConfigProduct productConfigProductToBeAdded) throws Exception {

		AddProductConfigProduct command = new AddProductConfigProduct(productConfigProductToBeAdded);
		ProductConfigProduct productConfigProduct = ((ProductConfigProductAdded) Scheduler.execute(command).data()).getAddedProductConfigProduct();
		
		if (productConfigProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfigProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfigProduct could not be created.");
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
	public boolean updateProductConfigProduct(HttpServletRequest request) throws Exception {

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

		ProductConfigProduct productConfigProductToBeUpdated = new ProductConfigProduct();

		try {
			productConfigProductToBeUpdated = ProductConfigProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfigProduct(productConfigProductToBeUpdated, productConfigProductToBeUpdated.getConfigOptionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductConfigProduct with the specific Id
	 * 
	 * @param productConfigProductToBeUpdated
	 *            the ProductConfigProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{configOptionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductConfigProduct(@RequestBody ProductConfigProduct productConfigProductToBeUpdated,
			@PathVariable String configOptionId) throws Exception {

		productConfigProductToBeUpdated.setConfigOptionId(configOptionId);

		UpdateProductConfigProduct command = new UpdateProductConfigProduct(productConfigProductToBeUpdated);

		try {
			if(((ProductConfigProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productConfigProductId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigProductId", productConfigProductId);
		try {

			Object foundProductConfigProduct = findProductConfigProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfigProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productConfigProductId}")
	public ResponseEntity<Object> deleteProductConfigProductByIdUpdated(@PathVariable String productConfigProductId) throws Exception {
		DeleteProductConfigProduct command = new DeleteProductConfigProduct(productConfigProductId);

		try {
			if (((ProductConfigProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfigProduct could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productConfigProduct/\" plus one of the following: "
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
