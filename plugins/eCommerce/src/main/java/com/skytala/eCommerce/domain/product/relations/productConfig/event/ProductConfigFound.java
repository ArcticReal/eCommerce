package com.skytala.eCommerce.domain.product.relations.productConfig.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfig.model.ProductConfig;
public class ProductConfigFound implements Event{

	private List<ProductConfig> productConfigs;

	public ProductConfigFound(List<ProductConfig> productConfigs) {
		this.productConfigs = productConfigs;
	}

	public List<ProductConfig> getProductConfigs()	{
		return productConfigs;
	}

}
