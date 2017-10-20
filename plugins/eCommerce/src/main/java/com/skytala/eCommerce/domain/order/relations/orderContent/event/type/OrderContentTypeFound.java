package com.skytala.eCommerce.domain.order.relations.orderContent.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
public class OrderContentTypeFound implements Event{

	private List<OrderContentType> orderContentTypes;

	public OrderContentTypeFound(List<OrderContentType> orderContentTypes) {
		this.orderContentTypes = orderContentTypes;
	}

	public List<OrderContentType> getOrderContentTypes()	{
		return orderContentTypes;
	}

}
