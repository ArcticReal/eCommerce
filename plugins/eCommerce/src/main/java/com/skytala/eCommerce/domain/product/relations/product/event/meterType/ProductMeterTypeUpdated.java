package com.skytala.eCommerce.domain.product.relations.product.event.meterType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
public class ProductMeterTypeUpdated implements Event{

	private boolean success;

	public ProductMeterTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
