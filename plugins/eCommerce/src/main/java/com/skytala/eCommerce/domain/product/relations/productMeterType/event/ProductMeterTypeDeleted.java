package com.skytala.eCommerce.domain.product.relations.productMeterType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMeterType.model.ProductMeterType;
public class ProductMeterTypeDeleted implements Event{

	private boolean success;

	public ProductMeterTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
