package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model.OrderItemShipGrpInvRes;
public class OrderItemShipGrpInvResFound implements Event{

	private List<OrderItemShipGrpInvRes> orderItemShipGrpInvRess;

	public OrderItemShipGrpInvResFound(List<OrderItemShipGrpInvRes> orderItemShipGrpInvRess) {
		this.orderItemShipGrpInvRess = orderItemShipGrpInvRess;
	}

	public List<OrderItemShipGrpInvRes> getOrderItemShipGrpInvRess()	{
		return orderItemShipGrpInvRess;
	}

}
