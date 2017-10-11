package com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.model.OrderAdjustmentTypeAttr;
public class OrderAdjustmentTypeAttrFound implements Event{

	private List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs;

	public OrderAdjustmentTypeAttrFound(List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs) {
		this.orderAdjustmentTypeAttrs = orderAdjustmentTypeAttrs;
	}

	public List<OrderAdjustmentTypeAttr> getOrderAdjustmentTypeAttrs()	{
		return orderAdjustmentTypeAttrs;
	}

}
