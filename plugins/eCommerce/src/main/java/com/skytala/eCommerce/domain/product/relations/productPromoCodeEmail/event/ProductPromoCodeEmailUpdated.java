package com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.model.ProductPromoCodeEmail;
public class ProductPromoCodeEmailUpdated implements Event{

	private boolean success;

	public ProductPromoCodeEmailUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
