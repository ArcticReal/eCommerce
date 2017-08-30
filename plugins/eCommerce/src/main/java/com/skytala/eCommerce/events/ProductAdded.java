package com.skytala.eCommerce.events;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Product;

public class ProductAdded implements Event{

	Product product;
	
	public ProductAdded(Product newProduct){
		this.product = newProduct;
	}
}
