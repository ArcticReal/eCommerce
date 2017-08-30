package com.skytala.eCommerce.entity;

import java.util.Map;

public class Product {

	
	String productName;
	String productId;
	//TODO: attributes

	
	public Product(String productName, String productId) {
		this.productId = productId;
		this.productName = productName;
	}
	
	public Product(){
		
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public Map<String, Object> mapAttributeField() {
		return ProductMapper.map(this);
	}
}
