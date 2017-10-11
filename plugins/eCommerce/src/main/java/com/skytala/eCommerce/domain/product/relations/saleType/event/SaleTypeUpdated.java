package com.skytala.eCommerce.domain.product.relations.saleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.saleType.model.SaleType;
public class SaleTypeUpdated implements Event{

	private boolean success;

	public SaleTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
