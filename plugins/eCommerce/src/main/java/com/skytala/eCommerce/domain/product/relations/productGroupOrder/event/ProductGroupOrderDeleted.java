package com.skytala.eCommerce.domain.product.relations.productGroupOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGroupOrder.model.ProductGroupOrder;
public class ProductGroupOrderDeleted implements Event{

	private boolean success;

	public ProductGroupOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
