package com.companyname.skytalaPlugin.services;

import java.util.HashMap;
import java.util.Map;

public class ProductMapper {

	/*
	 * Maps the attributes of a product to a map
	 * 
	 * Note: when adding attributes to the product class, this method has to be edited too
	 * 
	 */
	public static Map<String, Object> map(Product product) {
		
		Map<String, Object> returnVal = new HashMap<String, Object>();
		
		returnVal.put("productId", product.getProductId());
		returnVal.put("productName", product.getProductName());
		
		return returnVal;
	}
	
	public static Product map(Map<String, Object> fields) {
		
		Product returnVal = new Product();
		
		if(fields.get("productName") != null) {
			returnVal.setProductName((String) fields.get("productName"));
		}

		if(fields.get("productId") != null) {
			returnVal.setProductName((String) fields.get("productId"));
		}
		
		return returnVal;
	}
	
}
