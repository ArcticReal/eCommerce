package com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn.ProductConfigOptionIactn;
public class ProductConfigOptionIactnAdded implements Event{

	private ProductConfigOptionIactn addedProductConfigOptionIactn;
	private boolean success;

	public ProductConfigOptionIactnAdded(ProductConfigOptionIactn addedProductConfigOptionIactn, boolean success){
		this.addedProductConfigOptionIactn = addedProductConfigOptionIactn;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigOptionIactn getAddedProductConfigOptionIactn() {
		return addedProductConfigOptionIactn;
	}

}
