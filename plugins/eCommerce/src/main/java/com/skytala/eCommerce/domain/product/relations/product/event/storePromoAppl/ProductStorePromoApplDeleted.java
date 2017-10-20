package com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
public class ProductStorePromoApplDeleted implements Event{

	private boolean success;

	public ProductStorePromoApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
