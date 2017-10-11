package com.skytala.eCommerce.domain.product.relations.productPriceActionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceActionType.model.ProductPriceActionType;
public class ProductPriceActionTypeUpdated implements Event{

	private boolean success;

	public ProductPriceActionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
