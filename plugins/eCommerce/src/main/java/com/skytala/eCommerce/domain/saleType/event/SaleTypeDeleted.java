package com.skytala.eCommerce.domain.saleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.saleType.model.SaleType;
public class SaleTypeDeleted implements Event{

	private boolean success;

	public SaleTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
