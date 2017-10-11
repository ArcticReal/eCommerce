package com.skytala.eCommerce.domain.product.relations.productConfigProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigProduct.model.ProductConfigProduct;
public class ProductConfigProductUpdated implements Event{

	private boolean success;

	public ProductConfigProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
