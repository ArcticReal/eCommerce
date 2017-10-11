package com.skytala.eCommerce.domain.product.relations.quantityBreak.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.QuantityBreak;
public class QuantityBreakDeleted implements Event{

	private boolean success;

	public QuantityBreakDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
