package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
public class OrderBlacklistFound implements Event{

	private List<OrderBlacklist> orderBlacklists;

	public OrderBlacklistFound(List<OrderBlacklist> orderBlacklists) {
		this.orderBlacklists = orderBlacklists;
	}

	public List<OrderBlacklist> getOrderBlacklists()	{
		return orderBlacklists;
	}

}
