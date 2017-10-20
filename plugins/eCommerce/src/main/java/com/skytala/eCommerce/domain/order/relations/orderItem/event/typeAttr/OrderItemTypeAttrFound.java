package com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;
public class OrderItemTypeAttrFound implements Event{

	private List<OrderItemTypeAttr> orderItemTypeAttrs;

	public OrderItemTypeAttrFound(List<OrderItemTypeAttr> orderItemTypeAttrs) {
		this.orderItemTypeAttrs = orderItemTypeAttrs;
	}

	public List<OrderItemTypeAttr> getOrderItemTypeAttrs()	{
		return orderItemTypeAttrs;
	}

}
