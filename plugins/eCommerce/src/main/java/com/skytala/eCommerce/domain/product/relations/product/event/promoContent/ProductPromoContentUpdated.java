package com.skytala.eCommerce.domain.product.relations.product.event.promoContent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;
public class ProductPromoContentUpdated implements Event{

	private boolean success;

	public ProductPromoContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
