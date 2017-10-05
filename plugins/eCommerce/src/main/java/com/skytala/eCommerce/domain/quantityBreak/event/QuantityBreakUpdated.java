package com.skytala.eCommerce.domain.quantityBreak.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreak.model.QuantityBreak;
public class QuantityBreakUpdated implements Event{

	private boolean success;

	public QuantityBreakUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
