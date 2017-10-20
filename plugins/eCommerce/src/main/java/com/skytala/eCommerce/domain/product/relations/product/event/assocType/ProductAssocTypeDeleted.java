package com.skytala.eCommerce.domain.product.relations.product.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
public class ProductAssocTypeDeleted implements Event{

	private boolean success;

	public ProductAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
