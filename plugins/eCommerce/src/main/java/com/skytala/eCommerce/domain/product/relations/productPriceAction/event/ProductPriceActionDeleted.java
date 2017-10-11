package com.skytala.eCommerce.domain.product.relations.productPriceAction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;
public class ProductPriceActionDeleted implements Event{

	private boolean success;

	public ProductPriceActionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
