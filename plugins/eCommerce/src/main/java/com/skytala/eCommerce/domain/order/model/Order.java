package com.skytala.eCommerce.domain.order.model;

import java.util.List;

import com.skytala.eCommerce.domain.order.header.OrderHeader;
import com.skytala.eCommerce.domain.order.item.OrderItem;

public class Order {

	private OrderHeader header;
	private List<OrderItem> items;

	public OrderHeader getHeader() {
		return header;
	}

	public void setHeader(OrderHeader header) {
		this.header = header;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
