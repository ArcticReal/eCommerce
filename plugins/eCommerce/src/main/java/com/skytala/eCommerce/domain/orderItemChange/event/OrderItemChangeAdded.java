package com.skytala.eCommerce.domain.orderItemChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemChange.model.OrderItemChange;
public class OrderItemChangeAdded implements Event{

	private OrderItemChange addedOrderItemChange;
	private boolean success;

	public OrderItemChangeAdded(OrderItemChange addedOrderItemChange, boolean success){
		this.addedOrderItemChange = addedOrderItemChange;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemChange getAddedOrderItemChange() {
		return addedOrderItemChange;
	}

}
