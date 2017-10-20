package com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
public class ProductStorePromoApplUpdated implements Event{

	private boolean success;

	public ProductStorePromoApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
