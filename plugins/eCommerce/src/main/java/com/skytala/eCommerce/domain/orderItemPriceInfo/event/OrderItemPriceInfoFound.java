package com.skytala.eCommerce.domain.orderItemPriceInfo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderItemPriceInfo.model.OrderItemPriceInfo;
public class OrderItemPriceInfoFound implements Event{

	private List<OrderItemPriceInfo> orderItemPriceInfos;

	public OrderItemPriceInfoFound(List<OrderItemPriceInfo> orderItemPriceInfos) {
		this.orderItemPriceInfos = orderItemPriceInfos;
	}

	public List<OrderItemPriceInfo> getOrderItemPriceInfos()	{
		return orderItemPriceInfos;
	}

}
