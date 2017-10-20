package com.skytala.eCommerce.domain.product.relations.product.event.priceChange;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceChange.ProductPriceChange;
public class ProductPriceChangeDeleted implements Event{

	private boolean success;

	public ProductPriceChangeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
