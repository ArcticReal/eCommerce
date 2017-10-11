package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
public class OrderBlacklistAdded implements Event{

	private OrderBlacklist addedOrderBlacklist;
	private boolean success;

	public OrderBlacklistAdded(OrderBlacklist addedOrderBlacklist, boolean success){
		this.addedOrderBlacklist = addedOrderBlacklist;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderBlacklist getAddedOrderBlacklist() {
		return addedOrderBlacklist;
	}

}
