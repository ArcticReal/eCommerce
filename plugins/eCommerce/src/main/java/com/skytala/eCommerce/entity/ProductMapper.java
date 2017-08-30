package com.skytala.eCommerce.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
	public static Product map(HttpServletRequest request) throws Exception {
		
		Product returnVal = new Product();
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		// Required; that's how it should look with required attributes (i think there wont be any except this one)
		if(!paramMap.containsKey("productId")) {
			throw new Exception("Error! Id required");
		}
		else {
			returnVal.setProductId(request.getParameter("productId"));
		}
		
		// every other attribute should be set like this
		if(paramMap.containsKey("productName")) {			
			returnVal.setProductName(request.getParameter("productName"));
		}

		// examples for parsing a string to other data types; getParameter only returns strings
		String test = request.getParameter("test");
		Float testInFloat = Float.parseFloat(test);

		String test2 = request.getParameter("some date");
		DateFormat df = new SimpleDateFormat();//i dont know how the time is formatted in ofbiz server
		Date test2InDate = df.parse(test2);
		
		return returnVal;
	}
}
