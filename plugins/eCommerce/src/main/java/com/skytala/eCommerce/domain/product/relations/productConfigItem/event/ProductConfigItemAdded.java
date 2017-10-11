package com.skytala.eCommerce.domain.product.relations.productConfigItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigItem.model.ProductConfigItem;
public class ProductConfigItemAdded implements Event{

	private ProductConfigItem addedProductConfigItem;
	private boolean success;

	public ProductConfigItemAdded(ProductConfigItem addedProductConfigItem, boolean success){
		this.addedProductConfigItem = addedProductConfigItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigItem getAddedProductConfigItem() {
		return addedProductConfigItem;
	}

}
