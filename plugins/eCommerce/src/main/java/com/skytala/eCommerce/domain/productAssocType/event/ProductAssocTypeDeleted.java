package com.skytala.eCommerce.domain.productAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productAssocType.model.ProductAssocType;
public class ProductAssocTypeDeleted implements Event{

	private boolean success;

	public ProductAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
