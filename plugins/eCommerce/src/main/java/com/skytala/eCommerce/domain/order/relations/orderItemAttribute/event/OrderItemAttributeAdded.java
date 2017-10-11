package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
public class OrderItemAttributeAdded implements Event{

	private OrderItemAttribute addedOrderItemAttribute;
	private boolean success;

	public OrderItemAttributeAdded(OrderItemAttribute addedOrderItemAttribute, boolean success){
		this.addedOrderItemAttribute = addedOrderItemAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemAttribute getAddedOrderItemAttribute() {
		return addedOrderItemAttribute;
	}

}
