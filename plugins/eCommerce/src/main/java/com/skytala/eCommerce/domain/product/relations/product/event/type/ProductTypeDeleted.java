package com.skytala.eCommerce.domain.product.relations.product.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
public class ProductTypeDeleted implements Event{

	private boolean success;

	public ProductTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
