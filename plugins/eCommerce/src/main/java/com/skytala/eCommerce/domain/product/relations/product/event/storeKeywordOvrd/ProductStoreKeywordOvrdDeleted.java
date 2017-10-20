package com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;
public class ProductStoreKeywordOvrdDeleted implements Event{

	private boolean success;

	public ProductStoreKeywordOvrdDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
