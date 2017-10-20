package com.skytala.eCommerce.domain.product.relations.product.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.assoc.ProductAssoc;
public class ProductAssocUpdated implements Event{

	private boolean success;

	public ProductAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
