package com.skytala.eCommerce.domain.product.relations.product.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
public class ProductAttributeDeleted implements Event{

	private boolean success;

	public ProductAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
