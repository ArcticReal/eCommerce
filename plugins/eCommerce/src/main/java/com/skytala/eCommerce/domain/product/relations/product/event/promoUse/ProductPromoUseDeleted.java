package com.skytala.eCommerce.domain.product.relations.product.event.promoUse;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
public class ProductPromoUseDeleted implements Event{

	private boolean success;

	public ProductPromoUseDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
