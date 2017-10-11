package com.skytala.eCommerce.domain.product.relations.productGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGlAccount.model.ProductGlAccount;
public class ProductGlAccountUpdated implements Event{

	private boolean success;

	public ProductGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
