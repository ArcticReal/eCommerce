package com.skytala.eCommerce.domain.product.relations.productPromoContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoContent.model.ProductPromoContent;
public class ProductPromoContentDeleted implements Event{

	private boolean success;

	public ProductPromoContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
