package com.skytala.eCommerce.domain.productMaintType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMaintType.model.ProductMaintType;
public class ProductMaintTypeUpdated implements Event{

	private boolean success;

	public ProductMaintTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
