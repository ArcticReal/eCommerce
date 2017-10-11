package com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.model.ProductPromoCodeEmail;
public class ProductPromoCodeEmailDeleted implements Event{

	private boolean success;

	public ProductPromoCodeEmailDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
