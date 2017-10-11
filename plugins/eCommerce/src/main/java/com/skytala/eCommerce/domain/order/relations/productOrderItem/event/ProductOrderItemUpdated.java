package com.skytala.eCommerce.domain.order.relations.productOrderItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.productOrderItem.model.ProductOrderItem;
public class ProductOrderItemUpdated implements Event{

	private boolean success;

	public ProductOrderItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
