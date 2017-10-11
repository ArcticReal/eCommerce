package com.skytala.eCommerce.domain.product.relations.productMaintType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMaintType.model.ProductMaintType;
public class ProductMaintTypeDeleted implements Event{

	private boolean success;

	public ProductMaintTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
