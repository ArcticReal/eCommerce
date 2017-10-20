package com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.priceInfo.OrderItemPriceInfo;
public class OrderItemPriceInfoAdded implements Event{

	private OrderItemPriceInfo addedOrderItemPriceInfo;
	private boolean success;

	public OrderItemPriceInfoAdded(OrderItemPriceInfo addedOrderItemPriceInfo, boolean success){
		this.addedOrderItemPriceInfo = addedOrderItemPriceInfo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemPriceInfo getAddedOrderItemPriceInfo() {
		return addedOrderItemPriceInfo;
	}

}
