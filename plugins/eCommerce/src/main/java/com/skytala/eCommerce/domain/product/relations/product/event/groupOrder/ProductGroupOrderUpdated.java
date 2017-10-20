package com.skytala.eCommerce.domain.product.relations.product.event.groupOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
public class ProductGroupOrderUpdated implements Event{

	private boolean success;

	public ProductGroupOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
