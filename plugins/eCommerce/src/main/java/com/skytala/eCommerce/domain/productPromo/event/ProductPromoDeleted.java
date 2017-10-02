package com.skytala.eCommerce.domain.productPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
public class ProductPromoDeleted implements Event{

	private boolean success;

	public ProductPromoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
