package com.skytala.eCommerce.domain.product.relations.product.event.promoCond;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;
public class ProductPromoCondUpdated implements Event{

	private boolean success;

	public ProductPromoCondUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
