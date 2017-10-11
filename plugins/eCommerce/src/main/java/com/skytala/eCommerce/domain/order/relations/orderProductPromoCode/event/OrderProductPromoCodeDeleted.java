package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
public class OrderProductPromoCodeDeleted implements Event{

	private boolean success;

	public OrderProductPromoCodeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
