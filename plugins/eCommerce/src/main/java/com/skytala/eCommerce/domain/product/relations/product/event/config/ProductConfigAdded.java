package com.skytala.eCommerce.domain.product.relations.product.event.config;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfig;
public class ProductConfigAdded implements Event{

	private ProductConfig addedProductConfig;
	private boolean success;

	public ProductConfigAdded(ProductConfig addedProductConfig, boolean success){
		this.addedProductConfig = addedProductConfig;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfig getAddedProductConfig() {
		return addedProductConfig;
	}

}
