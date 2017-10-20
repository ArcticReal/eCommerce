package com.skytala.eCommerce.domain.order.relations.orderType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
public class OrderTypeAttrAdded implements Event{

	private OrderTypeAttr addedOrderTypeAttr;
	private boolean success;

	public OrderTypeAttrAdded(OrderTypeAttr addedOrderTypeAttr, boolean success){
		this.addedOrderTypeAttr = addedOrderTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderTypeAttr getAddedOrderTypeAttr() {
		return addedOrderTypeAttr;
	}

}
