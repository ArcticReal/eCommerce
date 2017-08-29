package com.companyname.skytalaPlugin.services;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	@JsonProperty("productName")  
	private String productName;
	@JsonProperty("productId") 
	private String productId;
	
	public Product(String productName, String productId){
		setProductName(productName);
		setProductId(productId);
	}
	


	public String getProductId() {
		return productId;
	}

	
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Map<String, Object> mapAttributeField(){

		return ProductMapper.map(this);
	}
}
