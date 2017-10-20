package com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
public class OrderTermAttributeAdded implements Event{

	private OrderTermAttribute addedOrderTermAttribute;
	private boolean success;

	public OrderTermAttributeAdded(OrderTermAttribute addedOrderTermAttribute, boolean success){
		this.addedOrderTermAttribute = addedOrderTermAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderTermAttribute getAddedOrderTermAttribute() {
		return addedOrderTermAttribute;
	}

}
