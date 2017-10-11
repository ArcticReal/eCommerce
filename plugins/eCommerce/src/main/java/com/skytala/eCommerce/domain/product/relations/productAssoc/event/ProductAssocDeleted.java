package com.skytala.eCommerce.domain.product.relations.productAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;
public class ProductAssocDeleted implements Event{

	private boolean success;

	public ProductAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
