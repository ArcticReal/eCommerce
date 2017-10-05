package com.skytala.eCommerce.domain.orderAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderAdjustment.model.OrderAdjustment;
public class OrderAdjustmentAdded implements Event{

	private OrderAdjustment addedOrderAdjustment;
	private boolean success;

	public OrderAdjustmentAdded(OrderAdjustment addedOrderAdjustment, boolean success){
		this.addedOrderAdjustment = addedOrderAdjustment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAdjustment getAddedOrderAdjustment() {
		return addedOrderAdjustment;
	}

}
