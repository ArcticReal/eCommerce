package com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;
public class QuantityBreakTypeDeleted implements Event{

	private boolean success;

	public QuantityBreakTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}