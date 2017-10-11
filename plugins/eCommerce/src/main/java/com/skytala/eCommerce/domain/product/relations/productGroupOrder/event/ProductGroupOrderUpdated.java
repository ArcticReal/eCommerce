package com.skytala.eCommerce.domain.product.relations.productGroupOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGroupOrder.model.ProductGroupOrder;
public class ProductGroupOrderUpdated implements Event{

	private boolean success;

	public ProductGroupOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
