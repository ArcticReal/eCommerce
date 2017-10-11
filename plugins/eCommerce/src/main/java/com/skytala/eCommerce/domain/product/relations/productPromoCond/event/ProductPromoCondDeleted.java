package com.skytala.eCommerce.domain.product.relations.productPromoCond.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCond.model.ProductPromoCond;
public class ProductPromoCondDeleted implements Event{

	private boolean success;

	public ProductPromoCondDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
