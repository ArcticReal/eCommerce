package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Product;

public class ProductFound implements Event{

	private List<Product> products;
	
	public ProductFound(List<Product> newProductList){
		this.setProducts(newProductList);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> foundProducts) {
		this.products = foundProducts;
	}
}
