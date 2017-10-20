package com.skytala.eCommerce.domain.product.relations.product.event.priceCond;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceCond.ProductPriceCond;
public class ProductPriceCondDeleted implements Event{

	private boolean success;

	public ProductPriceCondDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
