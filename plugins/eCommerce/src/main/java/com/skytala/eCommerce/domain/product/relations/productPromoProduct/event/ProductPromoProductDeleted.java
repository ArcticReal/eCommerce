package com.skytala.eCommerce.domain.product.relations.productPromoProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoProduct.model.ProductPromoProduct;
public class ProductPromoProductDeleted implements Event{

	private boolean success;

	public ProductPromoProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
