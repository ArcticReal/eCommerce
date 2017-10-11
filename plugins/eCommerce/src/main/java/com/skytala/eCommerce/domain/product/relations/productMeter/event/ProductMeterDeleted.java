package com.skytala.eCommerce.domain.product.relations.productMeter.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMeter.model.ProductMeter;
public class ProductMeterDeleted implements Event{

	private boolean success;

	public ProductMeterDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
