package com.skytala.eCommerce.domain.product.relations.quantityBreakType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.quantityBreakType.model.QuantityBreakType;
public class QuantityBreakTypeUpdated implements Event{

	private boolean success;

	public QuantityBreakTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
