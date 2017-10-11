package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
public class OrderProductPromoCodeUpdated implements Event{

	private boolean success;

	public OrderProductPromoCodeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
