package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
public class OrderAdjustmentTypeAdded implements Event{

	private OrderAdjustmentType addedOrderAdjustmentType;
	private boolean success;

	public OrderAdjustmentTypeAdded(OrderAdjustmentType addedOrderAdjustmentType, boolean success){
		this.addedOrderAdjustmentType = addedOrderAdjustmentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAdjustmentType getAddedOrderAdjustmentType() {
		return addedOrderAdjustmentType;
	}

}
