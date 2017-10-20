package com.skytala.eCommerce.domain.product.relations.product.event.groupOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
public class ProductGroupOrderDeleted implements Event{

	private boolean success;

	public ProductGroupOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
