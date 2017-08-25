package com.companyname.skytalaPlugin.services;

public class Product {

	private String productName;
	private String productId;
	
	Product(String newName, String newId){
		setProductName(newName);
		setProductId(newId);
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
	
}
