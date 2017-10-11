package com.skytala.eCommerce.domain.order.relations.orderContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContentType.model.OrderContentType;
public class OrderContentTypeFound implements Event{

	private List<OrderContentType> orderContentTypes;

	public OrderContentTypeFound(List<OrderContentType> orderContentTypes) {
		this.orderContentTypes = orderContentTypes;
	}

	public List<OrderContentType> getOrderContentTypes()	{
		return orderContentTypes;
	}

}
