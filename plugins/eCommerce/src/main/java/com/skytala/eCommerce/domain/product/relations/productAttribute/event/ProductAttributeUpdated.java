package com.skytala.eCommerce.domain.product.relations.productAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAttribute.model.ProductAttribute;
public class ProductAttributeUpdated implements Event{

	private boolean success;

	public ProductAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
