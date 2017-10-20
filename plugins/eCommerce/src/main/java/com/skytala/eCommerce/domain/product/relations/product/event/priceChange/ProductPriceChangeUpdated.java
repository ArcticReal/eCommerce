package com.skytala.eCommerce.domain.product.relations.product.event.priceChange;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceChange.ProductPriceChange;
public class ProductPriceChangeUpdated implements Event{

	private boolean success;

	public ProductPriceChangeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
