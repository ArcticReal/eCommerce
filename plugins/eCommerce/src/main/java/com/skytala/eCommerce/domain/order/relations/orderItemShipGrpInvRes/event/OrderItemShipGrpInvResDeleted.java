package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model.OrderItemShipGrpInvRes;
public class OrderItemShipGrpInvResDeleted implements Event{

	private boolean success;

	public OrderItemShipGrpInvResDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
