package com.skytala.eCommerce.domain.productPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
public class ProductPromoUpdated implements Event{

	private boolean success;

	public ProductPromoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
