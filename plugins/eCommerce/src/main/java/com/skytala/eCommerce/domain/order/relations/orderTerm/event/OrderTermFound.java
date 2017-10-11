package com.skytala.eCommerce.domain.order.relations.orderTerm.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
public class OrderTermFound implements Event{

	private List<OrderTerm> orderTerms;

	public OrderTermFound(List<OrderTerm> orderTerms) {
		this.orderTerms = orderTerms;
	}

	public List<OrderTerm> getOrderTerms()	{
		return orderTerms;
	}

}
