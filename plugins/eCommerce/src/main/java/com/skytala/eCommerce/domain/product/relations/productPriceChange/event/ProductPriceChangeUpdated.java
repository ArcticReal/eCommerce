package com.skytala.eCommerce.domain.product.relations.productPriceChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceChange.model.ProductPriceChange;
public class ProductPriceChangeUpdated implements Event{

	private boolean success;

	public ProductPriceChangeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
