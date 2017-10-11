package com.skytala.eCommerce.domain.product.relations.productConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfig.model.ProductConfig;
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
