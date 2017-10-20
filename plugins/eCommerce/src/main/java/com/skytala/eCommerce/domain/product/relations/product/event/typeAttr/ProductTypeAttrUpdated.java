package com.skytala.eCommerce.domain.product.relations.product.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
public class ProductTypeAttrUpdated implements Event{

	private boolean success;

	public ProductTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
