package com.skytala.eCommerce.domain.order.relations.productOrderItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.productOrderItem.model.ProductOrderItem;
public class ProductOrderItemFound implements Event{

	private List<ProductOrderItem> productOrderItems;

	public ProductOrderItemFound(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}

	public List<ProductOrderItem> getProductOrderItems()	{
		return productOrderItems;
	}

}
