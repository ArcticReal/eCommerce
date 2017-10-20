package com.skytala.eCommerce.domain.order.relations.orderItem.event.product;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.product.ProductOrderItem;
public class ProductOrderItemFound implements Event{

	private List<ProductOrderItem> productOrderItems;

	public ProductOrderItemFound(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}

	public List<ProductOrderItem> getProductOrderItems()	{
		return productOrderItems;
	}

}
