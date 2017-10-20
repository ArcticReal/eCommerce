package com.skytala.eCommerce.domain.product.relations.product.event.configConfig;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
public class ProductConfigConfigFound implements Event{

	private List<ProductConfigConfig> productConfigConfigs;

	public ProductConfigConfigFound(List<ProductConfigConfig> productConfigConfigs) {
		this.productConfigConfigs = productConfigConfigs;
	}

	public List<ProductConfigConfig> getProductConfigConfigs()	{
		return productConfigConfigs;
	}

}
