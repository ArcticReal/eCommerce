package com.skytala.eCommerce.domain.product.relations.productGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGlAccount.model.ProductGlAccount;
public class ProductGlAccountDeleted implements Event{

	private boolean success;

	public ProductGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
