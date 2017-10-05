package com.skytala.eCommerce.domain.orderBlacklistType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderBlacklistType.model.OrderBlacklistType;
public class OrderBlacklistTypeAdded implements Event{

	private OrderBlacklistType addedOrderBlacklistType;
	private boolean success;

	public OrderBlacklistTypeAdded(OrderBlacklistType addedOrderBlacklistType, boolean success){
		this.addedOrderBlacklistType = addedOrderBlacklistType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderBlacklistType getAddedOrderBlacklistType() {
		return addedOrderBlacklistType;
	}

}
