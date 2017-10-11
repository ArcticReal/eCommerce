package com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.model.OrderItemTypeAttr;
public class OrderItemTypeAttrAdded implements Event{

	private OrderItemTypeAttr addedOrderItemTypeAttr;
	private boolean success;

	public OrderItemTypeAttrAdded(OrderItemTypeAttr addedOrderItemTypeAttr, boolean success){
		this.addedOrderItemTypeAttr = addedOrderItemTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemTypeAttr getAddedOrderItemTypeAttr() {
		return addedOrderItemTypeAttr;
	}

}
