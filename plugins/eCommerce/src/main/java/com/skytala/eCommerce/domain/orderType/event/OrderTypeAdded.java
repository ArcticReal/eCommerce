package com.skytala.eCommerce.domain.orderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderType.model.OrderType;
public class OrderTypeAdded implements Event{

	private OrderType addedOrderType;
	private boolean success;

	public OrderTypeAdded(OrderType addedOrderType, boolean success){
		this.addedOrderType = addedOrderType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderType getAddedOrderType() {
		return addedOrderType;
	}

}
