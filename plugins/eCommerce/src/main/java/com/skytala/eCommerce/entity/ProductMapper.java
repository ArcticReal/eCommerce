package com.skytala.eCommerce.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.ofbiz.entity.GenericValue;

public class ProductMapper {

	/*
	 * Maps the attributes of a product to a map
	 * 
	 * Note: when adding attributes to the product class, this method has to be edited too
	 * 
	 */
	public static Map<String, Object> map(Product product) {
		
		Map<String, Object> returnVal = new HashMap<String, Object>();
		
		
		if(product.getProductId() != null) {
			returnVal.put("productId", product.getProductId());
		}
		
		if(product.getProductName() != null) {
			returnVal.put("productName", product.getProductName());
		}
		
		return returnVal;
	}
	
	public static Product map(Map<String, Object> fields) {
		
		Product returnVal = new Product("","");
		
		if(fields.get("productName") != null) {
			returnVal.setProductName((String) fields.get("productName"));
		}

		if(fields.get("productId") != null) {
			returnVal.setProductName((String) fields.get("productId"));
		}
		
		return returnVal;
	}
	
	public static Product map(GenericValue val) {
		Product returnVal = new Product();
		
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductName(val.getString("productName"));
		
		return returnVal;
			
	}
	
}
