package com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;
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
