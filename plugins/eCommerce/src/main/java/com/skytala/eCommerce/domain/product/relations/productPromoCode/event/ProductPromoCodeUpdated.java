package com.skytala.eCommerce.domain.product.relations.productPromoCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCode.model.ProductPromoCode;
public class ProductPromoCodeUpdated implements Event{

	private boolean success;

	public ProductPromoCodeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
