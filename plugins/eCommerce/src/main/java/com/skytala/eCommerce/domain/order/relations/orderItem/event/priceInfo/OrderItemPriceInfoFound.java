package com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.priceInfo.OrderItemPriceInfo;
public class OrderItemPriceInfoFound implements Event{

	private List<OrderItemPriceInfo> orderItemPriceInfos;

	public OrderItemPriceInfoFound(List<OrderItemPriceInfo> orderItemPriceInfos) {
		this.orderItemPriceInfos = orderItemPriceInfos;
	}

	public List<OrderItemPriceInfo> getOrderItemPriceInfos()	{
		return orderItemPriceInfos;
	}

}
