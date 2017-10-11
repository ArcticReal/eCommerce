package com.skytala.eCommerce.domain.order.relations.orderContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
public class OrderContentAdded implements Event{

	private OrderContent addedOrderContent;
	private boolean success;

	public OrderContentAdded(OrderContent addedOrderContent, boolean success){
		this.addedOrderContent = addedOrderContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderContent getAddedOrderContent() {
		return addedOrderContent;
	}

}
