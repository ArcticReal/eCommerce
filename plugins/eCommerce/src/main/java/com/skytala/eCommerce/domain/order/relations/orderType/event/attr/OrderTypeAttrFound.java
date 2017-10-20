package com.skytala.eCommerce.domain.order.relations.orderType.event.attr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
public class OrderTypeAttrFound implements Event{

	private List<OrderTypeAttr> orderTypeAttrs;

	public OrderTypeAttrFound(List<OrderTypeAttr> orderTypeAttrs) {
		this.orderTypeAttrs = orderTypeAttrs;
	}

	public List<OrderTypeAttr> getOrderTypeAttrs()	{
		return orderTypeAttrs;
	}

}
