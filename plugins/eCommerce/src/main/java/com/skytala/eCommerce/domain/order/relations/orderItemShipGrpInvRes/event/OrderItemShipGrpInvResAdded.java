package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model.OrderItemShipGrpInvRes;
public class OrderItemShipGrpInvResAdded implements Event{

	private OrderItemShipGrpInvRes addedOrderItemShipGrpInvRes;
	private boolean success;

	public OrderItemShipGrpInvResAdded(OrderItemShipGrpInvRes addedOrderItemShipGrpInvRes, boolean success){
		this.addedOrderItemShipGrpInvRes = addedOrderItemShipGrpInvRes;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemShipGrpInvRes getAddedOrderItemShipGrpInvRes() {
		return addedOrderItemShipGrpInvRes;
	}

}
