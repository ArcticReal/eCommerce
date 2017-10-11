package com.skytala.eCommerce.domain.order.relations.orderHeader.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
public class OrderHeaderFound implements Event{

	private List<OrderHeader> orderHeaders;

	public OrderHeaderFound(List<OrderHeader> orderHeaders) {
		this.orderHeaders = orderHeaders;
	}

	public List<OrderHeader> getOrderHeaders()	{
		return orderHeaders;
	}

}
