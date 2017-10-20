package com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;
public class OrderAdjustmentTypeAttrFound implements Event{

	private List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs;

	public OrderAdjustmentTypeAttrFound(List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs) {
		this.orderAdjustmentTypeAttrs = orderAdjustmentTypeAttrs;
	}

	public List<OrderAdjustmentTypeAttr> getOrderAdjustmentTypeAttrs()	{
		return orderAdjustmentTypeAttrs;
	}

}
