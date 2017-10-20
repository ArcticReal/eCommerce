package com.skytala.eCommerce.domain.product.relations.product.event.priceCond;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceCond.ProductPriceCond;
public class ProductPriceCondUpdated implements Event{

	private boolean success;

	public ProductPriceCondUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
