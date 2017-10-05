package com.skytala.eCommerce.domain.productMeterType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMeterType.model.ProductMeterType;
public class ProductMeterTypeUpdated implements Event{

	private boolean success;

	public ProductMeterTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
