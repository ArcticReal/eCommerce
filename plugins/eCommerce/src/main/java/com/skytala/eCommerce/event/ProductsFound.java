package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Product;

public class ProductsFound implements Event{

	private List<Product> foundProducts;
	
	public ProductsFound(List<Product> newProductList){
		this.setFoundProducts(newProductList);
	}

	public List<Product> getFoundProducts() {
		return foundProducts;
	}

	public void setFoundProducts(List<Product> foundProducts) {
		this.foundProducts = foundProducts;
	}
}
