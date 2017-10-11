package com.skytala.eCommerce.domain.product.relations.productPromoAction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoAction.model.ProductPromoAction;
public class ProductPromoActionUpdated implements Event{

	private boolean success;

	public ProductPromoActionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
