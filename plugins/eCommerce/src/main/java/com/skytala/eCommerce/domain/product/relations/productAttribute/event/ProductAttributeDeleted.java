package com.skytala.eCommerce.domain.product.relations.productAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAttribute.model.ProductAttribute;
public class ProductAttributeDeleted implements Event{

	private boolean success;

	public ProductAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
