package com.skytala.eCommerce.domain.product.relations.product.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
public class ProductTypeAttrDeleted implements Event{

	private boolean success;

	public ProductTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
