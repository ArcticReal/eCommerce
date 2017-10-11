package com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.model.ProductStoreKeywordOvrd;
public class ProductStoreKeywordOvrdUpdated implements Event{

	private boolean success;

	public ProductStoreKeywordOvrdUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
