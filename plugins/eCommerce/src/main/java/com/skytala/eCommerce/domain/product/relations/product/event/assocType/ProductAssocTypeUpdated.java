package com.skytala.eCommerce.domain.product.relations.product.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
public class ProductAssocTypeUpdated implements Event{

	private boolean success;

	public ProductAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
