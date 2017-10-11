package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
public class OrderProductPromoCodeAdded implements Event{

	private OrderProductPromoCode addedOrderProductPromoCode;
	private boolean success;

	public OrderProductPromoCodeAdded(OrderProductPromoCode addedOrderProductPromoCode, boolean success){
		this.addedOrderProductPromoCode = addedOrderProductPromoCode;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderProductPromoCode getAddedOrderProductPromoCode() {
		return addedOrderProductPromoCode;
	}

}
