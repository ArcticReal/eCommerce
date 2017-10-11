package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;
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
