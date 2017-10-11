package com.skytala.eCommerce.domain.order.relations.quoteAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteAdjustment.model.QuoteAdjustment;
public class QuoteAdjustmentUpdated implements Event{

	private boolean success;

	public QuoteAdjustmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
