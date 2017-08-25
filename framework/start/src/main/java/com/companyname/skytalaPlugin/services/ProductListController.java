package com.companyname.skytalaPlugin.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
					
					returnval.add(new Product(buf.get(i).getString("productName"), buf.get(i).getString("productId")));
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
				returnval.add(new Product(buf.get(i).getString("productName"), buf.get(i).getString("productId")));
				
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
	@RequestMapping(method=RequestMethod.POST, value = "/createProduct")
	public boolean createProduct(Product newProduct) {
				
			
		Delegator delegator = DelegatorFactory.getDelegator("default");
		boolean returnval = true;

		try {
			GenericValue newValue = delegator.makeValue("Product", newProduct);
			delegator.create(newValue);
			
		} catch (GenericEntityException e){
			returnval = false;
			e.printStackTrace();
		}
				
		
		return returnval;
	}
	
	/*
	 * updates a product in the ofbiz database
	 * 
	 * @param newProduct: The product that will be created
	 * 
	 * returns the number of rows updated
	 * 
	 */
	@RequestMapping(method=RequestMethod.PUT, value = "/updateProduct")
	public int updateProduct(Product newProduct) {
		
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		int returnval = 0;
		
		
		try {
			
			
			GenericValue newValue = delegator.makeValue("Product", newProduct);	
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
