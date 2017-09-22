package com.skytala.eCommerce.control;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddProduct;
import com.skytala.eCommerce.command.DeleteProduct;
import com.skytala.eCommerce.command.UpdateProduct;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ProductMapper;
import com.skytala.eCommerce.event.ProductAdded;
import com.skytala.eCommerce.event.ProductDeleted;
import com.skytala.eCommerce.event.ProductFound;
import com.skytala.eCommerce.event.ProductUpdated;
import com.skytala.eCommerce.query.FindProductsBy;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<Product>> queryReturnVal = new HashMap<>();
	private static Map<String, RequestMethod> validRequests = new HashMap<>();

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
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductsBy(@RequestParam Map<String, String> allRequestParams) {

		FindProductsBy query = new FindProductsBy(allRequestParams);

		int usedTicketId;

		synchronized (ProductController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductFound.class,
				event -> sendProductsFoundMessage(((ProductFound) event).getProducts(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return ResponseEntity.ok().body(queryReturnVal.remove(usedTicketId));

	}

	public void sendProductsFoundMessage(List<Product> products, int usedTicketId) {
		queryReturnVal.put(usedTicketId, products);
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
	public boolean createProduct(HttpServletRequest request) {

		Product productToBeAdded = new Product();
		try {
			productToBeAdded = ProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
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
	public boolean createProduct(Product productToBeAdded) {

		AddProduct com = new AddProduct(productToBeAdded);
		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductAdded.class,
				event -> sendProductChangedMessage(((ProductAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProduct(HttpServletRequest request) {

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

		return updateProduct(productToBeUpdated, productToBeUpdated.getProductId());

	}

	/**
	 * Updates the Product with the specific Id
	 * 
	 * @param productToBeUpdated
	 *            the Product thats to be updated
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productId}/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean updateProduct(Product productToBeUpdated, @PathVariable String productId) {

		productToBeUpdated.setProductId(productId);

		UpdateProduct com = new UpdateProduct(productToBeUpdated);

		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductUpdated.class,
				event -> sendProductChangedMessage(((ProductUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	/**
	 * removes a Product from the database
	 * 
	 * @deprecated
	 * @param productId:
	 *            the id of the Product thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deleteproductById(@RequestParam(value = "productId") String productId) {

		DeleteProduct com = new DeleteProduct(productId);

		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductDeleted.class,
				event -> sendProductChangedMessage(((ProductDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	public void sendProductChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productId}")
	public ResponseEntity<Object> findById(@PathVariable String productId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productId", productId);

		return findProductsBy(requestParams);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	public ResponseEntity<Object> deleteProductByIdUpdated(@PathVariable String productId) {
		DeleteProduct com = new DeleteProduct(productId);

		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductDeleted.class,
				event -> sendProductChangedMessage(((ProductDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (commandReturnVal.remove(usedTicketId)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product was deleted successfully.");

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Product could not be deleted");

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
