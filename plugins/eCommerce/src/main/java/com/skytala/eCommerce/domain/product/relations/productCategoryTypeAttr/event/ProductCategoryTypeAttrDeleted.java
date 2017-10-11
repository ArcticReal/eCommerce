package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;
public class ProductCategoryTypeAttrDeleted implements Event{

	private boolean success;

	public ProductCategoryTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
