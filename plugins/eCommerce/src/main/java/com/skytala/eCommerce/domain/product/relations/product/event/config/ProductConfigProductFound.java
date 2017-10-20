package com.skytala.eCommerce.domain.product.relations.product.event.config;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
public class ProductConfigProductFound implements Event{

	private List<ProductConfigProduct> productConfigProducts;

	public ProductConfigProductFound(List<ProductConfigProduct> productConfigProducts) {
		this.productConfigProducts = productConfigProducts;
	}

	public List<ProductConfigProduct> getProductConfigProducts()	{
		return productConfigProducts;
	}

}
