package com.skytala.eCommerce.domain.product.relations.product.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
public class ProductPromoUpdated implements Event{

	private boolean success;

	public ProductPromoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
