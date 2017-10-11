package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;
public class OrderItemAssocFound implements Event{

	private List<OrderItemAssoc> orderItemAssocs;

	public OrderItemAssocFound(List<OrderItemAssoc> orderItemAssocs) {
		this.orderItemAssocs = orderItemAssocs;
	}

	public List<OrderItemAssoc> getOrderItemAssocs()	{
		return orderItemAssocs;
	}

}
