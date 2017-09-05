package com.skytala.eCommerce.entity;

import java.util.List;

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
