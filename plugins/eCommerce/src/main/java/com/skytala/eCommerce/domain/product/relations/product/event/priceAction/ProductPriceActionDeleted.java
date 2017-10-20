package com.skytala.eCommerce.domain.product.relations.product.event.priceAction;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceAction.ProductPriceAction;
public class ProductPriceActionDeleted implements Event{

	private boolean success;

	public ProductPriceActionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
