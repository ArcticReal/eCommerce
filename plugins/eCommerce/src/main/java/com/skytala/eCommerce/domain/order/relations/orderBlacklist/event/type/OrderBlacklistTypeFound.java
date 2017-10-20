package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;
public class OrderBlacklistTypeFound implements Event{

	private List<OrderBlacklistType> orderBlacklistTypes;

	public OrderBlacklistTypeFound(List<OrderBlacklistType> orderBlacklistTypes) {
		this.orderBlacklistTypes = orderBlacklistTypes;
	}

	public List<OrderBlacklistType> getOrderBlacklistTypes()	{
		return orderBlacklistTypes;
	}

}
