package com.skytala.eCommerce.domain.productPriceChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceChange.model.ProductPriceChange;
public class ProductPriceChangeDeleted implements Event{

	private boolean success;

	public ProductPriceChangeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
