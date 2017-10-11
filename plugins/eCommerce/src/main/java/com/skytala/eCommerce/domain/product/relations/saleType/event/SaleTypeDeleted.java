package com.skytala.eCommerce.domain.product.relations.saleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.saleType.model.SaleType;
public class SaleTypeDeleted implements Event{

	private boolean success;

	public SaleTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
