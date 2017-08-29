package com.companyname.skytalaPlugin.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.skytalaPlugin.command.Scheduler;
import com.skytala.skytalaPlugin.command.Product.AddProduct;


@RestController
@RequestMapping("/api")
public class ProductListController {
	
	
	/*
	 * searches for products by:
	 * 
	 * @param name: name of the product
	 * @param id: id of the product
	 * 
	 * if no name or id is given, every product will be returned
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/findProductsBy")
	public List<Product> findProductsBy(@RequestParam(value = "name", defaultValue = "") String name, 
											@RequestParam(value = "id", defaultValue = "") String id) {
	

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Product> returnval = new ArrayList<Product>();		

		try {
			List<GenericValue> buf = delegator.findAll("Product", false);
		
			for (int i = 0; i < buf.size(); i++) {
				if(buf.get(i).getString("productName") != null && 
						(buf.get(i).getString("productName").contains(name) && 
						buf.get(i).getString("productId").contains(id))) {
					
					Product foundProduct = new Product("","");
					foundProduct.setProductId(buf.get(i).getString("productId"));
					foundProduct.setProductName(buf.get(i).getString("productName"));
					
					returnval.add(foundProduct);
				}
		
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
	
		return returnval;
	}
	
	
	/*
	 * returns a list with all products from ofbiz
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/findAllProducts")
	public List<Product> findAllProducts (){
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Product> returnval = new ArrayList<Product>();		

		try {
			List<GenericValue> buf = delegator.findAll("Product", false);
			
			for (int i = 0; i < buf.size(); i++) {
				Product foundProduct = new Product("","");
				foundProduct.setProductId(buf.get(i).getString("productId"));
				foundProduct.setProductName(buf.get(i).getString("productName"));
				
				returnval.add(foundProduct);				
			}
			
			
		}catch(GenericEntityException e) {
			e.printStackTrace();
		}
		
		return returnval;
	}
	

	/*
	 * creates a new Product in the ofbiz database
	 * 
	 * @param newProduct: the product that will be inserted into the DB
	 * 
	 * returns true on success
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/createProduct", consumes = "application/x-www-form-urlencoded")
	public GenericValue createProduct(HttpServletRequest request) {
		
		String productName = request.getParameter("productName");
		String productId = request.getParameter("productId");
				
		Product newProduct = new Product(productName, productId);
		
		AddProduct command = new AddProduct(newProduct);
		
		GenericValue returnVal = null;
		
		Scheduler scheduler = Scheduler.instance();
		scheduler.schedule(command);
		
		try {
			scheduler.executeNext();
			returnVal = command.getCreatedProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return returnVal;		
		
	}
	
	/*
	 * updates a product in the ofbiz database
	 * 
	 * @param newProduct: The product that will be created
	 * 
	 * returns the number of rows updated
	 * 
	 */
	@RequestMapping(method=RequestMethod.PUT, value = "/updateProduct", consumes = "application/x-www-form-urlencoded")
	public int updateProduct(HttpServletRequest request) {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			data = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		dataMap = Splitter.on('&')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                        .limit(2)
                        .trimResults())
                .split(data);
		
		
		System.out.println(dataMap);
		
		String productName = dataMap.get("productName");
		String productId = dataMap.get("productId");
		
		
		Product newProduct = new Product(productName, productId);
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		int returnval = 0;
		
		
		try {
			
			GenericValue newValue = delegator.makeValue("Product", newProduct.mapAttributeField());	
			returnval = delegator.store(newValue);

			
		}catch(GenericEntityException e) {
			e.printStackTrace();
		}
		
		return returnval;
		
	}
	

	/*
	 * removes a product from the database
	 * 
	 * @param productId: the id of the product thats to be removed
	 * 
	 */
	@RequestMapping(method=RequestMethod.DELETE, value = "/removeProduct")
	public int removeProductById(@RequestParam(value = "id") String productId) {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		int returnval = 0;
		
		try {
			returnval = delegator.removeByAnd("Product", UtilMisc.toMap("productId", productId));
			
			
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		
		
		return returnval;
	}
	
	
}
