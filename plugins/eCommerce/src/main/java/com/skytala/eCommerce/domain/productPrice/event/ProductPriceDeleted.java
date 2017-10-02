package com.skytala.eCommerce.domain.productPrice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
public class ProductPriceDeleted implements Event{

	private boolean success;

	public ProductPriceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
