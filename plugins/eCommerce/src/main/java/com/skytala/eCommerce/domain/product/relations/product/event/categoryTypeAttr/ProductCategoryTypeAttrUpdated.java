package com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryTypeAttr.ProductCategoryTypeAttr;
public class ProductCategoryTypeAttrUpdated implements Event{

	private boolean success;

	public ProductCategoryTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
