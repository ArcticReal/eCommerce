package com.skytala.eCommerce.domain.order.relations.orderContent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
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
