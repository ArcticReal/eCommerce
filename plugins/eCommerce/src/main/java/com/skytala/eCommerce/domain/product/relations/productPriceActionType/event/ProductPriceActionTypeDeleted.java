package com.skytala.eCommerce.domain.product.relations.productPriceActionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceActionType.model.ProductPriceActionType;
public class ProductPriceActionTypeDeleted implements Event{

	private boolean success;

	public ProductPriceActionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
