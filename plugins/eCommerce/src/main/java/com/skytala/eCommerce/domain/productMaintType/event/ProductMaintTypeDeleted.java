package com.skytala.eCommerce.domain.productMaintType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMaintType.model.ProductMaintType;
public class ProductMaintTypeDeleted implements Event{

	private boolean success;

	public ProductMaintTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
