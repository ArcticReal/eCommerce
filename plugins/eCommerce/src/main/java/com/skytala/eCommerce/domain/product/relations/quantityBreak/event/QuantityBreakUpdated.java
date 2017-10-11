package com.skytala.eCommerce.domain.product.relations.quantityBreak.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.QuantityBreak;
public class QuantityBreakUpdated implements Event{

	private boolean success;

	public QuantityBreakUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
