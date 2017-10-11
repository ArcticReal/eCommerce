package com.skytala.eCommerce.domain.product.relations.productMaintType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMaintType.model.ProductMaintType;
public class ProductMaintTypeUpdated implements Event{

	private boolean success;

	public ProductMaintTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
