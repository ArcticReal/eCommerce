package com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.priceInfo.OrderItemPriceInfo;
public class OrderItemPriceInfoUpdated implements Event{

	private boolean success;

	public OrderItemPriceInfoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
