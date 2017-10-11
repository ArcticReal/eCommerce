package com.skytala.eCommerce.domain.order.relations.orderHeader.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
public class OrderHeaderAdded implements Event{

	private OrderHeader addedOrderHeader;
	private boolean success;

	public OrderHeaderAdded(OrderHeader addedOrderHeader, boolean success){
		this.addedOrderHeader = addedOrderHeader;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderHeader getAddedOrderHeader() {
		return addedOrderHeader;
	}

}
