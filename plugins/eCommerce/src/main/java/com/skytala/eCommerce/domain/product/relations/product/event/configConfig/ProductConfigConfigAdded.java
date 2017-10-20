package com.skytala.eCommerce.domain.product.relations.product.event.configConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
public class ProductConfigConfigAdded implements Event{

	private ProductConfigConfig addedProductConfigConfig;
	private boolean success;

	public ProductConfigConfigAdded(ProductConfigConfig addedProductConfigConfig, boolean success){
		this.addedProductConfigConfig = addedProductConfigConfig;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigConfig getAddedProductConfigConfig() {
		return addedProductConfigConfig;
	}

}
