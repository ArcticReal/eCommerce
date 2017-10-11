package com.skytala.eCommerce.domain.order.relations.orderContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
public class OrderContactMechAdded implements Event{

	private OrderContactMech addedOrderContactMech;
	private boolean success;

	public OrderContactMechAdded(OrderContactMech addedOrderContactMech, boolean success){
		this.addedOrderContactMech = addedOrderContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderContactMech getAddedOrderContactMech() {
		return addedOrderContactMech;
	}

}
