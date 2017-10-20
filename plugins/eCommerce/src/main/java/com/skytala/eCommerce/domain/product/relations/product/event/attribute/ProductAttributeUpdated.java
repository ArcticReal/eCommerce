package com.skytala.eCommerce.domain.product.relations.product.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
public class ProductAttributeUpdated implements Event{

	private boolean success;

	public ProductAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
