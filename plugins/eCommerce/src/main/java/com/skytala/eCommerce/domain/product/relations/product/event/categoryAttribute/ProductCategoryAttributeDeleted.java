package com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
public class ProductCategoryAttributeDeleted implements Event{

	private boolean success;

	public ProductCategoryAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
