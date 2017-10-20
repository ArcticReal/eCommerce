package com.skytala.eCommerce.domain.product.relations.product.event.configOption;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configOption.ProductConfigOption;
public class ProductConfigOptionAdded implements Event{

	private ProductConfigOption addedProductConfigOption;
	private boolean success;

	public ProductConfigOptionAdded(ProductConfigOption addedProductConfigOption, boolean success){
		this.addedProductConfigOption = addedProductConfigOption;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigOption getAddedProductConfigOption() {
		return addedProductConfigOption;
	}

}
