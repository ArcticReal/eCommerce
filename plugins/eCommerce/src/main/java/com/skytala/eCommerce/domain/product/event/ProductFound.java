package com.skytala.eCommerce.domain.product.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.model.Product;
public class ProductFound implements Event{

	private List<Product> products;

	public ProductFound(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts()	{
		return products;
	}

}
