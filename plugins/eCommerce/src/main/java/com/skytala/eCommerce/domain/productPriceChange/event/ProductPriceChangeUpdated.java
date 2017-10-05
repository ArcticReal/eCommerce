package com.skytala.eCommerce.domain.productPriceChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceChange.model.ProductPriceChange;
public class ProductPriceChangeUpdated implements Event{

	private boolean success;

	public ProductPriceChangeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
