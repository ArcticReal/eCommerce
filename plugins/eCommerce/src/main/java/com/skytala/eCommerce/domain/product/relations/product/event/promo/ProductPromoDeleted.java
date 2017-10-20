package com.skytala.eCommerce.domain.product.relations.product.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
public class ProductPromoDeleted implements Event{

	private boolean success;

	public ProductPromoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
