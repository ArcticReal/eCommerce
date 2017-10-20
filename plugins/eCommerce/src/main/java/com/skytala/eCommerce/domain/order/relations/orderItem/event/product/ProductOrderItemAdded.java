package com.skytala.eCommerce.domain.order.relations.orderItem.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.product.ProductOrderItem;
public class ProductOrderItemAdded implements Event{

	private ProductOrderItem addedProductOrderItem;
	private boolean success;

	public ProductOrderItemAdded(ProductOrderItem addedProductOrderItem, boolean success){
		this.addedProductOrderItem = addedProductOrderItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductOrderItem getAddedProductOrderItem() {
		return addedProductOrderItem;
	}

}
