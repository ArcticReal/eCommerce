package com.skytala.eCommerce.domain.product.relations.product.event.glAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.glAccount.ProductGlAccount;
public class ProductGlAccountUpdated implements Event{

	private boolean success;

	public ProductGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
