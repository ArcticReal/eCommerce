package com.skytala.eCommerce.domain.product.relations.product.event.promoCode;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCode.ProductPromoCode;
public class ProductPromoCodeDeleted implements Event{

	private boolean success;

	public ProductPromoCodeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
