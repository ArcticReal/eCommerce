package com.skytala.eCommerce.domain.product.relations.productPromoUse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoUse.model.ProductPromoUse;
public class ProductPromoUseUpdated implements Event{

	private boolean success;

	public ProductPromoUseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
