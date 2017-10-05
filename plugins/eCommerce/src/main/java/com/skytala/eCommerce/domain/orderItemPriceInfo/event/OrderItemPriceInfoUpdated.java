package com.skytala.eCommerce.domain.orderItemPriceInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemPriceInfo.model.OrderItemPriceInfo;
public class OrderItemPriceInfoUpdated implements Event{

	private boolean success;

	public OrderItemPriceInfoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
