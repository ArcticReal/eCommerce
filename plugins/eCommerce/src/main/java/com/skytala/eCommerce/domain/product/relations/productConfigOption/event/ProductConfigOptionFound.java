package com.skytala.eCommerce.domain.product.relations.productConfigOption.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigOption.model.ProductConfigOption;
public class ProductConfigOptionFound implements Event{

	private List<ProductConfigOption> productConfigOptions;

	public ProductConfigOptionFound(List<ProductConfigOption> productConfigOptions) {
		this.productConfigOptions = productConfigOptions;
	}

	public List<ProductConfigOption> getProductConfigOptions()	{
		return productConfigOptions;
	}

}
