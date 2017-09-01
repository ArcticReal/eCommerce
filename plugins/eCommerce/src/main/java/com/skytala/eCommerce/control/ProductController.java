package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.skytala.eCommerce.event.ProductUpdated;
import com.skytala.eCommerce.event.ProductsFound;
import com.skytala.eCommerce.query.FindAllProducts;
import com.skytala.eCommerce.query.FindProductsBy;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private static int requestTicketId = 0;
	private static Map<Integer, List<Product>> queryReturnVal = new HashMap<>();
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();

	/**
	 * 
	 * @return returns a list with all products from ofbiz
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = {"/findAll", "/find", "/", "list"})
	public List<Product> findAll() {
		FindAllProducts query = new FindAllProducts();
		int usedTicketId;

		synchronized (ProductController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductsFound.class,
				event -> sendProductFoundMessage(((ProductsFound) event).getFoundProducts(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {
		}
		return queryReturnVal.remove(usedTicketId);

	}

	/**
	 * searches for products by:
	 * 
	 * 
	 * @param name: name of the product
	 * @param id: id of the product
	 * 
	 * @return a list of Products the filter applys to if no name or id is given,
	 *         every product will be returned
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/findBy")
	public List<Product> findBy(@RequestParam Map<String, String> allRequestParams) {

		FindProductsBy query = new FindProductsBy(allRequestParams);
		int usedTicketId;

		synchronized (ProductController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(ProductsFound.class,
				event -> sendProductFoundMessage(((ProductsFound) event).getFoundProducts(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	public void sendProductFoundMessage(List<Product> products, int id) {
		queryReturnVal.put(id, products);
	}

	/**
	 * Notice: this method will only be called by Spring, if you want to create a
	 * Product, please use the other method
	 * 
	 * @param request: the servletrequest from spring
	 * 
	 * @return returns true on success
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"/create", "/insert"}, consumes = "application/x-www-form-urlencoded")
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
	 * creates a new Product
	 * 
	 * @param productToBeAdded: the product thats to be added
	 * 
	 * @return returns true on success and false on fail
	 * 
	 */
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
	 * updates a product in the ofbiz database; this method will only be calles by
	 * the Spring DispatcherServlet
	 * 
	 * @param newProduct: The product that will be created
	 * 
	 * @return returns true on success / false when failed
	 * 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update")
	public boolean updateProduct(HttpServletRequest request) {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if(br != null) {
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
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

		return this.updateProducut(productToBeUpdated);

	}

	/**
	 * updates a product in the ofbiz database will change any other attributes of
	 * the product according to the parameters for the product with given ID
	 * 
	 * @param productToBeUpdated:
	 *            the product that will be updated
	 *            
	 * @return returns true on success / false when failed
	 * 
	 */
	public boolean updateProducut(Product productToBeUpdated) {

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
	 * removes a product from the database
	 * 
	 * @param productId:
	 *            the id of the product thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = {"/removeById", "/removeBy"})
	public boolean removeProductById(@RequestParam(value = "productId") String productId) {

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

	public void sendProductChangedMessage(boolean success, int id) {
		commandReturnVal.put(id, success);
	}
	
	@RequestMapping(value = (" * "))
	public String returnErrorPage() {

		
		return "Error 404: Page not found! Check your spelling and/or your request method.";
	}

}
