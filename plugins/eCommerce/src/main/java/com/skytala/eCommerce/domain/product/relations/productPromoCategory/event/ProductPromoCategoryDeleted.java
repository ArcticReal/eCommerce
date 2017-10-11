package com.skytala.eCommerce.domain.product.relations.productPromoCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCategory.model.ProductPromoCategory;
public class ProductPromoCategoryDeleted implements Event{

	private boolean success;

	public ProductPromoCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
