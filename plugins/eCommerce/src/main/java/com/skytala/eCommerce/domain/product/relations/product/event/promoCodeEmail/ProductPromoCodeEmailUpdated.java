package com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;
public class ProductPromoCodeEmailUpdated implements Event{

	private boolean success;

	public ProductPromoCodeEmailUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
