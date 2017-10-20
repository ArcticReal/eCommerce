package com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
public class ProductCategoryAttributeUpdated implements Event{

	private boolean success;

	public ProductCategoryAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
