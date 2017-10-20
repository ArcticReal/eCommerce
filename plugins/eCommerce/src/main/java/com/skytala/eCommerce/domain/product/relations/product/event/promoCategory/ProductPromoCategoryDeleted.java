package com.skytala.eCommerce.domain.product.relations.product.event.promoCategory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;
public class ProductPromoCategoryDeleted implements Event{

	private boolean success;

	public ProductPromoCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
