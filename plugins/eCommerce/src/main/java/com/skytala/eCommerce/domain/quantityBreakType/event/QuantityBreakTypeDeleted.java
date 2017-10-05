package com.skytala.eCommerce.domain.quantityBreakType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreakType.model.QuantityBreakType;
public class QuantityBreakTypeDeleted implements Event{

	private boolean success;

	public QuantityBreakTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
