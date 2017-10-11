package com.skytala.eCommerce.domain.product.relations.productPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromo.model.ProductPromo;
public class ProductPromoUpdated implements Event{

	private boolean success;

	public ProductPromoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
