package com.skytala.eCommerce.domain.order.relations.orderItemContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemContactMech.model.OrderItemContactMech;
public class OrderItemContactMechAdded implements Event{

	private OrderItemContactMech addedOrderItemContactMech;
	private boolean success;

	public OrderItemContactMechAdded(OrderItemContactMech addedOrderItemContactMech, boolean success){
		this.addedOrderItemContactMech = addedOrderItemContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemContactMech getAddedOrderItemContactMech() {
		return addedOrderItemContactMech;
	}

}
