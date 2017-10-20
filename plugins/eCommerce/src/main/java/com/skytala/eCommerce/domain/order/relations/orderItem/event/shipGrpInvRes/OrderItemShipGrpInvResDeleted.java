package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGrpInvRes.OrderItemShipGrpInvRes;
public class OrderItemShipGrpInvResDeleted implements Event{

	private boolean success;

	public OrderItemShipGrpInvResDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
