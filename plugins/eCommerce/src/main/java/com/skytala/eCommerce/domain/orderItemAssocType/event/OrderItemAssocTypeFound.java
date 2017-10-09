package com.skytala.eCommerce.domain.orderItemAssocType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemAssocType.model.OrderItemAssocType;
public class OrderItemAssocTypeFound implements Event{

	private List<OrderItemAssocType> orderItemAssocTypes;

	public OrderItemAssocTypeFound(List<OrderItemAssocType> orderItemAssocTypes) {
		this.orderItemAssocTypes = orderItemAssocTypes;
	}

	public List<OrderItemAssocType> getOrderItemAssocTypes()	{
		return orderItemAssocTypes;
	}

}