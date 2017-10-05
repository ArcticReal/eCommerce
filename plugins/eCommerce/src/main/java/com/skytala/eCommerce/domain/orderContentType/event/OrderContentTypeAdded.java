package com.skytala.eCommerce.domain.orderContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderContentType.model.OrderContentType;
public class OrderContentTypeAdded implements Event{

	private OrderContentType addedOrderContentType;
	private boolean success;

	public OrderContentTypeAdded(OrderContentType addedOrderContentType, boolean success){
		this.addedOrderContentType = addedOrderContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderContentType getAddedOrderContentType() {
		return addedOrderContentType;
	}

}
