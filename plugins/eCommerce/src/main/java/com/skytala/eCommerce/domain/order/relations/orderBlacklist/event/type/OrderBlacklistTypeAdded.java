package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;
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
