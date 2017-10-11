package com.skytala.eCommerce.domain.order.relations.orderTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
public class OrderTermAdded implements Event{

	private OrderTerm addedOrderTerm;
	private boolean success;

	public OrderTermAdded(OrderTerm addedOrderTerm, boolean success){
		this.addedOrderTerm = addedOrderTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderTerm getAddedOrderTerm() {
		return addedOrderTerm;
	}

}
