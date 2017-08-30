package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddProduct;
import com.skytala.eCommerce.command.UpdateProduct;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ProductMapper;
import com.skytala.eCommerce.event.ProductAdded;
import com.skytala.eCommerce.event.ProductsFound;
import com.skytala.eCommerce.event.ProductUpdated;
import com.skytala.eCommerce.query.FindAllProducts;
import com.skytala.eCommerce.query.FindProductsBy;


@RestController
@RequestMapping("/api/product")
public class ProductController {

	private static int requestTicketId = 0;
	private static Map<Integer, List<Product>> queryReturnVal;
	private static Map<Integer, Boolean> commandReturnVal;
	
	
	/*
	 * returns a list with all products from ofbiz
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/findAll")
	public List<Product> findAll() {
		FindAllProducts query = new FindAllProducts();
		int usedTicketId;
		
		synchronized(ProductController.class) {
			Broker.instance().subscribe(ProductsFound.class, 
					event -> sendProductFoundMessage(((ProductsFound) event).getFoundProducts(), requestTicketId));
			
			usedTicketId = requestTicketId;
			requestTicketId++;
		}

		query.execute();
		
		while(!queryReturnVal.containsKey(usedTicketId)) {
		}
		return queryReturnVal.remove(usedTicketId);
		
	}


	
	/*
	 * searches for products by:
	 * 
	 * @param name: name of the product
	 * @param id: id of the product
	 * 
	 * if no name or id is given, every product will be returned
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/findBy")
	public List<Product> findBy(Map<String, Object> filter){
		
		FindProductsBy query = new FindProductsBy(filter);
		int usedTicketId;
		
		synchronized(ProductController.class) {
			Broker.instance().subscribe(ProductsFound.class, 
					event -> sendProductFoundMessage(((ProductsFound) event).getFoundProducts(), requestTicketId));
			
			usedTicketId = requestTicketId;
			requestTicketId++;
		}

		query.execute();
		
		while(!queryReturnVal.containsKey(usedTicketId)) {
		}
		return queryReturnVal.remove(usedTicketId);
		
	}
	
	public void sendProductFoundMessage(List<Product> products, int id) {
		queryReturnVal.put(id, products);
	}
	
	
	/*
	 * creates a new Product in the ofbiz database
	 * 
	 * @param newProduct: the product that will be inserted into the DB
	 * 
	 * returns true on success
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/create", consumes = "application/x-www-form-urlencoded")
	public boolean createProduct(HttpServletRequest request) {
		
		Product productToBeAdded = new Product();
		try {
			productToBeAdded = ProductMapper.map(request);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		AddProduct com = new AddProduct(productToBeAdded);
		int usedTicketId;
		
		synchronized(ProductController.class) {			
			Broker.instance().subscribe(ProductAdded.class, 
					event -> sendProductChangedMessage(((ProductAdded) event).isSuccess(), requestTicketId));
			
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		
		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while(!commandReturnVal.containsKey(usedTicketId)) {	
		}
		
		
		return commandReturnVal.remove(usedTicketId);
	}
	
	/*
	 * updates a product in the ofbiz database
	 * will change any other attributes of the product according to the parameters for the product
	 * with given ID
	 * 
	 * @param newProduct: The product that will be created
	 * 
	 * returns true on success / false when failed
	 * 
	 */
	@RequestMapping(method=RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProduct(HttpServletRequest request) {


		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			data = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		dataMap = Splitter.on('&')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                        .limit(2)
                        .trimResults())
                .split(data);

		
		Product productToBeUpdated = ProductMapper.map(dataMap);
		
		UpdateProduct com = new UpdateProduct(productToBeUpdated);
		int usedTicketId;
		
		synchronized(ProductController.class) {			
			Broker.instance().subscribe(ProductUpdated.class, 
					event -> sendProductChangedMessage(((ProductUpdated) event).isSuccess(), requestTicketId));
			
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		
		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while(!commandReturnVal.containsKey(usedTicketId)) {	
		}
		
		
		return commandReturnVal.remove(usedTicketId);

	}


	//TODO: deleted
	
	public void sendProductChangedMessage(boolean success, int id) {
		commandReturnVal.put(id, success);
	}

}
