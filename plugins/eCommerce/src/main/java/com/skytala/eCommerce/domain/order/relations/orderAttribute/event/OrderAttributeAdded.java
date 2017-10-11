package com.skytala.eCommerce.domain.order.relations.orderAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
public class OrderAttributeAdded implements Event{

	private OrderAttribute addedOrderAttribute;
	private boolean success;

	public OrderAttributeAdded(OrderAttribute addedOrderAttribute, boolean success){
		this.addedOrderAttribute = addedOrderAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAttribute getAddedOrderAttribute() {
		return addedOrderAttribute;
	}

}
