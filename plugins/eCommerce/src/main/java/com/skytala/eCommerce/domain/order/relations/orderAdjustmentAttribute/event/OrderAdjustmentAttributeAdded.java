package com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.model.OrderAdjustmentAttribute;
public class OrderAdjustmentAttributeAdded implements Event{

	private OrderAdjustmentAttribute addedOrderAdjustmentAttribute;
	private boolean success;

	public OrderAdjustmentAttributeAdded(OrderAdjustmentAttribute addedOrderAdjustmentAttribute, boolean success){
		this.addedOrderAdjustmentAttribute = addedOrderAdjustmentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAdjustmentAttribute getAddedOrderAdjustmentAttribute() {
		return addedOrderAdjustmentAttribute;
	}

}
