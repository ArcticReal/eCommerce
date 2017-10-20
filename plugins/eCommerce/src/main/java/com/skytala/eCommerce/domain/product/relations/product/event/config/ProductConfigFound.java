package com.skytala.eCommerce.domain.product.relations.product.event.config;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfig;
public class ProductConfigFound implements Event{

	private List<ProductConfig> productConfigs;

	public ProductConfigFound(List<ProductConfig> productConfigs) {
		this.productConfigs = productConfigs;
	}

	public List<ProductConfig> getProductConfigs()	{
		return productConfigs;
	}

}
