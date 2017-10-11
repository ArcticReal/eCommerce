package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
public class OrderProductPromoCodeFound implements Event{

	private List<OrderProductPromoCode> orderProductPromoCodes;

	public OrderProductPromoCodeFound(List<OrderProductPromoCode> orderProductPromoCodes) {
		this.orderProductPromoCodes = orderProductPromoCodes;
	}

	public List<OrderProductPromoCode> getOrderProductPromoCodes()	{
		return orderProductPromoCodes;
	}

}
