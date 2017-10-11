package com.skytala.eCommerce.domain.order.relations.orderTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.model.OrderTypeAttr;
public class OrderTypeAttrFound implements Event{

	private List<OrderTypeAttr> orderTypeAttrs;

	public OrderTypeAttrFound(List<OrderTypeAttr> orderTypeAttrs) {
		this.orderTypeAttrs = orderTypeAttrs;
	}

	public List<OrderTypeAttr> getOrderTypeAttrs()	{
		return orderTypeAttrs;
	}

}
