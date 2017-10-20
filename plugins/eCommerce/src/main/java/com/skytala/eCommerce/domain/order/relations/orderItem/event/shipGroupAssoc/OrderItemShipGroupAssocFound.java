package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;
public class OrderItemShipGroupAssocFound implements Event{

	private List<OrderItemShipGroupAssoc> orderItemShipGroupAssocs;

	public OrderItemShipGroupAssocFound(List<OrderItemShipGroupAssoc> orderItemShipGroupAssocs) {
		this.orderItemShipGroupAssocs = orderItemShipGroupAssocs;
	}

	public List<OrderItemShipGroupAssoc> getOrderItemShipGroupAssocs()	{
		return orderItemShipGroupAssocs;
	}

}
