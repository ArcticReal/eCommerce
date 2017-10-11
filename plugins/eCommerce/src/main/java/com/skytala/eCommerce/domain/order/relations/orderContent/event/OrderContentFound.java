package com.skytala.eCommerce.domain.order.relations.orderContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
public class OrderContentFound implements Event{

	private List<OrderContent> orderContents;

	public OrderContentFound(List<OrderContent> orderContents) {
		this.orderContents = orderContents;
	}

	public List<OrderContent> getOrderContents()	{
		return orderContents;
	}

}
