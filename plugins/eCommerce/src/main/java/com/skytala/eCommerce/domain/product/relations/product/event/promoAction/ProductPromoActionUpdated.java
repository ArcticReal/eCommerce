package com.skytala.eCommerce.domain.product.relations.product.event.promoAction;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;
public class ProductPromoActionUpdated implements Event{

	private boolean success;

	public ProductPromoActionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
