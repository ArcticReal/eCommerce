package com.skytala.eCommerce.domain.product.relations.productTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productTypeAttr.model.ProductTypeAttr;
public class ProductTypeAttrUpdated implements Event{

	private boolean success;

	public ProductTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
