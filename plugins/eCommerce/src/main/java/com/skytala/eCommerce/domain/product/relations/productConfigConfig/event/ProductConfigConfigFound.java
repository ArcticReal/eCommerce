package com.skytala.eCommerce.domain.product.relations.productConfigConfig.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigConfig.model.ProductConfigConfig;
public class ProductConfigConfigFound implements Event{

	private List<ProductConfigConfig> productConfigConfigs;

	public ProductConfigConfigFound(List<ProductConfigConfig> productConfigConfigs) {
		this.productConfigConfigs = productConfigConfigs;
	}

	public List<ProductConfigConfig> getProductConfigConfigs()	{
		return productConfigConfigs;
	}

}
