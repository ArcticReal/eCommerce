package com.skytala.eCommerce.domain.product.relations.product.event.price;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
public class ProductPriceDeleted implements Event{

	private boolean success;

	public ProductPriceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
