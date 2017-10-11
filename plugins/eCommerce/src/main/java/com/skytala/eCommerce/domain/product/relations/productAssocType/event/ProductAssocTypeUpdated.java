package com.skytala.eCommerce.domain.product.relations.productAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAssocType.model.ProductAssocType;
public class ProductAssocTypeUpdated implements Event{

	private boolean success;

	public ProductAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
