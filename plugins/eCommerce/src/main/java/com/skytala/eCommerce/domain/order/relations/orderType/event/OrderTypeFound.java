package com.skytala.eCommerce.domain.order.relations.orderType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;
public class OrderTypeFound implements Event{

	private List<OrderType> orderTypes;

	public OrderTypeFound(List<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}

	public List<OrderType> getOrderTypes()	{
		return orderTypes;
	}

}
