package com.skytala.eCommerce.domain.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Function;
import com.skytala.eCommerce.domain.product.dto.ProductDetailsDTO;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;
import com.skytala.eCommerce.domain.product.relations.product.control.attribute.ProductAttributeController;
import com.skytala.eCommerce.domain.product.relations.product.control.price.ProductPriceController;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.util.ProductAttributes;
import org.apache.ofbiz.base.util.UtilMisc;
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
import com.skytala.eCommerce.domain.product.command.AddProduct;
import com.skytala.eCommerce.domain.product.command.DeleteProduct;
import com.skytala.eCommerce.domain.product.command.UpdateProduct;
import com.skytala.eCommerce.domain.product.event.ProductAdded;
import com.skytala.eCommerce.domain.product.event.ProductDeleted;
import com.skytala.eCommerce.domain.product.event.ProductFound;
import com.skytala.eCommerce.domain.product.event.ProductUpdated;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.query.FindProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	@Resource
	ProductPriceController priceController;

	@Resource
	ProductAttributeController attributeController;

	public ProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Product
	 * @return a List with the Products
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<Product>> findProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductsBy query = new FindProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Product> products =((ProductFound) Scheduler.execute(query).data()).getProducts();


		return ResponseEntity.ok().body(products);

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
	public ResponseEntity<Object> createProduct(HttpServletRequest request) throws Exception {

		Product productToBeAdded = new Product();
		try {
			productToBeAdded = ProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProduct(productToBeAdded);

	}

	/**
	 * creates a new Product entry in the ofbiz database
	 * 
	 * @param productToBeAdded
	 *            the Product thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProduct(@RequestBody Product productToBeAdded) throws Exception {

		AddProduct command = new AddProduct(productToBeAdded);
		Product product = ((ProductAdded) Scheduler.execute(command).data()).getAddedProduct();
		
		if (product != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(product);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Product could not be created.");
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
	public boolean updateProduct(HttpServletRequest request) throws Exception {

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

		Product productToBeUpdated = new Product();

		try {
			productToBeUpdated = ProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProduct(productToBeUpdated, productToBeUpdated.getProductId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Product with the specific Id
	 * 
	 * @param productToBeUpdated
	 *            the Product thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProduct(@RequestBody Product productToBeUpdated,
			@PathVariable String productId) throws Exception {

		productToBeUpdated.setProductId(productId);

		UpdateProduct command = new UpdateProduct(productToBeUpdated);

		try {
			if(((ProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productId}")
	public ResponseEntity<Product> findById(@PathVariable String productId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productId", productId);
		try {

			List<Product> foundProducts = findProductsBy(requestParams).getBody();
			if(foundProducts.size()!=1)
				return notFound();
			return ResponseEntity.status(HttpStatus.OK).body(foundProducts.get(0));
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	public ResponseEntity<Object> deleteProductByIdUpdated(@PathVariable String productId) throws Exception {
		DeleteProduct command = new DeleteProduct(productId);

		try {
			if (((ProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Product could not be deleted");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productId}/details")
	public ResponseEntity<ProductDetailsDTO> findByIdWithDetails(@PathVariable String productId) throws Exception {

		ResponseEntity<Product> response = findById(productId);
		if(response.getStatusCode().equals(HttpStatus.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		Product product = response.getBody();

		Map<String, String> filter = UtilMisc.toMap("productPricePurposeId", "PURCHASE",
													"productPriceTypeId", "DEFAULT_PRICE");

		final List<ProductPrice> productPrices = priceController.findProductPricesBy(filter)
															    .getBody();

		filter.clear();


		final List<ProductAttribute> attributes = attributeController.findProductAttributesBy(filter)
																     .getBody();

		return successful(ProductDetailsDTO.create(product, productPrices, attributes));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/productList")
	public ResponseEntity getAllProductListItems() throws Exception {

		List<Product> products = findProductsBy(new HashMap<>()).getBody();

		Map<String, String> filter = UtilMisc.toMap("productPricePurposeId", "PURCHASE",
													"productPriceTypeId", "DEFAULT_PRICE");

		final List<ProductPrice> productPrices = priceController.findProductPricesBy(filter)
				                                                .getBody();

		filter.clear();
		filter.put("attrName", ProductAttributes.AUTHOR);

		final List<ProductAttribute> authors = attributeController.findProductAttributesBy(filter)
																			.getBody();

		List<ProductListItemDTO> results = new LinkedList<>();

		results = products.stream()
						  .map((Product prod) -> ProductListItemDTO.create(prod, productPrices, authors))
						  .collect(Collectors.toList());

		return successful(results);

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/product/\" plus one of the following: "
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
