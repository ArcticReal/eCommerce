package com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.model.OrderAdjustmentTypeAttr;
public class OrderAdjustmentTypeAttrAdded implements Event{

	private OrderAdjustmentTypeAttr addedOrderAdjustmentTypeAttr;
	private boolean success;

	public OrderAdjustmentTypeAttrAdded(OrderAdjustmentTypeAttr addedOrderAdjustmentTypeAttr, boolean success){
		this.addedOrderAdjustmentTypeAttr = addedOrderAdjustmentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderAdjustmentTypeAttr getAddedOrderAdjustmentTypeAttr() {
		return addedOrderAdjustmentTypeAttr;
	}

}
